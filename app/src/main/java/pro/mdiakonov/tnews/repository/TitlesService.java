package pro.mdiakonov.tnews.repository;

import java.util.List;

import pro.mdiakonov.tnews.api.pojo.Title;

public interface TitlesService {
    List<Title> getTitles(int page);
}
