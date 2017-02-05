package com.test.avick.trucknearby.Model.MapClusterModel;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by avickbiswas on 03/02/17.
 */

public class TruckData implements ClusterItem {

    public final String name;
    public final int pictureResource;


    private final LatLng mPosition;

    public TruckData(LatLng position, String name , int pictureResource) {
        this.mPosition = position;
        this.name = name;
        this.pictureResource = pictureResource;
    }


    @Override
    public LatLng getPosition() {
        return null;
    }
}
