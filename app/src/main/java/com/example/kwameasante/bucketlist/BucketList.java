package com.example.kwameasante.bucketlist;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by tyty5_000 on 2/10/2016.
 */
public class BucketList extends Application {

    public ArrayList<String> marked_views = null;

    public BucketList() {
        marked_views = new ArrayList<String>();
    }

}
