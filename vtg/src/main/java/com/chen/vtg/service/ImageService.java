package com.chen.vtg.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {

    /**
     *
     * @param multipartFile
     * @return String
     */
    String saveImg(MultipartFile multipartFile);

    /**
     *
     * @param imageUrl
     * @return boolean
     */
    boolean deleteImg(String imageUrl);

    /**
     *
     * @param multipartFile
     * @return
     */
    String uploadImgWithAliyunOSS(MultipartFile multipartFile);

    /**
     *
     * @param fileUrl
     * @return
     */
    void deleteImgWithAliyunOSS(String fileUrl);
}
