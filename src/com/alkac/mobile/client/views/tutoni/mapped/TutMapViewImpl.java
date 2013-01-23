/*******************************************************************************
 * TutMapViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
 *
 * Copyright (c) 2012 Emitrom LLC. All rights reserved.
 *
 * The Kitchen Sink is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * The Kitchen Sink is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * the Kitchen Sink. If not, see http://www.emitrom.com/gpl_license
 *
 * For licensing questions, please contact us at licensing@emitrom.com
 *
 ******************************************************************************/
package com.alkac.mobile.client.views.tutoni.mapped;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.layout.*;
import com.emitrom.gwt4.touch.client.widgets.GoogleMap;
import com.emitrom.gwt4.touch.maps.client.GMap;
import com.emitrom.gwt4.touch.maps.client.base.LatLng;
import com.emitrom.gwt4.touch.maps.client.core.MVCArray;
import com.emitrom.gwt4.touch.maps.client.core.MapTypeId;
import com.emitrom.gwt4.touch.maps.client.overlays.InfoWindow;
import com.emitrom.gwt4.touch.maps.client.overlays.Marker;
import com.emitrom.gwt4.touch.maps.client.overlays.MarkerImage;
import com.emitrom.gwt4.touch.maps.client.overlays.Polyline;
import com.emitrom.gwt4.touch.maps.client.overlays.options.PolylineOptions;
import com.alkac.mobile.client.KitchenSinkServiceAsync;
import com.alkac.mobile.client.core.Util;
import com.alkac.mobile.client.views.tutoni.DingDong;
import com.alkac.mobile.client.views.tutoni.IGeoLocater;
import com.alkac.mobile.client.views.tutoni.LoginInfo;
import com.alkac.mobile.shared.Ferman;
import com.alkac.mobile.shared.Location;
import com.alkac.mobile.shared.Zgort;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TutMapViewImpl extends Panel implements TutMapView, IGeoLocater {

    @SuppressWarnings("unused")
    private TutMapView.Presenter presenter;
    private InfoWindow infoWindow;
    private MVCArray paths;
    private GMap map;
    private Timer timer;
    private TutClientState state;
    private Marker selfMarker;
    private Marker tasiyiciMarker;
    private final IGeoLocater geoLocator = this;
    private boolean selfMarkerVisible = true;

    KitchenSinkServiceAsync service = Util.getService();

    public TutMapViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(TutMapView.Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {
        state = TutClientState.FirstRun;
        GoogleMap map = new GoogleMap(true);
        doServiceMatrix(map.getMap());

        initializeTimer();

        initLocation();
        add(map);
    }

    private void initializeTimer() {
        timer = new Timer() {
            @Override
            public void run() {
                LoginInfo.getUserInfo().setGeoLocation(geoLocator);

            }
        };
    }

    private void startTimer() {
        timer.scheduleRepeating(3000);
    }

    private void initLocation() {
        LoginInfo.getUserInfo().setGeoLocation(this);

    }

    public void setGeoPosition(final LatLng latLng) {
        if (state == TutClientState.FirstRun) {
            paths.push(latLng);

            selfMarker = new Marker(map, latLng);
            selfMarker.setIcon(new MarkerImage("self.png"));
            selfMarker.setTitle("#-" + paths.getLength());

            tasiyiciMarker = new Marker(map, latLng);
            tasiyiciMarker.setIcon(new MarkerImage("Car.png"));
            tasiyiciMarker.setVisible(false);
            state = TutClientState.NextRun;
            startTimer();
        }
        else
        {
            service.getFerman(LoginInfo.getUserInfo(),new Location(latLng.getLat(),latLng.getLng()),new AsyncCallback<Ferman>() {
                @Override
                public void onFailure(Throwable caught) {
                    MessageBox.alert("Fermana ulaşmada hata - "+ caught.getMessage());
                }

                @Override
                public void onSuccess(Ferman result) {
                    selfMarker.setPosition(latLng);
                    tasiyiciMarker.setPosition(new LatLng(result.getLocation().getLatitude(),result.getLocation().getLongitude()));
                    if (result.getZgort() == Zgort.Normal)
                    {
                        selfMarkerVisible = true;
                        selfMarker.setVisible(true);
                        tasiyiciMarker.setIcon(new MarkerImage("Car.png"));
                        tasiyiciMarker.setVisible(true);
                    }
                    else
                    {
                        if (selfMarkerVisible)
                        {
                            MessageBox.alert("Araba sende!!!!");
                            DingDong.gotPresent.play();
                            tasiyiciMarker.setIcon(new MarkerImage("Car.png"));
                        }
                        else
                            tasiyiciMarker.setIcon(new MarkerImage("Carok.png"));

                        selfMarkerVisible = false;
                        selfMarker.setVisible(false);
                        tasiyiciMarker.setVisible(true);
                    }

                }
            });

        }
    }

    @Override
    public void setGeoError(String errorMessage) {
        MessageBox.alert(errorMessage);
    }

    private void doServiceMatrix(final GMap map) {

        this.map = map;
        map.setMapType(MapTypeId.ROADMAP);
        PolylineOptions options = new PolylineOptions();
        options.setStrokeColor("#000000");
        options.setStrokeOpacity(1);
        options.setStrokeWeight(3);

        final Polyline polyLine = new Polyline(options);
        polyLine.setMap(map);
        paths = polyLine.getPath();

    }

//            infoWindow = new InfoWindow();
//            infoWindow.setContent("I m the starting point");
//            infoWindow.open(map, selfMarker);


}