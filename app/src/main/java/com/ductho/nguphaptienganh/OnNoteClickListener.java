package com.ductho.nguphaptienganh;

import android.view.View;

public class OnNoteClickListener implements View.OnClickListener {
    public int id;
    public String title, description;

    public OnNoteClickListener(int id, String title,String description) {
        this.id = id;
        this.title  = title;
        this.description = description;
    }

    public void onClick(View v) {
    }
}
