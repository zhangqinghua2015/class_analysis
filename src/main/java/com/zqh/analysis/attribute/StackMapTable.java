package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: LineNumberTable
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class StackMapTable extends AttributeInfo {

    private byte[] number_of_entries = new byte[2];
    private StackMapFrame[] entries;

    @Override
    public String toString() {
        return "Exceptions{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", number_of_exceptions="  + bytesToInt(number_of_entries) + " // " + bytesToHexString(number_of_entries) +
                ", exceptions_index_table=" + Arrays.toString(entries) +
                '}';
    }

    public byte[] getNumber_of_entries() {
        return number_of_entries;
    }

    public void setNumber_of_entries(byte[] number_of_entries) {
        this.number_of_entries = number_of_entries;
    }

    public StackMapFrame[] getEntries() {
        return entries;
    }

    public void setEntries(StackMapFrame[] entries) {
        this.entries = entries;
    }
}
