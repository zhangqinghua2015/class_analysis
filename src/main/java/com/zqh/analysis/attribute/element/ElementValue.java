package com.zqh.analysis.attribute.element;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class ElementValue {

    protected byte[] tag;

    @Override
    public String toString() {
        return "ElementValue{" +
                " tag=" + (char)bytesToInt(tag) + " // " + bytesToHexString(tag) +
                '}';
    }

    public byte[] getTag() {
        return tag;
    }

    public void setTag(byte[] tag) {
        this.tag = tag;
    }
}
