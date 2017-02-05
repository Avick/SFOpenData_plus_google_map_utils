package com.test.avick.trucknearby.Activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.avick.trucknearby.Model.TruckVo;
import com.test.avick.trucknearby.R;
import com.test.avick.trucknearby.Utils.CommonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by avickbiswas on 03/02/17.
 */


public abstract class BaseMapActivity extends Activity implements OnMapReadyCallback {
    private GoogleMap mMap;
    ArrayList<TruckVo> truckList;
    LinearLayout truckDetailsLayout;
    TextView txtApplicantName, txtAddress, txtTiming, txtStatus, txtFoodItems;
    protected int getLayoutId() {
        return R.layout.map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        truckDetailsLayout = (LinearLayout) findViewById(R.id.truck_details_layout);
        txtApplicantName = (TextView) findViewById(R.id.txt_applicant_name);
        txtAddress = (TextView) findViewById(R.id.txt_truck_address);
        txtTiming = (TextView) findViewById(R.id.txt_truck_timming_hours);
        txtStatus = (TextView) findViewById(R.id.txt_truck_status);
        txtFoodItems = (TextView) findViewById(R.id.txt_food_item);
        String jsonStr = CommonUtils.getJsonFromRaw(this, R.raw.sample_truck_nearby);
        Type listType = new TypeToken<ArrayList<TruckVo>>() {}.getType();
        truckList = new Gson().fromJson(jsonStr, listType);
        setUpMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
        truckDetailsLayout.setVisibility(View.GONE);
    }




    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        startDemo();
    }

    private void setUpMap() {
        ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    protected abstract void startDemo();

    protected GoogleMap getMap() {
        return mMap;
    }
}