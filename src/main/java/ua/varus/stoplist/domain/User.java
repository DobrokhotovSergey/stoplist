package ua.varus.stoplist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String username;
//    @JsonIgnore
    private String password;
    private String position;
    private String firstname;
    private String lastname;
    private boolean enabled;
    private boolean online;
    private Date lastLoginDate;
    private UserRole userRole;
}
