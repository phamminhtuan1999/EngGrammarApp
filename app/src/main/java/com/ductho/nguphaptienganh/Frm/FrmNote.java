package com.ductho.nguphaptienganh.Frm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ductho.nguphaptienganh.Adapter.AdapterNote;
import com.ductho.nguphaptienganh.Activity.AddActivity;
import com.ductho.nguphaptienganh.DBHelper;
import com.ductho.nguphaptienganh.Model.BaiHoc;
import com.ductho.nguphaptienganh.Model.Note;
import com.ductho.nguphaptienganh.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FrmNote extends Fragment {
    public static AdapterNote adapterNote;
    static List<Note> notes;
    static List<Note> copyNotes;
    RecyclerView rvNote;
    RecyclerView.LayoutManager layoutManager;
    static DBHelper noteDBHelperInstance;
    FloatingActionButton btnAdd;
    public FrmNote() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_note, container, false);
        setHasOptionsMenu(true);
        noteDBHelperInstance = new DBHelper(getContext());
        notes = noteDBHelperInstance.getAllNotes();
        copyNotes = null;
        rvNote = view.findViewById(R.id.rv_note);
        btnAdd = view.findViewById(R.id.button_add_note);
        layoutManager = new LinearLayoutManager(getContext());
        rvNote.setLayoutManager(layoutManager);
        adapterNote = new AdapterNote(getContext(),notes);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) itemSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notes.clear();
                if(newText.equals("")){
                    notes.addAll(copyNotes);
                }else {
                    for(Note note:copyNotes){
                        if(note.getTitle().toLowerCase().contains(newText)){
                            notes.add(note);
                        }
                    }
                }
                adapterNote.notifyDataSetChanged();
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    public static void setList(){
        copyNotes = noteDBHelperInstance.getAllNotes();
        notes.clear();
        notes.addAll(copyNotes);
    }
}
