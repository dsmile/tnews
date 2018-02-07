package pro.mdiakonov.tnews.api;

import java.io.IOException;

public class WrappedResponseException extends IOException {
    private String resultCode;

    public WrappedResponseException(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCode() {
        return resultCode;
    }
}
