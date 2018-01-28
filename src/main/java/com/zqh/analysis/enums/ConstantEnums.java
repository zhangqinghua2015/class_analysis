package com.zqh.analysis.enums;

/**
 * @fileName: ConstantEnums
 * @author: qhzhang
 * @date: 2018/01/26 13:19
 * @discription:
 */
public enum ConstantEnums {

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

    private ConstantEnums(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }

    public static ConstantEnums getConstantEnum(int tag) {
        for(ConstantEnums constantEnum : ConstantEnums.values()) {
            if (constantEnum.tag == tag) {
                return constantEnum;
            }
        }
        return null;
    }
}