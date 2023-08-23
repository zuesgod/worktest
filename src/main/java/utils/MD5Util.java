package utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zeus
 * @date 2022-09-14 14:53
 **/
public class MD5Util {
    // 将文本转换为32位小写的MD5
    public static String textToMd5L32(String plainText) {
        String result = null;
        // 判断需要转换的文本是否为空
        if (StringUtils.isBlank(plainText)) {
            return null;
        }
        try {
            // 进行实例化和初始化
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 得到一个操作系统默认的字节编码格式的字节数组
            byte[] byteInput = plainText.getBytes();
            // 对得到的字节数组进行处理
            md5.update(byteInput);
            // 进行Hash计算并得到返回结果
            byte[] btResult = md5.digest();
            // 得到进行Hash计算后数据的长度
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : btResult) {
                int bt = b&0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            result = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 将文本转换为32位大写的MD5
    public static String textToMd5U32(String plainText) {
        if (StringUtils.isBlank(plainText)) {
            return null;
        }
        String result = textToMd5L32(plainText);
        result = result.toUpperCase();
        return result;
    }
}
