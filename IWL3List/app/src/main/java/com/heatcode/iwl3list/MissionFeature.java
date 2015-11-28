package com.heatcode.iwl3list;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by HeatherGuin on 11/27/2015.
 */
public class MissionFeature extends FrameLayout{

        public MissionFeature(Context context) {
            super(context);

            LayoutInflater layoutInflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutInflater.inflate(R.layout.feature, this);
        }

           public synchronized void setTitleId(int titleId) {
            ((TextView) (findViewById(R.id.title))).setText(titleId);
        }

        public synchronized void setDescriptionId(int descriptionId) {
            ((TextView) (findViewById(R.id.description))).setText(descriptionId);
        }

    }