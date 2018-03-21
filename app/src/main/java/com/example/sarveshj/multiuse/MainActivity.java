package com.example.sarveshj.multiuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView=(ListView) findViewById(R.id.listView);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Timer");
        arrayList.add("TO DO List");


        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_selectable_list_item,arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:
                        Intent intent=new Intent(getApplicationContext(),TimerActivity.class);
                        startActivity(intent);
                    break;
                    case 1:
                        Intent intent1=new Intent(getApplicationContext(),ToDoActivity.class);
                        startActivity(intent1);
                    break;
                }
            }
        });

    }
}
