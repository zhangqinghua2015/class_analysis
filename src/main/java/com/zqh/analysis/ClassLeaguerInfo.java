package com.zqh.analysis;

import com.zqh.analysis.attribute.AttributeInfo;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: FieldInfo
 * @author: qhzhang
 * @date: 2018/01/26 15:35
 * @discription:
 */
public class ClassLeaguerInfo extends Info {
    private String typeName;
    private byte[] name_index = new byte[2];
    private byte[] descriptor_index = new byte[2];

    public ClassLeaguerInfo(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {

        StringBuilder attributeSb = new StringBuilder("{");
        if (null != attributes) {
            int i=0;
            for (AttributeInfo attributeInfo : attributes) {
                attributeSb.append("\n                     ").append(i++).append("_").append(attributeInfo).append(",");
            }
        }
        attributeSb.append("\n              }");

        return "       " + typeName + "{" +
                "\n              access_flag=" + bytesToInt(access_flags) + " // " + bytesToHexString(access_flags) +
                ", \n              name_index=" + bytesToInt(name_index) + " // " + bytesToHexString(name_index) +
                ", \n              descriptor_index=" + bytesToInt(descriptor_index) + " // " + bytesToHexString(descriptor_index) +
                ", \n              attributes_count=" + bytesToInt(attributes_count) + " // " + bytesToHexString(attributes_count) +
                ", \n              attributes=" + attributeSb.toString() +
                "\n         }";
    }

    public byte[] getName_index() {
        return name_index;
    }

    public void setName_index(byte[] name_index) {
        this.name_index = name_index;
    }

    public byte[] getDescriptor_index() {
        return descriptor_index;
    }

    public void setDescriptor_index(byte[] descriptor_index) {
        this.descriptor_index = descriptor_index;
    }
}

