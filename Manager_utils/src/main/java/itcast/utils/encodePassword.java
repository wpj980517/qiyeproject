package itcast.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class encodePassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
    }
}
