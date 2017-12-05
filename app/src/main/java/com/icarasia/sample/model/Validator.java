package com.icarasia.sample.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aveek on 04/12/2017.
 */

public class Validator {
    private Pattern pattern;
    private Matcher matcher;
    private String emailPattern =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String passPattern ="^(?=.{8,})(?=.*[@#$%^&+=]).*$";
    private String mobilePattern ="^[+]?6?01\\d{8}$";

    public boolean validateEmail(String email){
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean validatePassword (String password){
        pattern = Pattern.compile(passPattern);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public boolean validateMobile (String mobile){
        pattern = Pattern.compile(mobilePattern);
        matcher = pattern.matcher(mobile);
        return matcher.matches();
    }
}
