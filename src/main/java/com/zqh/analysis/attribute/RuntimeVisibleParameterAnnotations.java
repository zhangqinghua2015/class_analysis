package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class RuntimeVisibleParameterAnnotations extends AttributeInfo {

    private byte[] num_parameters = new byte[1];
    private ParameterAnnotationInfo[] parameterAnnotationInfos;

    public RuntimeVisibleParameterAnnotations(boolean visible) {
        this.name = visible ? "RuntimeVisibleParameterAnnotations" : "RuntimeInVisibleParameterAnnotations";
    }

    @Override
    public String toString() {
        return name + "{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", num_parameters="  + bytesToInt(num_parameters) + " // " + bytesToHexString(num_parameters) +
                ", parameterAnnotationInfos=" + Arrays.toString(parameterAnnotationInfos) +
                '}';
    }


    public byte[] getNum_parameters() {
        return num_parameters;
    }

    public void setNum_parameters(byte[] num_parameters) {
        this.num_parameters = num_parameters;
    }

    public ParameterAnnotationInfo[] getParameterAnnotationInfos() {
        return parameterAnnotationInfos;
    }

    public void setParameterAnnotationInfos(ParameterAnnotationInfo[] parameterAnnotationInfos) {
        this.parameterAnnotationInfos = parameterAnnotationInfos;
    }
}
