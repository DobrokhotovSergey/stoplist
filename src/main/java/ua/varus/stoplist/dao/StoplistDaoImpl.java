package ua.varus.stoplist.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import ua.varus.stoplist.domain.Codificator;
import ua.varus.stoplist.domain.StoplistRow;
import ua.varus.stoplist.domain.StoplistSearchForm;
import ua.varus.stoplist.jdbc.CodificatorsRowMapperImpl;
import ua.varus.stoplist.jdbc.StoplistOperatorRowMapperImpl;
import ua.varus.stoplist.jdbc.StoplistRiskAdminRowMapperImpl;
import ua.varus.stoplist.jdbc.StoplistRowMapperImpl;
import ua.varus.stoplist.service.UserService;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Slf4j
public class StoplistDaoImpl implements StoplistDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    private static final String SELECT_STOPLIST = "SELECT id, inn, okpo, fio, birth_date, pasport_serial, " +
            "pasport_number, create_date, edit_date, status, remove_date, comment, c.code as codificator_code, c.name as codificator_name, create_login_employee," +
            "edit_login_employee, create_company, created_department, source FROM stoplist t join stoplist_codificators c on t.codificator = c.code ";

    ;

    @Override
    public List<StoplistRow> getList(StoplistSearchForm searchForm) {
        String QUERY = " where status = 'A' and ";
        List<Object> listObject = new ArrayList<>();
        if(!"".equals(searchForm.getInn())){
            QUERY += "inn = ?::decimal(10) and ";
            listObject.add(searchForm.getInn());
        }
        if(!"".equals(searchForm.getFio())){
            QUERY += "upper(fio) = ? and ";
            listObject.add(searchForm.getFio().toUpperCase());
        }
        if(searchForm.getBirtdate() != null){
            QUERY += "birth_date = ? and ";
            listObject.add(searchForm.getBirtdate());
        }


        QUERY= QUERY.substring(0, QUERY.lastIndexOf(" and "));

        try{

            Authentication auth =  SecurityContextHolder.getContext().getAuthentication();

            Collection<GrantedAuthority> roles = ((User)auth.getPrincipal()).getAuthorities();


            List<StoplistRow> list;
            String a = roles.toArray()[0].toString();

            if("ROLE_OPERATOR".equals(a)){

                list = jdbcTemplate.query(SELECT_STOPLIST+QUERY, new StoplistOperatorRowMapperImpl(),
                        listObject.stream().toArray(String[]::new));

            }else if("ROLE_RADMIN".equals(a)){
                list = jdbcTemplate.query(SELECT_STOPLIST+QUERY, new StoplistRowMapperImpl(),
                        listObject.stream().toArray(String[]::new));
            }
            else{
                    list = jdbcTemplate.query(SELECT_STOPLIST+QUERY, new StoplistRowMapperImpl(),
                            listObject.stream().toArray(String[]::new));
            }





            return list;

        }catch (Exception ex){
            log.error("Error getList : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
        }
        return null;
    }



    @Override
    public StoplistRow insertStopList(StoplistRow stoplistRow) {

        boolean containInnOrOkpo = stoplistRow.getInn()!=null;
        String QUERY = "insert into stoplist (create_date, fio, comment, codificator, create_login_employee, edit_login_employee, " +
                "create_company, created_department, source,  " +
                "pasport_serial, pasport_number, birth_date, innOrOkpo ) values (now(), ?,?,?,?,?,?,?,?,?,?,?,?) ";
        if(containInnOrOkpo){
            if("phys".equals(stoplistRow.getStatus())){
                stoplistRow.setInn(stoplistRow.getInn());
                QUERY = QUERY.replace(", innOrOkpo", ",inn");
            }else if("jur".equals(stoplistRow.getStatus())){
                stoplistRow.setOkpo(stoplistRow.getInn());
                QUERY = QUERY.replace(", innOrOkpo", ",okpo");
            }

        }else{
            QUERY = QUERY.replace(", innOrOkpo", "");
            QUERY = QUERY.replaceFirst("\\?\\,","");
        }

       String userName = userService.getUser();

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            String finalQUERY = QUERY;
            jdbcTemplate.update(
                    conn -> {
                        PreparedStatement ps = conn.prepareStatement(finalQUERY, new String[]{"id"});
                        ps.setString(1, stoplistRow.getFio());
                        ps.setString(2, stoplistRow.getComment());
                        ps.setByte(3, stoplistRow.getCodificator().getCode());
                        ps.setString(4, userName);
                        ps.setString(5, userName);
                        ps.setString(6, stoplistRow.getCreateCompany());
                        ps.setString(7, stoplistRow.getCreateDepartment());
                        ps.setString(8, stoplistRow.getSource());
                        ps.setString(9, stoplistRow.getPasportSerial());
                        ps.setString(10, stoplistRow.getPasportNumber());
                        java.sql.Date birth = null;
                        if(stoplistRow.getBirthDate()!=null){
                            birth = new java.sql.Date(stoplistRow.getBirthDate().getTime());
                        }
                        ps.setDate(11, birth);
                        if(stoplistRow.getInn()!=null){
                           ps.setBigDecimal(12, stoplistRow.getInn());
                        }else if(stoplistRow.getOkpo()!=null){
                           ps.setBigDecimal(12, stoplistRow.getOkpo());
                        }
                        return ps;
                    },
                    keyHolder);

        }catch (Exception ex){
            log.error("Error insertStopList : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));

        }

        try{
            return jdbcTemplate.queryForObject(SELECT_STOPLIST+" where id=?", new StoplistRowMapperImpl(),new Object[]{keyHolder.getKey()});

        }catch (Exception ex){
            log.error("Error select after insertStopList : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));

        }

       return null;
    }

    private static final String DELETE_STOPLIST = "update stoplist set status = 'D', edit_login_employee=?, edit_date=now() where id = ? ";

    @Override
    public boolean deleteStoplistRow(int id) {
        try{
            Authentication auth =  SecurityContextHolder.getContext().getAuthentication();

            String userName = ((User)auth.getPrincipal()).getUsername();
            int result = jdbcTemplate.update(DELETE_STOPLIST, new Object[]{userName, id});
            if(result==1){
                return true;
            }
        }catch (Exception ex){
            log.error("Error deleteStoplistRow : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));

        }
        return false;
    }

    @Override
    public StoplistRow editStoplistRow(StoplistRow stoplistRow) {

        boolean containInnOrOkpo = stoplistRow.getInn()!=null;
        String QUERY = "update stoplist set edit_date=now(), fio=?, comment=?, codificator=?, edit_login_employee=?, " +
                "create_company=?, created_department=?, source=?,  " +
                "pasport_serial=?, pasport_number=?, birth_date=? ";


        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();

        String userName = ((User)auth.getPrincipal()).getUsername();
        Collection<GrantedAuthority> roles = ((User)auth.getPrincipal()).getAuthorities();


        int maxIndex = 10;
        if(containInnOrOkpo){
            if("phys".equals(stoplistRow.getStatus())){
                stoplistRow.setInn(stoplistRow.getInn());
                stoplistRow.setOkpo(null);
                QUERY =QUERY+", inn=?, okpo=null ";
                maxIndex++;

            }else if("jur".equals(stoplistRow.getStatus())){
                stoplistRow.setOkpo(stoplistRow.getInn());
                stoplistRow.setInn(null);
                QUERY =QUERY+", okpo=?, inn=null ";
                maxIndex++;
            }

        }else{
            QUERY = QUERY +", okpo=null, inn=null ";
        }
        QUERY = QUERY + " where id=? ";



        try {
            String finalQUERY = QUERY;
            int finalMaxIndex = maxIndex;
            jdbcTemplate.update(
                    conn -> {
                        PreparedStatement ps = conn.prepareStatement(finalQUERY);
                        ps.setString(1, stoplistRow.getFio());
                        ps.setString(2, stoplistRow.getComment());
                        ps.setByte(3, stoplistRow.getCodificator().getCode());
                        ps.setString(4, userName);
                        ps.setString(5, stoplistRow.getCreateCompany());
                        ps.setString(6, stoplistRow.getCreateDepartment());
                        ps.setString(7, stoplistRow.getSource());
                        ps.setString(8, stoplistRow.getPasportSerial());
                        ps.setString(9, stoplistRow.getPasportNumber());

                        java.sql.Date birth = null;
                        if(stoplistRow.getBirthDate()!=null){
                            birth = new java.sql.Date(stoplistRow.getBirthDate().getTime());
                        }

                        ps.setDate(10,  birth);
                        if(stoplistRow.getInn()!=null){
                            ps.setBigDecimal(11, stoplistRow.getInn());
                        }else if(stoplistRow.getOkpo()!=null){
                            ps.setBigDecimal(11, stoplistRow.getOkpo());
                        }

                        ps.setInt(finalMaxIndex +1, stoplistRow.getId());
                        return ps;
                    }
                    );

        }catch (Exception ex){
            log.error("Error editStopList : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));

        }

        try{
            return jdbcTemplate.queryForObject(SELECT_STOPLIST+" where id=?", new StoplistRowMapperImpl(),new Object[]{stoplistRow.getId()});

        }catch (Exception ex){
            log.error("Error select after edittStopList : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));

        }

        return null;
    }

    private static  final String SELECT_CODIFICATORS = "select code, name from stoplist_codificators";

    @Override
    public List<Codificator> getListCodificators() {
        try{
            return jdbcTemplate.query(SELECT_CODIFICATORS, new CodificatorsRowMapperImpl());
        }catch (Exception ex){
            log.error("Error select getListCodificators : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
        }
        return null;
    }

    private static  final String SELECT_COMPANY = "select name from stoplist_company";

    @Override
    public List<String> getListCompany() {
        try{
            return jdbcTemplate.queryForList(SELECT_COMPANY, String.class);
        }catch (Exception ex){
            log.error("Error select getListCompany : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
        }
        return null;
    }

    private static  final String SELECT_DEPARTMENT = "select name from stoplist_department";
    @Override
    public List<String> getListDepartmnet() {
        try{
            return jdbcTemplate.queryForList(SELECT_DEPARTMENT, String.class);
        }catch (Exception ex){
            log.error("Error select getListDepartmnet : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
        }
        return null;
    }

    private static final String INSERT_CODIFICATOR =  "insert into stoplist_codificators (code, name) values (? , ?)";

    @Override
    public List<Codificator> insertStopCodificator(Codificator codificator) {
        try{
            int result = jdbcTemplate.update(INSERT_CODIFICATOR, new Object[]{codificator.getCode(), codificator.getName()});
            if(result==1){
                return getListCodificators();
            }

        }catch (Exception ex){
            log.error("Error insertStopCodificator : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));

        }
        return null;
    }

    private static final String SELECT_SOURCE =  "select name from stoplist_source";
        @Override
        public List<String> getListSources() {
            try{
                return jdbcTemplate.queryForList(SELECT_SOURCE, String.class);
            }catch (Exception ex){
                log.error("Error select getListSources : {}, {}", ExceptionUtils.getMessage(ex), ExceptionUtils.getMessage(ex.getCause()));
            }
            return null;
        }
    }
