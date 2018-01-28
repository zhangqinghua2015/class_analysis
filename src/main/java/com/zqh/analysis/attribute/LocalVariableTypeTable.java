package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: LocalVariableTypeTable
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class LocalVariableTypeTable extends AttributeInfo {

    private byte[] local_variable_type_table_length = new byte[2];
    private LocalVariableTypeInfo[] local_variable_type_infos;

    @Override
    public String toString() {
        return "LocalVariableTypeTable{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", local_variable_type_table_length="  + bytesToInt(local_variable_type_table_length) + " // " + bytesToHexString(local_variable_type_table_length) +
                ", local_variable_type_infos=" + Arrays.toString(local_variable_type_infos) +
                '}';
    }

    public byte[] getLocal_variable_type_table_length() {
        return local_variable_type_table_length;
    }

    public void setLocal_variable_type_table_length(byte[] local_variable_type_table_length) {
        this.local_variable_type_table_length = local_variable_type_table_length;
    }

    public LocalVariableTypeInfo[] getLocal_variable_type_infos() {
        return local_variable_type_infos;
    }

    public void setLocal_variable_type_infos(LocalVariableTypeInfo[] local_variable_type_infos) {
        this.local_variable_type_infos = local_variable_type_infos;
    }
}
