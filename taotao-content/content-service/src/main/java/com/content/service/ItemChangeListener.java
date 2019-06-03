package com.content.service;

import com.taotao.jedis.JedisClient;
import com.taotao.jedis.JedisClientPool;
import com.taotao.mapper.SearchMapper;
import com.taotao.pojo.SearchItem;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ItemChangeListener implements MessageListener {
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private SolrServer solrServer;
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                String text = textMessage.getText();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(text);
                List<SearchItem> searchItems = searchMapper.findSearchItemByDate(date);
                for (SearchItem searchItem:searchItems) {
                    SolrInputDocument document = new SolrInputDocument();
                    document.addField("id", searchItem.getId());
                    document.addField("item_title", searchItem.getTitle());
                    document.addField("item_sell_point", searchItem.getSellPoint());
                    document.addField("item_price", searchItem.getPrice());
                    document.addField("item_image", searchItem.getImage());
                    document.addField("item_category_name", searchItem.getCategoryName());
                    document.addField("item_desc", searchItem.getItemDesc());
                    solrServer.add(document);
                }
                solrServer.commit();
                JedisClientPool jedisClient = new JedisClientPool();
                jedisClient.hdels();
            } catch (JMSException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
