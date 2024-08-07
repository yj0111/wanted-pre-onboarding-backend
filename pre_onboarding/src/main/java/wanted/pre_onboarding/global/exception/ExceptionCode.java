package wanted.pre_onboarding.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  ErrorCode는 임의로 지정하였음
 */
@Getter
@AllArgsConstructor
public enum ExceptionCode {
    NOT_EXIST_COMPANY_EXCEPTION(430,"회사가 존재하지 않습니다."),
    NOT_EXIST_POSTING_EXCEPTION(431,"공고가 존재하지 않습니다."),
    NOT_EXIST_MEMBER_EXCEPTION(432,"회원이 존재하지 않습니다."),
    ALREADY_APPLY_EXCEPTION(433,"이미 지원한 공고입니다."),
    SERVER_EXCEPTION(500, "서버에서 예측하지 못한 에러가 발생했습니다.");

    private final int errorCode;
    private final String errorMessage;
}
