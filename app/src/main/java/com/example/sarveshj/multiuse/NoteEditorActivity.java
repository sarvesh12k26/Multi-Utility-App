package com.example.sarveshj.multiuse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText=(EditText) findViewById(R.id.editText);

        Intent intent=getIntent();
        noteId=intent.getIntExtra("noteId",-1);
        //when no note is added initially
        if(noteId!=-1){
            editText.setText(ToDoActivity.notes.get(noteId));
        }
        //when note is added previously, and now is to added from menu in MainActivity
        else{
            ToDoActivity.notes.add("");
            noteId=ToDoActivity.notes.size()-1;
            ToDoActivity.arrayAdapter.notifyDataSetChanged();
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ToDoActivity.notes.set(noteId,String.valueOf(charSequence));
                ToDoActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.sarveshj.multiuse", Context.MODE_PRIVATE);

                HashSet<String> set=new HashSet(ToDoActivity.notes);

                sharedPreferences.edit().putStringSet("notes",set).apply();



            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
