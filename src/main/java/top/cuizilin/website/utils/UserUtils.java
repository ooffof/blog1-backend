package top.cuizilin.website.utils;


import org.springframework.util.DigestUtils;

import java.io.*;
import java.net.URL;

public class UserUtils {
    public static final String salt = "springbootyyds";
    /**
     * 随机获取用户头像
     */
    public static byte[] getUserAvatar() throws IOException {
        URL url = new URL(Constants.avatarUrl);
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;

        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        byte[] context=out.toByteArray();
        in.close();
        out.close();
        return context;
    }

    public static String getMD5Password(String originPassword){
        return DigestUtils.md5DigestAsHex((originPassword+salt).getBytes());
    }
}
