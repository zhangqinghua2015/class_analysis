package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: Signature
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class Signature extends AttributeInfo {

    private byte[] signature_index = new byte[2];

    @Override
    public String toString() {
        return "Signature{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", signature_index="  + bytesToInt(signature_index) + " // " + bytesToHexString(signature_index) +
                '}';
    }

    public byte[] getSignature_index() {
        return signature_index;
    }

    public void setSignature_index(byte[] signature_index) {
        this.signature_index = signature_index;
    }
}
