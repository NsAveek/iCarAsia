package com.icarasia.sample.main;

import com.icarasia.sample.model.User;

/**
 * Created by Aveek on 06/12/2017.
 */

public interface IMainModel {
    User getUserInfo(String email) throws Exception;
    boolean updateUserMobileModel(String email,String newMobileNumber) throws Exception;
    void logoutModel(OnFinishedListener listener);
    interface OnFinishedListener {
        void onRunning();
        void onFinished();
    }
}
