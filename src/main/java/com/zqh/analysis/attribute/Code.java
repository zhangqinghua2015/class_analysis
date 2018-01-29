package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: Code
 * @author: qhzhang
 * @date: 2018/01/26 17:41
 * @discription:
 */
public class Code extends AttributeInfo {

    private byte[] max_stack = new byte[2];
    private byte[] max_locals = new byte[2];
    private byte[] code_length = new byte[4];
    private byte[][] code;
    private byte[] excepton_table_length = new byte[2];
    private ExceptionInfo[] exception_table;

    public Code() {
        setName("Code");
    }

    @Override
    public String toString() {
        StringBuilder codeSb = new StringBuilder("{");
        if (null != code) {
            for (byte[] bytes : code) {
                codeSb.append(bytesToInt(max_stack)).append(" // ").append(bytesToHexString(max_stack)).append(", ");
            }
        }
        codeSb.append("}");

        StringBuilder attributeSb = new StringBuilder("{");
        if (null != attributes) {
            int i=0;
            for (AttributeInfo attributeInfo : attributes) {
                attributeSb.append(i++).append("_").append(attributeInfo).append(",");
            }
        }
        attributeSb.append("}");

        return "Code{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", max_stack=" + bytesToInt(max_stack) + " // " + bytesToHexString(max_stack) +
                ", max_locals=" + bytesToInt(max_locals) + " // " + bytesToHexString(max_locals) +
                ", code_length=" + bytesToInt(code_length) + " // " + bytesToHexString(code_length) +
                ", code=" + codeSb.toString() +
                ", excepton_table_length=" + bytesToInt(excepton_table_length) + " // " + bytesToHexString(excepton_table_length) +
                ", exception_table=" + Arrays.toString(exception_table) +
                ", attributes_count=" + bytesToInt(attributes_count) + " // " + bytesToHexString(attributes_count) +
                ", attributes=" + attributeSb.toString() +
                '}';
    }

    public byte[] getMax_stack() {
        return max_stack;
    }

    public void setMax_stack(byte[] max_stack) {
        this.max_stack = max_stack;
    }

    public byte[] getMax_locals() {
        return max_locals;
    }

    public void setMax_locals(byte[] max_locals) {
        this.max_locals = max_locals;
    }

    public byte[] getCode_length() {
        return code_length;
    }

    public void setCode_length(byte[] code_length) {
        this.code_length = code_length;
    }

    public byte[][] getCode() {
        return code;
    }

    public void setCode(byte[][] code) {
        this.code = code;
    }

    public byte[] getExcepton_table_length() {
        return excepton_table_length;
    }

    public void setExcepton_table_length(byte[] excepton_table_length) {
        this.excepton_table_length = excepton_table_length;
    }

    public ExceptionInfo[] getException_table() {
        return exception_table;
    }

    public void setException_table(ExceptionInfo[] exception_table) {
        this.exception_table = exception_table;
    }
}
