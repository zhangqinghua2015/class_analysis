package com.zqh.analysis.attribute.element;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class ClassElementValue extends ElementValue {

    private byte[] class_info_index = new byte[2];

    @Override
    public String toString() {
        return "ClassElementValue{" +
                " tag=" + (char)bytesToInt(tag) + " // " + bytesToHexString(tag) +
                ", const_value_index=" + bytesToInt(class_info_index) + " // " + bytesToHexString(class_info_index) +
                '}';
    }

    public byte[] getClass_info_index() {
        return class_info_index;
    }

    public void setClass_info_index(byte[] class_info_index) {
        this.class_info_index = class_info_index;
    }
}
