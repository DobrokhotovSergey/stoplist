package ua.varus.stoplist.jdbc;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ua.varus.stoplist.domain.User;
import ua.varus.stoplist.domain.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapperImpl implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(rs.getString("role")));

        UserRole userRole = UserRole.builder()
                .role(rs.getString("role"))
                .userRoleId(rs.getInt("user_role_id"))
                .build();

        User user = User.builder()
                .username(rs.getString("userName"))
                .password(rs.getString("password"))
                .enabled(rs.getBoolean("enabled"))
                .firstname(rs.getString("firstname"))
                .lastname(rs.getString("lastname"))
                .position(rs.getString("position"))
                .userRole(userRole)
                .build();

        return user;
    }
}
