package com.zqh.analysis.attribute.target.targetinfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: ThrowsTarget
 * @author: qhzhang
 * @date: 2018/01/29 15:57
 * @discription:
 */
public class ThrowsTarget extends TargetInfo {

    private byte[] throws_type_index = new byte[2];

    @Override
    public String toString() {
        return "ThrowsTarget{" +
                "throws_type_index=" + bytesToInt(throws_type_index) + " // " + bytesToHexString(throws_type_index) +
                '}';
    }

    public byte[] getThrows_type_index() {
        return throws_type_index;
    }

    public void setThrows_type_index(byte[] throws_type_index) {
        this.throws_type_index = throws_type_index;
    }
}
