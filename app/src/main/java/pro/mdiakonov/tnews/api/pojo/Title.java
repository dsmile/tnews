package pro.mdiakonov.tnews.api.pojo;

import com.google.gson.annotations.SerializedName;

import pro.mdiakonov.tnews.repository.entities.TitleEntry;

public class Title {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    public String name;
    @SerializedName("publicationDate")
    TDate publicationDate;

    public Title(TitleEntry titleEntry) {
        this.id = titleEntry.getNewsId();
        this.name = titleEntry.getName();
        this.publicationDate = new TDate(titleEntry.getPublicationDate());
    }
}
