package com.icarasia.sample.login.fragment.registrationFragment;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.icarasia.sample.R;
import com.icarasia.sample.application.ICarAsia;
import com.icarasia.sample.login.fragment.loginFragment.ILoginFragmentView;
import com.icarasia.sample.model.Validator;

/**
 * Created by Aveek on 07/12/2017.
 */

public class RegistrationFragmentPresenterImpl implements IRegistrationFragmentPresenter{

    private IRegistrationFragmentView registrationFragmentView;
    private IRegistrationFragmentModel registrationFragmentModel;
    private Validator mValidator = new Validator();

    public RegistrationFragmentPresenterImpl(IRegistrationFragmentView registrationFragmentView, IRegistrationFragmentModel registrationFragmentModel){
        this.registrationFragmentView = registrationFragmentView;
        this.registrationFragmentModel = registrationFragmentModel;
    }


    @Override
    public void setUpSpinnerData(Context context) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,R.layout.spinner_usertype, R.id.txtSpn,context.getResources().getStringArray(R.array.usertype));
        registrationFragmentView.setupSpinnerAdapter(adapter);
        registrationFragmentView.setOnItemSelectedListener(context);
    }

    @Override
    public void validateFields(EditText etFirstName,EditText etLastName,EditText etRegistrationEmail,EditText etRegistrationPassword,EditText etRegistrationMobile, String spinnerText) {
        if (checkEmpty(etRegistrationEmail,etRegistrationPassword,etRegistrationMobile)){
            registrationFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.warning_form_completion));
        }else if (!mValidator.validateEmail(etRegistrationEmail.getText().toString().trim())){
            registrationFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.error_email_validation));
        }else if (!mValidator.validatePassword(etRegistrationPassword.getText().toString().trim())){
            registrationFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.error_password_validation));
        }else if (!mValidator.validateMobile(etRegistrationMobile.getText().toString().trim())){
            registrationFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.error_mobile_validation));
        }else if (spinnerText.equals(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.spn_default_text))){
            registrationFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.warning_spinner));
        }else {
            if (!registrationFragmentModel.checkEmailExistence(etRegistrationEmail.getText().toString().trim())) {
                if (registrationFragmentModel.saveIntoDatabase(etFirstName.getText().toString().trim(),
                        etLastName.getText().toString().trim(),etRegistrationEmail.getText().toString().trim(),etRegistrationPassword.getText().toString().trim(),
                        etRegistrationMobile.getText().toString().trim(),spinnerText)){
                    registrationFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.success_entry));
                    registrationFragmentView.switchTab();
                }else {
                    registrationFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.failed_entry));
                }
            }else {
                registrationFragmentView.showMessage(ICarAsia.getInstance().getApplicationContext().getResources().getString(R.string.text_email_exists));
            }
        }
    }
    private boolean checkEmpty(EditText etRegistrationEmail,EditText etRegistrationPassword,EditText etRegistrationMobile){ // TODO : Check Spinner data
        if (etRegistrationEmail.getText().toString().trim().equals("") || etRegistrationPassword.getText().toString().trim().equals("")
                || etRegistrationMobile.getText().toString().trim().equals("")) return true;
        return false;
    }
}
