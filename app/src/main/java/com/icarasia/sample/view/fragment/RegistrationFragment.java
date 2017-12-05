package com.icarasia.sample.view.fragment;

import android.content.Context;
import android.net.Uri;
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
import com.icarasia.sample.model.Validator;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Aveek on 04/12/2017.
 */

public class RegistrationFragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private EditText etRegistrationEmail,etRegistrationPassword,
            etRegistrationFirstName,etRegistrationLastName,getEtRegistrationMobile;
    private Spinner spnUserType;
    private Button btnSignUp;
    private RelativeLayout mLayout;
    private Validator mValidator;
    private ViewPager viewPager;
    private Realm realm;

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
        mValidator = new Validator();
        realm = Realm.getDefaultInstance();
        etRegistrationEmail = registrationFragment.findViewById(R.id.et_registration_email);
        etRegistrationPassword = registrationFragment.findViewById(R.id.et_registration_password);
        etRegistrationFirstName = registrationFragment.findViewById(R.id.et_registration_first_name);
        etRegistrationLastName = registrationFragment.findViewById(R.id.et_registration_last_name);
        getEtRegistrationMobile =registrationFragment.findViewById(R.id.et_registration_mobile_number);

        setUpSpinnerData();
        return registrationFragment;
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

    private void switchTab(){
        viewPager = getActivity().findViewById(
                R.id.pager_login);
        viewPager.setCurrentItem(0);
    }

    private boolean checkEmpty(){ // TODO : Check Spinner data
        if (etRegistrationEmail.getText().toString().trim().equals("") || etRegistrationPassword.getText().toString().trim().equals("")
                || getEtRegistrationMobile.getText().toString().trim().equals("")) return true;
        return false;
    }

    private void setUpSpinnerData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_usertype, R.id.txtSpn,getResources().getStringArray(R.array.usertype));
        spnUserType.setAdapter(adapter);
        spnUserType.setOnItemSelectedListener(this);
    }

    private boolean checkEmailExistence(String email){
        try {
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }

            RealmResults <User> userRealmResults = realm.where(User.class).findAll();
            for (User result : userRealmResults) {
                if (result.getEmail().equals(etRegistrationEmail.getText().toString().trim())) {
                    Log.d("email already existed: ", result.getEmail());
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean saveIntoDatabase(){
        try {

            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }

            Number currentIdNum = realm.where(User.class).max("userId");
            int nextId = currentIdNum == null? 1 : currentIdNum.intValue() + 1;

            User user = new User();
            user.setUserId(nextId);
            user.setFirstName(etRegistrationFirstName.getText().toString().trim());
            user.setLastName(etRegistrationLastName.getText().toString().trim());
            user.setEmail(etRegistrationEmail.getText().toString().trim());
            user.setPassword(etRegistrationPassword.getText().toString().trim());
            user.setMobileNumber(getEtRegistrationMobile.getText().toString().trim());
            user.setUserType(spnUserType.getSelectedItem().toString());


            realm.copyToRealmOrUpdate(user);
            realm.commitTransaction();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("error : ",e.toString());
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signup:
                if (checkEmpty()){
                    Snackbar.make(mLayout,getResources().getString(R.string.warning_form_completion),Snackbar.LENGTH_SHORT).show();
                }else if (!mValidator.validateEmail(etRegistrationEmail.getText().toString().trim())){
                    Snackbar.make(mLayout,getResources().getString(R.string.error_email_validation),Snackbar.LENGTH_SHORT).show();
                }else if (!mValidator.validatePassword(etRegistrationPassword.getText().toString().trim())){
                    Snackbar.make(mLayout,getResources().getString(R.string.error_password_validation),Snackbar.LENGTH_SHORT).show();
                }else if (!mValidator.validateMobile(getEtRegistrationMobile.getText().toString().trim())){
                    Snackbar.make(mLayout,getResources().getString(R.string.error_mobile_validation),Snackbar.LENGTH_SHORT).show();
                }else if (spnUserType.getSelectedItem().toString().equals(getResources().getString(R.string.spn_default_text))){
                    Snackbar.make(mLayout,getResources().getString(R.string.warning_spinner),Snackbar.LENGTH_SHORT).show();
                }else {
                    if (!checkEmailExistence(etRegistrationEmail.getText().toString().trim())) {
                        if (saveIntoDatabase()){
                            Snackbar.make(mLayout,getResources().getString(R.string.success_entry),Snackbar.LENGTH_SHORT).show();
                            switchTab();
                            }else {
                            Snackbar.make(mLayout,getResources().getString(R.string.failed_entry),Snackbar.LENGTH_SHORT).show();
                        }
                    }else {
                        Snackbar.make(mLayout,getResources().getString(R.string.text_email_exists),Snackbar.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (position == 0) {

        }else if (position == 1){

        }else if (position == 2){

        }else if (position == 3){

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
