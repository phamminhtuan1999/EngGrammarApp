package com.ductho.nguphaptienganh.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.ductho.nguphaptienganh.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 0;
    //    SHA-1 F7:DB:2E:10:6A:24:27:60:6A:14:78:91:EE:6D:1C:A4:FD:B6:40:74
//    public static final String ID_TOKEN = "366312246126-squmjmmsqf7pemt8mm4udhbv7lpjhfh2.apps.googleusercontent.com";
    Button mBtnSignin;
    TextView mTvSignup;
    TextInputEditText mEdtEmail, mEdtPassword;
    //    Cursor cursor;
//    DBHelper dbHelper;
//    SQLiteDatabase db;
    private FirebaseAuth mAuth;

    GoogleSignInClient mGoogleSignInClient;
    SignInButton mSignInButton;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if(currentUser.getDisplayName()!=null){
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            String name = currentUser.getDisplayName();
//            intent.putExtra("name", name);
//            startActivity(intent);
//        }
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
        mSignInButton = findViewById(R.id.btnGoogleSignIn);
        mAuth = FirebaseAuth.getInstance();

        createRequest();

        mSignInButton.setOnClickListener(v -> {
            signInWithGoogle();
        });

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

    private void createRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("ABC", "firebaseAuthWithGoogle:" + account.getId());
                Log.d("ABC", "firebaseAuthWithGoogle:" + account.getIdToken());
//                Log.d("ABC", task.getException().getMessage());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("ABC", "Google sign in failed", e);
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("ABC", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                // Name, email address, and profile photo Url
//                                String name = user.getDisplayName();
//                                String email = user.getEmail();
//                                Uri photoUrl = user.getPhotoUrl();
//                                // Check if user's email is verified
//                                boolean emailVerified = user.isEmailVerified();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                intent.putExtra("name", name);
                                startActivity(intent);
//                            updateUI(user);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ABC", "signInWithCredential:failure", task.getException());
//                            Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//            Log.d("ABC", account.getDisplayName());
//            // Signed in successfully, show authenticated UI.
////            updateUI(account);
//        } catch (ApiException e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w("ABC", "signInResult:failed code=" + e.getStatusCode());
////            updateUI(null);
//        }
//    }

//    private void signup(String email, String password) {
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("ABC", "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
////                            updateUI(user);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("ABC", "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
////                            updateUI(null);
//                        }
//
//                        // ...
//                    }
//                });
//    }

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