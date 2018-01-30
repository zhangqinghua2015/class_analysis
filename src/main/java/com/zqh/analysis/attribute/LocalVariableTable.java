package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: LocalVariableTable
 * @author: qhzhang
 * @date: 2018/01/26 18:55
 * @discription:
 */
public class LocalVariableTable extends AttributeInfo {

    private byte[] local_variable_table_length = new byte[2];
    private LocalVariableInfo[] local_variable_table;

    @Override
    public String toString() {
        return "LocalVariableTable{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", local_variable_table_length="  + bytesToInt(local_variable_table_length) + " // " + bytesToHexString(local_variable_table_length) +
                ", local_variable_table=" + Arrays.toString(local_variable_table) +
                '}';
    }

    public byte[] getLocal_variable_table_length() {
        return local_variable_table_length;
    }

    public void setLocal_variable_table_length(byte[] local_variable_table_length) {
        this.local_variable_table_length = local_variable_table_length;
    }

    public LocalVariableInfo[] getLocal_variable_table() {
        return local_variable_table;
    }

    public void setLocal_variable_table(LocalVariableInfo[] local_variable_table) {
        this.local_variable_table = local_variable_table;
    }
}
