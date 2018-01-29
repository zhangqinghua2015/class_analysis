package com.zqh.analysis.attribute.target.targetinfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: TypeParameterBoundTarget
 * @author: qhzhang
 * @date: 2018/01/29 15:57
 * @discription:
 */
public class TypeParameterBoundTarget extends TargetInfo {

    private byte[] type_parameter_index = new byte[1];
    private byte[] bound_index = new byte[1];

    @Override
    public String toString() {
        return "TypeParameterBoundTarget{" +
                "type_parameter_index=" + bytesToInt(type_parameter_index) + " // " + bytesToHexString(type_parameter_index) +
                "bound_index=" + bytesToInt(bound_index) + " // " + bytesToHexString(bound_index) +
                '}';
    }

    public byte[] getType_parameter_index() {
        return type_parameter_index;
    }

    public void setType_parameter_index(byte[] type_parameter_index) {
        this.type_parameter_index = type_parameter_index;
    }

    public byte[] getBound_index() {
        return bound_index;
    }

    public void setBound_index(byte[] bound_index) {
        this.bound_index = bound_index;
    }
}
