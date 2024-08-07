package wanted.pre_onboarding.global.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wanted.pre_onboarding.global.response.ResponseResult;

import java.rmi.ServerException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotExistCompanyException.class)
    public ResponseResult NotExistCompanyException(NotExistCompanyException err) {
        log.info("Error : {}", err.getClass());
        log.info("Error Message : {}", err.getMessage());
        return ResponseResult.exceptionResponse(ExceptionCode.NOT_EXIST_COMPANY_EXCEPTION, err.getMessage());
    }

    @ExceptionHandler(NotExistPostingException.class)
    public ResponseResult NotExistPostingException(NotExistPostingException err) {
        log.info("Error : {}", err.getClass());
        log.info("Error Message : {}", err.getMessage());
        return ResponseResult.exceptionResponse(ExceptionCode.NOT_EXIST_POSTING_EXCEPTION, err.getMessage());
    }

    @ExceptionHandler(ServerException.class)
    public ResponseResult ServerException(ServerException err) {
        log.info("Error : {}", err.getClass());
        log.info("Error Message : {}", err.getMessage());
        return ResponseResult.exceptionResponse(ExceptionCode.SERVER_EXCEPTION, err.getMessage());
    }
}
