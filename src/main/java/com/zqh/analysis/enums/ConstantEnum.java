package com.zqh.analysis.enums;

/**
 * @fileName: ConstantEnum
 * @author: qhzhang
 * @date: 2018/01/26 13:19
 * @discription:
 */
public enum ConstantEnum {

    UTF_8_info(1),
    Integer_info(3),
    Float_info(4),
    Long_info(5),
    Double_info(6),
    Class_info(7),
    String_info(8),
    Fieldref_info(9),
    Methodref_info(10),
    InterfaceMethodref_info(11),
    NameAndType_info(12),
    MethodHandle_info(15),
    MethodType_info(16),
    InvokeDynamic_info(18);

    private int tag;

    private ConstantEnum(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }

    public static ConstantEnum getConstantEnum(int tag) {
        for(ConstantEnum constantEnum : ConstantEnum.values()) {
            if (constantEnum.tag == tag) {
                return constantEnum;
            }
        }
        return null;
    }
}