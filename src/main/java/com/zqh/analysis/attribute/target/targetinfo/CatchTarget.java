package com.zqh.analysis.attribute.target.targetinfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: CatchTarget
 * @author: qhzhang
 * @date: 2018/01/29 15:57
 * @discription:
 */
public class CatchTarget extends TargetInfo {

    private byte[] exception_table_index = new byte[2];

    @Override
    public String toString() {
        return "CatchTarget{" +
                "exception_table_index=" + bytesToInt(exception_table_index) + " // " + bytesToHexString(exception_table_index) +
                '}';
    }

    public byte[] getException_table_index() {
        return exception_table_index;
    }

    public void setException_table_index(byte[] exception_table_index) {
        this.exception_table_index = exception_table_index;
    }
}
