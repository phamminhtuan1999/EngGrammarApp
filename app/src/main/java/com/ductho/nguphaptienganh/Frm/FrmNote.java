package com.ductho.nguphaptienganh.Frm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ductho.nguphaptienganh.Adapter.AdapterNote;
import com.ductho.nguphaptienganh.Adapter.AddActivity;
import com.ductho.nguphaptienganh.DBHelper;
import com.ductho.nguphaptienganh.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FrmNote extends Fragment {
    public static AdapterNote adapterNote;
    RecyclerView rvNote;
    RecyclerView.LayoutManager layoutManager;
    DBHelper noteDBHelperInstance;
    FloatingActionButton btnAdd;
    public FrmNote() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_note, container, false);
        setHasOptionsMenu(true);
        noteDBHelperInstance = new DBHelper(getContext());
        rvNote = view.findViewById(R.id.rv_note);
        btnAdd = view.findViewById(R.id.button_add_note);
        layoutManager = new LinearLayoutManager(getContext());
        rvNote.setLayoutManager(layoutManager);
        adapterNote = new AdapterNote(getContext(),noteDBHelperInstance);
        rvNote.setAdapter(adapterNote);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
