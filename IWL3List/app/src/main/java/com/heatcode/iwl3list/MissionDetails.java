package com.heatcode.iwl3list;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by HeatherGuin on 11/27/2015.
 */
public class MissionDetails {

    public final int titleId;

    public final int descriptionId;


    public final Class<? extends AppCompatActivity> activityClass;

    public MissionDetails(
            int titleId, int descriptionId, Class<? extends AppCompatActivity> activityClass) {
        this.titleId = titleId;
        this.descriptionId = descriptionId;
        this.activityClass = activityClass;
    }

}
