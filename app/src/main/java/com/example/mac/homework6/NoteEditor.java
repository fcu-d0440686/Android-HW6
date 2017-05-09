package com.example.mac.homework6;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteEditor extends ActionBarActivity {

    EditText title,body;
    ArrayList<String> titlelist;
    SQLiteDatabase base;
    int pos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = (EditText)findViewById(R.id.editText);
        body = (EditText)findViewById(R.id.editText2);
        Intent intent = getIntent();
        pos = intent.getIntExtra("NOTEPOS",-1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        HBO openhelper = new HBO(this);
        base = openhelper.getWritableDatabase();

        titlelist = NoteDB.getNoteList(base);

        if (pos!=-1) {
            String Title = titlelist.get(pos);
            title.setText(Title);
            body.setText(NoteDB.getBody(base, Title));
        }
        else {
            title.setText("");
            body.setText("");
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        String Title = title.getText().toString();
        if (Title.length() == 0) {
            Toast.makeText(this, "標題不能為空白，便條無儲存",
                    Toast.LENGTH_LONG).show();
        } else {
            NoteDB.add(base, title.getText().toString(),
                    body.getText().toString());
        }
    }

    boolean isTitleExist(String Title) {
        for (int i = 0; i < titlelist.size(); i++)
            if (Title.equalsIgnoreCase(titlelist.get(i)))
                return true;
        return false;
    }


}
