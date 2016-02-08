package com.example.kwameasante.bucketlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;
import android.graphics.Paint;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<String> marked_views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        if(savedInstanceState != null) {
            marked_views = savedInstanceState.getStringArrayList("marked_views");

        }else{

            marked_views = new ArrayList<String>();
        }

        for (int i = 1; i < 10; i++) {
            String view_id = "textView" + Integer.toString(i);
            if(marked_views.contains(view_id)) {
                int tv_id = getResources().getIdentifier(view_id, "id", this.getPackageName());
                TextView tv = (TextView)findViewById(tv_id);
                tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String button_tag = extras.getString("button_tag");
            String prev_id = extras.getString("prev_id");

            int t_id = getResources().getIdentifier("textView" + prev_id, "id", this.getPackageName());
            TextView t = (TextView)findViewById(t_id);
            if(button_tag.equals("done")) {
                t.setPaintFlags(t.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                marked_views.add("textView" + prev_id);
            } else {
                t.setPaintFlags(t.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                for(int i = 0; i < marked_views.size(); i++) {
                    if(marked_views.get(i).equals("textView" + prev_id)) {
                        marked_views.remove(i);
                    }
                }
            }
        }



    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("marked_views", marked_views);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

       // marked_views = savedInstanceState.getStringArrayList("marked_views");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void strike(View view){
        TextView t = (TextView) view;
        Intent i = new Intent(getApplicationContext(), Info_Activity.class);
        String view_id = t.getTag().toString();
        String id_num = view_id.substring(view_id.length() - 1, view_id.length());

        String item_text = t.getText().toString();

        i.putExtra("info", id_num);
        i.putExtra("item_text", item_text);

        if((t.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) > 0) {
            i.putExtra("completed", "yes");
        } else {
            i.putExtra("completed", "no");
        }



        startActivity(i);



        /*
        if((t.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) > 0)
            t.setPaintFlags(t.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
        else
            t.setPaintFlags(t.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        */

    }




}
