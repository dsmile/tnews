package pro.mdiakonov.tnews.api.pojo;

import com.google.gson.annotations.SerializedName;

public class Content {
    @SerializedName("title")
    Title title;
    // html formatted
    @SerializedName("content")
    String content;

    public Title getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
