package com.ando.base.net;

/**
 * Title: RemoteRepository
 * <p>
 * Description:
 * </p>
 *
 * @author Changbao
 * @date 2019/1/19
 */
public class RemoteRepository {

    private IRequest mRequestApi;

    public RemoteRepository(String baseUrl) {
        this.mRequestApi = RetrofitModule.getInstance().getApiService(IRequest.class);
    }

//    public Flowable<LoginEntity> login(String phoneNumber, String password, String timestamp, String token) {
//        return this.mRequestApi.login(phoneNumber, password, timestamp, token)
//                .compose(TransformerHelper.handleResponse(LoginStatus.class));
//    }

//    public Flowable<String> verify(String phoneNumber, String timestamp, String token) {
//        return this.mRequestApi.verify(phoneNumber, timestamp, token)
//                .compose(TransformerHelper.handleResponse(VerifyCodeStatus.class));
//    }
//
//    public Flowable<String> register(String phoneNumber, String checkCode, String password, String timestamp, String token) {
//        return this.mRequestApi.register(phoneNumber, checkCode, password, timestamp, token)
//                .compose(TransformerHelper.handleResponse(RegisterStatus.class));
//    }
}