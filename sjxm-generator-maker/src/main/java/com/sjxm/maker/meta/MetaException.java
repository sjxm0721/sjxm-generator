package com.sjxm.maker.meta;

/**
 * @Author: 四季夏目
 * @Date: 2024/2/17
 * @Description:
 */
public class MetaException extends RuntimeException{

    public MetaException(String message){
        super(message);
    }

    public MetaException(String message,Throwable cause){
        super(message,cause);
    }
}
