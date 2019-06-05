package com.content.service;

import com.taotao.mapper.SearchMapper;
import com.taotao.pojo.SearchItem;
import com.taotao.result.FtpUtil;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemChangeFtlListener implements MessageListener {
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


    @Autowired
    private SearchMapper searchMapper;
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                String text = textMessage.getText();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(text);
                List<SearchItem> searchItems = searchMapper.findSearchItemByDate(date);
                Configuration configuration = new Configuration(Configuration.getVersion());
                configuration.setDirectoryForTemplateLoading(new File("E:\\ftl"));
                configuration.setDefaultEncoding("UTF-8");
                Template template = configuration.getTemplate("item.ftl");
                Map map = new HashMap();
                map.put("item",searchItems.get(0));
                String newFile = "E:\\ftl\\static\\"+searchItems.get(0).getId()+".html";
                Writer writer = new FileWriter(new File(newFile));
                template.process(map,writer);
                writer.close();
                //SFTP上传
                String filepath = "";
                String newName = searchItems.get(0).getId()+".html";
                FileInputStream fis = new FileInputStream(new File(newFile));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len;
                byte[] buffer = new byte[1024];
                while ((len = fis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                byte[] bytes = baos.toByteArray();
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                FtpUtil.uploadFileSftp(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FILI_UPLOAD_PATH, filepath, newName, inputStream);
                fis.close();
                baos.close();
            } catch (TemplateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
