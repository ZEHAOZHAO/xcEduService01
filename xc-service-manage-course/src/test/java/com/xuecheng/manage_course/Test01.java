package com.xuecheng.manage_course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

/**
 * ${Author}: jason.zhao
 * 2019/5/18 22:29
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test01 {
    @Test
    public  void test01() {
        String token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiaXRjYXN0In0.lQOqL1s4DpDHROUAibkz6EMf6hcM7HmTPgmg-SlkacVoQAV7y3XQ7LXxiua6SJlN_uNX_EFjzIshEg_kyy972DtymtRMc2NIO5HzIF5I4oQCxNPsJdhu6qQni6sTas3q0JbAarMZSajDX7HhzVSYWPQJCussA4e1r9oFxDcoAo6TEAXOW8gRHzNIygQz1yCj6mdf4UOHI070kRy7f3BdhmrUJdOuDIMoRBYS4WsEOibAU1UCNPaJAXpZC0ihrtdY7SCg1N43fimeFOHrfpLb6OmRF7v7uvGMgrhg9JIYDbJ6nbode5OJkNceRx8QUICre2yKAe0ctlvXO0REf6OpRA";
        ClassPathResource classPathResource = new ClassPathResource("publickey.txt");
        try {
            InputStream inputStream = classPathResource.getInputStream();
            byte[] b = new byte[2000];
            int read = inputStream.read(b);
            while (read>-1){
                read = inputStream.read(b);
            }
            String publicKey = new String(b);
            Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey));
            String encoded = jwt.getEncoded();
            String claims = jwt.getClaims();
            System.out.println(encoded);
            System.out.println(claims);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
