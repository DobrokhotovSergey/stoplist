package ua.varus.stoplist.service;

import ua.varus.stoplist.domain.Codificator;
import ua.varus.stoplist.domain.StoplistSearchForm;
import ua.varus.stoplist.domain.StoplistRow;

import java.util.List;

public interface StoplistService {
    List<StoplistRow> getList(StoplistSearchForm searchForm);

    StoplistRow insertStopList(StoplistRow stoplistRow);

    boolean deleteStoplistRow(int id);

    StoplistRow editStoplistRow(StoplistRow stoplistRow);

    List<Codificator> getListCodificators();

    List<String> getListCompany();

    List<String> getListDepartmnet();

    List<Codificator> insertStopCodificator(Codificator codificator);

    List<String> getListSources();
}
