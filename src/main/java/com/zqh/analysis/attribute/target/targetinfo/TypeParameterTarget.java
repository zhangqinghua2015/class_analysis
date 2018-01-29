package com.zqh.analysis.attribute.target.targetinfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: TypeParameterTarget
 * @author: qhzhang
 * @date: 2018/01/29 15:57
 * @discription:
 */
public class TypeParameterTarget extends TargetInfo {

    private byte[] type_parameter_index = new byte[1];

    @Override
    public String toString() {
        return "TypeParameterTarget{" +
                "type_parameter_index=" + bytesToInt(type_parameter_index) + " // " + bytesToHexString(type_parameter_index) +
                '}';
    }

    public byte[] getType_parameter_index() {
        return type_parameter_index;
    }

    public void setType_parameter_index(byte[] type_parameter_index) {
        this.type_parameter_index = type_parameter_index;
    }

}
