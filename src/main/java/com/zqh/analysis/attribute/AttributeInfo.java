package com.zqh.analysis.attribute;

import com.zqh.analysis.Info;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: AttributeInfo
 * @author: qhzhang
 * @date: 2018/01/26 15:36
 * @discription:
 */
public class AttributeInfo extends Info {

    protected String name;
    protected byte[] attribute_name_index; // = new byte[2];
    protected byte[] attribute_length = new byte[4];
    protected byte[] info; // byte[attribute_length]

    @Override
    public String toString() {
        return name + "{" +
                ", \n                            attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", \n                            attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", \n                            info=" + new String(info) + " // " + bytesToHexString(info) +
                "\n                       }";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAttribute_name_index() {
        return attribute_name_index;
    }

    public void setAttribute_name_index(byte[] attribute_name_index) {
        this.attribute_name_index = attribute_name_index;
    }

    public byte[] getAttribute_length() {
        return attribute_length;
    }

    public void setAttribute_length(byte[] attribute_length) {
        this.attribute_length = attribute_length;
    }

    public byte[] getInfo() {
        return info;
    }

    public void setInfo(byte[] info) {
        this.info = info;
    }
}

