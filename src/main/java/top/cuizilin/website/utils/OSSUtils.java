package top.cuizilin.website.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

public class OSSUtils {
    private static final String accessKeyId = "LTAI5tLXcEoox9KeZysjJB2Z";
    private static final String accessKeySecret = "sQ2ccnqmSJDiZ99Fm4ZblF7caNr1v8";
    private static final String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    private static final String bucketName = "cuizilin-bucket-1";
    private static final String imagePath = "images/";

    public static String saveImg(byte[] image){
        OSS OSSClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String imageURL = imagePath + UUID.randomUUID();
        PutObjectResult putObjectResult = OSSClient.putObject(bucketName, imageURL, new ByteArrayInputStream(image));
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 180);
        URL url = OSSClient.generatePresignedUrl(bucketName, imageURL, expiration);
        OSSClient.shutdown();
        return url.toString();
    }
}
