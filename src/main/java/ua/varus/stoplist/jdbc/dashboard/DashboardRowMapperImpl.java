package ua.varus.stoplist.jdbc.dashboard;

import org.springframework.jdbc.core.RowMapper;
import ua.varus.stoplist.domain.Codificator;
import ua.varus.stoplist.domain.dashboard.TableDashboard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardRowMapperImpl implements RowMapper<TableDashboard> {

    @Override
    public TableDashboard mapRow(ResultSet rs, int i) throws SQLException {

        Codificator codificator = Codificator.builder()
                .name(rs.getString("name"))
                .code(rs.getByte("code"))
                .build();

        TableDashboard tableDashboard = TableDashboard.builder()
                .codificator(codificator)
                .count1(rs.getInt("count1"))
                .count2(rs.getInt("count2"))
                .diff(rs.getInt("diff"))
                .build();

        return tableDashboard;
    }
}
