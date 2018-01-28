package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: RuntimeVisibleAnnotations
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class RuntimeVisibleAnnotations extends AttributeInfo {

    private byte[] num_annotations = new byte[2];
    private AnnotationInfo[] annotation_infos;

    public RuntimeVisibleAnnotations(boolean visible) {
        this.name = visible ? "RuntimeVisibleAnnotations" : "RuntimeInVisibleAnnotations";
    }

    @Override
    public String toString() {
        return name + "{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", num_annotations="  + bytesToInt(num_annotations) + " // " + bytesToHexString(num_annotations) +
                ", annotation_infos=" + Arrays.toString(annotation_infos) +
                '}';
    }

    public byte[] getNum_annotations() {
        return num_annotations;
    }

    public void setNum_annotations(byte[] num_annotations) {
        this.num_annotations = num_annotations;
    }

    public AnnotationInfo[] getAnnotation_infos() {
        return annotation_infos;
    }

    public void setAnnotation_infos(AnnotationInfo[] annotation_infos) {
        this.annotation_infos = annotation_infos;
    }
}
