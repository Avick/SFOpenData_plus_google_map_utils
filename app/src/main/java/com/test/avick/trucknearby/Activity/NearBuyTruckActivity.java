package com.test.avick.trucknearby.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.test.avick.trucknearby.Model.TruckVo;
import com.test.avick.trucknearby.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avickbiswas on 03/02/17.
 */

public class NearBuyTruckActivity extends BaseMapActivity implements ClusterManager.OnClusterClickListener<TruckVo>, ClusterManager.OnClusterInfoWindowClickListener<TruckVo>, ClusterManager.OnClusterItemClickListener<TruckVo>, ClusterManager.OnClusterItemInfoWindowClickListener<TruckVo>  {

    private ClusterManager<TruckVo> mClusterManager;

    private class TruckRenderer extends DefaultClusterRenderer<TruckVo> {
        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;

        public TruckRenderer() {
            super(getApplicationContext(), getMap(), mClusterManager);

            View truckProfile = getLayoutInflater().inflate(R.layout.truck_layout, null);
            mClusterIconGenerator.setContentView(truckProfile);
            mClusterImageView = (ImageView) truckProfile.findViewById(R.id.truck_icon);
            //mImageView  = (ImageView) truckProfile.findViewById(R.id.truck_icon);
            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_truck_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_truck_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
        }

        @Override
        protected void onBeforeClusterItemRendered(TruckVo truck, MarkerOptions markerOptions) {

            mImageView.setImageResource(R.drawable.truck_icon);
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title("cnn : " + truck.cnn);
        }

//        @Override
//        protected void onBeforeClusterRendered(Cluster<Person> cluster, MarkerOptions markerOptions) {
//
//        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            return cluster.getSize() > 1;
        }
    }


    @Override
    protected void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.773972, -122.431297), 11f));
        mClusterManager = new ClusterManager<TruckVo>(this, getMap());
        mClusterManager.setRenderer(new TruckRenderer());
        getMap().setOnCameraIdleListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);
        addItems();
        mClusterManager.cluster();
    }

    @Override
    public boolean onClusterClick(Cluster<TruckVo> cluster) {

        truckDetailsLayout.setVisibility(View.GONE);

        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        // Get the LatLngBounds
        final LatLngBounds bounds = builder.build();

        // Animate camera to the bounds
        try {
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<TruckVo> cluster) {

    }

    @Override
    public void onClusterItemInfoWindowClick(TruckVo truckVo) {

    }

    @Override
    public boolean onClusterItemClick(TruckVo truckVo) {
        txtApplicantName.setText("Applicant : " + truckVo.applicant);
        txtAddress.setText("Address : " + truckVo.address );
        txtTiming.setText("Timing : " + truckVo.dayshours);
        txtStatus.setText("Status: " + truckVo.status);
        txtFoodItems.setText("FoodItems: " + truckVo.fooditems);
        truckDetailsLayout.setVisibility(View.VISIBLE);
        return false;
        //return false;
    }


    private void addItems() {
        for(int i = 0; i < truckList.size(); i++) {
            truckList.get(i).setLatLong(truckList.get(i).latitude, truckList.get(i).longitude);
            mClusterManager.addItem(truckList.get(i));
        }
    }
}
