package com.lin.infrastructure.commons;

import com.lin.infrastructure.commons.enums.BizErrorCode;

/**
 * @author linzihao 业务异常
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    String code = ResultData.ERR;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

    public BusinessException(BizErrorCode bizErrorCode) {
        super(bizErrorCode.getMsg());
        this.code = bizErrorCode.getCode();
    }

    public String getCode() {
        return code;
    }

}
