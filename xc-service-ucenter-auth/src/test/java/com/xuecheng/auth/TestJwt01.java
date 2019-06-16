package com.xuecheng.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * ${Author}: jason.zhao
 * 2019/5/18 20:44
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJwt01 {
    @Test
    public void  test(){
        String keyLocation = "xc.keystore";
        String keyStore_password = "xuechengkeystore";
        ClassPathResource resource = new ClassPathResource(keyLocation);
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(resource,keyStore_password.toCharArray());
        String keyPassword = "xuecheng";
        String alies = "xckey";
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alies, keyPassword.toCharArray());
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
        //jwt令牌的内容
        Map<String,String> body = new HashMap<>();
        body.put("name","itcast");
        String bodyString = JSON.toJSONString(body);
        Jwt jwt = JwtHelper.encode(bodyString, new RsaSigner(aPrivate));
        String encoded = jwt.getEncoded();
        System.out.println(encoded);

    }

}
