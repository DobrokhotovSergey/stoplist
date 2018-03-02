package ua.varus.stoplist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.varus.stoplist.dao.StoplistDao;
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
}
