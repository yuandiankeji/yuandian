package com.yuandian.server.core.consts;

public enum ApplyConst {

    //未处理/ /0,申请中，1已同意，2，一拒绝
    DEFAULT_OPTION(0),
    //已同意
    APPLY_AGREE(1),
    //已拒绝
    REFUSE_APPLY(2),
    //屏蔽
    BLACK_APPLY(3);
    private int code;

    ApplyConst(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
