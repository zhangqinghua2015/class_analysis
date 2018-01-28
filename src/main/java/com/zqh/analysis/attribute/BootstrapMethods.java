package com.zqh.analysis.attribute;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: BootstrapMethods
 * @author: qhzhang
 * @date: 2018/01/26 18:14
 * @discription:
 */
public class BootstrapMethods extends AttributeInfo {

    private byte[] num_bootstrap_methods = new byte[2];
    private BootstrapMethod[] bootstrap_methods;

    @Override
    public String toString() {
        return "BootstrapMethods{" +
                " attribute_name_index=" + bytesToInt(attribute_name_index) + " // " + bytesToHexString(attribute_name_index) +
                ", attribute_length=" + bytesToInt(attribute_length) + " // " + bytesToHexString(attribute_length) +
                ", num_bootstrap_methods="  + bytesToInt(num_bootstrap_methods) + " // " + bytesToHexString(num_bootstrap_methods) +
                ", bootstrap_methods=" + Arrays.toString(bootstrap_methods) +
                '}';
    }

    public byte[] getNum_bootstrap_methods() {
        return num_bootstrap_methods;
    }

    public void setNum_bootstrap_methods(byte[] num_bootstrap_methods) {
        this.num_bootstrap_methods = num_bootstrap_methods;
    }

    public BootstrapMethod[] getBootstrap_methods() {
        return bootstrap_methods;
    }

    public void setBootstrap_methods(BootstrapMethod[] bootstrap_methods) {
        this.bootstrap_methods = bootstrap_methods;
    }
}
