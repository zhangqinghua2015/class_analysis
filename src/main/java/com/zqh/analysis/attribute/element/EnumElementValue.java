package com.zqh.analysis.attribute.element;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class EnumElementValue extends ElementValue {

    private byte[] type_name_index = new byte[2];
    private byte[] const_name_index = new byte[2];

    @Override
    public String toString() {
        return "EnumElementValue{" +
                " tag=" + (char)bytesToInt(tag) + " // " + bytesToHexString(tag) +
                ", type_name_index=" + bytesToInt(type_name_index) + " // " + bytesToHexString(type_name_index) +
                ", const_name_index=" + bytesToInt(const_name_index) + " // " + bytesToHexString(const_name_index) +
                '}';
    }

    public byte[] getType_name_index() {
        return type_name_index;
    }

    public void setType_name_index(byte[] type_name_index) {
        this.type_name_index = type_name_index;
    }

    public byte[] getConst_name_index() {
        return const_name_index;
    }

    public void setConst_name_index(byte[] const_name_index) {
        this.const_name_index = const_name_index;
    }
}
