package com.example.splashscreen.comman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splashscreen.R;

import java.util.Calendar;

public class SignUp2ndClass extends AppCompatActivity {

    //variables
    Button nextButton,loginButton;
    ImageView backButton;
    TextView titleTextView;

    //text field variables
    RadioGroup radioGroup;
    DatePicker datePicker;
    RadioButton selectGender;
    //intent variables
    String _fullName,_userName,_email,_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2nd_class);

        //receaveing intent from 1st sign up screen
        Intent intent = getIntent();
        _fullName = intent.getStringExtra("fullName");
        _userName = intent.getStringExtra("userName");
        _email = intent.getStringExtra("email");
        _password = intent.getStringExtra("password");



        //hooks
        nextButton = findViewById(R.id.sign_up_next_button);
        backButton = findViewById(R.id.sign_up_back_button);
        loginButton = findViewById(R.id.sign_up_login_button);
        titleTextView = findViewById(R.id.sign_up_title_text);


        //field variable hooks
        radioGroup = findViewById(R.id.radio);
        datePicker = findViewById(R.id.age_picker);





        //functons
        callNextSignUpScreen();
    }

    private void callNextSignUpScreen() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if the fields are blank or not
                if(!validateGender() | !validateAge()){
                    return;
                }

                //hook to get the selected gender from radio buttons after using the radio group for the gender selection
                selectGender = findViewById(radioGroup.getCheckedRadioButtonId());
                String _gender = selectGender.getText().toString();

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                String _date = day+"/"+month+"/"+year;

                Intent intent = new Intent(getApplicationContext(),SignUp3rdClass.class);
                intent.putExtra("gender",_gender);
                intent.putExtra("date", _date);
                intent.putExtra("fullName",_fullName);
                intent.putExtra("userName",_userName);
                intent.putExtra("email",_email);
                intent.putExtra("password",_password);


                //add transition
                Pair[] pairs = new Pair[4];

                pairs[0] = new Pair<View,String>(backButton,"transition_back_arrow");
                pairs[1] = new Pair<View,String>(nextButton,"transition_next_button");
                pairs[2] = new Pair<View,String>(loginButton,"transition_login_button");
                pairs[3] = new Pair<View,String>(titleTextView,"transition_title_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this,pairs);
                    startActivity(intent,options.toBundle());
                }else {
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateGender(){
        if(radioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"please select gender!",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }

    }

    private boolean validateAge(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userYear = datePicker.getYear();
        int isAgeValid =  currentYear - userYear;
        if(isAgeValid < 14){
            Toast.makeText(this,"your age is not vlaid!",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}