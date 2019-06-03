package com.content.service;

import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.result.EasyUICatTree;
import com.taotao.result.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
            easyUICatTree.setParentId(tbContentCategory.getParentId());
            result.add(easyUICatTree);
        }
        return result;
    }

    @Override
    public TaotaoResult tbContentCategoryCreate(Long parentId, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        tbContentCategory.setIsParent(false);
        Date date = new Date();
        tbContentCategory.setCreated(date);
        tbContentCategory.setUpdated(date);
        tbContentCategoryMapper.addContentCategory(tbContentCategory);
        TbContentCategory contentCategoryOfParent = tbContentCategoryMapper.findContentCategoryById(parentId);
        if(!contentCategoryOfParent.getIsParent()){
            contentCategoryOfParent.setIsParent(true);
            tbContentCategoryMapper.updateContentCategoryIsParent(contentCategoryOfParent);
        }
        return TaotaoResult.ok(tbContentCategory);
    }

    @Override
    public void tbContentCategoryUpdateName(Long id, String name) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(id);
        tbContentCategory.setName(name);
        tbContentCategory.setUpdated(new Date());
        tbContentCategoryMapper.updateContentCategoryName(tbContentCategory);
    }

    @Override
    public void tbContentCategoryDelete(Long parentId, Long id) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(id);
        Date date = new Date();
        tbContentCategory.setUpdated(date);
        tbContentCategory.setStatus(2);
        tbContentCategoryMapper.updateContentCategoryStatus(tbContentCategory);
        List<TbContentCategory> contentCategorysOfParent = tbContentCategoryMapper.getContentCategorys(parentId);
        if(contentCategorysOfParent.size()==0){
            TbContentCategory tbContentCategoryOfParent = new TbContentCategory();
            tbContentCategoryOfParent.setId(parentId);
            tbContentCategoryOfParent.setUpdated(date);
            tbContentCategoryOfParent.setIsParent(false);
            tbContentCategoryMapper.updateContentCategoryIsParent(tbContentCategoryOfParent);
        }
    }
}
