package com.icarasia.sample.login.fragment.loginFragment;

import android.widget.EditText;

import com.icarasia.sample.R;
import com.icarasia.sample.application.ICarAsia;
import com.icarasia.sample.model.User;
import com.icarasia.sample.model.Validator;

/**
 * Created by Aveek on 07/12/2017.
 */

public class LoginFragmentPresenterImpl implements ILoginFragmentPresenter {

    private ILoginFragmentView loginFragmentView;
    private ILoginFragmentModel loginFragmentModel;
    private Validator mValidator = new Validator();

    public LoginFragmentPresenterImpl(ILoginFragmentView loginFragmentView, ILoginFragmentModel loginFragmentModel) {
        this.loginFragmentView = loginFragmentView;
        this.loginFragmentModel = loginFragmentModel;
    }


    @Override
    public void validateFields(EditText etEmail, EditText etPassword) {
        if (checkEmpty(etEmail,etPassword)) {
            loginFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.warning_form_completion));
        } else if (!mValidator.validateEmail(etEmail.getText().toString().trim())) {
            loginFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.error_email_validation));
        } else if (!mValidator.validatePassword(etPassword.getText().toString().trim())) {
            loginFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.error_password_validation));
        } else {
            if (loginFragmentModel.validateLogin(etEmail.getText().toString().trim(),etPassword.getText().toString().trim())){
                loginFragmentView.switchActivity();
            }else {
                loginFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.error_invalid_auth));
            }
        }
    }

    private boolean checkEmpty(EditText etEmail, EditText etPassword){
        if (etEmail.getText().toString().trim().equals("") || etPassword.getText().toString().trim().equals(""))
            return true;
        return false;
    }
}
