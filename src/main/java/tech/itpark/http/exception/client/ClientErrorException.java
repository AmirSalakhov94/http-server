package tech.itpark.http.exception.client;

public class ClientErrorException extends RuntimeException {
    public ClientErrorException() {
    }

    public ClientErrorException(String message) {
        super(message);
    }

    public ClientErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientErrorException(Throwable cause) {
        super(cause);
    }

    public ClientErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCodeName() {
        return "Client error";
    }

    public int getCode() {
        return 400;
    }

}
