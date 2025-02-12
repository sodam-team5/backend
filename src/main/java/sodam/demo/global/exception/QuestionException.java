package sodam.demo.global.exception;

import sodam.demo.global.apipayload.BaseErrorCode;

public class QuestionException extends GeneralException {
    public QuestionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
