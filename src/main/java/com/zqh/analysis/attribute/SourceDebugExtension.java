package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: SourceDebugExtension
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class SourceDebugExtension extends AttributeInfo {

    private byte[] debug_extension;

    @Override
    public String toString() {
        return "SourceDebugExtension{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", debug_extension="  + new String(debug_extension) + " // " + bytesToHexString(debug_extension) +
                '}';
    }

    public byte[] getDebug_extension() {
        return debug_extension;
    }

    public void setDebug_extension(byte[] debug_extension) {
        this.debug_extension = debug_extension;
    }
}
