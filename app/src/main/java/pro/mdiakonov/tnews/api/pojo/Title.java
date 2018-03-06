package pro.mdiakonov.tnews.api.pojo;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import pro.mdiakonov.tnews.model.entities.TitleEntry;

public class Title implements Comparable<Title> {
    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("text")
    String text;
    @SerializedName("publicationDate")
    TDate publicationDate;

    public Title(TitleEntry titleEntry) {
        this.id = titleEntry.getNewsId();
        this.name = titleEntry.getName();
        this.text = titleEntry.getText();
        this.publicationDate = new TDate(titleEntry.getPublicationDate());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public TDate getPublicationDate() {
        return publicationDate;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull Title o) {
        return Long.compare(this.getPublicationDate().getMilliseconds(),
                o.getPublicationDate().getMilliseconds());
    }
}
