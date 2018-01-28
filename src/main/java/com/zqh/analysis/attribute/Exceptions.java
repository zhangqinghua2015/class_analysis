package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: Exceptions
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class Exceptions extends AttributeInfo {

    private byte[] number_of_exceptions = new byte[2];
    private ExceptionIndexTable[] exceptions_index_table;

    @Override
    public String toString() {
        return "Exceptions{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", number_of_exceptions="  + bytesToInt(number_of_exceptions) + " // " + bytesToHexString(number_of_exceptions) +
                ", exceptions_index_table=" + Arrays.toString(exceptions_index_table) +
                '}';
    }

    public byte[] getNumber_of_exceptions() {
        return number_of_exceptions;
    }

    public void setNumber_of_exceptions(byte[] number_of_exceptions) {
        this.number_of_exceptions = number_of_exceptions;
    }

    public ExceptionIndexTable[] getExceptions_index_table() {
        return exceptions_index_table;
    }

    public void setExceptions_index_table(ExceptionIndexTable[] exceptions_index_table) {
        this.exceptions_index_table = exceptions_index_table;
    }
}
