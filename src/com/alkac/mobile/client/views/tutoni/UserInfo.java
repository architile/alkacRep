package com.alkac.mobile.client.views.tutoni;

import com.emitrom.gwt4.touch.device.client.core.handlers.geolocation.GeoLocationHandler;
import com.emitrom.gwt4.touch.device.client.geolocation.GeoLocation;
import com.emitrom.gwt4.touch.device.client.geolocation.GeoLocationOptions;
import com.emitrom.gwt4.touch.device.client.geolocation.Position;
import com.emitrom.gwt4.touch.device.client.geolocation.PositionError;
import com.emitrom.gwt4.touch.maps.client.base.LatLng;

/**
 * Created by IntelliJ IDEA.
 * User: pair5
 * Date: 30.04.2012
 * Time: 16:26
 * To change this template use File | Settings | File Templates.
 */
public enum UserInfo {
    Zero,
    One,
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight {
        @Override
        public void setGeoLocation(IGeoLocater geolocator) {
            LatLng latLng = new LatLng(39.9, 32.8);
            geolocator.setGeoPosition(latLng);
        }
    },
    Nine;

    public void setGeoLocation(final IGeoLocater geolocator) {
        GeoLocationOptions locationOptions = new GeoLocationOptions();
        locationOptions.setEnableHighAccuracy(true);
        locationOptions.setMaximumAge(true);
        locationOptions.setFrequency(600000);
        GeoLocation.get().getCurrentLocation(new GeoLocationHandler() {
            @Override
            public void onError(PositionError error) {
                geolocator.setGeoError(error.getMessage());
            }

            @Override
            public void onSuccess(Position position) {
                LatLng latLng = new LatLng(position.getCoords().getLatitude(), position.getCoords().getLongitude());
                geolocator.setGeoPosition(latLng);
            }
        }, locationOptions);

    }
}
