
package com.example.pial.newovilist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signin {

    @SerializedName("email_token")
    @Expose
    private String emailToken;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("alt_phone")
    @Expose
    private String altPhone;
    @SerializedName("addr")
    @Expose
    private String addr;
    @SerializedName("message")
    @Expose
    private String message;

    public String getEmailToken() {
        return emailToken;
    }

    public void setEmailToken(String emailToken) {
        this.emailToken = emailToken;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}