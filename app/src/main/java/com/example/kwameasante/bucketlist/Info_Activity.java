package com.example.kwameasante.bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class Info_Activity extends AppCompatActivity {

    private String current_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String info_string = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("info");
            current_id = value;
            String info = "info" + current_id;
            int string_id = this.getResources().getIdentifier(info, "string", this.getPackageName());
            info_string = getString(string_id);
        //    Log.i("string", info_string);
        }

        TextView changeView = (TextView) findViewById(R.id.info_text);
        changeView.setText(info_string);

    }





    public void finish_item(View view) {
        Button b = (Button) view;
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        String button_tag = b.getTag().toString();
        i.putExtra("button_tag", button_tag);
        i.putExtra("prev_id", current_id);
        startActivity(i);
    }

    public void still_working(View view) {
        Button b = (Button) view;
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        String button_tag = b.getTag().toString();
        i.putExtra("button_tag", button_tag);
        i.putExtra("prev_id", current_id);
        startActivity(i);
    }

}
