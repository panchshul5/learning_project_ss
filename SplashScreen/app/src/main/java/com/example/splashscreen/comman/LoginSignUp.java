package com.example.splashscreen.comman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.splashscreen.R;

public class LoginSignUp extends AppCompatActivity {

    //variables
    Button loginButton,joinUsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);


        //hooks
        loginButton = findViewById(R.id.sign_up_login_button);
        joinUsButton = findViewById(R.id.join_us_button);


        //button functions
        openLoginPage();
        openSignUpPage();

    }

    private void openSignUpPage() {

        joinUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(joinUsButton,"transition_join_us_button");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginSignUp.this,pairs);
                    startActivity(intent,options.toBundle());
                }else{
                    startActivity(intent);
                }

            }
        });
    }

    private void openLoginPage() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(loginButton,"transition_login");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginSignUp.this,pairs);
                    startActivity(intent,options.toBundle());
                }else{
                    startActivity(intent);
                }

            }
        });
    }
}