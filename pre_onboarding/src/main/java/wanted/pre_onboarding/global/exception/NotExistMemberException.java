package wanted.pre_onboarding.global.exception;

public class NotExistMemberException extends RuntimeException {
    public NotExistMemberException() {
        super(ExceptionCode.NOT_EXIST_MEMBER_EXCEPTION.getErrorMessage());
    }

    public NotExistMemberException(String message) {
        super(message);
    }
}