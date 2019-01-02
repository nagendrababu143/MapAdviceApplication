package android.pronixits.com.mapadviceapplication.models;

import com.google.android.gms.maps.model.LatLng;

public class TrafficUpdate {

    private String userid;
    private Double latitude;
    private Double longitude;
    private String date;
    private String traffic_reason;
    private String placename;


    public TrafficUpdate() {
    }

    public TrafficUpdate(String userid, Double latitude, Double longitude, String date, String traffic_reason, String placename) {
        this.userid = userid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.traffic_reason = traffic_reason;
        this.placename = placename;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTraffic_reason() {
        return traffic_reason;
    }

    public void setTraffic_reason(String traffic_reason) {
        this.traffic_reason = traffic_reason;
    }

    public String getPlacename() {
        return placename;
    }

    public void setPlacename(String placename) {
        this.placename = placename;
    }
}
