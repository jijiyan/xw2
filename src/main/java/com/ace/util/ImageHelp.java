package com.ace.util;

import com.ace.entity.file.Image;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by bamboo on 17-11-28.
 */
public class ImageHelp {

    public static Set<Image> save2Disk(MultipartFile files[], String webUploadPath, String organizationId, String departmentId, String userId) {
        Set<Image> imageSet = new HashSet<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                if (file.getContentType().contains("image")) {
                    try {
                        String fileName = file.getOriginalFilename();
                        String extensionName = StringUtils.substringAfter(fileName, ".");
                        String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                        String dataDirectory = organizationId.concat(File.separator).concat(departmentId).concat(File.separator).concat(userId).concat(File.separator);
                        String filePath = webUploadPath.concat(dataDirectory);
                        File dest = new File(filePath, newFileName);
                        if (!dest.getParentFile().exists()) {
                            dest.getParentFile().mkdirs();
                        }
                        file.transferTo(dest);
                        String imageUrl = "/api/image/".concat(dataDirectory).concat(newFileName);
                        Image image = new Image();
                        image.setUrl(userId);
                        image.setUrl(imageUrl);
                        image.setUserId(userId);
                        imageSet.add(image);
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
        }
        return imageSet;
    }

    /*public static Set<Image> save2Qiniu(MultipartFile files[], String webUploadPath, String organizationId, String departmentId, String userId) {
        Set<Image> imageSet = new HashSet<>();
        String keyPrefix = organizationId + "_" + departmentId + "_" + userId + "_" + String.valueOf(System.currentTimeMillis());
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (!file.isEmpty()) {
                if (file.getContentType().contains("image")) {
                    Image image = new Image();
                    image.setUrl(userId);
                    image.setUrl("http://orahxdcid.bkt.clouddn.com/".concat(keyPrefix).concat(String.valueOf(i)));
                    image.setUserId(userId);
                    imageSet.add(image);
                }
            }
        }
        System.out.println(1);
        uploadQiniu(keyPrefix,files);
        System.out.println(2);
        return imageSet;
    }
    private static void uploadQiniu(String keyPrefix, MultipartFile files[]) {
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (!file.isEmpty()) {
                if (file.getContentType().contains("image")) {
                    try {
                        // Lambda Runnable
                        qiNiu.upload(ByteStreams.toByteArray(file.getInputStream()), keyPrefix.concat(String.valueOf(i)));
                    } catch (Exception e) {
                    }
                }
            }
        }
        System.out.println(3);
    }*/
}
