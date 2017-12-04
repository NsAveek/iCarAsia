package com.icarasia.sample.view.fragment;

import android.content.Context;
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
import android.widget.Spinner;

import com.icarasia.sample.R;

/**
 * Created by Aveek on 04/12/2017.
 */

public class RegistrationFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private EditText etRegistrationEmail,etRegistrationPassword,
            etRegistrationFirstName,etRegistrationLastName,getEtRegistrationMobile;
    private Spinner spnUserType;
    private Button btnSignUp;
    private RelativeLayout mLayout;
    public RegistrationFragment() {

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
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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
        View loginFragment = inflater.inflate(R.layout.fragment_registration, container, false);
        mLayout = (RelativeLayout) loginFragment.findViewById(R.id.rl_registration_fragment);
        btnSignUp = loginFragment.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(this);
        etRegistrationEmail = loginFragment.findViewById(R.id.et_registration_email);
        etRegistrationPassword = loginFragment.findViewById(R.id.et_registration_password);
        etRegistrationFirstName = loginFragment.findViewById(R.id.et_registration_first_name);
        etRegistrationLastName = loginFragment.findViewById(R.id.et_registration_last_name);
        getEtRegistrationMobile =loginFragment.findViewById(R.id.et_registration_mobile_number);
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
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private boolean checkEmpty(){ // TODO : Check Spinner data
        if (etRegistrationEmail.getText().toString().trim().equals("") || etRegistrationPassword.getText().toString().trim().equals("")
                || getEtRegistrationMobile.getText().toString().trim().equals("")) return true;
        return false;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signup:
                if (checkEmpty()){
                    Snackbar.make(mLayout,"Please complete the form",Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
