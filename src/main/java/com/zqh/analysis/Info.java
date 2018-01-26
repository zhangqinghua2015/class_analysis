package com.zqh.analysis;

import com.zqh.analysis.attribute.AttributeInfo;

/**
 * @fileName: Info
 * @author: qhzhang
 * @date: 2018/01/26 15:58
 * @discription:
 */
public abstract class Info {


    protected byte[] access_flags = new byte[2];
    protected byte[] attributes_count = new byte[2];
    protected AttributeInfo[] attributes; // length=attributes_count


    public byte[] getAccess_flags() {
        return access_flags;
    }

    public void setAccess_flags(byte[] access_flags) {
        this.access_flags = access_flags;
    }

    public byte[] getAttributes_count() {
        return attributes_count;
    }

    public void setAttributes_count(byte[] attributes_count) {
        this.attributes_count = attributes_count;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }

}
