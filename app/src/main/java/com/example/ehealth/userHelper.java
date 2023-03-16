package com.example.ehealth;

public class userHelper {
    String Name, Email, Phone, Password, Gender;
    double Weight, Height, BMI;

    public userHelper() {
    }


    public userHelper(String name, String email, String phone, String gender, String password, String weight, String height, String BMI) {
        Name = name;
        Email = email;
        Phone = phone;
        Gender = gender;
        Password = password;
        Weight = Double.parseDouble(weight);
        Height = Double.parseDouble(height);
        this.BMI = Double.parseDouble(BMI);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = Double.parseDouble(weight);
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = Double.parseDouble(height);
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = Double.parseDouble(BMI);
    }
}

