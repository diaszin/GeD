package com.projetofinal.ged.domain;


import java.text.DateFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class User {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private Date birthdayDate;


    public User(Long id, String fullName, String email, String password, Date birthdayDate) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.birthdayDate = birthdayDate;
    }

    public void setFullName(String fullName) throws Exception {
        String patternRegexForFullName = "^[A-ZÁÀÂÃÉÈÊÍÌÎÓÒÔÕÚÙÛÇ][a-záàâãéèêíìîóòôõúùûç]+ [A-ZÁÀÂÃÉÈÊÍÌÎÓÒÔÕÚÙÛÇ][a-záàâãéèêíìîóòôõúùûç]+(?: [A-ZÁÀÂÃÉÈÊÍÌÎÓÒÔÕÚÙÛÇ][a-záàâãéèêíìîóòôõúùûç]+)?$";
        if(fullName.matches(patternRegexForFullName)){
            throw new Exception("O nome do usuário deve conter nome e sobrenome");
        }

        this.fullName = fullName;
    }

    public String getFullName(){
        return this.fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pasword) {
        this.password = pasword;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
