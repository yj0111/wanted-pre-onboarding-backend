package wanted.pre_onboarding.global.exception;

public class NotExistCompanyException extends RuntimeException {
    public NotExistCompanyException() {
        super(ExceptionCode.NOT_EXIST_COMPANY_EXCEPTION.getErrorMessage());
    }

    public NotExistCompanyException(String message) {
        super(message);
    }
}