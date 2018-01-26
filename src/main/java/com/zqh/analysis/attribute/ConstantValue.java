package com.zqh.analysis.attribute;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: SourceFile
 * @author: qhzhang
 * @date: 2018/01/26 19:08
 * @discription:
 */
public class ConstantValue extends AttributeInfo {

    private byte[] constantvalue_index = new byte[2];

    @Override
    public String toString() {
        return "ConstantValue{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", constantvalue_index=" + bytesToInt(constantvalue_index) + " // " + bytesToHexString(constantvalue_index) +
                '}';
    }

    public byte[] getConstantvalue_index() {
        return constantvalue_index;
    }

    public void setConstantvalue_index(byte[] constantvalue_index) {
        this.constantvalue_index = constantvalue_index;
    }
}
