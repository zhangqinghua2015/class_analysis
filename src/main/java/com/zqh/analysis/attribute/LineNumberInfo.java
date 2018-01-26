package com.zqh.analysis.attribute;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: LineNumberInfo
 * @author: qhzhang
 * @date: 2018/01/26 18:17
 * @discription:
 */
public class LineNumberInfo {

    private byte[] star_pc = new byte[2];
    private byte[] line_number = new byte[2];

    @Override
    public String toString() {
        return "LineNumberInfo{" +
                "star_pc=" + bytesToInt(star_pc) + " // " + bytesToHexString(star_pc) +
                ", line_number=" + bytesToInt(line_number) + " // " + bytesToHexString(line_number) +
                '}';
    }

    public byte[] getStar_pc() {
        return star_pc;
    }

    public void setStar_pc(byte[] star_pc) {
        this.star_pc = star_pc;
    }

    public byte[] getLine_number() {
        return line_number;
    }

    public void setLine_number(byte[] line_number) {
        this.line_number = line_number;
    }
}
