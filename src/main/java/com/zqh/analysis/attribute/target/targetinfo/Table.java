package com.zqh.analysis.attribute.target.targetinfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: Table
 * @author: qhzhang
 * @date: 2018/01/29 16:09
 * @discription:
 */
public class Table {

    private byte[] start_pc = new byte[2];
    private byte[] length = new byte[2];
    private byte[] index = new byte[2];

    @Override
    public String toString() {
        return "Table{" +
                "start_pc=" + bytesToInt(start_pc) + " // " + bytesToHexString(start_pc) +
                ", length=" + bytesToInt(length) + " // " + bytesToHexString(length) +
                ", index=" + bytesToInt(index) + " // " + bytesToHexString(index) +
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

    public byte[] getIndex() {
        return index;
    }

    public void setIndex(byte[] index) {
        this.index = index;
    }
}
