package com.heatcode.iwl3list;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static class CustomArrayAdapter extends ArrayAdapter<MissionDetails> {


        public CustomArrayAdapter(Context context) {
            super(context, R.layout.feature, R.id.title);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MissionFeature missionFeature;
            if (convertView instanceof MissionFeature) {
                missionFeature = (MissionFeature) convertView;
            } else {
                missionFeature = new MissionFeature(getContext());
            }

            MissionDetails missionDetails = getItem(position);

            missionFeature.setTitleId(missionDetails.titleId);
            missionFeature.setDescriptionId(missionDetails.descriptionId);

            Resources resources = getContext().getResources();
            String title = resources.getString(missionDetails.titleId);
            String description = resources.getString(missionDetails.descriptionId);
            missionFeature.setContentDescription(title + ". " + description);

            return missionFeature;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list  = (ListView) findViewById(android.R.id.list);
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked);
        list.setAdapter(adapter);
        list.setOnClickListener((View.OnClickListener) this);
        list.setEmptyView(findViewById(android.R.id.empty));
        }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MissionDetails missionDetails =
                        (MissionDetails) parent.getAdapter().getItem(position);
                startActivity(new Intent(this, missionDetails.activityClass));
    }
}






