package com.zqh.analysis.attribute;

import com.zqh.analysis.attribute.element.ElementValue;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: AnnotationsDefault
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class AnnotationsDefault extends AttributeInfo {

    protected ElementValue default_value;

    @Override
    public String toString() {
        return "AnnotationsDefault{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", default_value="  + default_value +
                '}';
    }

    public ElementValue getDefault_value() {
        return default_value;
    }

    public void setDefault_value(ElementValue default_value) {
        this.default_value = default_value;
    }
}
