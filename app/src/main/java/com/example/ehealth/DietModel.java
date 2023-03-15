package com.example.ehealth;

public class DietModel {
    String dietName, dietDescription;

    public DietModel(String dietName, String dietDescription) {
        this.dietName = dietName;
        this.dietDescription = dietDescription;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getDietDescription() {
        return dietDescription;
    }

    public void setDietDescription(String dietDescription) {
        this.dietDescription = dietDescription;
    }
}
