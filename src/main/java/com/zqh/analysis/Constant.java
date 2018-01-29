package com.zqh.analysis;

import com.zqh.analysis.enums.ConstantEnum;

import static com.zqh.analysis.enums.ConstantEnum.getConstantEnum;
import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;
import static com.zqh.analysis.utils.NumberUtil.bytesToLong;

/**
 * @fileName: Constant
 * @author: qhzhang
 * @date: 2018/01/26 13:14
 * @discription:
 */
public class Constant {
    private byte[] tag = new byte[1];
    private byte[] length = new byte[2]; // UTF-8编码字符串字节数
    private byte[] bytes; // 基本数据类型长度或者字符串字节;
    private byte[] reference_kind = new byte[1];
    private byte[] index1 = new byte[2]; //new byte[1]; 指向常量池的索引，index/bootstrap_method_index/descriptor_index;
    private byte[] index2 = new byte[2]; // 指向常量池的索引(方法或字段指向NameAndType的索引)，index/reference_index/name_and_type_index;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("         Constant{ // ");
        sb.append(ConstantEnum.getConstantEnum(bytesToInt(tag))).append(" \n")
                .append("             tag=").append(bytesToInt(tag)).append(" // ").append(bytesToHexString(tag));
        switch (getConstantEnum(bytesToInt(tag))) {
            case UTF_8_info:
                sb.append(", \n             length=").append(bytesToInt(length)).append(" // ").append(bytesToHexString(length))
                        .append(", \n             bytes=").append(new String(bytes)).append(" // ").append(bytesToHexString(bytes));
                break;
            case Integer_info:
                sb.append(", \n             bytes=").append(bytesToInt(bytes)).append(" // ").append(bytesToHexString(bytes));
                break;
            case Float_info:
                sb.append(", \n             bytes=").append(new String(bytes)).append(" // ").append(bytesToHexString(bytes));
                break;
            case Long_info:
                sb.append(", \n             bytes=").append(bytesToLong(bytes)).append(" // ").append(bytesToHexString(bytes));
                break;
            case Double_info:
                sb.append(", \n             bytes=").append(new String(bytes)).append(" // ").append(bytesToHexString(bytes));
                break;
            case Class_info:
            case String_info:
                sb.append(", \n             index=").append(bytesToInt(index1)).append(" // ").append(bytesToHexString(index1));
                break;
            case MethodType_info:
                sb.append(", \n             descriptor_index=").append(bytesToInt(index1)).append(" // ").append(bytesToHexString(index1));
                break;
            case Fieldref_info:
            case Methodref_info:
            case InterfaceMethodref_info:
            case NameAndType_info:
                sb.append(", \n             index=").append(bytesToInt(index1)).append(" // ").append(bytesToHexString(index1));
                sb.append(", \n             index=").append(bytesToInt(index2)).append(" // ").append(bytesToHexString(index2));
                break;
            case InvokeDynamic_info:
                sb.append(", \n             bootstrap_method_attr_index=").append(bytesToInt(index1)).append(" // ").append(bytesToHexString(index1));
                sb.append(", \n             name_and_type_index=").append(bytesToInt(index2)).append(" // ").append(bytesToHexString(index2));
                break;
            case MethodHandle_info:
                sb.append(", \n             reference_kind=").append(bytesToInt(reference_kind)).append(" // ").append(bytesToHexString(reference_kind));
                sb.append(", \n             reference_index=").append(bytesToInt(index2)).append(" // ").append(bytesToHexString(index2));
                break;
        }
        sb.append("\n           }");
        return sb.toString();
    }

    public byte[] getTag() {
        return tag;
    }

    public void setTag(byte tag[]) {
        this.tag = tag;
    }

    public byte[] getLength() {
        return length;
    }

    public void setLength(byte[] length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getReference_kind() {
        return reference_kind;
    }

    public void setReference_kind(byte[] reference_kind) {
        this.reference_kind = reference_kind;
    }

    public byte[] getIndex1() {
        return index1;
    }

    public void setIndex1(byte[] index1) {
        this.index1 = index1;
    }

    public byte[] getIndex2() {
        return index2;
    }

    public void setIndex2(byte[] index2) {
        this.index2 = index2;
    }
}

