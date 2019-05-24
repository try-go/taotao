package com.taotao.service.impl;

import com.taotao.result.FtpUtil;
import com.taotao.result.IDUtils;
import com.taotao.result.PictureResult;
import com.taotao.service.PictureService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Calendar;

@Service
public class PictureServiceImpl implements PictureService{
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FILI_UPLOAD_PATH}")
    private String FILI_UPLOAD_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public PictureResult uploadFile(byte[] bytes, String name) {
        PictureResult pictureResult = new PictureResult();
        try{
            String newName = IDUtils.genImageName() + name.substring(name.lastIndexOf("."));
            Calendar cal = Calendar.getInstance();
            String filepath = cal.get(cal.YEAR) + "/" + (cal.get(cal.MONTH)+1) + "/" + cal.get(cal.DATE);
            //转换二进制流
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FILI_UPLOAD_PATH, filepath, newName, inputStream);
            pictureResult.setError(0);
            pictureResult.setUrl(IMAGE_BASE_URL+"/"+filepath+"/"+newName);
            return pictureResult;
        }catch (Exception e){
            pictureResult.setError(1);
            pictureResult.setMessage("上传错误");
            return pictureResult;
        }
    }
}
