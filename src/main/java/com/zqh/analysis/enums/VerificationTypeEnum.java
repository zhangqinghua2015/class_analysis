package com.zqh.analysis.enums;

import com.zqh.analysis.attribute.verification.VerificationTypeInfo;

/**
 * @fileName: VerificationTypeEnum
 * @author: qhzhang
 * @date: 2018/01/29 9:55
 * @discription:
 */
public enum VerificationTypeEnum {

    ITEM_Top(0, "Top_variable_info"),
    ITEM_Integer(1, "Integer_variable_info"),
    ITEM_Float(2, "Float_variable_info"),
    ITEM_Double(3, "Double_variable_info"),
    ITEM_Long(4, "Long_variable_info"),
    ITEM_Null(5, "Null_variable_info"),
    ITEM_UninitializedThis(6, "UninitializedThis_variable_info"),
    ITEM_Object(7, "Object_variable_info"),
    ITEM_Uninitialized(8, "Uninitialized_variable_info");

    private int tag;
    private String desc;

    VerificationTypeEnum(int tag, String desc) {
        this.desc = desc;
        this.tag = tag;
    }

    public static VerificationTypeEnum getByTag(int tag) {
        for (VerificationTypeEnum verificationTypeEnum : VerificationTypeEnum.values()) {
            if (verificationTypeEnum.tag == tag) {
                return verificationTypeEnum;
            }
        }
        return null;
    }

    public int getTag() {
        return tag;
    }

    public String getDesc() {
        return desc;
    }
}
