package ua.varus.stoplist.jdbc;

import ua.varus.stoplist.domain.StoplistRow;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoplistOperatorRowMapperImpl extends StoplistRowMapperImpl{

    @Override
    public StoplistRow mapRow(ResultSet rs, int i) throws SQLException, NullPointerException {
        StoplistRow m = super.mapRow(rs, i);
        m.setSource(null);
        m.setComment(null);
        return m;
    }
}
