package com.content.service;

import com.taotao.result.SearchResult;
import com.taotao.result.TaotaoResult;

public interface SearchService {
    TaotaoResult updateIndex();
    SearchResult getSearchItem(String queryString,int page,int rows);
}
