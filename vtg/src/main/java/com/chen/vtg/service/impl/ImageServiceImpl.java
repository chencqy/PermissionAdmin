package com.chen.vtg.service.impl;

import com.chen.vtg.service.ImageService;
import com.chen.vtg.utils.ImageUtil;
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

    @Override
    public String saveImg(MultipartFile image) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime time = LocalDateTime.now();
        String timePath = time.format(formatter);
        String path = "E:\\Program\\image\\" + timePath;

        String imageUrl = null;
        try {
            if (image != null) {
                String fileName = ImageUtil.saveImg(image, path);
                imageUrl = "http://localhost/image/" + timePath + "/" + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageUrl;
    }

    @Override
    public boolean deleteImg(String imageUrl) {
        imageUrl = "E:\\Program" + imageUrl.substring(imageUrl.lastIndexOf("/image/")).replaceAll("/", "\\\\");
        return ImageUtil.deleteImg(imageUrl);
    }
}
