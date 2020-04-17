package com.erfan.android.huskeliste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickListener {
    private EditText itemET;
    private Button btn;
    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private AdapterView<?> parent;
    private View view;
    private int position;
    private long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemET =findViewById(R.id.item_edit_text);
        btn=findViewById(R.id.add_btn);
        itemsList = findViewById(R.id.items_list);

        items = FileHelper.readData(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);

        btn.setOnClickListener(this);

        itemsList.setOnItemClickListener(this);


    }
  public void onClick(View v){
        switch (v.getId()){
            case R.id.add_btn:
                String itemEntered = itemET.getText().toString();
                adapter.add(itemEntered);
                itemET.setText("");

                try {
                    FileHelper.writeData(items, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(this, "Varen lagt til", Toast.LENGTH_SHORT).show();
            break;
        }
  }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        try {
            FileHelper.writeData(items, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Slette", Toast.LENGTH_SHORT).show();
    }


}
