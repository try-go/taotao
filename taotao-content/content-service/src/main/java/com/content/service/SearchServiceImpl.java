package com.content.service;

import com.taotao.mapper.SearchMapper;
import com.taotao.pojo.SearchItem;
import com.taotao.result.SearchResult;
import com.taotao.result.TaotaoResult;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public TaotaoResult updateIndex() {
        List<SearchItem> searchItems = searchMapper.findSearchItems();
        try {
            for (SearchItem item : searchItems) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", item.getId());
                document.addField("item_title", item.getTitle());
                document.addField("item_sell_point", item.getSellPoint());
                document.addField("item_price", item.getPrice());
                document.addField("item_image", item.getImage());
                document.addField("item_category_name", item.getCategoryName());
                document.addField("item_desc", item.getItemDesc());
                solrServer.add(document);
            }
            solrServer.commit();
            return TaotaoResult.ok();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SearchResult getSearchItem(String queryString, int page, int rows) {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString);
        query.setStart((page-1)*rows);
        query.setRows(rows);
        query.set("df","item_title");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        SearchResult result = new SearchResult();
        try {
            QueryResponse response = solrServer.query(query);
            SolrDocumentList solrDocumentList = response.getResults();
            result.setRecordCount(response.getResults().getNumFound());
            List<SearchItem> itemList = new ArrayList<>();
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            for (SolrDocument solrDocument : solrDocumentList) {
                SearchItem item = new SearchItem();
                item.setId(Long.parseLong((String) solrDocument.get("id")));
                item.setCategoryName((String) solrDocument.get("item_category_name"));
                item.setImage((String) solrDocument.get("item_image"));
                item.setPrice((Long) solrDocument.get("item_price") );
                item.setSellPoint((String) solrDocument.get("item_sell_point"));
                //取高亮显示
                List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
                //有高亮显示的内容时。
                if (list != null && list.size() > 0) {
                    item.setTitle(list.get(0));
                } else {
                    item.setTitle((String) solrDocument.get("item_title"));
                }
                //添加到商品列表
                itemList.add(item);
            }
            result.setItemList(itemList);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        long recordCount = result.getRecordCount();
        long pageCount = recordCount / rows;
        if (recordCount % rows > 0) {
            pageCount++;
        }
        result.setPageCount(pageCount);
        return result;
    }

}
