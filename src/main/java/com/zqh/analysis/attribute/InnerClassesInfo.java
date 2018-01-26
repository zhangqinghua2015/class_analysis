package com.zqh.analysis.attribute;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: InnerClassesInfo
 * @author: qhzhang
 * @date: 2018/01/26 19:15
 * @discription:
 */
public class InnerClassesInfo {

    private byte[] inner_class_info_index = new byte[2];
    private byte[] outer_class_info_index = new byte[2];
    private byte[] inner_name_index = new byte[2];
    private byte[] inner_class_access_flags = new byte[2];

    @Override
    public String toString() {
        return "InnerClassesInfo{" +
                "inner_class_info_index=" + bytesToInt(inner_class_info_index) + " // " + bytesToHexString(inner_class_info_index) +
                ", outer_class_info_index=" + bytesToInt(outer_class_info_index) + " // " + bytesToHexString(outer_class_info_index) +
                ", inner_name_index=" + bytesToInt(inner_name_index) + " // " + bytesToHexString(inner_name_index) +
                ", inner_class_access_flags=" + bytesToInt(inner_class_access_flags) + " // " + bytesToHexString(inner_class_access_flags) +
                '}';
    }

    public byte[] getInner_class_info_index() {
        return inner_class_info_index;
    }

    public void setInner_class_info_index(byte[] inner_class_info_index) {
        this.inner_class_info_index = inner_class_info_index;
    }

    public byte[] getOuter_class_info_index() {
        return outer_class_info_index;
    }

    public void setOuter_class_info_index(byte[] outer_class_info_index) {
        this.outer_class_info_index = outer_class_info_index;
    }

    public byte[] getInner_name_index() {
        return inner_name_index;
    }

    public void setInner_name_index(byte[] inner_name_index) {
        this.inner_name_index = inner_name_index;
    }

    public byte[] getInner_class_access_flags() {
        return inner_class_access_flags;
    }

    public void setInner_class_access_flags(byte[] inner_class_access_flags) {
        this.inner_class_access_flags = inner_class_access_flags;
    }
}
