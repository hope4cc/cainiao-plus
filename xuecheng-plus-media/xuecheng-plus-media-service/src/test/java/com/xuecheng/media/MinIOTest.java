package com.xuecheng.media;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import io.minio.*;
import io.minio.errors.MinioException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Mr.M
 * @version 1.0
 * @description 测试minio上传文件、删除文件、查询文件
 * @date 2022/10/13 14:42
 */
public class MinIOTest {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://1.15.87.229:9000")
                            .credentials("minioadmin", "minioadmin")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("testbucket").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("testbucket").build());
            } else {
                System.out.println("Bucket 'public' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("testbucket")
                            .object("test/111.jpg")
                            .filename("/Users/hope4cc/Home/111.jpg")
                            .build());
            System.out.println("上传成功了");
            System.out.println("'/Users/hope4cc/Home/111.jpg' is successfully uploaded as " + "object '111.jpg' to bucket 'public'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }

    static MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://1.15.87.229:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();


    @Test
    public void upload() {
        try {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket("testbucket")
                    .object("111.jpg")//同一个桶内对象名不能重复
                    .filename("/Users/hope4cc/Home/111.jpg")
                    .build();
            //上传
            minioClient.uploadObject(uploadObjectArgs);
            System.out.println("上传成功了");
        } catch (Exception e) {
            System.out.println("上传失败");
        }


    }

    //指定桶内的子目录
    @Test
    public void upload2() {

        try {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket("testbucket")
                    .object("test/111.jpg")//同一个桶内对象名不能重复
                    .filename("/Users/hope4cc/Home/111.jpg")
                    .build();
            //上传
            minioClient.uploadObject(uploadObjectArgs);
            System.out.println("上传成功了");
        } catch (Exception e) {
            System.out.println("上传失败");
        }


    }

    //删除文件
    @Test
    public void delete() {

        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket("testbucket").object("test/111.jpg").build();
            minioClient.removeObject(removeObjectArgs);
            System.out.println("删除成功");
        } catch (Exception e) {
        }

    }

    //查询文件
    @Test
    public void getFile() {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket("testbucket").object("111.jpg").build();
        try (
                FilterInputStream inputStream = minioClient.getObject(getObjectArgs);
                FileOutputStream outputStream = new FileOutputStream(new File("/Users/hope4cc/Home/1111.jpg"));
        ) {

            if (inputStream != null) {
                IOUtils.copy(inputStream, outputStream);
            }
        } catch (Exception e) {
        }

    }

}
