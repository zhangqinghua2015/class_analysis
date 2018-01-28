package com.zqh.analysis.attribute.element;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class ArrayElementValue extends ElementValue {

    private byte[] num_values = new byte[2];
    private ElementValue[] values;

    @Override
    public String toString() {
        return "ArrayElementValue{" +
                " tag=" + (char)bytesToInt(tag) + bytesToHexString(tag) +
                ", num_values=" + bytesToInt(num_values) + " // " + bytesToHexString(num_values) +
                ", values=" + Arrays.toString(values) +
                '}';
    }

    public byte[] getNum_values() {
        return num_values;
    }

    public void setNum_values(byte[] num_values) {
        this.num_values = num_values;
    }

    public ElementValue[] getValues() {
        return values;
    }

    public void setValues(ElementValue[] values) {
        this.values = values;
    }
}
