package com.ando.base.net;

/**
 * Title: BaseResponse
 * <p>
 * Description:网络实体基类
 * </p>
 *
 * @author Changbao
 * @date 2019/1/19
 */
public class BaseResponse<T> {

    public String status;
    public String msg;
    public T result;
}