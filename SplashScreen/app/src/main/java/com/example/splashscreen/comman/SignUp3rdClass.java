package com.example.splashscreen.comman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.splashscreen.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUp3rdClass extends AppCompatActivity {

    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;
    Button nextButton,loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up3rd_class);

        //hooks
        scrollView = findViewById(R.id.sign_up_3rd_screen_scroll_view);
        phoneNumber = findViewById(R.id.sign_up_phone_number);
        countryCodePicker = findViewById(R.id.country_code_picker);
        nextButton = findViewById(R.id.sign_up_next_button);
        loginButton = findViewById(R.id.sign_up_login_button);


        verifyPhoneNumber();
    }

    private void verifyPhoneNumber() {

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validatePhoneNumber()){
                    return;
                }

                //receaveing intent from 2nd sign up screen
                Intent intent = getIntent();
                String _date = intent.getStringExtra("date");
                String _gender = intent.getStringExtra("gender");
                String _userName = intent.getStringExtra("userName");
                String _fullName = intent.getStringExtra("fullName");
                String _email = intent.getStringExtra("email");
                String _passWord = intent.getStringExtra("password");

                String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();
                String _phoneNo = "+"+countryCodePicker.getFullNumber()+_getUserEnteredPhoneNumber;

                Intent thisIntent = new Intent(getApplicationContext(),VerifyOTP.class);
                thisIntent.putExtra("fullName",_fullName);
                thisIntent.putExtra("email",_email);
                thisIntent.putExtra("userName",_userName);
                thisIntent.putExtra("gender",_gender);
                thisIntent.putExtra("date",_date);
                thisIntent.putExtra("password",_passWord);
                thisIntent.putExtra("phoneNumber",_phoneNo);

                //add transition
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(scrollView,"transition_OTP_screen");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp3rdClass.this,pairs);
                    startActivity(thisIntent,options.toBundle());
                }else {
                    startActivity(thisIntent);
                }
            }
        });
    }

    private boolean validatePhoneNumber(){

        String val = phoneNumber.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            phoneNumber.setError("field cannot be left blank");
            return false;
        }else {
            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;
        }
    }
}