package com.example.splashscreen.comman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splashscreen.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

public class Forget_password extends AppCompatActivity {

    //variables
    ImageView icon;
    TextView title,description;
    TextInputLayout phoneNumberTextField;
    CountryCodePicker countryCodePicker;
    Button nextButton;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forget_password);

        //hooks
        icon = findViewById(R.id.forget_password_icon);
        title = findViewById(R.id.forget_password_title);
        description = findViewById(R.id.forget_password_description);
        phoneNumberTextField = findViewById(R.id.forget_password_phone_number);
        countryCodePicker = findViewById(R.id.forget_password_country_code_picker);
        nextButton = findViewById(R.id.forget_password_next_button);

        //animation
        animation = AnimationUtils.loadAnimation(this,R.anim.side_anim);


        //set animation to all the elements

        icon.setAnimation(animation);
        title.setAnimation(animation);
        description.setAnimation(animation);
        phoneNumberTextField.setAnimation(animation);
        countryCodePicker.setAnimation(animation);
        nextButton.setAnimation(animation);
    }

    //when user clicks the next button
    public void verifyForgetPasswordPhoneNumber(View view){

        if (!validateFields()) {
            return;
        }

        //get data from fields
        String _phoneNumber = phoneNumberTextField.getEditText().getText().toString().trim();

        if (_phoneNumber.charAt(0) == '0') {
            _phoneNumber = _phoneNumber.substring(1);
        };

        final String _completePhoneNumber = "+"+countryCodePicker.getFullNumber()+_phoneNumber;

        //check whether user exists in database or not
        Query checkUser = FirebaseDatabase.getInstance().getReference("users").orderByChild("phoneNumber").equalTo(_completePhoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //phone number is correctly virified
                if(snapshot.exists()){
                    phoneNumberTextField.setError(null);
                    phoneNumberTextField.setErrorEnabled(false);

                    Intent intent = new Intent(getApplicationContext(),VerifyOTP.class);
                    intent.putExtra("phoneNumber",_completePhoneNumber);
                    intent.putExtra("whatToDo","updateData");
                    startActivity(intent);
                    finish();
                }else {
                    phoneNumberTextField.setError("no such user exists!");
                    phoneNumberTextField.requestFocus();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private boolean validateFields() {

        String _phoneNumber = phoneNumberTextField.getEditText().getText().toString().trim();
        //String _password = password.getEditText().getText().toString().trim();

        if (_phoneNumber.isEmpty()) {
            phoneNumberTextField.setError("phone number cannot be empty!");
            phoneNumberTextField.requestFocus();
            return false;
        } else {
            phoneNumberTextField.setError(null);
            phoneNumberTextField.setErrorEnabled(false);
            //password.setErrorEnabled(false);
            return true;
        }

    }
}