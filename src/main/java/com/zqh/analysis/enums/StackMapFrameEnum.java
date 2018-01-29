package com.zqh.analysis.enums;

/**
 * @fileName: StackMapFrame
 * @author: qhzhang
 * @date: 2018/01/29 10:11
 * @discription:
 */
public enum StackMapFrameEnum {

    SAME(0, 63, "same_frame"),
    SAME_LOCALS_1_STACK_ITEM(64, 127, "same_locals_1_stack_item_frame"),
    NONE(128, 246, "NONE"),
    SAME_LOCALS_1_STACK_ITEM_EXTENDS(247, 247, "same_locals_1_stack_item_frame_extends"),
    CHOP(248, 250, "chop_frame"),
    SAME_FRAME_EXTENDS(251, 251, "same_frame_extends"),
    APPEND(252, 254, "append_frame"),
    FULL_FRAME(255, 255, "full_frame");

    private int frame_type_min_value;
    private int frame_type_max_value;
    private String desc;

    StackMapFrameEnum(int frame_type_min_value, int frame_type_max_value, String desc) {
        this.desc = desc;
        this.frame_type_max_value = frame_type_max_value;
        this.frame_type_min_value = frame_type_min_value;
    }
    public static StackMapFrameEnum getByFrameType(int frameType) {
         for (StackMapFrameEnum stackMapFrameEnum : StackMapFrameEnum.values()) {
            if (stackMapFrameEnum.frame_type_min_value <= frameType && stackMapFrameEnum.frame_type_max_value >= frameType) {
                return stackMapFrameEnum;
            }
        }
        return null;
    }

    public int getFrame_type_min_value() {
        return frame_type_min_value;
    }

    public int getFrame_type_max_value() {
        return frame_type_max_value;
    }

    public String getDesc() {
        return desc;
    }
}
