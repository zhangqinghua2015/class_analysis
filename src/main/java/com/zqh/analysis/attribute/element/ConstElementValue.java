package com.zqh.analysis.attribute.element;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class ConstElementValue extends ElementValue {

    private byte[] const_value_index = new byte[2];

    @Override
    public String toString() {
        return "ConstElementValue{" +
                " tag=" + (char)bytesToInt(tag) + " // " + bytesToHexString(tag) +
                ", const_value_index=" + bytesToInt(const_value_index) + " // " + bytesToHexString(const_value_index) +
                '}';
    }

    public byte[] getConst_value_index() {
        return const_value_index;
    }

    public void setConst_value_index(byte[] const_value_index) {
        this.const_value_index = const_value_index;
    }
}
