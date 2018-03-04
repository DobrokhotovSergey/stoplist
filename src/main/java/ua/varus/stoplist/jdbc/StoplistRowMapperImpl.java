package ua.varus.stoplist.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ua.varus.stoplist.domain.Codificator;
import ua.varus.stoplist.domain.StoplistRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StoplistRowMapperImpl implements RowMapper<StoplistRow> {

    @Override
    public StoplistRow mapRow(ResultSet rs, int i) throws SQLException, NullPointerException {

        Timestamp createDate = rs.getTimestamp("create_date");
        Timestamp editDate = rs.getTimestamp("edit_date");
        Timestamp removeDate = rs.getTimestamp("remove_date");

        LocalDateTime createDateTime = null, removeDateTime = null, editDateTime = null;

        if(createDate != null){
            createDateTime =  createDate.toLocalDateTime();
        }
        if(editDate != null){
            editDateTime = editDate.toLocalDateTime();
        }
        if(removeDate != null){
            removeDateTime = removeDate.toLocalDateTime();
        }

        Codificator codificators = Codificator.builder()
                .code(rs.getByte("codificator_code"))
                .name(rs.getString("codificator_name")).build();

        StoplistRow s2 = new StoplistRow();
        s2.setSource(rs.getString("source"));

        StoplistRow stoplistRow = StoplistRow.builder()
                .id(rs.getInt("id"))
                .inn(rs.getBigDecimal("inn"))
                .okpo(rs.getBigDecimal("okpo"))
                .fio(rs.getString("fio"))
                .birthDate(rs.getDate("birth_date"))
                .pasportSerial(rs.getString("pasport_serial"))
                .pasportNumber(rs.getString("pasport_number"))
                .createDate(createDateTime)
                .editDate(editDateTime)
                .status(rs.getString("status"))
                .removeDate(removeDateTime)
                .comment(rs.getString("comment"))
                .codificator(codificators)
                .createLoginEmployee(rs.getString("create_login_employee"))
                .editLoginEmployee(rs.getString("edit_login_employee"))
                .createCompany(rs.getString("create_company"))
                .createDepartment(rs.getString("created_department"))
                .source(s2.getSource())
                .build();

        return stoplistRow;
    }


}
