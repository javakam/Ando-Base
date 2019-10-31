package com.ando.base.net;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Title: IRequest
 * <p>
 * Description:接口
 * </p>
 *
 * @author Changbao
 * @date 2019/1/19
 */
public interface IRequest {

    String HOST = "http://android-ability.uuuqwr.com/";

    /**
     * 登录
     */
//    @POST("loginAccount.php")
//    @FormUrlEncoded
//    Flowable<BaseResponse<LoginEntity>> login(@Field("phoneNumber") String phoneNumber, @Field("password") String password,
//                                              @Field("timestamp") String timestamp, @Field("token") String token);

    /**
     * 获取验证码
     */
    @POST("sendPhoneCheckCode.php")
    @FormUrlEncoded
    Flowable<BaseResponse<String>> verify(@Field("phoneNumber") String phoneNumber, @Field("timestamp") String timestamp, @Field("token") String token);

    /**
     * 注册
     */
    @POST("checkPhoneCode.php")
    @FormUrlEncoded
    Flowable<BaseResponse<String>> register(@Field("phoneNumber") String phoneNumber,
                                            @Field("checkCode") String checkCode, @Field("password") String password,
                                            @Field("timestamp") String timestamp, @Field("token") String token);
}