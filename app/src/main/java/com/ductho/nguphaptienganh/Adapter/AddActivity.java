package com.ductho.nguphaptienganh.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ductho.nguphaptienganh.DBHelper;
import com.ductho.nguphaptienganh.Frm.FrmNote;
import com.ductho.nguphaptienganh.R;

public class AddActivity extends AppCompatActivity {
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";

    Button btnAddNote,btnSave,btnDel;
    EditText edtTitle, edtDescription;
    DBHelper noteDbHelper;
    int id;
    String title, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        noteDbHelper = new DBHelper(AddActivity.this);
        edtTitle = findViewById(R.id.edt_title);
        edtDescription = findViewById(R.id.edt_description);
        btnAddNote = findViewById(R.id.btn_add_note);
        btnSave = findViewById(R.id.btn_save_note);
        btnDel = findViewById(R.id.btn_del_note);
//        getSupportActionBar().setHomeAsUpIndicator();
        Intent intent = getIntent();
        if(intent.hasExtra(KEY_ID)&&
                intent.hasExtra(KEY_TITLE)&&
                intent.hasExtra(KEY_DESCRIPTION)){
            setTitle("Chỉnh sửa ghi chú");
            btnAddNote.setVisibility(View.GONE);
            btnSave.setVisibility(View.VISIBLE);
            btnDel.setVisibility(View.VISIBLE);
            id = intent.getIntExtra(KEY_ID,-1);
            title = intent.getStringExtra(KEY_TITLE);
            description = intent.getStringExtra(KEY_DESCRIPTION);
            edtTitle.setText(title);
            edtDescription.setText(description);
        }
        else{setTitle("Thêm ghi chú");}

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
                noteDbHelper.updateNote(id,title,description);
                FrmNote.adapterNote.notifyDataSetChanged();
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteDbHelper.deleteNote(id);
                FrmNote.adapterNote.notifyDataSetChanged();
                finish();
            }
        });

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
                noteDbHelper.addNote(title,description);
                FrmNote.adapterNote.notifyDataSetChanged();
                finish();

            }
        });
    }

    private void fetchInfo() {
        title = edtTitle.getText().toString().trim();
        description = edtDescription.getText().toString().trim();
        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Hãy điền đủ tiêu đề và nội dung", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
