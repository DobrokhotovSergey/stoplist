package ua.varus.stoplist.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.varus.stoplist.dao.api.ApiDao;
import ua.varus.stoplist.domain.StoplistRow;
import ua.varus.stoplist.domain.api.CodificatorApi;
import ua.varus.stoplist.domain.api.FlagCodificatorApi;
import ua.varus.stoplist.domain.api.FormFioDate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService{

    @Autowired
    private ApiDao apiDao;


    @Override
    public List<CodificatorApi> getListCodificator(BigDecimal inn) {
        return apiDao.getListCodificator(inn);
    }

    @Override
    public FlagCodificatorApi getFlagCodificatorApi(BigDecimal inn) {
        return apiDao.getFlagCodificatorApi(inn);
    }

    @Override
    public FlagCodificatorApi getFlagByFioDate(FormFioDate formFioDate) {
        return apiDao.getFlagByFioDate(formFioDate);
    }

    @Override
    public FlagCodificatorApi getFlagByFirstFio(FormFioDate formFioDate) {
        return apiDao.getFlagByFirstFio(formFioDate);
    }

    @Override
    public boolean addStopList(StoplistRow stoplistRow) {
        return apiDao.addStopList(stoplistRow);
    }
}
