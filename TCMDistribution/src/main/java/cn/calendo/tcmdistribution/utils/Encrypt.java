package cn.calendo.tcmdistribution.utils;

import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 加密技术
 */
@Component
public class Encrypt {

    /**
     * MD5加密
     * @param text
     * @return
     */
    public String MD5Encrypt(String text){
        String md5text = SecureUtil.md5(text);
        System.out.println(md5text);
        return md5text;
    }

    /**
     * MD5解密
     * @param md5text
     * @return
     */
    public String MD5Decrypt(String md5text){
        byte[] decode = SecureUtil.decode(md5text);
        String dec = Arrays.toString(decode);
        System.out.println(dec);
        return dec;
    }

}
