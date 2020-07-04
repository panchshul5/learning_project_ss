package com.example.splashscreen.comman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splashscreen.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    //variables
    Button nextButton, loginButton;
    ImageView backButton;
    TextView titleTextView;

    //sign up field variables
    TextInputLayout fullName, userName, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_sign_up);

        //hooks
        nextButton = findViewById(R.id.sign_up_next_button);
        backButton = findViewById(R.id.sign_up_back_button);
        loginButton = findViewById(R.id.sign_up_login_button);
        titleTextView = findViewById(R.id.sign_up_title_text);


        //sign up field variables hooks
        fullName = findViewById(R.id.sign_up_full_name);
        userName = findViewById(R.id.sign_up_user_name);
        email = findViewById(R.id.sign_up_email_text);
        password = findViewById(R.id.sign_up_password_text);



        //functons
        callNextSignUpScreen();
    }

    private void callNextSignUpScreen() {


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if the fields are filled or not;
                if(!validateFullName() | !validateUserName() | !validateEmail() | !validatePassword()){
                    return;
                }
                String _fullName = fullName.getEditText().getText().toString();
                String _userName = userName.getEditText().getText().toString();
                String _email = email.getEditText().getText().toString();
                String _password = password.getEditText().getText().toString();
               // String _fullName = fullName.getEditText().getText().toString();

                Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);
                intent.putExtra("fullName",  _fullName);
                intent.putExtra("userName",  _userName);
                intent.putExtra("email",  _email);
                intent.putExtra("password", _password);

                //add transition
                Pair[] pairs = new Pair[4];

                pairs[0] = new Pair<View, String>(backButton, "transition_back_arrow");
                pairs[1] = new Pair<View, String>(nextButton, "transition_next_button");
                pairs[2] = new Pair<View, String>(loginButton, "transition_login_button");
                pairs[3] = new Pair<View, String>(titleTextView, "transition_title_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateFullName() {

        String val = fullName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            fullName.setError("Field cannot be Empty!");
            return false;
        } else {
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateUserName() {

        String val = userName.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,20}\\z";
        if (val.isEmpty()) {
            userName.setError("Field cannot be Empty!");
            return false;
        } else if (val.length() > 20) {
            userName.setError("username is too large!");
            return false;
        } else if (!val.matches(checkspaces)) {
            userName.setError("no white spaces allowed!");
            return false;
        } else {
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {

        String val = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field cannot be Empty!");
            return false;
        } else if (!val.matches(checkEmail)) {
            email.setError("Invalid Email!");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {

        String val = password.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            password.setError("Field cannot be Empty!");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}