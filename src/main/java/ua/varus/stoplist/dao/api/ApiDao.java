package ua.varus.stoplist.dao.api;

import ua.varus.stoplist.domain.StoplistRow;
import ua.varus.stoplist.domain.api.CodificatorApi;
import ua.varus.stoplist.domain.api.FlagCodificatorApi;
import ua.varus.stoplist.domain.api.FormFioDate;

import java.math.BigDecimal;
import java.util.List;

public interface ApiDao {

    List<CodificatorApi> getListCodificator(BigDecimal inn);

    FlagCodificatorApi getFlagCodificatorApi(BigDecimal inn);

    FlagCodificatorApi getFlagByFioDate(FormFioDate formFioDate);

    FlagCodificatorApi getFlagByFirstFio(FormFioDate formFioDate);

    boolean addStopList(StoplistRow stoplistRow);
}
