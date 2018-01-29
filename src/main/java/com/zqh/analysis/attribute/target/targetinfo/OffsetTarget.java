package com.zqh.analysis.attribute.target.targetinfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: OffsetTarget
 * @author: qhzhang
 * @date: 2018/01/29 15:57
 * @discription:
 */
public class OffsetTarget extends TargetInfo {

    private byte[] offset = new byte[2];

    @Override
    public String toString() {
        return "OffsetTarget{" +
                "offset=" + bytesToInt(offset) + " // " + bytesToHexString(offset) +
                '}';
    }

    public byte[] getOffset() {
        return offset;
    }

    public void setOffset(byte[] offset) {
        this.offset = offset;
    }
}
