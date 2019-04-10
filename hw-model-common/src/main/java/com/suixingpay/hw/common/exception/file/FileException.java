package com.suixingpay.hw.common.exception.file;

import com.suixingpay.hw.common.exception.base.BaseException;
import com.suixingpay.hw.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * suixingpay
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
