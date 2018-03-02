package ua.varus.stoplist.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPassword {

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        String pswWithoutHash = "o1m3e54ga8Rty3";
        //
        System.out.println(passwordEncoder().encode(pswWithoutHash));
        //$2a$10$Teb98Vuq.jhBaz/pfI0p8uKbSBqEEqauILjGkEmJ0l78v0OkHrJRW




    }



}
