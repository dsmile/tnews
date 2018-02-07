package pro.mdiakonov.tnews.api.pojo;

import com.google.gson.annotations.SerializedName;

public class WrappedResponse<T> {
    @SerializedName("resultCode")
    public String resultCode;
    @SerializedName("payload")
    public T payload;
    @SerializedName("trackingId")
    String trackingId;
}
