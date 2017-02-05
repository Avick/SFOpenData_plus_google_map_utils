package com.test.avick.trucknearby.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by avickbiswas on 02/02/17.
 */

public class TruckVo  implements ClusterItem{

    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("applicant")
    @Expose
    public String applicant;
    @SerializedName("approved")
    @Expose
    public String approved;
    @SerializedName("block")
    @Expose
    public String block;
    @SerializedName("blocklot")
    @Expose
    public String blocklot;
    @SerializedName("cnn")
    @Expose
    public String cnn;
    @SerializedName("dayshours")
    @Expose
    public String dayshours;
    @SerializedName("expirationdate")
    @Expose
    public String expirationdate;
    @SerializedName("facilitytype")
    @Expose
    public String facilitytype;
    @SerializedName("fooditems")
    @Expose
    public String fooditems;
    @SerializedName("latitude")
    @Expose
    public String latitude;
//    @SerializedName("location")
//    @Expose
//    public LocationVO location;
    @SerializedName("locationdescription")
    @Expose
    public String locationdescription;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("lot")
    @Expose
    public String lot;
    @SerializedName("objectid")
    @Expose
    public String objectid;
    @SerializedName("permit")
    @Expose
    public String permit;
    @SerializedName("priorpermit")
    @Expose
    public String priorpermit;
    @SerializedName("received")
    @Expose
    public String received;
    @SerializedName("schedule")
    @Expose
    public String schedule;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("x")
    @Expose
    public String x;
    @SerializedName("y")
    @Expose
    public String y;


    public String name;
    public int pictureResource;
    private LatLng mPosition;

    public void setLatLong(String latitude, String longitude) {
        this.mPosition = new LatLng(Double.parseDouble(latitude.trim()), Double.parseDouble(longitude.trim()));;
    }



    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
