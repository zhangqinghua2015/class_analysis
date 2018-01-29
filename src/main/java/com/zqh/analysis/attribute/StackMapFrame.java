package com.zqh.analysis.attribute;

import com.zqh.analysis.attribute.verification.VerificationTypeInfo;
import com.zqh.analysis.enums.StackMapFrameEnum;

import java.util.Arrays;
import java.util.Objects;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class StackMapFrame {

    private byte[] frame_type = new byte[1];
    private byte[] offset_delta;
    private byte[] number_of_locals;
    private VerificationTypeInfo[] locals;
    private byte[] number_of_stack_items;
    private VerificationTypeInfo[] stack;

    @Override
    public String toString() {
        StackMapFrameEnum stackMapFrameEnum = StackMapFrameEnum.getByFrameType(bytesToInt(frame_type));

        return stackMapFrameEnum.getDesc() + "{" +
                "frame_type=" + bytesToInt(frame_type) + " // " + stackMapFrameEnum + "  " + bytesToHexString(frame_type) +
                (Objects.isNull(offset_delta) ? "" : ", offset_delta=" + bytesToInt(offset_delta) + " // " + bytesToHexString(offset_delta)) +
                (Objects.isNull(number_of_locals) ? "" : ", number_of_locals=" + bytesToInt(number_of_locals) + " // " + bytesToHexString(number_of_locals)) +
                (Objects.isNull(locals) ? "" : ", locals=" + Arrays.toString(locals)) +
                (Objects.isNull(number_of_stack_items) ? "" : ", number_of_stack_items=" + bytesToInt(number_of_stack_items) + " // " + bytesToHexString(number_of_stack_items)) +
                (Objects.isNull(stack) ? "" : ", stack=" + Arrays.toString(stack)) +
                '}';
    }

    public byte[] getFrame_type() {
        return frame_type;
    }

    public void setFrame_type(byte[] frame_type) {
        this.frame_type = frame_type;
    }

    public byte[] getOffset_delta() {
        return offset_delta;
    }

    public void setOffset_delta(byte[] offset_delta) {
        this.offset_delta = offset_delta;
    }

    public byte[] getNumber_of_locals() {
        return number_of_locals;
    }

    public void setNumber_of_locals(byte[] number_of_locals) {
        this.number_of_locals = number_of_locals;
    }

    public VerificationTypeInfo[] getStack() {
        return stack;
    }

    public void setStack(VerificationTypeInfo[] stack) {
        this.stack = stack;
    }

    public byte[] getNumber_of_stack_items() {
        return number_of_stack_items;
    }

    public void setNumber_of_stack_items(byte[] number_of_stack_items) {
        this.number_of_stack_items = number_of_stack_items;
    }

    public VerificationTypeInfo[] getLocals() {
        return locals;
    }

    public void setLocals(VerificationTypeInfo[] locals) {
        this.locals = locals;
    }
}
