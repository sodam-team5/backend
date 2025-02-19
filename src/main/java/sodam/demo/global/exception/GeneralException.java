package sodam.demo.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sodam.demo.global.apipayload.BaseErrorCode;
import sodam.demo.global.apipayload.ErrorReasonDto;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode code;

    // 에러 메시지 정보 제공
    public ErrorReasonDto getErrorReason() {
        return this.code.getReason();
    }

    // HTTP 상태 코드 반환
    public ErrorReasonDto getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}