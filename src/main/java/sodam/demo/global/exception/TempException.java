package sodam.demo.global.exception;

import sodam.demo.global.apipayload.BaseErrorCode;

public class TempException extends GeneralException {
    public TempException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
