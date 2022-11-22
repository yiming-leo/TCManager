package cn.calendo.tcmdistribution.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.digest.HMac;import cn.hutool.crypto.digest.HmacAlgorithm;

import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.springframework.stereotype.Component;



/**
 * 加密技术
 */
@Component
public class Encrypt {

    /**
     * SM4加密
     *
     * @param text 明文
     * @return 密文
     */
    public String SM4Encrypt(String text) {
        SymmetricCrypto sm4 = new SymmetricCrypto("SM4/ECB/PKCS5Padding");
        String encryptHex = sm4.encryptHex(text);
        System.out.println(encryptHex);
        return encryptHex;
    }

    /**
     * SM4解密
     *
     * @param text 密文
     * @return 明文
     */
    public String SM4Decrypt(String text) {
        SymmetricCrypto sm4 = new SymmetricCrypto("SM4/ECB/PKCS5Padding");
        System.out.println(sm4.decryptStr(text, CharsetUtil.CHARSET_UTF_8));
        return sm4.decryptStr(text, CharsetUtil.CHARSET_UTF_8);
    }

    /**
     * AES加密
     *
     * @param text 明文
     * @return 密文
     */
    public String AESEncrypt(String text,SymmetricCrypto aes) {
        String encryptHex = aes.encryptHex(text);
        System.out.println(encryptHex);
        return encryptHex;
    }

    /**
     * AES解密
     *
     * @param text 密文
     * @return 明文
     */
    public String AESDecrypt(String text,SymmetricCrypto aes) {
        String decryptStr = aes.decryptStr(text, CharsetUtil.CHARSET_UTF_8);
        System.out.println(decryptStr);
        return decryptStr;
    }

    /**
     * HMacMD5加密
     *
     * @param text 明文
     * @param key  密钥
     * @return 密文
     */
    public String HMacMD5Encrypt(String text, String key) {
        byte[] keyBytes = key.getBytes();
        HMac hMac = new HMac(HmacAlgorithm.HmacMD5, keyBytes);
        System.out.println("hMac: " + hMac);
        String digestHex = hMac.digestHex(text);
        System.out.println(digestHex);
        return digestHex;
    }

}
