package com.zqh.analysis.enums;

/**
 * @fileName: TargetTypeEnum
 * @author: qhzhang
 * @date: 2018/01/29 15:22
 * @discription:
 */
public enum TargetTypeEnum {

    type_parameter_target(0, 1),
    supertype_target(16, 16),
    type_parameter_bound_target(17, 18),
    empty_target(19, 21),
    formal_parameter_target(22, 22),
    throws_target(23, 23),
    localvar_target(64, 65),
    catch_target(66, 66),
    offset_target(67, 70),
    type_argument_target(71, 75),
    ;

    private int target_type_min;
    private int target_type_max;

    TargetTypeEnum(int target_type_min, int target_type_max) {
        this.target_type_min = target_type_min;
        this.target_type_max = target_type_max;
    }

    public static TargetTypeEnum getByTargetType(int target_type) {
        for (TargetTypeEnum targetTypeEnum : TargetTypeEnum.values()) {
            if (target_type >= targetTypeEnum.target_type_min && target_type <= targetTypeEnum.target_type_max) {
                return targetTypeEnum;
            }
        }
        return null;
    }
}
