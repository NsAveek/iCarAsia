package com.icarasia.sample.login.fragment.registrationFragment;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by Aveek on 07/12/2017.
 */

public interface IRegistrationFragmentPresenter {
    void setUpSpinnerData(Context context);
    void validateFields(EditText firstName,EditText lastName,EditText etRegistrationEmail, EditText etRegistrationPassword, EditText etRegistrationMobile, String s);
}
