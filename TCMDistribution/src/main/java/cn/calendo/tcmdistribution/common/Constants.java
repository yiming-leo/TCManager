package cn.calendo.tcmdistribution.common;

/**
 * 常量群
 */
public class Constants {
    /**
     * 医院发往邮政的接口地址
     */
    public static final String POSTER_URL_POST_NORMAL = "http://localhost:8081/poster/from_tcmd";
    /**
     * 医院发往邮政的召回的接口地址
     */
    public static final String POSTER_URL_POST_REJECT = "http://localhost:8081/poster/from_tcmd/reject";
    /**
     * AES加密算法密钥
     */
    public static final String AES_KEY = "0123456789012345";
}
