package com.heatcode.iwl3list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashSet;

/**
 * This shows to include a map in lite mode in a ListView.
 * Note the use of the view holder pattern with the
 * {@link com.google.android.gms.maps.OnMapReadyCallback}.
 */
public class MapsActivity extends AppCompatActivity {

    private ListFragment mList;

    private MapAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        mAdapter = new MapAdapter(this, LIST_LOCATIONS);
        mList = (ListFragment) getSupportFragmentManager().findFragmentById(android.R.id.list);
        mList.setListAdapter(mAdapter);

        // Set a RecyclerListener to clean MapView from ListView
        AbsListView lv = mList.getListView();
        lv.setRecyclerListener(mRecycleListener);

    }

    private class MapAdapter extends ArrayAdapter<NamedMissions> {

        private final HashSet<MapView> mMaps = new HashSet<MapView>();

        public MapAdapter(Context context, NamedMissions[] locations) {
            super(context, R.layout.activity_maps_vert, R.id.list_vert_text, locations);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ViewHolder holder;

            // Check if a view can be reused, otherwise inflate a layout and set up the view holder
            if (row == null) {
                // Inflate view from layout file
                row = getLayoutInflater().inflate(R.layout.activity_maps_vert, null);

                // Set up holder and assign it to the View
                holder = new ViewHolder();
                holder.mapView = (MapView) row.findViewById(R.id.list_vert_map);
                holder.title = (TextView) row.findViewById(R.id.list_vert_text);

                row.setTag(holder);

                // Initialise the MapView
                holder.initializeMapView();

                // Keep track of MapView
                mMaps.add(holder.mapView);
            } else {
                // View has already been initialised, get its holder
                holder = (ViewHolder) row.getTag();
            }

            // Get the NamedMissions for this item and attach it to the MapView
            NamedMissions item = getItem(position);
            holder.mapView.setTag(item);

            // Ensure the map has been initialised by the on map ready callback in ViewHolder.
            // If it is not ready yet, it will be initialised with the NamedMissions set as its tag
            // when the callback is received.
            if (holder.map != null) {
                // The map is already ready to be used
                setMapLocation(holder.map, item);
            }

            // Set the text label for this item
            holder.title.setText(item.name);

            return row;
        }

        /**
         * Retuns the set of all initialised {@link MapView} objects.
         *
         * @return All MapViews that have been initialised programmatically by this adapter
         */
        public HashSet<MapView> getMaps() {
            return mMaps;
        }
    }

    private static void setMapLocation(GoogleMap map, NamedMissions data) {
        // Add a marker for this item and set the camera
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(data.location, 13f));
        map.addMarker(new MarkerOptions().position(data.location));

        // Set the map type back to normal.
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }


    class ViewHolder implements OnMapReadyCallback {

        MapView mapView;

        TextView title;

        GoogleMap map;

        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(getApplicationContext());
            map = googleMap;
            NamedMissions data = (NamedMissions) mapView.getTag();
            if (data != null) {
                setMapLocation(map, data);
            }
        }

        /**
         * Initialises the MapView by calling its lifecycle methods.
         */
        public void initializeMapView() {
            if (mapView != null) {
                // Initialise the MapView
                mapView.onCreate(null);
                // Set the map ready callback to receive the GoogleMap object
                mapView.getMapAsync(this);
            }
        }

    }

    private AbsListView.RecyclerListener mRecycleListener = new AbsListView.RecyclerListener() {

        @Override
        public void onMovedToScrapHeap(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder != null && holder.map != null) {
                // Clear the map and free up resources
                holder.map.clear();
                holder.map.setMapType(GoogleMap.MAP_TYPE_NONE);
            }

        }
    };

    private static class NamedMissions {

        public final String name;

        public final LatLng location;

        NamedMissions(String name, LatLng location) {
            this.name = name;
            this.location = location;
        }
    }

    private static final NamedMissions[] LIST_LOCATIONS = new NamedMissions[]{
            new NamedMissions("Frazier History Museum", new LatLng (38.257848, -85.764523)),
            new NamedMissions("Louisville Glassworks", new LatLng(38.256315, -85.764541)),
            new NamedMissions("Louisville Slugger Museum", new LatLng(38.257083, -85.764005)),
            new NamedMissions("Kentucky Science Center", new LatLng(38.257660, -85.762665)),
            new NamedMissions("Kentucky Museum of Arts and Crafts(KMAC)", new LatLng(38.257419,	-85.762104)),
            new NamedMissions("21 C Museum Hotel", new LatLng(38.256782, -85.761776)),
            new NamedMissions("Muhammad Ali Center", new LatLng(38.258223, -85.759859)),
            new NamedMissions("Evan Williams Bourbon Experience", new LatLng(38.256668, -85.759491)),
            new NamedMissions("The Speed", new LatLng(38.218311, -85.761471))


    };

}

























































//public class MapsActivity extends AppCompatActivity {
//
//    private GoogleMap mMap;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_maps);
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(android.R.id.list);
//        mapFragment.getMapAsync(this);
//    }
//
//
//    /**
//     * Manipulates the map once available.
//     * This callback is triggered when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
//}
