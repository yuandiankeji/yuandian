package com.yuandian.core.net;

/**
 * @author twjitm 2019/4/15/22:21
 */
public class IoMessage {
    int head;
    int version;
    int cmd;
    int status;
    int length;
    byte[] bytes;

    public IoMessage() {
    }

    public IoMessage(int head, int version, int cmd, int status, int length, byte[] bytes) {
        this.head = head;
        this.version = version;
        this.cmd = cmd;
        this.status = status;
        this.length = length;
        this.bytes = bytes;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
