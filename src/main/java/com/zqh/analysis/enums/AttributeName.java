package com.zqh.analysis.enums;

/**
 * @fileName: AttributeName
 * @author: qhzhang
 * @date: 2018/01/26 15:37
 * @discription:
 */

public enum AttributeName {
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
    AnnotationDefault,
    BootstrapMethods;

    public static AttributeName getByNamge(String name) {
        for (AttributeName attributeName : AttributeName.values()) {
            if (attributeName.name().equals(name)) {
                return attributeName;
            }
        }
        return null;
    }
}

