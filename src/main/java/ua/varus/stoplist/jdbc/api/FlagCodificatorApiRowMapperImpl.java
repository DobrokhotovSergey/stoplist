package ua.varus.stoplist.jdbc.api;

import org.springframework.jdbc.core.RowMapper;
import ua.varus.stoplist.domain.api.FlagCodificatorApi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FlagCodificatorApiRowMapperImpl implements RowMapper<FlagCodificatorApi> {

    @Override
    public FlagCodificatorApi mapRow(ResultSet rs, int i) throws SQLException {


        Timestamp editDate = rs.getTimestamp("edit_date");

        LocalDateTime editDateTime = null;

        if(editDate != null){
            editDateTime =  editDate.toLocalDateTime();
        }

        FlagCodificatorApi flagCodificatorApi = FlagCodificatorApi.builder()
                .contains(rs.getBoolean("exists"))
                .lastEditDate(editDateTime)
                .build();
        return flagCodificatorApi;
    }
}
