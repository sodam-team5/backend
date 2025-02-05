package sodam.demo.global.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sodam.demo.global.apipayload.ApiResponse;
import sodam.demo.global.apipayload.BaseErrorCode;
import sodam.demo.global.apipayload.ErrorReasonDto;
import sodam.demo.global.apipayload.status.ErrorStatus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        String errorMessages = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.joining(", "));

        return buildErrorResponse(e, ErrorStatus._BAD_REQUEST, HttpHeaders.EMPTY, request, errorMessages);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), Optional.ofNullable(fieldError.getDefaultMessage()).orElse("Invalid value"))
        );

        return buildErrorResponse(e, ErrorStatus._BAD_REQUEST, headers, request, errors);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception e, BaseErrorCode errorCode, HttpHeaders headers, WebRequest request, Object data) {
        ErrorReasonDto reason = errorCode.getReasonHttpStatus();
        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(), reason.getMessage(), data);
        return super.handleExceptionInternal(e, body, headers, reason.getHttpStatus(), request);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> handleGeneralException(GeneralException e, WebRequest request) {
        log.error("General exception occurred: ", e);
        return buildErrorResponse(e, e.getCode(), HttpHeaders.EMPTY, request, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnhandledException(Exception e, WebRequest request) {
        log.error("Unhandled exception occurred: ", e);
        return buildErrorResponse(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, request, e.getMessage());
    }
}