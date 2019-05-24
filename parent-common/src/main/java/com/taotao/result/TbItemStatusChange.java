package com.taotao.result;

import java.io.Serializable;
import java.util.List;

public class TbItemStatusChange implements Serializable{
    private int status;
    private List<Long> ids;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
