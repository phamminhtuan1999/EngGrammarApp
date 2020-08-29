package com.ductho.nguphaptienganh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ductho.nguphaptienganh.Activity.AddActivity;
import com.ductho.nguphaptienganh.DBHelper;
import com.ductho.nguphaptienganh.Model.Note;
import com.ductho.nguphaptienganh.OnNoteClickListener;
import com.ductho.nguphaptienganh.R;

import java.util.List;

import static com.ductho.nguphaptienganh.Activity.AddActivity.*;


public class AdapterNote extends RecyclerView.Adapter<AdapterNote.NoteViewHolder> {
    private Context mContext;
//    private DBHelper mNoteDBHelper;
    private List<Note> mNotes;

    public AdapterNote(Context context, List<Note> notes) {
        mContext = context;
        mNotes = notes;
//        mNoteDBHelper = productDBHelper;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
//        Note currentNote = mNoteDBHelper.query(position);
        Note currentNote = mNotes.get(position);
        int ID = currentNote.getID();
        String title = currentNote.getTitle();
        final String description = currentNote.getDescription();



        holder.tvID.setText(String.valueOf(ID));
        holder.tvTitle.setText(title);
        holder.tvDescription.setText(description);
        holder.cvNote.setOnClickListener(new OnNoteClickListener(ID, title, description) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddActivity.class);
                intent.putExtra(KEY_ID, id);
                intent.putExtra(KEY_TITLE, title);
                intent.putExtra(KEY_DESCRIPTION, description);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
//        return (int) mNoteDBHelper.count();
        return mNotes.size();
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvID, tvTitle, tvDescription;
        CardView cvNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            cvNote = itemView.findViewById(R.id.cv_note);
        }
    }

}
