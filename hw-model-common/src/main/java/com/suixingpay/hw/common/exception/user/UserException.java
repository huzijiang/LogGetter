package com.suixingpay.hw.common.exception.user;

import com.suixingpay.hw.common.exception.base.BaseException;
import com.suixingpay.hw.common.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * suixingpay
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
