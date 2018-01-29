package com.zqh.analysis.attribute.target.targetinfo;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: LocalvarTarget
 * @author: qhzhang
 * @date: 2018/01/29 15:57
 * @discription:
 */
public class LocalvarTarget extends TargetInfo {

    private byte[] table_length = new byte[2];
    private Table[] tables;

    @Override
    public String toString() {
        return "LocalvarTarget{" +
                "table_length=" + bytesToInt(table_length) + " // " + bytesToHexString(table_length) +
                ", tables=" + Arrays.toString(tables) +
                '}';
    }

    public byte[] getTable_length() {
        return table_length;
    }

    public void setTable_length(byte[] table_length) {
        this.table_length = table_length;
    }

    public Table[] getTables() {
        return tables;
    }

    public void setTables(Table[] tables) {
        this.tables = tables;
    }

}
