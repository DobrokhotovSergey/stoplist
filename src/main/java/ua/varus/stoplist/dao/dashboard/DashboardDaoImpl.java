package ua.varus.stoplist.dao.dashboard;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.varus.stoplist.domain.dashboard.CalcDashboard;
import ua.varus.stoplist.domain.dashboard.TableDashboard;
import ua.varus.stoplist.jdbc.dashboard.DashboardRowMapperImpl;

import java.util.List;

@Slf4j
@Repository
public class DashboardDaoImpl implements DashboardDao{

    private JdbcTemplate jdbcTemplate;

    public DashboardDaoImpl(  @Qualifier("riski-postgresql") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String CALC_DASHBOARD =
            "select count(a.id) as count1, b.code, b.name \n" +
            "into temp temp_sl_1\n" +
            "from stoplist a right join stoplist_codificators b on a.codificator = b.code and a.create_date < ?::date and status='A'\n" +
            "group by b.code;\n" +
            "\n" +
            "\n" +
            "\n" +
            "select count(a.id) as count2, b.code, b.name \n" +
            "into temp temp_sl_2\n" +
            "from stoplist a right join stoplist_codificators b on a.codificator = b.code and a.create_date < ?::date and status='A'\n" +
            "group by b.code;\n" +
            "\n" +
            "\n" +
            "select a.code, a.name, a.count1, b.count2, (count2-count1) as diff into temp temp_sl_total from temp_sl_1 a join temp_sl_2 b on a.code = b.code ;\n";



 private static final String SELECT_DASHBOARD =  "select * from temp_sl_total\n" +
            "union all \n" +
            "select * from(\n" +
            "select -1, 'итого' , sum(count1) , sum(count2) ,  sum(diff) as increase from temp_sl_total ) total order by code desc";

    @Transactional
    @Override
    public List<TableDashboard> calcDashboard(CalcDashboard calcDashboard) {

        try{

            int result = jdbcTemplate.update(CALC_DASHBOARD, new Object[]{calcDashboard.getDate1(), calcDashboard.getDate2()});
            List<TableDashboard> list = jdbcTemplate.query(SELECT_DASHBOARD, new DashboardRowMapperImpl());
            return list;
        }catch (Exception ex){
            log.error("Error calcDashboard : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
        }

        return null;
    }
}
