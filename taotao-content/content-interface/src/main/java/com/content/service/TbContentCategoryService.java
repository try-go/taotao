package com.content.service;

import com.taotao.result.EasyUICatTree;
import com.taotao.result.TaotaoResult;

import java.util.List;

public interface TbContentCategoryService {
    List<EasyUICatTree> getContentCategorys(long parentId);

    /**
     * 增加一个内容分类，要生产sortOrder，isParent，created，updated
     * 对父类更新，若父类是文件，就把他更新成文件夹
     * @param parentId
     * @param name
     * @return
     */
    TaotaoResult tbContentCategoryCreate(Long parentId,String name);

    /**
     * 更新内容分类，要生产updated
     * @param id
     * @param name
     */
    void tbContentCategoryUpdateName(Long id,String name);

    /**
     * 删除内容分类
     * 判断父级是否还有子分类，若没有子分类，更新父级的打开状态isParent
     * @param parentId
     * @param id
     */
    void tbContentCategoryDelete(Long parentId,Long id);
}
