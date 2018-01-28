package com.zqh.analysis.attribute;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class ExceptionIndexTable {

    private byte[] index = new byte[2];



    public byte[] getIndex() {
        return index;
    }

    public void setIndex(byte[] index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "ExceptionIndexTable{" +
                " attribute_name_index=" + bytesToInt(index) + " // " + bytesToHexString(index) +
                '}';
    }
}
