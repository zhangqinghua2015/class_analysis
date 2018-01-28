package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class ParameterAnnotationInfo {

    private byte[] num_annotations = new byte[2];
    private AnnotationInfo[] annotation_infos;

    @Override
    public String toString() {
        return "ParameterAnnotationInfo{" +
                " num_annotations="  + bytesToInt(num_annotations) + " // " + bytesToHexString(num_annotations) +
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
