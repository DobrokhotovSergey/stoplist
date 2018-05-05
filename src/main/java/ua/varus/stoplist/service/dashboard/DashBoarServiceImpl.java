package ua.varus.stoplist.service.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.varus.stoplist.dao.dashboard.DashboardDao;
import ua.varus.stoplist.domain.dashboard.CalcDashboard;
import ua.varus.stoplist.domain.dashboard.TableDashboard;

import java.util.List;

@Service
public class DashBoarServiceImpl implements DashboardService {

    @Autowired
    private DashboardDao dashboardDao;

    @Override
    public List<TableDashboard> calcDashboard(CalcDashboard calcDashboard) {
        return dashboardDao.calcDashboard(calcDashboard);
    }
}
