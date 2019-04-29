package com.yuandian.entity;

public class ReportPO {
    private Long id;

    private Long reportUid;

    private Long reportedUid;

    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportUid() {
        return reportUid;
    }

    public void setReportUid(Long reportUid) {
        this.reportUid = reportUid;
    }

    public Long getReportedUid() {
        return reportedUid;
    }

    public void setReportedUid(Long reportedUid) {
        this.reportedUid = reportedUid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }
}