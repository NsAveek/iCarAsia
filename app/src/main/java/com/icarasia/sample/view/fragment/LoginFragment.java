package com.icarasia.sample.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.icarasia.sample.R;
import com.icarasia.sample.main.MainActivity;
import com.icarasia.sample.model.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aveek on 04/12/2017.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private RelativeLayout mLayout;
    private Validator mValidator;

    public LoginFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DBTFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View loginFragment = inflater.inflate(R.layout.fragment_login, container, false);
        mLayout = (RelativeLayout) loginFragment.findViewById(R.id.rl_login_fragment);
        mValidator = new Validator();
        etEmail = (EditText) loginFragment.findViewById(R.id.et_login_email);
        etPassword = (EditText) loginFragment.findViewById(R.id.et_login_password);
        btnLogin = (Button) loginFragment.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        return loginFragment;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    public void onPause() {
        super.onPause();
//        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(r);
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private boolean checkEmpty() {
        if (etEmail.getText().toString().trim().equals("") || etPassword.getText().toString().trim().equals(""))
            return true;
        return false;
    }

    private void switchToHome() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("userEmail", etEmail.getText().toString().trim());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkEmpty()) {
                    Snackbar.make(mLayout, getResources().getString(R.string.warning_form_completion), Snackbar.LENGTH_LONG).show();
                } else if (!mValidator.validateEmail(etEmail.getText().toString().trim())) {
                    Snackbar.make(mLayout, getResources().getString(R.string.error_email_validation), Snackbar.LENGTH_LONG).show();
                } else if (!mValidator.validatePassword(etPassword.getText().toString().trim())) {
                    Snackbar.make(mLayout, getResources().getString(R.string.error_password_validation), Snackbar.LENGTH_LONG).show();
                } else {
                    switchToHome();
                }
                break;
        }
    }
}
