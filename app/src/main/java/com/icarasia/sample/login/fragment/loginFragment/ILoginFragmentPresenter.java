package com.icarasia.sample.login.fragment.loginFragment;

import android.widget.EditText;

import com.icarasia.sample.model.User;

/**
 * Created by Aveek on 07/12/2017.
 */

public interface ILoginFragmentPresenter {
    void validateFields(EditText etEmail, EditText etPassword);
}
