package com.zqh.analysis.attribute;

import com.zqh.analysis.attribute.target.targetinfo.TargetInfo;
import com.zqh.analysis.attribute.target.typepath.TypePath;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: TypeAnnotationInfo
 * @author: qhzhang
 * @date: 2018/01/29 16:48
 * @discription:
 */
public class TypeAnnotationInfo {

    private byte[] target_type = new byte[1];
    private TargetInfo target_info;
    private TypePath type_path = new TypePath();
    private byte[] type_index = new byte[2];
    private byte[] num_element_value_pairs = new byte[2];
    private ElementValuePair[] element_value_pairs;

    @Override
    public String toString() {
        return "TypeAnnotationInfo{" +
                ", target_type=" + bytesToInt(target_type) + " // " + bytesToHexString(target_type) +
                ", target_info=" + target_info +
                ", type_path=" + type_path +
                ", type_index=" + bytesToInt(type_index) + " // " + bytesToHexString(type_index) +
                ", num_element_value_pairs=" + bytesToInt(num_element_value_pairs) + " // " + bytesToHexString(num_element_value_pairs) +
                ", element_value_pairs=" + Arrays.toString(element_value_pairs) +
                '}';
    }

    public byte[] getTarget_type() {
        return target_type;
    }

    public void setTarget_type(byte[] target_type) {
        this.target_type = target_type;
    }

    public TargetInfo getTarget_info() {
        return target_info;
    }

    public void setTarget_info(TargetInfo target_info) {
        this.target_info = target_info;
    }

    public TypePath getType_path() {
        return type_path;
    }

    public void setType_path(TypePath type_path) {
        this.type_path = type_path;
    }

    public byte[] getType_index() {
        return type_index;
    }

    public void setType_index(byte[] type_index) {
        this.type_index = type_index;
    }

    public byte[] getNum_element_value_pairs() {
        return num_element_value_pairs;
    }

    public void setNum_element_value_pairs(byte[] num_element_value_pairs) {
        this.num_element_value_pairs = num_element_value_pairs;
    }

    public ElementValuePair[] getElement_value_pairs() {
        return element_value_pairs;
    }

    public void setElement_value_pairs(ElementValuePair[] element_value_pairs) {
        this.element_value_pairs = element_value_pairs;
    }
}
