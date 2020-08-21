package com.ductho.nguphaptienganh.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ductho.nguphaptienganh.DBHelper;
import com.ductho.nguphaptienganh.R;

public class SignupActivity extends AppCompatActivity {
    EditText name, user1, pass, cnfrmpass;
    Button signin;
    ImageView rback;
    Cursor cursor;
    SQLiteDatabase db;
    CheckBox rshow;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dbHelper = new DBHelper(this);

        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.password);
        cnfrmpass = (EditText) findViewById(R.id.cnfrmpassword);
        user1 = (EditText) findViewById(R.id.user);
        rshow = (CheckBox) findViewById(R.id.rshowPass);
        signin = (Button) findViewById(R.id.signin);

        showPass();



    }

    public void signinBtn(View view) {

        if(name.getText().toString().equals("")||
                user1.getText().toString().equals("")||
                pass.getText().toString().equals("")||cnfrmpass.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
            return;
        }

        // check if both password matches
        if(!pass.getText().toString().equals(cnfrmpass.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
        }



        else {
            dbHelper.addUser(name.getText().toString(),
                    user1.getText().toString(), pass.getText().toString(),
                    cnfrmpass.getText().toString());

            Toast.makeText(SignupActivity.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }

    public void showPass(){
        rshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    cnfrmpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    cnfrmpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    public void onBackPressed()
    {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
