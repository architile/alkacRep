package com.alkac.mobile.client.views.tutoni;

import com.emitrom.gwt4.touch.maps.client.base.LatLng;

/**
 * Created by IntelliJ IDEA.
 * User: pair5
 * Date: 30.04.2012
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public interface IGeoLocater {
    void setGeoPosition(LatLng latLng);
    void setGeoError(String errorMessage);
}
