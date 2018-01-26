package com.zqh.analysis.attribute;

import static com.zqh.analysis.utils.NumberUtil.*;

/**
 * @fileName: ExceptionInfo
 * @author: qhzhang
 * @date: 2018/01/26 17:45
 * @discription:
 */
public class ExceptionInfo {

    private byte[] start_pc = new byte[2];
    private byte[] end_pc = new byte[2];
    private byte[] handler_pc = new byte[2];
    private byte[] catch_type = new byte[2];

    @Override
    public String toString() {
        return "ExceptionInfo{" +
                "start_pc=" + bytesToInt(start_pc) + " // " + bytesToHexString(start_pc) +
                ", end_pc=" + bytesToInt(end_pc) + " // " + bytesToHexString(end_pc) +
                ", handler_pc=" + bytesToInt(handler_pc) + " // " + bytesToHexString(handler_pc) +
                ", catch_type=" + bytesToInt(catch_type) + " // " + bytesToHexString(catch_type) +
                '}';
    }

    public byte[] getStart_pc() {
        return start_pc;
    }

    public void setStart_pc(byte[] start_pc) {
        this.start_pc = start_pc;
    }

    public byte[] getEnd_pc() {
        return end_pc;
    }

    public void setEnd_pc(byte[] end_pc) {
        this.end_pc = end_pc;
    }

    public byte[] getHandler_pc() {
        return handler_pc;
    }

    public void setHandler_pc(byte[] handler_pc) {
        this.handler_pc = handler_pc;
    }

    public byte[] getCatch_type() {
        return catch_type;
    }

    public void setCatch_type(byte[] catch_type) {
        this.catch_type = catch_type;
    }
}
