package com.example.splashscreen.comman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splashscreen.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPassword extends AppCompatActivity {

    //variables
    ImageView icon ;
    TextView title,description;
    TextInputLayout newPassword,confirmPassword;
    Button updateButton;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_set_new_password);

        //hooks
        icon = findViewById(R.id.set_new_password_icon);
        title = findViewById(R.id.set_new_password_title);
        description = findViewById(R.id.set_new_password_description);
        newPassword = findViewById(R.id.new_password_text_view);
        confirmPassword = findViewById(R.id.confirm_password_text_view);
        updateButton = findViewById(R.id.update_button);
        //animation hook
        animation = AnimationUtils.loadAnimation(this,R.anim.side_anim);

        //setting Animation
        icon.setAnimation(animation);
        title.setAnimation(animation);
        description.setAnimation(animation);
        newPassword.setAnimation(animation);
        confirmPassword.setAnimation(animation);
        updateButton.setAnimation(animation);
    }

    //onclick attribute function of the update button
    public void updatePasswordButton(View view){


        if(!validatePassword() | !validateConfirmPassword()){
            return;
        }

        //checking if confirm password and new password matches

        //get data from fields
        String _newPassword = newPassword.getEditText().getText().toString().trim();
        String _phoneNumber = getIntent().getStringExtra("phoneNumber");

        //update data in firebase and sessions
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.child(_phoneNumber).child("password").setValue(_newPassword);
        startActivity(new Intent(getApplicationContext(),ForgetPasswordSuccessMessage.class));
        finish();
    }

    private boolean validatePassword() {

            String _password = newPassword.getEditText().getText().toString().trim();

            if (_password.isEmpty()) {
                newPassword.setError("password cannot be empty!");
                newPassword.requestFocus();
                return false;
            } else {
                newPassword.setError(null);
                newPassword.setErrorEnabled(false);
                return true;
            }
    }

    private boolean validateConfirmPassword(){

        String _password = confirmPassword.getEditText().getText().toString().trim();

        if (_password.isEmpty()) {
            confirmPassword.setError("password cannot be empty!");
            confirmPassword.requestFocus();
            return false;
        } else {
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
            return true;
        }
    }

}