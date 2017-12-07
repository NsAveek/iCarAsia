package com.icarasia.sample.main;

import com.icarasia.sample.model.User;

/**
 * Created by Aveek on 06/12/2017.
 */

public interface IMainPresenter {

    void onResume();
    void onDestroy();
    void editMobileNumber();
    void updateMobileNumber(String email,String newMobileNumber);
    String showUserType(String email);
    User getUserInfo(String email) throws Exception;
    boolean logout();

}
