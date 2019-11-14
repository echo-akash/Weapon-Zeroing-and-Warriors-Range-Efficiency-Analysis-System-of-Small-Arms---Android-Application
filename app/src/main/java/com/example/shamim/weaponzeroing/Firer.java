package com.example.shamim.weaponzeroing;

public class Firer {
    private int id;
    private String Name, DOB, Cell, NID, Religion, Village, PO, PS,Dist,Gender,Unit,Company;

    public Firer(int id, String name, String DOB, String cell, String NID, String religion, String village, String PO, String PS, String dist, String gender, String unit, String company) {
        this.id = id;
        Name = name;
        this.DOB = DOB;
        Cell = cell;
        this.NID = NID;
        Religion = religion;
        Village = village;
        this.PO = PO;
        this.PS = PS;
        Dist = dist;
        Gender = gender;
        Unit = unit;
        Company = company;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getDOB() {
        return DOB;
    }

    public String getCell() {
        return Cell;
    }

    public String getNID() {
        return NID;
    }

    public String getReligion() {
        return Religion;
    }

    public String getVillage() {
        return Village;
    }

    public String getPO() {
        return PO;
    }

    public String getPS() {
        return PS;
    }

    public String getDist() {
        return Dist;
    }

    public String getGender() {
        return Gender;
    }

    public String getUnit() {
        return Unit;
    }

    public String getCompany() {
        return Company;
    }
}
