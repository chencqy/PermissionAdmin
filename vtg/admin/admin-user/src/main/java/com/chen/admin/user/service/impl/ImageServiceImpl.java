package com.chen.admin.user.service.impl;

import com.chen.admin.user.service.ImageService;
import com.chen.admin.user.exception.ImgException;
import com.chen.admin.user.utils.ImageUtil;
import com.chen.admin.user.utils.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author : Chen
 * @Date : Create in 2019/6/24 19:43
 * @Description :
 * @Modify by :
 */
@Service
public class ImageServiceImpl implements ImageService {

    private static final String PRODUCTION_PATH = "E:\\Program\\image\\";
    private static final String PRODUCTION_IMAGE_URL = "http://localhost/image/";
    private static final String DEVELOPMENT_PATH = "/home/chen/image/";
    private static final String DEVELOPMENT_IMAGE_URL = "http://47.108.64.204/home/chen/image/";

    @Autowired
    OSSClientUtil ossClientUtil;

    @Override
    public String saveImg(MultipartFile image) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime time = LocalDateTime.now();
        String timePath = time.format(formatter);
        // String path = PRODUCTION_PATH + timePath;
        String path = DEVELOPMENT_PATH + timePath;

        String imageUrl = null;
        try {
            if (image != null) {
                String fileName = ImageUtil.saveImg(image, path);
                // imageUrl = PRODUCTION_IMAGE_URL + timePath + "/" + fileName;
                imageUrl = DEVELOPMENT_IMAGE_URL + timePath + "/" + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageUrl;
    }

    @Override
    public boolean deleteImg(String imageUrl) {
        // imageUrl = "E:\\Program" + imageUrl.substring(imageUrl.lastIndexOf("/image/")).replaceAll("/", "\\\\");
        return ImageUtil.deleteImg(imageUrl);
    }

    @Override
    public String uploadImgWithAliyunOSS(MultipartFile file) {

        if (file == null || file.getSize() <= 0) {
            throw new ImgException("file不能为空");
        }
        String name = ossClientUtil.uploadImg2Oss(file);
        String imgUrl = ossClientUtil.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];
    }

    @Override
    public void deleteImgWithAliyunOSS(String fileUrl) {
        ossClientUtil.deleteFileOSS(fileUrl);
    }


}
