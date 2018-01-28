package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: EnclosingMethod
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class EnclosingMethod extends AttributeInfo {

    private byte[] class_index = new byte[2];
    private byte[] method_index = new byte[2];

    @Override
    public String toString() {
        return "EnclosingMethod{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", class_index=" + bytesToInt(class_index) + " // " + bytesToHexString(class_index) +
                ", method_index=" + bytesToInt(method_index) + " // " + bytesToHexString(method_index) +
                '}';
    }

    public byte[] getClass_index() {
        return class_index;
    }

    public void setClass_index(byte[] class_index) {
        this.class_index = class_index;
    }

    public byte[] getMethod_index() {
        return method_index;
    }

    public void setMethod_index(byte[] method_index) {
        this.method_index = method_index;
    }
}
