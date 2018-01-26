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
public class LineNumberTable extends AttributeInfo {

    private byte[] line_number_table_length = new byte[2];
    private LineNumberInfo[] line_nember_table;

    @Override
    public String toString() {
        return "LineNumberTable{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", line_number_table_length="  + bytesToInt(line_number_table_length) + " // " + bytesToHexString(line_number_table_length) +
                ", line_nember_table=" + Arrays.toString(line_nember_table) +
                '}';
    }

    public byte[] getLine_number_table_length() {
        return line_number_table_length;
    }

    public void setLine_number_table_length(byte[] line_number_table_length) {
        this.line_number_table_length = line_number_table_length;
    }

    public LineNumberInfo[] getLine_nember_table() {
        return line_nember_table;
    }

    public void setLine_nember_table(LineNumberInfo[] line_nember_table) {
        this.line_nember_table = line_nember_table;
    }
}
