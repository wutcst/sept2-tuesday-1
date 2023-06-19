package cn.edu.whut.sept.zuul.controller;

import cn.edu.whut.sept.zuul.domain.Result;
import cn.edu.whut.sept.zuul.domain.User;
import cn.edu.whut.sept.zuul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;


@RestController
@RequestMapping("/user")
public class UserController {
   @Value("${security.pubKey}")
    private String pubKey;
   @Value("${security.priKey}")
    private String priKey;
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result userRegister(@RequestBody User u){
        return userService.register(decrypt(u));
    }
    @PostMapping("/login")
    public   Result userLogin(@RequestBody User u){
        return userService.login(decrypt(u));
    }

    @PutMapping("/pwd")
    public Result changePasswd(@RequestBody User u){
        return userService.changePasswd(decrypt(u));
    }
    @PutMapping
    public Result changeUser(@RequestBody User u){
        return userService.changeUser(u);
    }

    @DeleteMapping
    public Result deleteUser(User u){
        return userService.deleteUser(u);
    }

    private User decrypt(User u){
        User res = new User();
        Cipher rsa = null;
        try {
            rsa = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }


        try {
            rsa.init(Cipher.DECRYPT_MODE, getPrivateKey());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            res.setPasswd(new String( rsa.doFinal(Base64.getDecoder().decode(u.getPasswd()))));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        res.setUserName(u.getUserName());
        res.setEmail(u.getEmail());
        res.setId(u.getId());
        return res;
    }

    private PrivateKey getPrivateKey(){
        PKCS8EncodedKeySpec rsaPriKey = new PKCS8EncodedKeySpec(
                Base64.getDecoder().decode(priKey.getBytes(StandardCharsets.UTF_8))
        );
        KeyFactory rsaKeyGen = null;
        try {
            rsaKeyGen = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        PrivateKey key = null;
        try {
            key =rsaKeyGen.generatePrivate(rsaPriKey);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return key;
    }
}
