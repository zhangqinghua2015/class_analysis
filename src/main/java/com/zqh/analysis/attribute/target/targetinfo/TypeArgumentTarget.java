package com.zqh.analysis.attribute.target.targetinfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: TypeArgumentTarget
 * @author: qhzhang
 * @date: 2018/01/29 15:57
 * @discription:
 */
public class TypeArgumentTarget extends TargetInfo {

    private byte[] offset = new byte[2];
    private byte[] type_argument_index = new byte[1];

    @Override
    public String toString() {
        return "TypeArgumentTarget{" +
                "offset=" + bytesToInt(offset) + " // " + bytesToHexString(offset) +
                ", type_argument_index=" + bytesToInt(type_argument_index) + " // " + bytesToHexString(type_argument_index) +
                '}';
    }

    public byte[] getOffset() {
        return offset;
    }

    public void setOffset(byte[] offset) {
        this.offset = offset;
    }

    public byte[] getType_argument_index() {
        return type_argument_index;
    }

    public void setType_argument_index(byte[] type_argument_index) {
        this.type_argument_index = type_argument_index;
    }
}
