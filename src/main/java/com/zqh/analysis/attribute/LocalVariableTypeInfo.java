package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class LocalVariableTypeInfo {

    private byte[] start_pc = new byte[2];
    private byte[] length = new byte[2];
    private byte[] name_index = new byte[2];
    private byte[] signature_index = new byte[2];
    private byte[] index = new byte[2];

    @Override
    public String toString() {
        return "LocalVariableTypeInfo{" +
                " start_pc=" + bytesToInt(start_pc) + " // " + bytesToHexString(start_pc) +
                ", length=" + bytesToInt(length) + " // " + bytesToHexString(length) +
                ", name_index="  + bytesToInt(name_index) + " // " + bytesToHexString(name_index) +
                ", signature_index="  + bytesToInt(signature_index) + " // " + bytesToHexString(signature_index) +
                ", index="  + bytesToInt(index) + " // " + bytesToHexString(index) +
                '}';
    }

    public byte[] getStart_pc() {
        return start_pc;
    }

    public void setStart_pc(byte[] start_pc) {
        this.start_pc = start_pc;
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

    public byte[] getSignature_index() {
        return signature_index;
    }

    public void setSignature_index(byte[] signature_index) {
        this.signature_index = signature_index;
    }

    public byte[] getIndex() {
        return index;
    }

    public void setIndex(byte[] index) {
        this.index = index;
    }
}
