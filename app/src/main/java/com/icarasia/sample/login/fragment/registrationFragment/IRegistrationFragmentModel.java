package com.icarasia.sample.login.fragment.registrationFragment;

/**
 * Created by Aveek on 07/12/2017.
 */

public interface IRegistrationFragmentModel {

    boolean checkEmailExistence(String email);
    boolean saveIntoDatabase(String firstName,String lastName,String email,String pass,String mobile,String userType);
}
