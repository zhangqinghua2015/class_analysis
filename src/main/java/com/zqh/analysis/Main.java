package com.zqh.analysis;

import com.zqh.analysis.attribute.*;
import com.zqh.analysis.enums.AttributeName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.zqh.analysis.enums.ConstantEnum.getConstantEnum;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: Main
 * @author: qhzhang
 * @date: 2018/01/26 10:28
 * @discription:
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String classFilePath = args[0];
        ClassInfo classInfo = new ClassInfo();
        File file = new File(classFilePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(classInfo.getMagic());
            fis.read(classInfo.getMinor_version());
            fis.read(classInfo.getMajor_version());

            if (!readConstantPool(classInfo, fis)) {
                return;
            }

            fis.read(classInfo.getAccess_flags());
            fis.read(classInfo.getThis_class());
            fis.read(classInfo.getSuper_class());

            readInterfaces(classInfo, fis);

            readLeaguerInfos(classInfo, fis, "FieldInfo");

            readLeaguerInfos(classInfo, fis, "MethodInfo");

            readAttribute(fis, classInfo, classInfo);

            System.out.println(classInfo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fis) {
                fis.close();
            }
        }
    }

    private static void readInterfaces(ClassInfo classInfo, FileInputStream fis) throws IOException {
        fis.read(classInfo.getInterface_count());
        int interfaceCount = bytesToInt(classInfo.getInterface_count());
        if (0 < interfaceCount) {
            classInfo.setInterfaces(new byte[interfaceCount][2]);
            for (byte[] bytes : classInfo.getInterfaces()) {
                fis.read(bytes);
            }
        }
    }

    private static void readLeaguerInfos(ClassInfo classInfo, FileInputStream fis, String typeName) throws IOException {
        int fieldCount;
        if ("FieldInfo".equals(typeName)) {
            fis.read(classInfo.getFields_count());
            fieldCount = bytesToInt(classInfo.getFields_count());
        } else {
            fis.read(classInfo.getMethods_count());
            fieldCount = bytesToInt(classInfo.getMethods_count());
        }
        if (0 < fieldCount) {
            ClassLeaguerInfo info;
            ClassLeaguerInfo[] leaguerInfos = new ClassLeaguerInfo[fieldCount];
            if ("FieldInfo".equals(typeName)) {
                classInfo.setFields(leaguerInfos);
            } else {
                classInfo.setMethods(leaguerInfos);
            }
            for (int i=0; i<fieldCount; i++) {
                info = new ClassLeaguerInfo(typeName);
                fis.read(info.getAccess_flags());
                fis.read(info.getName_index());
                fis.read(info.getDescriptor_index());
                readAttribute(fis, info, classInfo);
                leaguerInfos[i] = info;
            }
        }
    }

    private static boolean readConstantPool(ClassInfo classInfo, FileInputStream fis) throws IOException {
        fis.read(classInfo.getConstant_pool_count());
        int constantPoolCount = bytesToInt(classInfo.getConstant_pool_count());
        if (1 < constantPoolCount) {
            Constant constant;
            Constant[] constants = new Constant[constantPoolCount - 1];
            for (int i = 0; i < constantPoolCount - 1; i++) {
                constant = new Constant();
                fis.read(constant.getTag());
                switch (getConstantEnum(bytesToInt(constant.getTag()))) {
                    case UTF_8_info:
                        fis.read(constant.getLength());
                        int length = bytesToInt(constant.getLength());
                        if (length > 0) {
                            constant.setBytes(new byte[length]);
                        }
                        fis.read(constant.getBytes());
                        break;
                    case Integer_info:
                    case Float_info:
                        constant.setBytes(new byte[4]);
                        fis.read(constant.getBytes());
                        break;
                    case Long_info:
                    case Double_info:
                        constant.setBytes(new byte[8]);
                        fis.read(constant.getBytes());
                        break;
                    case Class_info:
                    case String_info:
                    case MethodType_info:
                        fis.read(constant.getIndex1());
                        break;
                    case Fieldref_info:
                    case Methodref_info:
                    case InterfaceMethodref_info:
                    case NameAndType_info:
                    case InvokeDynamic_info:
                        fis.read(constant.getIndex1());
                        fis.read(constant.getIndex2());
                        break;
                    case MethodHandle_info:
                        fis.read(constant.getReference_kind());
                        fis.read(constant.getIndex2());
                        break;
                    default:
                        System.out.println("unkown constant tag");
                        return false;
                }
                constants[i] = constant;
            }
            classInfo.setConstant_pool(constants);
        }
        return true;
    }

    private static void readAttribute(FileInputStream fis, Info info, ClassInfo classInfo) throws IOException {
        fis.read(info.getAttributes_count());
        int attributeCount = bytesToInt(info.getAttributes_count());
        if (0 < attributeCount) {
            AttributeInfo attributeInfo;
            AttributeInfo[] attributeInfos = new AttributeInfo[attributeCount];
            byte[] attribute_name_index;
            for (int j = 0; j<attributeCount; j++) {
                attribute_name_index = new byte[2];
                fis.read(attribute_name_index);

                int nameIndex = bytesToInt(attribute_name_index);
                String name = classInfo.getAttributeNameByIndex(nameIndex);
                switch(AttributeName.getByNamge(name)) {
                    case Code:
                        attributeInfo = getCode(fis, classInfo, attribute_name_index);
                        break;
                    case ConstantValue:
                        attributeInfo = getConstantValue(fis, attribute_name_index);
                        break;
                    /*case Deprecated:

                        break;
                    case Exceptions:

                        break;
                    case EnclosingMethod:

                        break;*/
                    case InnerClasses:
                        attributeInfo = getInnerClasses(fis, attribute_name_index);
                        break;
                    case LineNumberTable:
                        attributeInfo = getLineNumberTable(fis, attribute_name_index);
                        break;
                    case LocalVariableTable:
                        attributeInfo = getLocalVariableTable(fis, attribute_name_index);
                        break;
                    /*case StackMapTable:

                        break;
                    case Signature:

                        break;*/
                    case SourceFile:
                        attributeInfo = getSourceFile(fis, attribute_name_index);
                        break;
                    /*case SourceDebugExtension:

                        break;
                    case Synthetic:

                        break;
                    case LocalVariableTypeTable:

                        break;
                    case RuntimeVisibleAnnotations:

                        break;
                    case RuntimeInvisibleAnnotations:

                        break;
                    case RuntimeVisibleParameterAnnotations:

                        break;
                    case RuntimeInvisibleParameterAnnotations:

                        break;
                    case AnnotationDefault:

                        break;
                    case BootstrapMethods:
                        break;*/
                    default:
                        System.out.println("unkown attribute name: " + name);
                        return;
                }


                attributeInfos[j] = attributeInfo;
            }
            info.setAttributes(attributeInfos);
        }
    }

    private static AttributeInfo getConstantValue(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        AttributeInfo attributeInfo;ConstantValue constantValue = new ConstantValue();
        attributeInfo = constantValue;
        constantValue.setAttribute_name_index(attribute_name_index);
        fis.read(constantValue.getAttribute_length());
        fis.read(constantValue.getConstantvalue_index());
        return attributeInfo;
    }

    private static AttributeInfo getSourceFile(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        AttributeInfo attributeInfo;SourceFile sourceFile = new SourceFile();
        attributeInfo = sourceFile;
        sourceFile.setAttribute_name_index(attribute_name_index);
        fis.read(sourceFile.getAttribute_length());
        fis.read(sourceFile.getSourcefile_index());
        return attributeInfo;
    }

    private static AttributeInfo getLocalVariableTable(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        AttributeInfo attributeInfo;LocalVariableTable localVariableTable = new LocalVariableTable();
        attributeInfo = localVariableTable;
        localVariableTable.setAttribute_name_index(attribute_name_index);
        fis.read(localVariableTable.getAttribute_length());
        fis.read(localVariableTable.getLocal_variable_table_length());
        int localVariableTableLength = bytesToInt(localVariableTable.getLocal_variable_table_length());
        if (0<localVariableTableLength) {
            LocalVariableInfo localVariableInfo;
            LocalVariableInfo[] localVariableInfos = new LocalVariableInfo[localVariableTableLength];
            localVariableTable.setLocal_variable_table(localVariableInfos);
            for (int i=0; i<localVariableTableLength; i++) {
                localVariableInfo = new LocalVariableInfo();
                localVariableInfos[i] = localVariableInfo;
                fis.read(localVariableInfo.getStar_pc());
                fis.read(localVariableInfo.getLength());
                fis.read(localVariableInfo.getName_index());
                fis.read(localVariableInfo.getDescriptor_index());
                fis.read(localVariableInfo.getIndex());
            }
        }
        return attributeInfo;
    }

    private static AttributeInfo getLineNumberTable(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        AttributeInfo attributeInfo;LineNumberTable lineNumberTable = new LineNumberTable();
        attributeInfo = lineNumberTable;
        lineNumberTable.setAttribute_name_index(attribute_name_index);
        fis.read(lineNumberTable.getAttribute_length());
        fis.read(lineNumberTable.getLine_number_table_length());
        int lineNumberTableLength = bytesToInt(lineNumberTable.getLine_number_table_length());
        if (0<lineNumberTableLength) {
            LineNumberInfo lineNumberInfo;
            LineNumberInfo[] lineNumberInfos = new LineNumberInfo[lineNumberTableLength];
            lineNumberTable.setLine_nember_table(lineNumberInfos);
            for (int i=0; i<lineNumberTableLength; i++) {
                lineNumberInfo = new LineNumberInfo();
                lineNumberInfos[i] = lineNumberInfo;
                fis.read(lineNumberInfo.getStar_pc());
                fis.read(lineNumberInfo.getLine_number());
            }
        }
        return attributeInfo;
    }

    private static AttributeInfo getInnerClasses(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        AttributeInfo attributeInfo;InnerClasses innerClasses = new InnerClasses();
        attributeInfo = innerClasses;
        innerClasses.setAttribute_name_index(attribute_name_index);
        fis.read(innerClasses.getAttribute_length());
        fis.read(innerClasses.getNumber_of_classes());
        int numberOfClasses = bytesToInt(innerClasses.getNumber_of_classes());
        if (0<numberOfClasses) {
            InnerClassesInfo innerClassesInfo;
            InnerClassesInfo[] innerClassesInfos = new InnerClassesInfo[numberOfClasses];
            for(int i=0; i<numberOfClasses; i++) {
                innerClassesInfo = new InnerClassesInfo();
                innerClassesInfos[i] = innerClassesInfo;
                fis.read(innerClassesInfo.getInner_class_info_index());
                fis.read(innerClassesInfo.getOuter_class_info_index());
                fis.read(innerClassesInfo.getInner_name_index());
                fis.read(innerClassesInfo.getInner_class_access_flags());
            }
        }
        return attributeInfo;
    }

    private static AttributeInfo getCode(FileInputStream fis, ClassInfo classInfo, byte[] attribute_name_index) throws IOException {
        AttributeInfo attributeInfo;Code code = new Code();
        code.setAttribute_name_index(attribute_name_index);
        fis.read(code.getAttribute_length());
        fis.read(code.getMax_stack());
        fis.read(code.getMax_locals());
        fis.read(code.getCode_length());
        int codeLength = bytesToInt(code.getCode_length());
        if (0 < codeLength) {
            code.setCode(new byte[codeLength][1]);
            for (byte[] bytes : code.getCode()) {
                fis.read(bytes);
            }
        }
        fis.read(code.getExcepton_table_length());
        int exceptionTableLength = bytesToInt(code.getExcepton_table_length());
        if (0 < exceptionTableLength) {
            ExceptionInfo exceptionInfo;
            ExceptionInfo[] exceptionInfos = new ExceptionInfo[exceptionTableLength];
            code.setException_table(exceptionInfos);

            for (int i=0; i<exceptionTableLength; i++) {
                exceptionInfo = new ExceptionInfo();
                fis.read(exceptionInfo.getStart_pc());
                fis.read(exceptionInfo.getEnd_pc());
                fis.read(exceptionInfo.getHandler_pc());
                fis.read(exceptionInfo.getCatch_type());
                exceptionInfos[i] = exceptionInfo;
            }
        }
        readAttribute(fis, code, classInfo);

        attributeInfo = code;
        return attributeInfo;
    }
}
