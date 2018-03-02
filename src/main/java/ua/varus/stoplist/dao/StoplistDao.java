package ua.varus.stoplist.dao;

import ua.varus.stoplist.domain.StoplistRow;
import ua.varus.stoplist.domain.StoplistSearchForm;

import java.util.List;

public interface StoplistDao {
    List<StoplistRow> getList(StoplistSearchForm searchForm);

    StoplistRow insertStopList(StoplistRow stoplistRow);

    boolean deleteStoplistRow(int id);

    StoplistRow editStoplistRow(StoplistRow stoplistRow);
}
