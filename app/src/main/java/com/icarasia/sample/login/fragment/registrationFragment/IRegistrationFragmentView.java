package com.icarasia.sample.login.fragment.registrationFragment;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Aveek on 07/12/2017.
 */

public interface IRegistrationFragmentView {
    void showMessage(String message);
    void switchTab();
    void setupSpinnerAdapter(ArrayAdapter<String> adapter);
    void setOnItemSelectedListener(Context context);
}
