package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

public class BootstrapMethod {
    private byte[] bootstrap_method_ref = new byte[2];
    private byte[] num_bootstrap_arguments = new byte[2];
    private byte[][] bootstrap_arguments;

    @Override
    public String toString() {
        StringBuilder bootstraprgumentsSb = new StringBuilder("{");
        if (null != bootstrap_arguments) {
            for (byte[] bytes : bootstrap_arguments) {
                bootstraprgumentsSb.append(bytesToInt(bytes)).append(" // ").append(bytesToHexString(bytes)).append(", ");
            }
        }
        bootstraprgumentsSb.append("}");

        return "BootstrapMethod{" +
                " bootstrap_method_ref=" + bytesToInt(bootstrap_method_ref) + " // " + bytesToHexString(bootstrap_method_ref) +
                ", num_bootstrap_arguments=" + bytesToInt(num_bootstrap_arguments) + " // " + bytesToHexString(num_bootstrap_arguments) +
                ", bootstrap_arguments=" + bootstraprgumentsSb.toString() +
                '}';
    }

    public byte[] getBootstrap_method_ref() {
        return bootstrap_method_ref;
    }

    public void setBootstrap_method_ref(byte[] bootstrap_method_ref) {
        this.bootstrap_method_ref = bootstrap_method_ref;
    }

    public byte[] getNum_bootstrap_arguments() {
        return num_bootstrap_arguments;
    }

    public void setNum_bootstrap_arguments(byte[] num_bootstrap_arguments) {
        this.num_bootstrap_arguments = num_bootstrap_arguments;
    }

    public byte[][] getBootstrap_arguments() {
        return bootstrap_arguments;
    }

    public void setBootstrap_arguments(byte[][] bootstrap_arguments) {
        this.bootstrap_arguments = bootstrap_arguments;
    }
}
