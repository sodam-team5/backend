package sodam.demo.global.exception;

import sodam.demo.global.apipayload.BaseErrorCode;

public class GuardianException extends GeneralException {
    public GuardianException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

