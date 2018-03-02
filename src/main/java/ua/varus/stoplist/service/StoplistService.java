package ua.varus.stoplist.service;

import ua.varus.stoplist.domain.StoplistSearchForm;
import ua.varus.stoplist.domain.StoplistRow;

import java.util.List;

public interface StoplistService {
    List<StoplistRow> getList(StoplistSearchForm searchForm);

    StoplistRow insertStopList(StoplistRow stoplistRow);

    boolean deleteStoplistRow(int id);

    StoplistRow editStoplistRow(StoplistRow stoplistRow);
}
