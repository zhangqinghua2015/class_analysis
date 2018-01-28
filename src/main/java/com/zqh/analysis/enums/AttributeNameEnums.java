package com.zqh.analysis.enums;

/**
 * @fileName: AttributeNameEnums
 * @author: qhzhang
 * @date: 2018/01/26 15:37
 * @discription:
 */

public enum AttributeNameEnums {
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

    public static AttributeNameEnums getByNamge(String name) {
        for (AttributeNameEnums attributeNameEnums : AttributeNameEnums.values()) {
            if (attributeNameEnums.name().equals(name)) {
                return attributeNameEnums;
            }
        }
        return null;
    }
}

