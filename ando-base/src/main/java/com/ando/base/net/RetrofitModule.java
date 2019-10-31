package com.ando.base.net;

import android.util.ArrayMap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ando.base.utils.AppUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Api 生成工具
 *
 * @author machangbao
 * @date 2019-03-29 14:56:43
 */
public class RetrofitModule {

    private static final String TAG = "123";
    private final static int DEFAULT_TIME_OUT = 60;
    private ArrayMap<Class<?>, Api> apiPool = new ArrayMap<>();

    private File httpCacheDirectory = new File(AppUtils.getInstance().getContext().getCacheDir(), "responses");
    private int cacheSize = 10 * 1024 * 1024; // 10M
    private Cache cache = new Cache(httpCacheDirectory, cacheSize);

    public void initApiService(@NonNull List<Class<?>> apiList) {
        for (Class<?> clz : apiList) {
            try {
                Field baseUrl = clz.getDeclaredField("baseUrl");
                if (Modifier.isFinal(baseUrl.getModifiers())) {
                    String url = (String) baseUrl.get(null);
                    apiPool.put(clz, new Api(clz, url, false));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <T> void addApiService(@NonNull Class<T> newApi) {
        if (apiPool.containsKey(newApi)) {
            return;
        }
        try {
            Field baseUrl = newApi.getDeclaredField("baseUrl");
            if (Modifier.isFinal(baseUrl.getModifiers())) {
                String url = (String) baseUrl.get(null);
                apiPool.put(newApi, new Api(newApi, url, false));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T getApiService(Class<T> clz) {
        if (apiPool.size() > 0 && apiPool.containsKey(clz)) {
            Api api = apiPool.get(clz);
            if (api.isInitialized) {
                return (T) api.api;
            } else {
                T retrofitApi = null;
//                if (clz == ApiWanMen.class) {
//                    retrofitApi = transWanmenInterface2RetrofitApi(clz, api.baseUrl);
//                } else {
//                    retrofitApi = transInterface2RetrofitApi(clz, api.baseUrl);
//                }
                retrofitApi = transInterface2RetrofitApi(clz, api.baseUrl);

                api.isInitialized = true;
                api.api = retrofitApi;
                return retrofitApi;
            }
        }
        return null;
    }

    private <T> T transWanmenInterface2RetrofitApi(Class<T> clz, String baseUrl) {
        //打印拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.i(TAG, message));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        String host = "";
        try {
            host = String.valueOf(new URI(baseUrl).getHost());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

//        HttpTokenInterceptor tokenInterceptor = new HttpTokenInterceptor();
//        HttpCacheInterceptor cacheInterceptor = new HttpCacheInterceptor();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                (BuildConfig.DEBUG ? new OkHttpClient.Builder() :
//                getTrusClient(AppUtils.getInstance().getAppContext().getResources().openRawResource(R.raw.ssl)))
                .hostnameVerifier(getHostnameVerifier(host))
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
//                .addNetworkInterceptor(tokenInterceptor)
//                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(logging)
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(clz);
    }

    private <T> T transInterface2RetrofitApi(Class<T> clz, String baseUrl) {
        //注意这里为了增加baseUrl能够修改，不在将IRequestApi设置为单例
        //拦截器
        Interceptor interceptor = chain -> {
            Request oldRequest = chain.request();
            // 添加新的参数
            HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host());
//                        .addQueryParameter("macAddress", Constant.MAC_ADDRESS)
//                        .addQueryParameter("userId", Constant.USER_ID)
//                        .addQueryParameter("dbSource", Constant.DBSOURCE);

            //新的请求
            Request newRequest = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
//                        .header("registeredChannels", "2")//来自1：iOS,2:Android,3:web
                    .url(authorizedUrlBuilder.build())
                    .build();

            return chain.proceed(newRequest);
        };
        //打印拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Log.i(TAG, message));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)       //添加拦截器
                .addInterceptor(logging)           //添加打印拦截器
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)//设置请求超时时间
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)    //设置出现错误进行重新连接。
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(clz);
    }

    /**
     * 对外提供的获取支持自签名的okhttp客户端
     *
     * @param certificate 自签名证书的输入流
     * @return 支持自签名的客户端
     */
    public OkHttpClient.Builder getTrusClient(InputStream certificate) {
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory;
        try {
            trustManager = trustManagerForCertificates(certificate);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            //使用构建出的trustManger初始化SSLContext对象
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            //获得sslSocketFactory对象
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager);
    }

    /**
     * 获去信任自签证书的trustManager
     *
     * @param in 自签证书输入流
     * @return 信任自签证书的trustManager
     * @throws GeneralSecurityException
     */
    private X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        //通过证书工厂得到自签证书对象集合
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }
        //为证书设置一个keyStore
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        //将证书放入keystore中
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }
        // Use it to build an X509 trust manager.
        //使用包含自签证书信息的keyStore去构建一个X509TrustManager
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(null, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private HostnameVerifier getHostnameVerifier(final String hostUrl) {
        return (hostname, session) -> hostUrl.equalsIgnoreCase(hostname);
    }

    private static final RetrofitModule ourInstance = new RetrofitModule();

    private RetrofitModule() {
    }

    public static RetrofitModule getInstance() {
        return ourInstance;
    }

    public static class Api {

        public Object api;      //Class -> Retrofit Api
        public String baseUrl;  //地址
        public boolean isInitialized;

        public Api(Object api, String baseUrl, boolean isInitialized) {
            this.api = api;
            this.baseUrl = baseUrl;
            this.isInitialized = isInitialized;
        }
    }
}