package wanted.pre_onboarding.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import wanted.pre_onboarding.global.exception.ExceptionCode;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult {

    int statusCode; // 요청에 대한 성공 또는 다양한 예외 코드 반환

    String messages; // 프론트에서 보여줄 메세지

    String developerMessage; //개발자를 위한 메세지

    LocalDateTime timestamp; //응답 시간

    public static final ResponseResult successResponse =
            ResponseResult.builder()
                    .statusCode(HttpStatus.OK.value())
                    .messages("성공 :)")
                    .developerMessage("성공하였습니다.")
                    .timestamp(LocalDateTime.now()).build();

    public static final ResponseResult failResponse =
            ResponseResult.builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .messages("실패 :(")
                    .developerMessage("실패하였습니다.")
                    .timestamp(LocalDateTime.now()).build();

    public static final ResponseResult exceptionResponse(ExceptionCode exceptionCode) {
        return ResponseResult.builder()
                .statusCode(exceptionCode.getErrorCode())
                .messages("에러발생 :(")
                .developerMessage(exceptionCode.getErrorMessage())
                .timestamp(LocalDateTime.now()).build();
    }

    public static final ResponseResult customExceptionResponse(ExceptionCode exceptionCode, String message) {
        return ResponseResult.builder()
                .statusCode(exceptionCode.getErrorCode())
                .messages(message)
                .developerMessage(exceptionCode.getErrorMessage())
                .timestamp(LocalDateTime.now()).build();
    }

}