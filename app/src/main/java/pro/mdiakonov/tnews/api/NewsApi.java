package pro.mdiakonov.tnews.api;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import pro.mdiakonov.tnews.api.pojo.Title;
import pro.mdiakonov.tnews.api.pojo.Content;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("news")
    Observable<List<Title>> getTitles(@Query("first") long first, @Query("last") long last);

    @GET("news_content")
    Single<Content> getContent(@Query("id") String id);
}
