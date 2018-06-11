package com.haibin.exception;

/**
 * @author haibin.tang
 * @create 2018-06-11 下午3:53
 **/
public class BizException extends RuntimeException {

    public BizException(String message) {
        super(message);
    }
}
