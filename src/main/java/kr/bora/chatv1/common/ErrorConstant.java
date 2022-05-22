package kr.bora.chatv1.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public enum ErrorConstant {

    SERVER_CANNOT_STARTED(1001,"네티 서버를 시작할 수 없음.");

    private final int errorCode;
    private final String errorMsg;

    ErrorConstant(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

}
