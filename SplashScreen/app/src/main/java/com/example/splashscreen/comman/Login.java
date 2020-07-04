package com.example.splashscreen.comman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import com.example.splashscreen.LocationOwner.RetailerDashboard;
import com.example.splashscreen.R;
import com.example.splashscreen.databases.SessionManager;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    //field variablies
    CountryCodePicker countryCodePicker;
    TextInputLayout phoneNumber, password;
    CheckBox rememberMe;
    EditText phoneNumberEditText;
    EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);

        //hooks
        countryCodePicker = findViewById(R.id.login_country_code_picker);
        phoneNumber = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);
        rememberMe = findViewById(R.id.login_checkbox);
        phoneNumberEditText = findViewById(R.id.login_phone_number_edit_text);
        passwordEditText = findViewById(R.id.login_password_edit_text);

        //check if phone number and password is already filled in the phone number and password text view
        SessionManager sessionManager = new SessionManager(Login.this,SessionManager.SESSION_REMEMBERME);
        if(sessionManager.checkRememberMe()){
            HashMap<String,String> userDetails = sessionManager.rememberMeDetailsFromSession();
            phoneNumberEditText.setText(userDetails.get(sessionManager.KEY_SESSIONPHONENUMBER));
            passwordEditText.setText(userDetails.get(sessionManager.KEY_SESSIONPASSWORD));

        }




        }
    public void letTheUserLogin(View v) {

        if (!validateFields()) {
            return;
        }

        //get data
        final String _password = password.getEditText().getText().toString().trim();
        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();

        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        }

        final String completePhoneNumber = "+" + countryCodePicker.getFullNumber() + _phoneNumber;

        if(rememberMe.isChecked()){
            SessionManager sessionManager = new SessionManager(Login.this,SessionManager.SESSION_REMEMBERME);
            sessionManager.createRememberMeSession(_phoneNumber,_password);
        }


        //verifying from database
        Query checkUser = FirebaseDatabase.getInstance().getReference("users").orderByChild("phoneNumber").equalTo(completePhoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);

                    String systemPassword = dataSnapshot.child(completePhoneNumber).child("password").getValue(String.class);

                    if (systemPassword.equals(_password)) {
                        password.setError(null);
                        password.setErrorEnabled(false);

                        String _fullName = dataSnapshot.child(completePhoneNumber).child("fullName").getValue(String.class);
                        String _email = dataSnapshot.child(completePhoneNumber).child("email").getValue(String.class);
                        String _userName = dataSnapshot.child(completePhoneNumber).child("userName").getValue(String.class);
                        String _phoneNumber = dataSnapshot.child(completePhoneNumber).child("phoneNumber").getValue(String.class);
                        String _dateOfBirth = dataSnapshot.child(completePhoneNumber).child("date").getValue(String.class);
                        String _gender = dataSnapshot.child(completePhoneNumber).child("gender").getValue(String.class);
                        String _userPassword = dataSnapshot.child(completePhoneNumber).child("password").getValue(String.class);

                        //Toast.makeText(Login.this, _fullName + "\n" + _gender, Toast.LENGTH_SHORT).show();

                        //create session
                        SessionManager sessionManager = new SessionManager(Login.this,SessionManager.SESSION_USERSESSION);
                        sessionManager.createLoginSession(_fullName,_userName,_email,_phoneNumber,_userPassword,_dateOfBirth,_gender);

                        startActivity(new Intent(getApplicationContext(), RetailerDashboard.class));


                    } else {
                        //password.setError("pass you entered is incorrect");
                        Toast.makeText(Login.this, "password is wrong!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "no Such User Exists!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void callForgetPasswordScreen(View view){
        startActivity(new Intent(getApplicationContext(),Forget_password.class));
    }

    private boolean validateFields() {

        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        String _password = password.getEditText().getText().toString().trim();

        if (_phoneNumber.isEmpty()) {
            phoneNumber.setError("phone number cannot be empty!");
            phoneNumber.requestFocus();
            return false;
        } else if (_password.isEmpty()) {
            password.setError("password cannot be empty!");
            password.requestFocus();
            return false;
        } else {
            phoneNumber.setError(null);
            password.setError(null);
            phoneNumber.setErrorEnabled(false);
            password.setErrorEnabled(false);
            return true;
        }

    }
}