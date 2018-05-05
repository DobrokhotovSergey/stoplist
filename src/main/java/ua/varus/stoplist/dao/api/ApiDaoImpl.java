package ua.varus.stoplist.dao.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.varus.stoplist.domain.StoplistRow;
import ua.varus.stoplist.domain.api.CodificatorApi;
import ua.varus.stoplist.domain.api.FlagCodificatorApi;
import ua.varus.stoplist.domain.api.FormFioDate;
import ua.varus.stoplist.jdbc.api.CodificatorApiRowMapperImpl;
import ua.varus.stoplist.jdbc.api.FlagCodificatorApiRowMapperImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Slf4j
public class ApiDaoImpl implements ApiDao {


    private JdbcTemplate jdbcTemplate;
    private static final String SELECT_CODIFICATOR_BY_INN = "select c.code as codificator_code, c.name as codificator_name, edit_date  from stoplist s join stoplist_codificators c on s.codificator = c.code where inn = ? ";

    public ApiDaoImpl(  @Qualifier("riski-postgresql") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CodificatorApi> getListCodificator(BigDecimal inn) {
        try{
            List<CodificatorApi> list = jdbcTemplate.query(SELECT_CODIFICATOR_BY_INN, new CodificatorApiRowMapperImpl() , new Object[]{inn});
            if(list.size()==0){
                return new ArrayList<>(Arrays.asList(new CodificatorApi(null, (byte) -1, null)));
            }
            return list;
        }catch (Exception ex){
            log.error("Error getListCodificator : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));

        }
        return new ArrayList<>(Arrays.asList(new CodificatorApi(null, (byte) -1, null)));
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(new CodificatorApi(null, (byte) -1, null)));
    }
    private static final String SELECT_FLAG = "SELECT (EXISTS (SELECT * FROM stoplist ))::int,  edit_date FROM stoplist WHERE inn = ? order by edit_date desc limit 1";

    @Override
    public FlagCodificatorApi getFlagCodificatorApi(BigDecimal inn) {
        try{
            FlagCodificatorApi flagCodificatorApi= jdbcTemplate.queryForObject(SELECT_FLAG, new FlagCodificatorApiRowMapperImpl(),  inn);
            return flagCodificatorApi;
        }catch (Exception ex){
            log.error("Error getFlagCodificatorApi : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
        }
        return new FlagCodificatorApi(false, null);
    }

    private static final String SELECT_FLAG_BY_FIO_DATE = "SELECT (EXISTS (SELECT * FROM stoplist ))::int,  " +
            "edit_date FROM stoplist WHERE upper(fio) = upper(?) " +
            "and birth_date = ? order by edit_date desc limit 1";


    @Override
    public FlagCodificatorApi getFlagByFioDate(FormFioDate formFioDate) {

        try{
            FlagCodificatorApi flagCodificatorApi= jdbcTemplate.queryForObject(SELECT_FLAG_BY_FIO_DATE,
                    new FlagCodificatorApiRowMapperImpl(),  new Object[]{
                    formFioDate.getFio().trim().replaceAll("\\s+", " "),
                            formFioDate.getBirthDate()});
            return flagCodificatorApi;
        }catch (Exception ex){
            log.error("Error getFlagByFioDate : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
        }
        return new FlagCodificatorApi(false, null);
    }

    @Override
    public FlagCodificatorApi getFlagByFirstFio(FormFioDate formFioDate) {
        try{
            FlagCodificatorApi flagCodificatorApi= jdbcTemplate.queryForObject(SELECT_FLAG_BY_FIO_DATE,
                    new FlagCodificatorApiRowMapperImpl(),  new Object[]{formFioDate.getFio(), formFioDate.getBirthDate()});
            return flagCodificatorApi;
        }catch (Exception ex){
            log.error("Error getFlagByFirstFio : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
        }
        return new FlagCodificatorApi(false, null);
    }

    private static final String ADD_STOPLIST = "insert into stoplist (create_date, fio, comment, codificator, " +
            "create_login_employee, edit_login_employee, create_company, created_department, source, \n" +
            "pasport_serial, pasport_number, birth_date, inn )\n" +
            "values (now(), ?,?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public boolean addStopList(StoplistRow stoplistRow) {

        try{
            int result = jdbcTemplate.update(ADD_STOPLIST, new Object[]{

                    stoplistRow.getFio(), stoplistRow.getComment(), stoplistRow.getCodificator().getCode(),
                    stoplistRow.getCreateLoginEmployee(), stoplistRow.getEditLoginEmployee(), stoplistRow.getCreateCompany(),
                    stoplistRow.getCreateDepartment(), stoplistRow.getSource(), stoplistRow.getPasportSerial(), stoplistRow.getPasportNumber(),
                    stoplistRow.getBirthDate(), stoplistRow.getInn()

            });
            if(result==1){
                return true;
            }
            else{
                return false;
            }

        }catch (Exception ex){
            log.error("Error addStopList : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
        }

        return false;
    }
}
