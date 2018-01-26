package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: InnerClasses
 * @author: qhzhang
 * @date: 2018/01/26 19:12
 * @discription:
 */
public class InnerClasses extends AttributeInfo {

    private byte[] number_of_classes = new byte[2];
    private InnerClassesInfo[] inner_classes;

    @Override
    public String toString() {
        return "InnerClasses{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", number_of_classes=" + bytesToInt(number_of_classes) + " // " + bytesToHexString(number_of_classes) +
                ", inner_classes=" + Arrays.toString(inner_classes) +
                '}';
    }

    public byte[] getNumber_of_classes() {
        return number_of_classes;
    }

    public void setNumber_of_classes(byte[] number_of_classes) {
        this.number_of_classes = number_of_classes;
    }

    public InnerClassesInfo[] getInner_classes() {
        return inner_classes;
    }

    public void setInner_classes(InnerClassesInfo[] inner_classes) {
        this.inner_classes = inner_classes;
    }
}
