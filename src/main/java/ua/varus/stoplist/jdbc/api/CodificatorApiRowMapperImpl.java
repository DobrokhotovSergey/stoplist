package ua.varus.stoplist.jdbc.api;

import org.springframework.jdbc.core.RowMapper;
import ua.varus.stoplist.domain.api.CodificatorApi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CodificatorApiRowMapperImpl implements RowMapper<CodificatorApi> {

    @Override
    public CodificatorApi mapRow(ResultSet rs, int i) throws SQLException {


        Timestamp editDate = rs.getTimestamp("edit_date");

        LocalDateTime editDateTime = null;

        if(editDate != null){
            editDateTime =  editDate.toLocalDateTime();
        }

        CodificatorApi codificatorApi = new CodificatorApi(
                editDateTime,
                rs.getByte("codificator_code"),
                rs.getString("codificator_name")
                );

        return codificatorApi;
    }
}
