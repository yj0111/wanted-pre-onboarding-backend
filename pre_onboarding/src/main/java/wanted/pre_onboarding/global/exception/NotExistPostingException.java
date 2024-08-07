package wanted.pre_onboarding.global.exception;

public class NotExistPostingException extends RuntimeException {
    public NotExistPostingException() {
        super(ExceptionCode.NOT_EXIST_POSTING_EXCEPTION.getErrorMessage());
    }

    public NotExistPostingException(String message) {
        super(message);
    }
}