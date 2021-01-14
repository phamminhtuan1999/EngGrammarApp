package com.ductho.nguphaptienganh.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ductho.nguphaptienganh.MainActivity;
import com.ductho.nguphaptienganh.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button mBtnSignin;
    TextView mTvSignup;
    TextInputEditText mEdtEmail, mEdtPassword;
    //    Cursor cursor;
//    DBHelper dbHelper;
//    SQLiteDatabase db;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser.getDisplayName()!=null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            String name = currentUser.getDisplayName();
            intent.putExtra("name", name);
            startActivity(intent);
        }
//        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        dbHelper = new DBHelper(this);
//        db = dbHelper.getReadableDatabase();
        mEdtEmail = findViewById(R.id.text_input_username);
        mEdtPassword = findViewById(R.id.text_input_password);
        mBtnSignin = findViewById(R.id.button_sign_in);
        mTvSignup = findViewById(R.id.tv_sign_up);
        mAuth = FirebaseAuth.getInstance();

        mBtnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cursor = db.rawQuery("SELECT * FROM " + DBHelper.USER_TABLE + " WHERE "
//                                + DBHelper.COLUMN_USERNAME + " =? AND " + DBHelper.COLUMN_PASSWORD + " =?",
//                        new String[]{username1.getText().toString(), pass.getText().toString()});
//
//                if (username1.getText().toString().equals("") ||
//                        pass.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Username and Password can't be empty", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                if (cursor != null) {
//                    if (cursor.getCount() > 0) {
//                        cursor.moveToFirst();
//                        String name = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
//                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!",
//                                Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        intent.putExtra("name",name);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(LoginActivity.this, "Invalid username or password!",
//                                Toast.LENGTH_SHORT).show();
//
//                    }
//                }
                String email = LoginActivity.this.mEdtEmail.getText().toString();
                String password = mEdtPassword.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        // Name, email address, and profile photo Url
                                        String name = user.getDisplayName();
                                        String email = user.getEmail();
                                        Uri photoUrl = user.getPhotoUrl();
                                        // Check if user's email is verified
                                        boolean emailVerified = user.isEmailVerified();
                                        intent.putExtra("name", name);
                                        startActivity(intent);
                                    }

                                } else {
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        mTvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
//                String email = LoginActivity.this.mEdtEmail.getText().toString();
//                String password = mEdtPassword.getText().toString();
//                signup(email, password);
            }
        });
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
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ABC", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Thoát ứng dụng")
                .setMessage("Bạn chắc chắn muốn thoát ứng dụng?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton("Không", null).show();
    }
}