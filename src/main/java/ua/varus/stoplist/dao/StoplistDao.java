package ua.varus.stoplist.dao;

import ua.varus.stoplist.domain.Codificator;
import ua.varus.stoplist.domain.StoplistRow;
import ua.varus.stoplist.domain.StoplistSearchForm;

import java.util.List;

public interface StoplistDao {
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
