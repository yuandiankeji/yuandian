package com.yuandian.server.core.consts;

public enum ApplyConst {

    //未处理
    DEFAULT_OPTION(1),
    //已同意
    APPLY_AGREE(2),
    //已拒绝
    REFUSE_APPLY(3),
    //屏蔽
    BLACK_APPLY(4);
    private int code;

    ApplyConst(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
