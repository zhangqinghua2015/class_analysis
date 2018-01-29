package com.zqh.analysis.attribute.target.typepath;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: TypePath
 * @author: qhzhang
 * @date: 2018/01/29 16:20
 * @discription:
 */
public class TypePath {
    private byte[] path_length = new byte[1];
    private Path[] paths;

    @Override
    public String toString() {
        return "TypePath{" +
                "path_length=" + bytesToInt(path_length) + " // " + bytesToHexString(path_length) +
                ", paths=" + Arrays.toString(paths) +
                '}';
    }

    public byte[] getPath_length() {
        return path_length;
    }

    public void setPath_length(byte[] path_length) {
        this.path_length = path_length;
    }

    public Path[] getPaths() {
        return paths;
    }

    public void setPaths(Path[] paths) {
        this.paths = paths;
    }
}
