package com.example.shamim.weaponzeroing;

public class SOSN {
    private int id;
    private String Date;
    private String RangeNum;
    private String DetailNum;
    private String FiringTargetNum;
    private String Target1A;
    private String Target2A;
    private String Target3A;
    private String Total;


    public SOSN(int id, String date, String rangeNum, String detailNum, String firingTargetNum, String target1A, String target2A, String target3A, String total) {
        this.id = id;
        Date = date;
        RangeNum = rangeNum;
        DetailNum = detailNum;
        FiringTargetNum = firingTargetNum;
        Target1A = target1A;
        Target2A = target2A;
        Target3A = target3A;
        Total = total;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return Date;
    }

    public String getRangeNum() {
        return RangeNum;
    }

    public String getDetailNum() {
        return DetailNum;
    }

    public String getFiringTargetNum() {
        return FiringTargetNum;
    }

    public String getTarget1A() {
        return Target1A;
    }

    public String getTarget2A() {
        return Target2A;
    }

    public String getTarget3A() {
        return Target3A;
    }

    public String getTotal() {
        return Total;
    }
}
