package com.zqh.analysis.attribute;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: LocalVariableInfo
 * @author: qhzhang
 * @date: 2018/01/26 18:57
 * @discription:
 */
public class LocalVariableInfo {

    private byte[] star_pc = new byte[2];
    private byte[] length = new byte[2];
    private byte[] name_index = new byte[2];
    private byte[] descriptor_index = new byte[2];
    private byte[] index = new byte[2];

    @Override
    public String toString() {
        return "LocalVariableInfo{" +
                "star_pc=" + bytesToInt(star_pc) + " // " + bytesToHexString(star_pc) +
                ", length=" + bytesToInt(length) + " // " + bytesToHexString(length) +
                ", name_index=" + bytesToInt(name_index) + " // " + bytesToHexString(name_index) +
                ", descriptor_index=" + bytesToInt(descriptor_index) + " // " + bytesToHexString(descriptor_index) +
                ", index=" + bytesToInt(index) + " // " + bytesToHexString(index) +
                '}';
    }

    public byte[] getStar_pc() {
        return star_pc;
    }

    public void setStar_pc(byte[] star_pc) {
        this.star_pc = star_pc;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getName_index() {
        return name_index;
    }

    public void setName_index(byte[] name_index) {
        this.name_index = name_index;
    }

    public byte[] getDescriptor_index() {
        return descriptor_index;
    }

    public void setDescriptor_index(byte[] descriptor_index) {
        this.descriptor_index = descriptor_index;
    }

    public byte[] getIndex() {
        return index;
    }

    public void setIndex(byte[] index) {
        this.index = index;
    }
}
