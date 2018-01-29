package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: RuntimeVisibleTypeAnnotations
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class RuntimeVisibleTypeAnnotations extends AttributeInfo {

    private byte[] num_annotations = new byte[2];
    private TypeAnnotationInfo[] annotations;

    public RuntimeVisibleTypeAnnotations(boolean visible) {
        this.name = visible ? "RuntimeVisibleTypeAnnotations" : "RuntimeInVisibleTypeAnnotations";
    }

    @Override
    public String toString() {
        return name + "{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", num_annotations="  + bytesToInt(num_annotations) + " // " + bytesToHexString(num_annotations) +
                ", annotations=" + Arrays.toString(annotations) +
                '}';
    }

    public byte[] getNum_annotations() {
        return num_annotations;
    }

    public void setNum_annotations(byte[] num_annotations) {
        this.num_annotations = num_annotations;
    }

    public TypeAnnotationInfo[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(TypeAnnotationInfo[] annotations) {
        this.annotations = annotations;
    }
}
