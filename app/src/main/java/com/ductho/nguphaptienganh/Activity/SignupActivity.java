package com.ductho.nguphaptienganh.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ductho.nguphaptienganh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {
    TextInputEditText mEdtName, mEdtEmail, mEdtPassword, mEdtConfirmPass;
    Button mBtnSignIn;
    ImageView rback;
//    Cursor cursor;
    SQLiteDatabase db;
    CheckBox rshow;
//    DBHelper dbHelper;
    private FirebaseAuth mAuth;
    String mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
//        dbHelper = new DBHelper(this);

        mEdtName = (TextInputEditText) findViewById(R.id.name);
        mEdtPassword = (TextInputEditText) findViewById(R.id.password);
        mEdtConfirmPass = (TextInputEditText) findViewById(R.id.cnfrmpassword);
        mEdtEmail = (TextInputEditText) findViewById(R.id.user);
        rshow = (CheckBox) findViewById(R.id.rshowPass);
        mBtnSignIn = (Button) findViewById(R.id.signin);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mEdtPassword.setTypeface(getResources().getFont(R.font.utm_avo_regular));
            mEdtConfirmPass.setTypeface(getResources().getFont(R.font.utm_avo_regular));
        }
        showPass();
    }

    public void signinBtn(View view) {
        mName = mEdtName.getText().toString().trim();
        String username = mEdtEmail.getText().toString().trim();
        String password = mEdtPassword.getText().toString().trim();
        String confirmPassword = mEdtConfirmPass.getText().toString().trim();
        if(mName.equals("")||
                username.equals("")||
                password.equals("")||
                confirmPassword.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
            return;
        }

        // check if both password matches
        if(!password.equals(confirmPassword))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
        }



        else {
//            dbHelper.addUser(mEdtName.getText().toString(),
//                    mEdtUserName.getText().toString(), mEdtPassword.getText().toString(),
//                    mEdtConfirmPass.getText().toString());
            signup(username,password);


        }

    }

    private void signup(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("ABC", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(mName)
//                                        .setPhotoUri(Uri.parse("https://cloud.addictivetips.com/wp-content/uploads/2011/02/snap20110215_144050.jpg"))
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
//                                                    Toast.makeText(SignupActivity.this, "Cap nhat thong tin thanh cong", Toast.LENGTH_SHORT).show();
                                                } else {
//                                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                Toast.makeText(SignupActivity.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ABC", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void showPass(){
        rshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mEdtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mEdtConfirmPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    mEdtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mEdtConfirmPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    public void onBackPressed()
    {
//        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//        startActivity(intent);
        finish();
    }
}
