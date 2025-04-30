package com.gomezc.pantrypal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class User {

    @NotEmpty
    private String uFirstName;

    @NotEmpty
    private String uLastName;

    @Id
    @NotEmpty
    private String uEmail;

    @NotEmpty
    private String uPassword;

    @NotEmpty
    private String uPhone;

    private Integer uHeight;
    private Integer uWeight;
    private Integer uAge;
    private String uGender;

    // --- Getters and Setters ---
    public String getuFirstName() {
        return uFirstName;
    }

    public void setuFirstName(String uFirstName) {
        this.uFirstName = uFirstName;
    }

    public String getuLastName() {
        return uLastName;
    }

    public void setuLastName(String uLastName) {
        this.uLastName = uLastName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public Integer getuHeight() {
        return uHeight;
    }

    public void setuHeight(Integer uHeight) {
        this.uHeight = uHeight;
    }

    public Integer getuWeight() {
        return uWeight;
    }

    public void setuWeight(Integer uWeight) {
        this.uWeight = uWeight;
    }

    public Integer getuAge() {
        return uAge;
    }

    public void setuAge(Integer uAge) {
        this.uAge = uAge;
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender;
    }
}
