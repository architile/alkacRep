package com.alkac.mobile.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: Pınar
 * Date: 01.05.2012
 * Time: 20:31
 * To change this template use File | Settings | File Templates.
 */
public class Location implements IsSerializable {
    double latitude;
    double longitude;

    public Location() {}

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
