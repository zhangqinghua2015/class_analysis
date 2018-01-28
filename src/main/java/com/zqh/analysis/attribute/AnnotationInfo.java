package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class AnnotationInfo {

    private byte[] type_index = new byte[2];
    private byte[] num_element_value_pairs = new byte[2];
    private ElementValuePair[] element_value_pairs;

    @Override
    public String toString() {
        return "AnnotationInfo{" +
                " type_index=" + bytesToInt(type_index) + " // " + bytesToHexString(type_index) +
                ", num_element_value_pairs=" + bytesToInt(num_element_value_pairs) + " // " + bytesToHexString(num_element_value_pairs) +
                ", element_value_pairs=" + Arrays.toString(element_value_pairs) +
                '}';
    }

    public byte[] getType_index() {
        return type_index;
    }

    public void setType_index(byte[] type_index) {
        this.type_index = type_index;
    }

    public byte[] getNum_element_value_pairs() {
        return num_element_value_pairs;
    }

    public void setNum_element_value_pairs(byte[] num_element_value_pairs) {
        this.num_element_value_pairs = num_element_value_pairs;
    }

    public ElementValuePair[] getElement_value_pairs() {
        return element_value_pairs;
    }

    public void setElement_value_pairs(ElementValuePair[] element_value_pairs) {
        this.element_value_pairs = element_value_pairs;
    }
}
