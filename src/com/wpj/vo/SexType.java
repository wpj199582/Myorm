package com.wpj.vo;

public enum SexType {
    Famele(0,"女性"),
    Male(1,"男性"),
    None(2,"未知");
    private int value;
    private String strValue;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    SexType(int value, String strValue) {
        this.value = value;
        this.strValue = strValue;
    }

    public  SexType findIntByvalue(int value){
        switch (value){
            case 0:return Famele;
            case 1:return Male;
            default:return None;
        }
    }
}
