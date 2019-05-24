package com.content.service;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.result.EasyUICatTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUICatTree> getContentCategorys(long parentId) {
        List<TbContentCategory> contentCategorys = tbContentCategoryMapper.getContentCategorys(parentId);
        List<EasyUICatTree> result = new ArrayList<>();
        for (TbContentCategory tbContentCategory : contentCategorys){
            EasyUICatTree easyUICatTree = new EasyUICatTree();
            easyUICatTree.setId(tbContentCategory.getId());
            easyUICatTree.setText(tbContentCategory.getName());
            easyUICatTree.setState(tbContentCategory.getIsParent()?"closed":"open");
            result.add(easyUICatTree);
        }
        return result;
    }
}
