package com.icarasia.sample.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.icarasia.sample.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnEditMobile,btnUserType,btnLogout;
    private TextView tvFirstName,tvSecondName,tvMobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btnEditMobile = findViewById(R.id.btn_main_edit_mobile);
        btnEditMobile.setOnClickListener(this);
        btnUserType = findViewById(R.id.btn_main_user_type);
        btnUserType.setOnClickListener(this);
        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
    }

    private void editMobileNumber(){
        //TODO: Open a dialog and edit the mobile number and save to Realm Database
    }

    private void showUserType(){
        Toast.makeText(this,getResources().getString(R.string.text_account_type),Toast.LENGTH_SHORT).show();
    }

    private void logout(){
        // TODO : Show Dialog "Logging Out " > Then move to Splash Screen Page
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_main_edit_mobile:
                editMobileNumber();
                break;
            case R.id.btn_main_user_type:
                showUserType();
                break;
            case R.id.btn_logout:
                logout();
                break;
        }
    }

}
