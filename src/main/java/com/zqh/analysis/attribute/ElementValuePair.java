package com.zqh.analysis.attribute;

import com.zqh.analysis.attribute.element.ElementValue;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class ElementValuePair {

    private byte[] element_name_index = new byte[2];
    private ElementValue element_value;

    @Override
    public String toString() {
        return "ElementValuePair{" +
                " element_name_index=" + bytesToInt(element_name_index) + " // " + bytesToHexString(element_name_index) +
                ", element_value=" + element_value +
                '}';
    }


    public byte[] getElement_name_index() {
        return element_name_index;
    }

    public void setElement_name_index(byte[] element_name_index) {
        this.element_name_index = element_name_index;
    }

    public ElementValue getElement_value() {
        return element_value;
    }

    public void setElement_value(ElementValue element_value) {
        this.element_value = element_value;
    }
}
