package pro.mdiakonov.tnews.api.pojo;

import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("title")
    Title title;
    // html formatted
    @SerializedName("content")
    public String content;
}
