package sodam.demo.global.exception;

import sodam.demo.global.apipayload.BaseErrorCode;

public class ElderException extends GeneralException {
    public ElderException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
