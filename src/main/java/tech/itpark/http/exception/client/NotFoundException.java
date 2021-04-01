package tech.itpark.http.exception.client;

public class NotFoundException extends ClientErrorException {
    private final String codeName = "Not found";
    private final int code = 404;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getCodeName() {
        return codeName;
    }

    @Override
    public int getCode() {
        return code;
    }
}
