package main.api.request;

public enum DTOErrorDescription {

    UNAUTHORIZED("Unauthorized"),
    CODE_MUST_BE_SUPPLIED("An authorization code must be supplied"),
    NULL("Null inputs"),
    REDIRECT_URI("Redirect URI mismatch"),
    INVALID_AUTHORIZATION("Invalid authorization code: CODE"),
    BAD_CREDENTIALS("Bad credentials"),
    EXPIRED("Token expired"),
    EXIST("This email already exist"),
    BAD_REQUEST("Bad Request"),
    CAPTCHA_INCORRECT("Captcha incorrect"),
    NOT_APPROVED("Email is not approved");

    private final String errorDescriptionMessage;

    DTOErrorDescription(String errorDescriptionMessage) {
        this.errorDescriptionMessage = errorDescriptionMessage;
    }

    public String get() {
        return errorDescriptionMessage;
    }
}
