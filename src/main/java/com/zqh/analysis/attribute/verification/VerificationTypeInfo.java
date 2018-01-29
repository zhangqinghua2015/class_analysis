package com.zqh.analysis.attribute.verification;

import com.zqh.analysis.enums.VerificationTypeEnum;

import java.util.Arrays;

import static com.zqh.analysis.utils.NumberUtil.bytesToHexString;
import static com.zqh.analysis.utils.NumberUtil.bytesToInt;

/**
 * @fileName: VerificationTypeInfo
 * @author: qhzhang
 * @date: 2018/01/29 9:48
 * @discription:
 */
public class VerificationTypeInfo {

    protected byte[] tag = new byte[1];
    private byte[] cpool_index_or_offset;

    @Override
    public String toString() {
        VerificationTypeEnum verificationTypeEnum = VerificationTypeEnum.getByTag(bytesToInt(tag));
        StringBuilder stringBuilder = new StringBuilder("");
        if (null != cpool_index_or_offset) {
            stringBuilder.append(", ").append(verificationTypeEnum == VerificationTypeEnum.ITEM_Object ? "offset=" : "cpool_index=")
                    .append(bytesToInt(cpool_index_or_offset)).append(" // ").append(bytesToHexString(cpool_index_or_offset));
        }
        return verificationTypeEnum.getDesc() + "{" +
                "tag=" + bytesToInt(tag) + " // " + verificationTypeEnum + "  " + bytesToHexString(tag) +
                stringBuilder.toString() +
                '}';
    }

    public byte[] getTag() {
        return tag;
    }

    public void setTag(byte[] tag) {
        this.tag = tag;
    }

    public byte[] getCpool_index_or_offset() {
        return cpool_index_or_offset;
    }

    public void setCpool_index_or_offset(byte[] cpool_index_or_offset) {
        this.cpool_index_or_offset = cpool_index_or_offset;
    }
}
