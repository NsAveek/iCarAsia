package com.icarasia.sample.login.fragment.loginFragment;

import com.icarasia.sample.model.User;

/**
 * Created by Aveek on 07/12/2017.
 */

public interface ILoginFragmentModel {
    User getUserInfo(String email) throws Exception;
    boolean validateLogin(String email, String password);

}
