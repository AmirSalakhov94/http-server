package tech.itpark.http.exception.server;

public class HttpVersionNotSupportedException extends ServerErrorException {

    private final int code = 412;
    private final String codeName = "Malformed Request";

    public HttpVersionNotSupportedException() {
        super();
    }

    public HttpVersionNotSupportedException(String message) {
        super(message);
    }

    public HttpVersionNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpVersionNotSupportedException(Throwable cause) {
        super(cause);
    }

    protected HttpVersionNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }
}
