package com.example.shamim.weaponzeroing;

public class Product {
    private int id;
    private String date;
    private String MPIH;
    private String MPIV;
    private String result;
    private String errorName;
    private String correctionMethod;

    //private String image;


    public Product(int id, String date, String MPIH, String MPIV, String result, String errorName, String correctionMethod) {
        this.id = id;
        this.date = date;
        this.MPIH = MPIH;
        this.MPIV = MPIV;
        this.result = result;
        this.errorName = errorName;
        this.correctionMethod = correctionMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMPIH() {
        return MPIH;
    }

    public void setMPIH(String MPIH) {
        this.MPIH = MPIH;
    }

    public String getMPIV() {
        return MPIV;
    }

    public void setMPIV(String MPIV) {
        this.MPIV = MPIV;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getCorrectionMethod() {
        return correctionMethod;
    }

    public void setCorrectionMethod(String correctionMethod) {
        this.correctionMethod = correctionMethod;
    }
}

