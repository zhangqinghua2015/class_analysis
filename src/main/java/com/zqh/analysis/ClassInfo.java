package com.zqh.analysis;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: ClassInfo
 * @author: qhzhang
 * @date: 2018/01/26 10:35
 * @discription:
 */
public class ClassInfo extends Info {

    private byte[] magic = new byte[4];
    private byte[] minor_version = new byte[2];
    private byte[] major_version = new byte[2];
    private byte[] constant_pool_count = new byte[2];
    private Constant[] constant_pool; // = new Constant[constant_pool_count]
    private byte[] this_class = new byte[2];
    private byte[] super_class = new byte[2];
    private byte[] interface_count = new byte[2];
    private byte[][] interfaces; // = new byte[interface][2];
    private byte[] fields_count = new byte[2];
    private ClassLeaguerInfo[] fields; // = new FieldInfo[fields_count];
    private byte[] methods_count = new byte[2];
    private ClassLeaguerInfo[] methods; // = new MethodInfo[method_count];

    public String getAttributeNameByIndex(int index) {
        return new String(constant_pool[index-1].getBytes());
    }

    @Override
    public String toString() {

        StringBuilder constantSb = new StringBuilder("{");
        if (null != constant_pool) {
            int i = 1;
            for (Constant constant : constant_pool) {
                constantSb.append("\n").append(i++).append(constant).append(",");
            }
        }
        constantSb.append("          \n }");

        StringBuilder interfaceSb = new StringBuilder("{");
        if (null != interfaces) {
            for (byte[] bytes : interfaces) {
                interfaceSb.append("\n      ").append(bytesToInt(access_flags)).append(" // ").append(bytesToHexString(access_flags));
            }
        }
        interfaceSb.append("        \n }");

        StringBuilder fieldSb = new StringBuilder("{");
        if (null != fields) {
            int i=0;
            for (ClassLeaguerInfo fieldInfo : fields) {
                fieldSb.append("\n").append(i++).append(fieldInfo).append(",");
            }
        }
        fieldSb.append("        \n }");

        StringBuilder methodSb = new StringBuilder("{");
        if (null != methods) {
            int i=0;
            for (ClassLeaguerInfo methodInfo : methods) {
                methodSb.append("\n").append(i++).append(methodInfo).append(",");
            }
        }
        methodSb.append("        \n }");

        return "ClassInfo{" +
                " \n magic=" + bytesToHexString(magic) +
                ", \n minor_version=" + bytesToInt(minor_version) + " // " + bytesToHexString(minor_version) +
                ", \n major_version=" + bytesToInt(major_version) + " // " + bytesToHexString(major_version) +
                ", \n constant_pool_count=" + bytesToInt(constant_pool_count) + " // " + bytesToHexString(constant_pool_count) +
                ", \n constant_pool=" + constantSb.toString() +
                ", \n access_flags=" + bytesToInt(access_flags) + " // " + bytesToHexString(access_flags) +
                ", \n this_class=" + bytesToInt(this_class) + " // " + bytesToHexString(this_class) +
                ", \n super_class=" + bytesToInt(super_class) + " // " + bytesToHexString(super_class) +
                ", \n interface_count=" + bytesToInt(interface_count) + " // " + bytesToHexString(interface_count) +
                ", \n interfaces=" + interfaceSb.toString() +
                ", \n fields_count=" + bytesToInt(fields_count) + " // " + bytesToHexString(fields_count) +
                ", \n fields=" + fieldSb.toString() +
                ", \n methods_count=" + bytesToInt(methods_count) + " // " + bytesToHexString(methods_count) +
                ", \n methods=" + methodSb.toString() +
                ", \n attributes_count=" + bytesToInt(attributes_count) + " // " + bytesToHexString(attributes_count) +
                ", \n attributes=" + Arrays.toString(attributes) +
                "\n}";
    }

    public byte[] getMagic() {
        return magic;
    }

    public void setMagic(byte[] magic) {
        this.magic = magic;
    }

    public byte[] getMinor_version() {
        return minor_version;
    }

    public void setMinor_version(byte[] minor_version) {
        this.minor_version = minor_version;
    }

    public byte[] getMajor_version() {
        return major_version;
    }

    public void setMajor_version(byte[] major_version) {
        this.major_version = major_version;
    }

    public byte[] getConstant_pool_count() {
        return constant_pool_count;
    }

    public void setConstant_pool_count(byte[] constant_pool_count) {
        this.constant_pool_count = constant_pool_count;
    }

    public Constant[] getConstant_pool() {
        return constant_pool;
    }

    public void setConstant_pool(Constant[] constant_pool) {
        this.constant_pool = constant_pool;
    }

    public byte[] getThis_class() {
        return this_class;
    }

    public void setThis_class(byte[] this_class) {
        this.this_class = this_class;
    }

    public byte[] getSuper_class() {
        return super_class;
    }

    public void setSuper_class(byte[] super_class) {
        this.super_class = super_class;
    }

    public byte[] getInterface_count() {
        return interface_count;
    }

    public void setInterface_count(byte[] interface_count) {
        this.interface_count = interface_count;
    }

    public byte[][] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(byte[][] interfaces) {
        this.interfaces = interfaces;
    }

    public byte[] getFields_count() {
        return fields_count;
    }

    public void setFields_count(byte[] fields_count) {
        this.fields_count = fields_count;
    }

    public ClassLeaguerInfo[] getFields() {
        return fields;
    }

    public void setFields(ClassLeaguerInfo[] fields) {
        this.fields = fields;
    }

    public byte[] getMethods_count() {
        return methods_count;
    }

    public void setMethods_count(byte[] methods_count) {
        this.methods_count = methods_count;
    }

    public ClassLeaguerInfo[] getMethods() {
        return methods;
    }

    public void setMethods(ClassLeaguerInfo[] methods) {
        this.methods = methods;
    }
}
