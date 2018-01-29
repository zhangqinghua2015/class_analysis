package com.zqh.analysis.attribute.target.typepath;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: Path
 * @author: qhzhang
 * @date: 2018/01/29 16:25
 * @discription:
 */
public class Path {

    private byte[] type_path_kind = new byte[1];
    private byte[] type_argument_index = new byte[1];

    @Override
    public String toString() {
        return "Path{" +
                "path_length=" + bytesToInt(type_path_kind) + " // " + bytesToHexString(type_path_kind) +
                ", type_argument_index=" + bytesToInt(type_argument_index) + " // " + bytesToHexString(type_argument_index) +
                '}';
    }

    public byte[] getType_path_kind() {
        return type_path_kind;
    }

    public void setType_path_kind(byte[] type_path_kind) {
        this.type_path_kind = type_path_kind;
    }

    public byte[] getType_argument_index() {
        return type_argument_index;
    }

    public void setType_argument_index(byte[] type_argument_index) {
        this.type_argument_index = type_argument_index;
    }
}
