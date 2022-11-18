package cn.calendo.tcmpost.common;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.springframework.stereotype.Component;

@Component
public class Encrypt {

    /**
     * AES加密
     *
     * @param text 明文
     * @return 密文
     */
    public String AESEncrypt(String text, SymmetricCrypto aes) {
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
}
