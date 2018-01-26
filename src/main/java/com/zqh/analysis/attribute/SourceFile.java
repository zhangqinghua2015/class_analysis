package com.zqh.analysis.attribute;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: SourceFile
 * @author: qhzhang
 * @date: 2018/01/26 19:08
 * @discription:
 */
public class SourceFile extends AttributeInfo {

    private byte[] sourcefile_index = new byte[2];

    @Override
    public String toString() {
        return "SourceFile{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", sourcefile_index=" + bytesToInt(sourcefile_index) + " // " + bytesToHexString(sourcefile_index) +
                '}';
    }

    public byte[] getSourcefile_index() {
        return sourcefile_index;
    }

    public void setSourcefile_index(byte[] sourcefile_index) {
        this.sourcefile_index = sourcefile_index;
    }
}
