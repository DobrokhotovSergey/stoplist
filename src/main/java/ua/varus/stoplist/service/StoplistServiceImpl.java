package ua.varus.stoplist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.varus.stoplist.dao.StoplistDao;
import ua.varus.stoplist.domain.Codificator;
import ua.varus.stoplist.domain.StoplistSearchForm;
import ua.varus.stoplist.domain.StoplistRow;

import java.util.List;

@Service
public class StoplistServiceImpl implements StoplistService {

    @Autowired
    private StoplistDao stoplistDao;

    @Override
    public List<StoplistRow> getList(StoplistSearchForm searchForm) {
        return stoplistDao.getList(searchForm);
    }

    @Override
    public StoplistRow insertStopList(StoplistRow stoplistRow) {
        return stoplistDao.insertStopList(stoplistRow);
    }

    @Override
    public boolean deleteStoplistRow(int id) {
        return stoplistDao.deleteStoplistRow(id);
    }

    @Override
    public StoplistRow editStoplistRow(StoplistRow stoplistRow) {
        return stoplistDao.editStoplistRow(stoplistRow);
    }

    @Override
    public List<Codificator> getListCodificators() {
        return stoplistDao.getListCodificators();
    }

    @Override
    public List<String> getListCompany() {
        return stoplistDao.getListCompany();
    }

    @Override
    public List<String> getListDepartmnet() {
        return stoplistDao.getListDepartmnet();
    }

    @Override
    public List<Codificator> insertStopCodificator(Codificator codificator) {
        return stoplistDao.insertStopCodificator(codificator);
    }

    @Override
    public List<String> getListSources() {
        return stoplistDao.getListSources();
    }
}
