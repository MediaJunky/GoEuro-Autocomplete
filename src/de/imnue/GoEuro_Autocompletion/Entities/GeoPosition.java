package de.imnue.GoEuro_Autocompletion.Entities;

/**
 * Created with IntelliJ IDEA.
 * User: Brian Nürnberg
 * Date: 08.10.2013
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeoPosition {

    private double latitude;
    private double longitude;

    public GeoPosition (double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
