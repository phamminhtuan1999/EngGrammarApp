package com.ductho.nguphaptienganh.Model;

import android.provider.BaseColumns;

public class NoteContract {
    private NoteContract(){};
    public static final class NoteEntry implements BaseColumns {
        public static final String NOTE_TABLE = "notes";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
    }

}
