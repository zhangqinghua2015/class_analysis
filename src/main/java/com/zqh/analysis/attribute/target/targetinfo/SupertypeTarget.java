package com.zqh.analysis.attribute.target.targetinfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: SupertypeTarget
 * @author: qhzhang
 * @date: 2018/01/29 15:57
 * @discription:
 */
public class SupertypeTarget extends TargetInfo {
    
    private byte[] supertype_index = new byte[2];

    @Override
    public String toString() {
        return "SupertypeTarget{" +
                "supertype_index=" + bytesToInt(supertype_index) + " // " + bytesToHexString(supertype_index) +
                '}';
    }

    public byte[] getSupertype_index() {
        return supertype_index;
    }

    public void setSupertype_index(byte[] supertype_index) {
        this.supertype_index = supertype_index;
    }

}
