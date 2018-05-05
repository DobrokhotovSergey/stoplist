package ua.varus.stoplist.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPassword {

    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        String pswWithoutHash = "p8uKbSBqEEqauILjGkEmJ";
        //
        System.out.println(passwordEncoder().encode(pswWithoutHash));
        //$2a$10$Teb98Vuq.jhBaz/pfI0p8uKbSBqEEqauILjGkEmJ0l78v0OkHrJRW

//        double a = 1.2;
//        double b = 1;
//
//        System.out.println(1.2 + 1 - 1.2);


    }



}
