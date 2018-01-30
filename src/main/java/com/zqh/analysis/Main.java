package com.zqh.analysis;

import com.zqh.analysis.attribute.*;
import com.zqh.analysis.attribute.element.*;
import com.zqh.analysis.attribute.target.targetinfo.*;
import com.zqh.analysis.attribute.target.typepath.Path;
import com.zqh.analysis.attribute.target.typepath.TypePath;
import com.zqh.analysis.attribute.verification.VerificationTypeInfo;
import com.zqh.analysis.enums.AttributeNameEnum;
import com.zqh.analysis.enums.StackMapFrameEnum;
import com.zqh.analysis.enums.TargetTypeEnum;
import com.zqh.analysis.enums.VerificationTypeEnum;

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

            readConstantPool(classInfo, fis);

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

    private static void readConstantPool(ClassInfo classInfo, FileInputStream fis) throws IOException {
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
                            fis.read(constant.getBytes());
                        } else {
                            constant.setBytes("".getBytes());
                        }
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
                        constants[i++] = constant;
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
                        throw new RuntimeException("unkown constant tag: " + getConstantEnum(bytesToInt(constant.getTag())));
                }
                constants[i] = constant;
            }
            classInfo.setConstant_pool(constants);
        }
    }

    private static void readAttribute(FileInputStream fis, Info info, ClassInfo classInfo) throws IOException {
        fis.read(info.getAttributes_count());
        int attributeCount = bytesToInt(info.getAttributes_count());
        if (0 < attributeCount) {
            AttributeInfo attributeInfo = null;
            AttributeInfo[] attributeInfos = new AttributeInfo[attributeCount];
            byte[] attribute_name_index;
            for (int j = 0; j<attributeCount; j++) {
                attribute_name_index = new byte[2];
                fis.read(attribute_name_index);

                int nameIndex = bytesToInt(attribute_name_index);
                String name = classInfo.getAttributeNameByIndex(nameIndex);
                switch(AttributeNameEnum.getByNamge(name)) {
                    case Code:
                        attributeInfo = getCode(fis, classInfo, attribute_name_index);
                        break;
                    case ConstantValue:
                        attributeInfo = getConstantValue(fis, attribute_name_index);
                        break;
                    case Deprecated:
                        attributeInfo = getAttributeInfo(fis, attribute_name_index, "Deprecated");
                        break;
                    case Synthetic:
                        attributeInfo = getAttributeInfo(fis, attribute_name_index, "Synthetic");
                        break;
                    case Exceptions:
                        attributeInfo = getExceptions(fis, attribute_name_index);
                        break;
                    case EnclosingMethod:
                        attributeInfo = getEnclosingMethod(fis, attribute_name_index);
                        break;
                    case InnerClasses:
                        attributeInfo = getInnerClasses(fis, attribute_name_index);
                        break;
                    case LineNumberTable:
                        attributeInfo = getLineNumberTable(fis, attribute_name_index);
                        break;
                    case LocalVariableTable:
                        attributeInfo = getLocalVariableTable(fis, attribute_name_index);
                        break;
                    case StackMapTable:
                        attributeInfo = getStackMapTable(fis, attribute_name_index);
                        break;
                    case Signature:
                        attributeInfo = getSignature(fis, attribute_name_index);
                        break;
                    case SourceFile:
                        attributeInfo = getSourceFile(fis, attribute_name_index);
                        break;
                    case SourceDebugExtension:
                        attributeInfo = getSourceDebugExtension(fis, attribute_name_index);
                        break;
                    case LocalVariableTypeTable:
                        attributeInfo = getLocalVariableTypeTable(fis, attribute_name_index);
                        break;
                    case RuntimeVisibleAnnotations:
                        attributeInfo = new RuntimeVisibleAnnotations(true);
                    case RuntimeInvisibleAnnotations:
                        if (null == attributeInfo) {
                            attributeInfo = new RuntimeVisibleAnnotations(false);
                        }
                        getRuntimeVisibleAnnotations(fis, (RuntimeVisibleAnnotations) attributeInfo, attribute_name_index);
                        break;
                    case RuntimeVisibleParameterAnnotations:
                        attributeInfo = new RuntimeVisibleParameterAnnotations(true);
                    case RuntimeInvisibleParameterAnnotations:
                        if (null == attributeInfo) {
                            attributeInfo = new RuntimeVisibleParameterAnnotations(false);
                        }
                        getRuntimeVisibleParameterAnnotations(fis, (RuntimeVisibleParameterAnnotations) attributeInfo, attribute_name_index);
                        break;
                    case RuntimeVisibleTypeAnnotations:
                        attributeInfo = new RuntimeVisibleTypeAnnotations(true);
                        break;
                    case RuntimeInvisibleTypeAnnotations:
                        if (null == attributeInfo) {
                            attributeInfo = new RuntimeVisibleTypeAnnotations(false);
                        }
                        getRuntimeVisibleTypeAnnotations(fis, (RuntimeVisibleTypeAnnotations) attributeInfo, attribute_name_index);
                        break;
                    case AnnotationDefault:
                        attributeInfo = getAnnotationsDefault(fis, attribute_name_index);
                        break;
                    case BootstrapMethods:
                        attributeInfo = getBootstrapMethods(fis, attribute_name_index);
                        break;
                    default:
                        throw new RuntimeException("unkown attribute name: " + name);
                }


                attributeInfos[j] = attributeInfo;
            }
            info.setAttributes(attributeInfos);
        }
    }

    private static StackMapTable getStackMapTable(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        StackMapTable stackMapTable = new StackMapTable();
        stackMapTable.setAttribute_name_index(attribute_name_index);
        fis.read(stackMapTable.getAttribute_length());
        if (!attributeLengthCheck(stackMapTable)) {
            return stackMapTable;
        }
        stackMapTable.setInfo(new byte[bytesToInt(stackMapTable.getAttribute_length())]);
        fis.read(stackMapTable.getNumber_of_entries());
        int numberOfEntries = bytesToInt(stackMapTable.getNumber_of_entries());
        if (0<numberOfEntries) {
            StackMapFrame stackMapFrame;
            StackMapFrame[] stackMapFrames = new StackMapFrame[numberOfEntries];
            stackMapTable.setEntries(stackMapFrames);
            for(int i=0; i<numberOfEntries; i++) {
                stackMapFrame = new StackMapFrame();
                stackMapFrames[i] = stackMapFrame;
                fis.read(stackMapFrame.getFrame_type());
                switch (StackMapFrameEnum.getByFrameType(bytesToInt(stackMapFrame.getFrame_type()))) {
                    case SAME:
                        break;
                    case SAME_LOCALS_1_STACK_ITEM:
                        stackMapFrame.setStack(getVerificationTypeInfos(fis, 1));
                        break;
                    case SAME_LOCALS_1_STACK_ITEM_EXTENDS:
                        stackMapFrame.setOffset_delta(new byte[2]);
                        fis.read(stackMapFrame.getOffset_delta());
                        stackMapFrame.setStack(getVerificationTypeInfos(fis, 1));
                        break;
                    case CHOP:
                    case SAME_FRAME_EXTENDS:
                        stackMapFrame.setOffset_delta(new byte[2]);
                        fis.read(stackMapFrame.getOffset_delta());
                        break;
                    case APPEND:
                        stackMapFrame.setOffset_delta(new byte[2]);
                        fis.read(stackMapFrame.getOffset_delta());
                        stackMapFrame.setLocals(getVerificationTypeInfos(fis, bytesToInt(stackMapFrame.getFrame_type()) - 251));
                        break;
                    case FULL_FRAME:
                        stackMapFrame.setOffset_delta(new byte[2]);
                        fis.read(stackMapFrame.getOffset_delta());
                        stackMapFrame.setNumber_of_locals(new byte[2]);
                        fis.read(stackMapFrame.getNumber_of_locals());
                        int numberOfLocals = bytesToInt(stackMapFrame.getNumber_of_locals());
                        if (0<numberOfLocals) {
                            stackMapFrame.setLocals(getVerificationTypeInfos(fis, numberOfLocals));
                        }
                        stackMapFrame.setNumber_of_stack_items(new byte[2]);
                        fis.read(stackMapFrame.getNumber_of_stack_items());
                        int numberOfStackItems = bytesToInt(stackMapFrame.getNumber_of_stack_items());
                        if (0<numberOfStackItems) {
                            stackMapFrame.setStack(getVerificationTypeInfos(fis, numberOfStackItems));
                        }
                        break;
                    default:
                        throw new RuntimeException("unkown stack_map_frame type: " + bytesToInt(stackMapFrame.getFrame_type()));
                }
            }
        }
        return stackMapTable;
    }

    private static VerificationTypeInfo[] getVerificationTypeInfos(FileInputStream fis, int numOfVerificationTypeInfos) throws IOException {
        VerificationTypeInfo[] verificationTypeInfos = new VerificationTypeInfo[numOfVerificationTypeInfos];
        VerificationTypeInfo verificationTypeInfo;
        for (int h=0; h<verificationTypeInfos.length; h++) {
            verificationTypeInfo = new VerificationTypeInfo();
            verificationTypeInfos[h] = verificationTypeInfo;
            fis.read(verificationTypeInfo.getTag());
            switch (VerificationTypeEnum.getByTag(bytesToInt(verificationTypeInfo.getTag()))) {
                case ITEM_Top:
                case ITEM_Integer:
                case ITEM_Float:
                case ITEM_Double:
                case ITEM_Long:
                case ITEM_Null:
                case ITEM_UninitializedThis:
                    break;
                case ITEM_Object:
                case ITEM_Uninitialized:
                    verificationTypeInfo.setCpool_index_or_offset(new byte[2]);
                    fis.read(verificationTypeInfo.getCpool_index_or_offset());
                    break;
                default:
                    throw new RuntimeException("unkown varification_type_info tag: " + bytesToInt(verificationTypeInfo.getTag()));
            }
        }
        return verificationTypeInfos;
    }

    private static void getRuntimeVisibleParameterAnnotations(FileInputStream fis, RuntimeVisibleParameterAnnotations attributeInfo, byte[] attribute_name_index) throws IOException {
        RuntimeVisibleParameterAnnotations runtimeVisibleParameterAnnotations = attributeInfo;
        runtimeVisibleParameterAnnotations.setAttribute_name_index(attribute_name_index);
        fis.read(runtimeVisibleParameterAnnotations.getAttribute_length());
        if (!attributeLengthCheck(runtimeVisibleParameterAnnotations)) {
            return;
        }
        fis.read(runtimeVisibleParameterAnnotations.getNum_parameters());
        int numParameters = bytesToInt(runtimeVisibleParameterAnnotations.getNum_parameters());
        if (0<numParameters) {
            ParameterAnnotationInfo[] parameterAnnotationInfos = new ParameterAnnotationInfo[numParameters];
            runtimeVisibleParameterAnnotations.setParameterAnnotationInfos(parameterAnnotationInfos);
            for(int i=0; i<numParameters; i++) {
                parameterAnnotationInfos[i] = getParameterAnnotationInfo(fis);
            }
        }
    }

    private static void getRuntimeVisibleTypeAnnotations(FileInputStream fis, RuntimeVisibleTypeAnnotations attributeInfo, byte[] attribute_name_index) throws IOException {
        RuntimeVisibleTypeAnnotations runtimeVisibleTypeAnnotations = attributeInfo;
        runtimeVisibleTypeAnnotations.setAttribute_name_index(attribute_name_index);
        fis.read(runtimeVisibleTypeAnnotations.getAttribute_length());
        if (!attributeLengthCheck(runtimeVisibleTypeAnnotations)) {
            return;
        }
        fis.read(runtimeVisibleTypeAnnotations.getNum_annotations());
        int numAnnotations = bytesToInt(runtimeVisibleTypeAnnotations.getNum_annotations());
        if (0<numAnnotations) {
            runtimeVisibleTypeAnnotations.setAnnotations(getTypeAnnotationInfos(fis, numAnnotations));
        }
    }

    private static void getRuntimeVisibleAnnotations(FileInputStream fis, RuntimeVisibleAnnotations attributeInfo, byte[] attribute_name_index) throws IOException {
        RuntimeVisibleAnnotations runtimeVisibleAnnotations = attributeInfo;
        runtimeVisibleAnnotations.setAttribute_name_index(attribute_name_index);
        fis.read(runtimeVisibleAnnotations.getAttribute_length());
        if (!attributeLengthCheck(runtimeVisibleAnnotations)) {
            return;
        }
        fis.read(runtimeVisibleAnnotations.getNum_annotations());
        int numAnnotations = bytesToInt(runtimeVisibleAnnotations.getNum_annotations());
        if (0<numAnnotations) {
            runtimeVisibleAnnotations.setAnnotation_infos(getAnnotationInfos(fis, numAnnotations));
        }
    }

    private static SourceDebugExtension getSourceDebugExtension(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        SourceDebugExtension sourceDebugExtension = new SourceDebugExtension();
        sourceDebugExtension.setAttribute_name_index(attribute_name_index);
        fis.read(sourceDebugExtension.getAttribute_length());
        if (!attributeLengthCheck(sourceDebugExtension)) {
            return sourceDebugExtension;
        }
        int attributeLength = bytesToInt(sourceDebugExtension.getAttribute_length());
        sourceDebugExtension.setDebug_extension(new byte[attributeLength]);
        fis.read(sourceDebugExtension.getDebug_extension());
        return sourceDebugExtension;
    }

    private static AnnotationsDefault getAnnotationsDefault(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        AnnotationsDefault annotationsDefault = new AnnotationsDefault();
        annotationsDefault.setAttribute_name_index(attribute_name_index);
        fis.read(annotationsDefault.getAttribute_length());
        if (!attributeLengthCheck(annotationsDefault)) {
            return annotationsDefault;
        }
        annotationsDefault.setDefault_value(getElementValue(fis));
        return annotationsDefault;
    }

    private static ParameterAnnotationInfo getParameterAnnotationInfo(FileInputStream fis) throws IOException {
        ParameterAnnotationInfo parameterAnnotationInfo;
        parameterAnnotationInfo = new ParameterAnnotationInfo();
        fis.read(parameterAnnotationInfo.getNum_annotations());
        int numAnnotations = bytesToInt(parameterAnnotationInfo.getNum_annotations());
        if (0<numAnnotations) {
            parameterAnnotationInfo.setAnnotation_infos(getAnnotationInfos(fis, numAnnotations));
        }
        return parameterAnnotationInfo;
    }

    private static TypeAnnotationInfo[] getTypeAnnotationInfos(FileInputStream fis, int numAnnotations) throws IOException {
        TypeAnnotationInfo[] typeAnnotationInfos = new TypeAnnotationInfo[numAnnotations];
        for(int h=0; h<numAnnotations; h++) {
            typeAnnotationInfos[h] = getTypeAnnotationInfo(fis);
        }
        return typeAnnotationInfos;
    }

    private static TypeAnnotationInfo getTypeAnnotationInfo(FileInputStream fis) throws IOException {
        TypeAnnotationInfo typeAnnotationInfo = new TypeAnnotationInfo();
        fis.read(typeAnnotationInfo.getTarget_type());
        switch (TargetTypeEnum.getByTargetType(bytesToInt(typeAnnotationInfo.getTarget_type()))) {
            case type_parameter_target:
                TypeParameterTarget typeParameterTarget = new TypeParameterTarget();
                typeAnnotationInfo.setTarget_info(typeParameterTarget);
                fis.read(typeParameterTarget.getType_parameter_index());
                break;
            case supertype_target:
                SupertypeTarget supertypeTarget = new SupertypeTarget();
                typeAnnotationInfo.setTarget_info(supertypeTarget);
                fis.read(supertypeTarget.getSupertype_index());
                break;
            case type_parameter_bound_target:
                TypeParameterBoundTarget typeParameterBoundTarget = new TypeParameterBoundTarget();
                typeAnnotationInfo.setTarget_info(typeParameterBoundTarget);
                fis.read(typeParameterBoundTarget.getType_parameter_index());
                fis.read(typeParameterBoundTarget.getBound_index());
                break;
            case empty_target:
                typeAnnotationInfo.setTarget_info(new TargetInfo());
                break;
            case formal_parameter_target:
                FormalParameterTarget formalParameterTarget = new FormalParameterTarget();
                typeAnnotationInfo.setTarget_info(formalParameterTarget);
                fis.read(formalParameterTarget.getFormal_parameter_index());
                break;
            case throws_target:
                ThrowsTarget throwsTarget = new ThrowsTarget();
                typeAnnotationInfo.setTarget_info(throwsTarget);
                fis.read(throwsTarget.getThrows_type_index());
                break;
            case localvar_target:
                LocalvarTarget localvarTarget = new LocalvarTarget();
                typeAnnotationInfo.setTarget_info(localvarTarget);
                fis.read(localvarTarget.getTable_length());
                int tableLength = bytesToInt(localvarTarget.getTable_length());
                if (0<tableLength) {
                    Table[] tables = new Table[tableLength];
                    Table table;
                    for(int i=0; i<tableLength; i++) {
                        table = new Table();
                        tables[i] = table;
                        fis.read(table.getStart_pc());
                        fis.read(table.getLength());
                        fis.read(table.getIndex());
                    }
                    localvarTarget.setTables(tables);
                }
                break;
            case catch_target:
                CatchTarget catchTarget = new CatchTarget();
                typeAnnotationInfo.setTarget_info(catchTarget);
                fis.read(catchTarget.getException_table_index());
                break;
            case offset_target:
                OffsetTarget offsetTarget = new OffsetTarget();
                typeAnnotationInfo.setTarget_info(offsetTarget);
                fis.read(offsetTarget.getOffset());
                break;
            case type_argument_target:
                TypeArgumentTarget typeArgumentTarget = new TypeArgumentTarget();
                typeAnnotationInfo.setTarget_info(typeArgumentTarget);
                fis.read(typeArgumentTarget.getOffset());
                fis.read(typeArgumentTarget.getType_argument_index());
                break;
            default:
                throw new RuntimeException("unkown target_type: " + bytesToInt(typeAnnotationInfo.getTarget_type()));
        }
        TypePath typePath = new TypePath();
        typeAnnotationInfo.setType_path(typePath);
        fis.read(typePath.getPath_length());
        int typePathLength = bytesToInt(typePath.getPath_length());
        if (0<typePathLength) {
            Path[] paths = new Path[typePathLength];
            Path path;
            for(int i=0; i<typePathLength; i++) {
                path = new Path();
                paths[i] = path;
                fis.read(path.getType_path_kind());
                fis.read(path.getType_argument_index());
            }
            typePath.setPaths(paths);
        }
        fis.read(typeAnnotationInfo.getType_index());
        fis.read(typeAnnotationInfo.getNum_element_value_pairs());
        int numElementValuePairs = bytesToInt(typeAnnotationInfo.getNum_element_value_pairs());
        if (0<numElementValuePairs) {
            typeAnnotationInfo.setElement_value_pairs(getElementValuePairs(fis, numElementValuePairs));
        }
        return typeAnnotationInfo;
    }

    private static ElementValuePair[] getElementValuePairs(FileInputStream fis, int numElementValuePairs) throws IOException {
        ElementValuePair elementValuePair;
        ElementValuePair[] elementValuePairs = new ElementValuePair[numElementValuePairs];
        for(int j=0; j<numElementValuePairs; j++) {
            elementValuePair = new ElementValuePair();
            fis.read(elementValuePair.getElement_name_index());
            elementValuePair.setElement_value(getElementValue(fis));
            elementValuePairs[j] = elementValuePair;
        }
        return elementValuePairs;
    }


    private static AnnotationInfo[] getAnnotationInfos(FileInputStream fis, int numAnnotations) throws IOException {
        AnnotationInfo[] annotationInfos = new AnnotationInfo[numAnnotations];
        for(int h=0; h<numAnnotations; h++) {
            annotationInfos[h] = getAnnotationInfo(fis);
        }
        return annotationInfos;
    }

    private static AnnotationInfo getAnnotationInfo(FileInputStream fis) throws IOException {
        AnnotationInfo annotationInfo;
        annotationInfo = new AnnotationInfo();
        fis.read(annotationInfo.getType_index());
        fis.read(annotationInfo.getNum_element_value_pairs());
        int numElementValuePairs = bytesToInt(annotationInfo.getNum_element_value_pairs());
        if (0<numElementValuePairs) {
            annotationInfo.setElement_value_pairs(getElementValuePairs(fis, numElementValuePairs));
        }
        return annotationInfo;
    }

    private static ElementValue getElementValue(FileInputStream fis) throws IOException {
        ElementValue elementValue;
        byte[] tag;
        tag = new byte[1];
        fis.read(tag);
        switch ((char)tag[0]) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
            case 's':
                ConstElementValue constElementValue = new ConstElementValue();
                fis.read(constElementValue.getConst_value_index());
                elementValue = constElementValue;
                break;
            case 'e':
                EnumElementValue enumElementValue = new EnumElementValue();
                fis.read(enumElementValue.getType_name_index());
                fis.read(enumElementValue.getConst_name_index());
                elementValue = enumElementValue;
                break;
            case 'c':
                ClassElementValue classElementValue = new ClassElementValue();
                fis.read(classElementValue.getClass_info_index());
                elementValue = classElementValue;
                break;
            case '@':
                AnnotationElementValue annotationElementValue = new AnnotationElementValue();
                annotationElementValue.setAnnotationInfo(getAnnotationInfo(fis));
                elementValue = annotationElementValue;
                break;
            case '[':
                ArrayElementValue arrayElementValue = new ArrayElementValue();
                fis.read(arrayElementValue.getNum_values());
                int numValues = bytesToInt(arrayElementValue.getNum_values());
                if (0<numValues) {
                    ElementValue[] elementValues = new ElementValue[numValues];
                    for(int i=0; i<numValues; i++) {
                        arrayElementValue.setValues(elementValues);
                        elementValues[i] = getElementValue(fis);
                    }
                }
                elementValue = arrayElementValue;
                break;
            default:
                throw new RuntimeException("unkown element_value tag: " + (char)tag[0]);

        }
        elementValue.setTag(tag);
        return elementValue;
    }

    private static AttributeInfo getLocalVariableTypeTable(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        LocalVariableTypeTable localVariableTypeTable = new LocalVariableTypeTable();
        localVariableTypeTable.setAttribute_name_index(attribute_name_index);
        fis.read(localVariableTypeTable.getAttribute_length());
        if (!attributeLengthCheck(localVariableTypeTable)) {
            return localVariableTypeTable;
        }
        fis.read(localVariableTypeTable.getLocal_variable_type_table_length());
        int getLocalVariableTypeTableLength = bytesToInt(localVariableTypeTable.getLocal_variable_type_table_length());
        if (0<getLocalVariableTypeTableLength) {
            LocalVariableTypeInfo localVariableTypeInfo;
            LocalVariableTypeInfo[] localVariableTypeInfos = new LocalVariableTypeInfo[getLocalVariableTypeTableLength];
            localVariableTypeTable.setLocal_variable_type_infos(localVariableTypeInfos);
            for(int i=0; i<getLocalVariableTypeTableLength; i++) {
                localVariableTypeInfo = new LocalVariableTypeInfo();
                localVariableTypeInfos[i] = localVariableTypeInfo;
                fis.read(localVariableTypeInfo.getStart_pc());
                fis.read(localVariableTypeInfo.getLength());
                fis.read(localVariableTypeInfo.getName_index());
                fis.read(localVariableTypeInfo.getSignature_index());
                fis.read(localVariableTypeInfo.getIndex());
            }
        }
        return localVariableTypeTable;
    }

    private static AttributeInfo getEnclosingMethod(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        EnclosingMethod enclosingMethod = new EnclosingMethod();
        enclosingMethod.setAttribute_name_index(attribute_name_index);
        fis.read(enclosingMethod.getAttribute_length());
        if (!attributeLengthCheck(enclosingMethod)) {
            return enclosingMethod;
        }
        fis.read(enclosingMethod.getClass_index());
        fis.read(enclosingMethod.getMethod_index());
        return enclosingMethod;
    }

    private static AttributeInfo getBootstrapMethods(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        BootstrapMethods bootstrapMethods = new BootstrapMethods();
        bootstrapMethods.setAttribute_name_index(attribute_name_index);
        fis.read(bootstrapMethods.getAttribute_length());
        if (!attributeLengthCheck(bootstrapMethods)) {
            return bootstrapMethods;
        }
        fis.read(bootstrapMethods.getNum_bootstrap_methods());
        int numBootstrapMethods = bytesToInt(bootstrapMethods.getNum_bootstrap_methods());
        if (0 < numBootstrapMethods) {
            BootstrapMethod bootstrapMethod;
            BootstrapMethod[] bootstrapMethodArray = new BootstrapMethod[numBootstrapMethods];
            bootstrapMethods.setBootstrap_methods(bootstrapMethodArray);
            for(int i=0; i<numBootstrapMethods; i++) {
                bootstrapMethod = new BootstrapMethod();
                bootstrapMethodArray[i] = bootstrapMethod;
                fis.read(bootstrapMethod.getBootstrap_method_ref());
                fis.read(bootstrapMethod.getNum_bootstrap_arguments());
                int numBootstrapArguments = bytesToInt(bootstrapMethod.getNum_bootstrap_arguments());
                if (0 < numBootstrapArguments) {
                    bootstrapMethod.setBootstrap_arguments(new byte[numBootstrapMethods][2]);
                    for (byte[] bytes : bootstrapMethod.getBootstrap_arguments()) {
                        fis.read(bytes);
                    }
                }
            }
        }
        return bootstrapMethods;
    }

    private static AttributeInfo getSignature(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        Signature signature = new Signature();
        signature.setAttribute_name_index(attribute_name_index);
        fis.read(signature.getAttribute_length());
        if (!attributeLengthCheck(signature)) {
            return signature;
        }
        fis.read(signature.getSignature_index());
        return signature;
    }

    private static AttributeInfo getAttributeInfo(FileInputStream fis, byte[] attribute_name_index, String name) throws IOException {
        AttributeInfo attributeInfo = new AttributeInfo();
        attributeInfo.setName(name);
        attributeInfo.setAttribute_name_index(attribute_name_index);
        fis.read(attributeInfo.getAttribute_length());
        if (!attributeLengthCheck(attributeInfo)) {
            return attributeInfo;
        }
        attributeInfo.setInfo(new byte[bytesToInt(attributeInfo.getAttribute_length())]);
        fis.read(attributeInfo.getInfo());
        return attributeInfo;
    }

    private static AttributeInfo getExceptions(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        Exceptions exceptions = new Exceptions();
        exceptions.setAttribute_name_index(attribute_name_index);
        fis.read(exceptions.getAttribute_length());
        if (!attributeLengthCheck(exceptions)) {
            return exceptions;
        }
        fis.read(exceptions.getNumber_of_exceptions());
        int numberOfExceptions = bytesToInt(exceptions.getNumber_of_exceptions());
        if (0<numberOfExceptions) {
            ExceptionIndexTable exceptionIndexTable;
            ExceptionIndexTable[] exceptionIndexTables = new ExceptionIndexTable[numberOfExceptions];
            exceptions.setExceptions_index_table(exceptionIndexTables);
            for(int i=0; i<numberOfExceptions; i++) {
                exceptionIndexTable = new ExceptionIndexTable();
                exceptionIndexTables[i] = exceptionIndexTable;
                fis.read(exceptionIndexTable.getIndex());
            }
        }
        return exceptions;
    }

    private static AttributeInfo getConstantValue(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        ConstantValue constantValue = new ConstantValue();
        constantValue.setAttribute_name_index(attribute_name_index);
        fis.read(constantValue.getAttribute_length());
        if (!attributeLengthCheck(constantValue)) {
            return constantValue;
        }
        fis.read(constantValue.getConstantvalue_index());
        return constantValue;
    }

    private static AttributeInfo getSourceFile(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        SourceFile sourceFile = new SourceFile();
        sourceFile.setAttribute_name_index(attribute_name_index);
        fis.read(sourceFile.getAttribute_length());
        if (!attributeLengthCheck(sourceFile)) {
            return sourceFile;
        }
        fis.read(sourceFile.getSourcefile_index());
        return sourceFile;
    }

    private static AttributeInfo getLocalVariableTable(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        LocalVariableTable localVariableTable = new LocalVariableTable();
        localVariableTable.setAttribute_name_index(attribute_name_index);
        fis.read(localVariableTable.getAttribute_length());
        if (!attributeLengthCheck(localVariableTable)) {
            return localVariableTable;
        }
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
        return localVariableTable;
    }

    private static AttributeInfo getLineNumberTable(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        LineNumberTable lineNumberTable = new LineNumberTable();
        lineNumberTable.setAttribute_name_index(attribute_name_index);
        fis.read(lineNumberTable.getAttribute_length());
        if (!attributeLengthCheck(lineNumberTable)) {
            return lineNumberTable;
        }
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
        return lineNumberTable;
    }

    private static AttributeInfo getInnerClasses(FileInputStream fis, byte[] attribute_name_index) throws IOException {
        InnerClasses innerClasses = new InnerClasses();
        innerClasses.setAttribute_name_index(attribute_name_index);
        fis.read(innerClasses.getAttribute_length());
        if (!attributeLengthCheck(innerClasses)) {
            return innerClasses;
        }
        fis.read(innerClasses.getNumber_of_classes());
        int numberOfClasses = bytesToInt(innerClasses.getNumber_of_classes());
        if (0<numberOfClasses) {
            InnerClassesInfo innerClassesInfo;
            InnerClassesInfo[] innerClassesInfos = new InnerClassesInfo[numberOfClasses];
            innerClasses.setInner_classes(innerClassesInfos);
            for(int i=0; i<numberOfClasses; i++) {
                innerClassesInfo = new InnerClassesInfo();
                innerClassesInfos[i] = innerClassesInfo;
                fis.read(innerClassesInfo.getInner_class_info_index());
                fis.read(innerClassesInfo.getOuter_class_info_index());
                fis.read(innerClassesInfo.getInner_name_index());
                fis.read(innerClassesInfo.getInner_class_access_flags());
            }
        }
        return innerClasses;
    }

    private static AttributeInfo getCode(FileInputStream fis, ClassInfo classInfo, byte[] attribute_name_index) throws IOException {
        Code code = new Code();
        code.setAttribute_name_index(attribute_name_index);
        fis.read(code.getAttribute_length());
        if (!attributeLengthCheck(code)) {
            return code;
        }
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

        return code;
    }

    private static boolean attributeLengthCheck(AttributeInfo attributeInfo) {
        return 0 < bytesToInt(attributeInfo.getAttribute_length());
    }
}
