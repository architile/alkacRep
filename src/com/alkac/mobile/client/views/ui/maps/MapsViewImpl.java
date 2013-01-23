/*******************************************************************************
 * MapsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
 * 
 * Copyright (c) 2012 Emitrom LLC. All rights reserved.
 *
 * The Kitchen Sink is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Kitchen Sink is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Kitchen Sink.  If not, see http://www.emitrom.com/gpl_license
 *  
 * For licensing questions, please contact us at licensing@emitrom.com
 *
 ******************************************************************************/
package com.alkac.mobile.client.views.ui.maps;

import java.util.ArrayList;

import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.client.widgets.GoogleMap;
import com.emitrom.gwt4.touch.maps.client.GMap;
import com.emitrom.gwt4.touch.maps.client.base.LatLng;
import com.emitrom.gwt4.touch.maps.client.core.MVCArray;
import com.emitrom.gwt4.touch.maps.client.core.MapTypeId;
import com.emitrom.gwt4.touch.maps.client.events.InteractionEvent;
import com.emitrom.gwt4.touch.maps.client.events.handlers.MapTapHandler;
import com.emitrom.gwt4.touch.maps.client.overlays.InfoWindow;
import com.emitrom.gwt4.touch.maps.client.overlays.Marker;
import com.emitrom.gwt4.touch.maps.client.overlays.Polyline;
import com.emitrom.gwt4.touch.maps.client.overlays.options.PolylineOptions;
import com.emitrom.gwt4.touch.maps.client.services.TravelMode;
import com.emitrom.gwt4.touch.maps.client.services.distancematrix.DistanceMatrixRequest;
import com.emitrom.gwt4.touch.maps.client.services.distancematrix.DistanceMatrixResponse;
import com.emitrom.gwt4.touch.maps.client.services.distancematrix.DistanceMatrixResponseElement;
import com.emitrom.gwt4.touch.maps.client.services.distancematrix.DistanceMatrixResponseRow;
import com.emitrom.gwt4.touch.maps.client.services.distancematrix.DistanceMatrixService;
import com.emitrom.gwt4.touch.maps.client.services.distancematrix.DistanceMatrixStatus;
import com.emitrom.gwt4.touch.maps.client.services.handlers.DistanceMatrixServiceHandler;
import com.google.gwt.user.client.Timer;

public class MapsViewImpl extends Panel implements MapsView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private ArrayList<LatLng> markerPool = new ArrayList<LatLng>(2);
    private InfoWindow infoWindow;

    public MapsViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {
        GoogleMap map = new GoogleMap(true);
        doServiceMatrix(map.getMap());
        add(map);
    }

    private void doServiceMatrix(final GMap map) {

        map.setMapType(MapTypeId.ROADMAP);
        PolylineOptions options = new PolylineOptions();
        options.setStrokeColor("#000000");
        options.setStrokeOpacity(1);
        options.setStrokeWeight(3);

        final Polyline polyLine = new Polyline(options);
        polyLine.setMap(map);
        final MVCArray paths = polyLine.getPath();

        map.addTapHandler(new MapTapHandler() {
            @Override
            public void onTap(InteractionEvent event) {
                if (markerPool.size() < 2) {
                    LatLng position = event.getLatLng();
                    paths.push(position);

                    Marker marker = new Marker(map, position);
                    marker.setTitle("#-" + paths.getLength());

                    markerPool.add(position);
                    displayRouteInfo(markerPool, marker, map);
                }

            }
        });

    }

    private void displayRouteInfo(final ArrayList<LatLng> pool, final Marker marker, final GMap map) {
        int size = pool.size();
        if (size == 2) {
            DistanceMatrixRequest request = new DistanceMatrixRequest();
            request.setOrigins(pool.get(0));
            request.setDestinations(pool.get(1));
            request.setTravelMode(TravelMode.DRIVING);
            request.setAvoidHighways(false);
            request.setAvoidTolls(true);

            DistanceMatrixService service = new DistanceMatrixService();
            service.getDistanceMatrix(request, new DistanceMatrixServiceHandler() {
                private DistanceMatrixResponseElement element;
                private DistanceMatrixResponseRow row;

                @Override
                public void onSuccess(DistanceMatrixResponse response, DistanceMatrixStatus distanceMatrixStatus) {

                    if (distanceMatrixStatus.equals(DistanceMatrixStatus.OK)) {
                        String[] origins = response.getOriginAddresses();
                        String[] destinations = response.getDestinationAdresses();
                        String message = "";

                        for (int i = 0; i < origins.length; i++) {
                            row = response.getRows().get(i);
                            element = row.getElements().get(i);

                            String distance = element.getDistance().getText();
                            String duration = element.getDuration().getText();
                            String from = origins[i];
                            String to = destinations[i];

                            message += "<b style='color:blue;'>From</b> : " + from + "<br/>";
                            message += "<b style='color:blue;'>To</b> : " + to + "<br/>";
                            message += "<b style='color:blue;'>Distance</b> : " + distance + "<br/>";
                            message += "<b style='color:blue;'>Duration</b> : " + duration + "<br/>";

                        }

                        if (infoWindow != null) {
                            infoWindow.close();
                        }

                        infoWindow = new InfoWindow();
                        infoWindow.setContent(message);
                        infoWindow.open(map, marker);
                        // close the infoWindow after 2 seconds
                        new Timer() {
                            @Override
                            public void run() {
                                infoWindow.close();
                            }
                        }.schedule(2500);

                    }

                }
            });
        } else if (size == 1) {
            infoWindow = new InfoWindow();
            infoWindow.setContent("I m the starting point");
            infoWindow.open(map, marker);
        }

    }

}