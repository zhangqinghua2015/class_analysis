package com.zqh.analysis.attribute.target.targetinfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: FormalParameterTarget
 * @author: qhzhang
 * @date: 2018/01/29 15:57
 * @discription:
 */
public class FormalParameterTarget extends TargetInfo {

    private byte[] formal_parameter_index = new byte[1];

    @Override
    public String toString() {
        return "FormalParameterTarget{" +
                "formal_parameter_index=" + bytesToInt(formal_parameter_index) + " // " + bytesToHexString(formal_parameter_index) +
                '}';
    }

    public byte[] getFormal_parameter_index() {
        return formal_parameter_index;
    }

    public void setFormal_parameter_index(byte[] formal_parameter_index) {
        this.formal_parameter_index = formal_parameter_index;
    }

}
