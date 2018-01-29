package com.zqh.analysis.enums;

/**
 * @fileName: AttributeNameEnum
 * @author: qhzhang
 * @date: 2018/01/26 15:37
 * @discription:
 */

public enum AttributeNameEnum {
    Code,
    ConstantValue,
    Deprecated,
    Exceptions,
    EnclosingMethod,
    InnerClasses,
    LineNumberTable,
    LocalVariableTable,
    StackMapTable,
    Signature,
    SourceFile,
    SourceDebugExtension,
    Synthetic,
    LocalVariableTypeTable,
    RuntimeVisibleAnnotations,
    RuntimeInvisibleAnnotations,
    RuntimeVisibleParameterAnnotations,
    RuntimeInvisibleParameterAnnotations,
    RuntimeVisibleTypeAnnotations,
    RuntimeInvisibleTypeAnnotations,
    AnnotationDefault,
    BootstrapMethods;

    public static AttributeNameEnum getByNamge(String name) {
        for (AttributeNameEnum attributeNameEnums : AttributeNameEnum.values()) {
            if (attributeNameEnums.name().equals(name)) {
                return attributeNameEnums;
            }
        }
        return null;
    }
}

