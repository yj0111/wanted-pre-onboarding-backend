package wanted.pre_onboarding.global.exception;

public class alreadyApplyException extends RuntimeException {
    public alreadyApplyException() {
        super(ExceptionCode.ALREADY_APPLY_EXCEPTION.getErrorMessage());
    }

    public alreadyApplyException(String message) {
        super(message);
    }
}