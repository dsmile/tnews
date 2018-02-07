package pro.mdiakonov.tnews.api.pojo;

import com.google.gson.annotations.SerializedName;

public class TDate {
    @SerializedName("milliseconds")
    long milliseconds;

    public TDate(long milliseconds) {
        this.milliseconds = milliseconds;
    }
}
