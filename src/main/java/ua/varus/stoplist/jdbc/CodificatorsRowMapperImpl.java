package ua.varus.stoplist.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ua.varus.stoplist.domain.Codificator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CodificatorsRowMapperImpl implements RowMapper<Codificator>{
    @Override
    public Codificator mapRow(ResultSet rs, int i) throws SQLException {
        Codificator codificators = Codificator.builder()
                .code(rs.getByte("code"))
                .name(rs.getString("name")).build();
        return codificators;
    }
}
