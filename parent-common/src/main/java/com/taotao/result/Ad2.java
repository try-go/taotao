package com.taotao.result;

import java.io.Serializable;

public class Ad2 implements Serializable{
    private String alt;
    private String href;
    private int index;
    private String src;
    private String ext;

    @Override
    public String toString() {
        return "Ad2{" +
                "alt='" + alt + '\'' +
                ", href='" + href + '\'' +
                ", index=" + index +
                ", src='" + src + '\'' +
                ", ext='" + ext + '\'' +
                '}';
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
