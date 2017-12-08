package com.icarasia.sample.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.icarasia.sample.R;
import com.icarasia.sample.splash.SplashActivity;

public class MainActivity extends AppCompatActivity implements IMainView,View.OnClickListener{

    private Button btnEditMobile,btnUserType,btnLogout;
    private TextView tvFirstName,tvSecondName,tvMobileNumber;
    private String loggedInUserEmail;
    private IMainPresenter presenter;
    private AlertDialog alertDialog;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        loggedInUserEmail = getIntent().getStringExtra("userEmail");
        alertDialog = new AlertDialog.Builder(this).create();
        btnEditMobile = findViewById(R.id.btn_main_edit_mobile);
        btnEditMobile.setOnClickListener(this);
        btnUserType = findViewById(R.id.btn_main_user_type);
        btnUserType.setOnClickListener(this);
        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
        tvFirstName=findViewById(R.id.tv_main_first_name);
        tvSecondName=findViewById(R.id.tv_main_last_name);
        tvMobileNumber = findViewById(R.id.tv_main_mobile);
        presenter = new MainPresenterImpl(this,new MainModel());
        presenter.setTextViewValues(loggedInUserEmail);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_main_edit_mobile:
                presenter.editMobileNumber();
                break;
            case R.id.btn_main_user_type:
                showMessage(presenter.showUserType(loggedInUserEmail));
                break;
            case R.id.btn_logout:
                presenter.logout();
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void switchActivity() {
        if (alertDialog!=null) alertDialog.dismiss();
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showDialog(String text) {
        if (alertDialog!=null) {
            alertDialog.setMessage(text);
            alertDialog.show();
        }
    }

    @Override
    public void editMobileDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        final View view = layoutInflater.inflate(R.layout.dialog_edit_mobile, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Please edit your mobile number");
        alertDialog.setCancelable(false);

        final EditText etComments = view.findViewById(R.id.etComments);
        try {
            etComments.setText(presenter.getUserInfo(loggedInUserEmail).getMobileNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.updateMobileNumber(loggedInUserEmail,etComments.getText().toString().trim());
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertDialog.setView(view);
        alertDialog.show();
    }

    @Override
    public void assignTextValues(String firstName, String lastName, String mobile) {
        tvFirstName.setText(firstName);
        tvSecondName.setText(lastName);
        tvMobileNumber.setText(mobile);
    }


    @Override
    protected void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
    }
}
