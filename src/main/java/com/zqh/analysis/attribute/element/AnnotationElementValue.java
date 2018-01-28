package com.zqh.analysis.attribute.element;

import com.zqh.analysis.attribute.AnnotationInfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class AnnotationElementValue extends ElementValue {

    private AnnotationInfo annotationInfo;

    @Override
    public String toString() {
        return "AnnotationElementValue{" +
                " tag=" + (char)bytesToInt(tag) + " // " + bytesToHexString(tag) +
                ", annotationInfo=" + annotationInfo +
                '}';
    }

    public AnnotationInfo getAnnotationInfo() {
        return annotationInfo;
    }

    public void setAnnotationInfo(AnnotationInfo annotationInfo) {
        this.annotationInfo = annotationInfo;
    }
}
