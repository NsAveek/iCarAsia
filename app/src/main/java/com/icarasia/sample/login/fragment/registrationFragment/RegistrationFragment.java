package com.icarasia.sample.login.fragment.registrationFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.icarasia.sample.R;
import com.icarasia.sample.model.User;

import io.realm.RealmResults;

/**
 * Created by Aveek on 04/12/2017.
 */

public class RegistrationFragment extends Fragment implements IRegistrationFragmentView,View.OnClickListener,AdapterView.OnItemSelectedListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private EditText etRegistrationEmail,etRegistrationPassword,
            etRegistrationFirstName,etRegistrationLastName, etRegistrationMobile;
    private Spinner spnUserType;
    private Button btnSignUp;
    private RelativeLayout mLayout;
    private ViewPager viewPager;
    private IRegistrationFragmentPresenter presenter;

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

        View registrationFragment = inflater.inflate(R.layout.fragment_registration, container, false);
        mLayout = registrationFragment.findViewById(R.id.rl_registration_fragment);
        spnUserType= registrationFragment.findViewById(R.id.spn_registration_user_type);
        btnSignUp = registrationFragment.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(this);
        etRegistrationEmail = registrationFragment.findViewById(R.id.et_registration_email);
        etRegistrationPassword = registrationFragment.findViewById(R.id.et_registration_password);
        etRegistrationFirstName = registrationFragment.findViewById(R.id.et_registration_first_name);
        etRegistrationLastName = registrationFragment.findViewById(R.id.et_registration_last_name);
        etRegistrationMobile =registrationFragment.findViewById(R.id.et_registration_mobile_number);

        presenter = new RegistrationFragmentPresenterImpl(this,new RegistrationFragmentModel());
        presenter.setUpSpinnerData(getActivity());

        return registrationFragment;
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

    public void switchTab(){
        viewPager = getActivity().findViewById(
                R.id.pager_login);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signup:
                presenter.validateFields(etRegistrationFirstName,etRegistrationLastName,etRegistrationEmail,etRegistrationPassword,etRegistrationMobile,spnUserType.getSelectedItem().toString());
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//        if (position == 0) {
//
//        }else if (position == 1){
//
//        }else if (position == 2){
//
//        }else if (position == 3){
//
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void setupSpinnerAdapter(ArrayAdapter<String> adapter){
        spnUserType.setAdapter(adapter);
    }

    @Override
    public void setOnItemSelectedListener(Context context){
        spnUserType.setOnItemSelectedListener(this);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(mLayout,message,Snackbar.LENGTH_LONG).show();
    }
    @Override
    public void clearFields(){
        etRegistrationEmail.setText("");
        etRegistrationPassword.setText("");
        etRegistrationMobile.setText("");
        etRegistrationFirstName.setText("");
        etRegistrationLastName.setText("");
        spnUserType.setSelection(0);
    }
}
