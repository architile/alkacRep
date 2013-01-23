/*******************************************************************************
 * TutoniViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.tutoni;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.*;
import com.emitrom.gwt4.touch.client.utils.TouchIcons;
import com.emitrom.gwt4.touch.client.widgets.Button;
import com.emitrom.gwt4.touch.device.client.core.handlers.geolocation.GeoLocationHandler;
import com.emitrom.gwt4.touch.device.client.geolocation.GeoLocation;
import com.emitrom.gwt4.touch.device.client.geolocation.GeoLocationOptions;
import com.emitrom.gwt4.touch.device.client.geolocation.Position;
import com.emitrom.gwt4.touch.device.client.geolocation.PositionError;
import com.emitrom.gwt4.touch.maps.client.base.LatLng;
import com.alkac.mobile.client.KitchenSinkServiceAsync;
import com.alkac.mobile.client.core.Util;
import com.alkac.mobile.client.views.tutoni.mapped.TutMapViewPlace;
import com.alkac.mobile.shared.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

public class TutoniViewImpl extends Panel implements TutoniView {

    private ArrayList<Button> buttonList = new ArrayList<Button>();

    private Presenter presenter;
    private final ArrayList<TouchIcons> iconList = new ArrayList<TouchIcons>();


    public TutoniViewImpl() {
        VBoxLayout vboxLayout = new VBoxLayout();
        vboxLayout.setPack(Pack.CENTER);
        vboxLayout.setAlign(Align.STRETCH);
        setLayout(vboxLayout);
        setScrollable(true);


        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        Panel flexPanel1 = getFlexPanel();
        Panel flexPanel2 = getFlexPanel();
        Panel flexPanel3 = getFlexPanel();

        // audio
        DingDong.gotPresent.setUrl("aa.mp3");
        DingDong.gotPresent.setLoop(false);

        createButtons();
        addListeners();
        for (int i = 0; i < 3; i++)
            flexPanel1.add(buttonList.get(i));
        for (int i = 3; i < 6; i++)
            flexPanel2.add(buttonList.get(i));
        for (int i = 6; i < 9; i++)
            flexPanel3.add(buttonList.get(i));
        add(flexPanel1);
        add(flexPanel2);
        add(flexPanel3);


    }

    private Panel getFlexPanel() {
        Panel flexPanel = new Panel();
        flexPanel.setFlex(1);
        HBoxLayout hboxLayout = new HBoxLayout();
        hboxLayout.setAlign(Align.MIDDLE);
        hboxLayout.setPack(Pack.CENTER);
        flexPanel.setLayout(hboxLayout);
        flexPanel.setBorder(2);
        return flexPanel;
    }

    private void createButtons() {
        iconList.add(TouchIcons.COMPASS1);
        iconList.add(TouchIcons.ATOM);
        iconList.add(TouchIcons.BATTERY_POWER);
        iconList.add(TouchIcons.BULB);
        iconList.add(TouchIcons.NUCLEAR);
        iconList.add(TouchIcons.MAGIC);
        iconList.add(TouchIcons.PHONE1);
        iconList.add(TouchIcons.USER);
        iconList.add(TouchIcons.SERVER);
        iconList.add(TouchIcons.FLAG);

        for (int i = 0; i < 9; i++) {
            Button button = new Button();
            button.setUi(UI.ACTION);
            button.setIconMask(true);
            button.setIconCls(iconList.get(i));
            button.setFlex(1);
            button.setMargin(10);
            buttonList.add(button);
        }
    }

    private void addListeners() {

        for (int i = 0; i < 7; i++) {
            final int finalIndex = i;
            buttonList.get(i).addTapHandler(new TapHandler() {
                @Override
                public void onTap(Button button, EventObject event) {
//                    MessageBox.alert("Title", "Pressed " + iconList.get(finalIndex).toString());
                    DingDong.gotPresent.play();
                    LoginInfo.setLogin(UserInfo.values()[finalIndex]);
                    presenter.goTo(new TutMapViewPlace());
                }
            });
        }

        buttonList.get(7).addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                KitchenSinkServiceAsync service = Util.getService();
                service.initGame(new AsyncCallback<Void>() {
                    public void onFailure(Throwable caught) {
                        MessageBox.alert("Oyun başlatılamadı!!");
                    }

                    public void onSuccess(Void result) {
                        MessageBox.alert("Oyun başladı");
                    }
                });

            }
        });

        buttonList.get(8).addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                MessageBox.prompt("Slm bob", "Kapish mesafesini gir", new MessageBox.PromptCallback() {
                    @Override
                    public void execute(String btnID, final String text) {
                        GeoLocationOptions locationOptions = new GeoLocationOptions();
                        locationOptions.setEnableHighAccuracy(true);
                        locationOptions.setMaximumAge(true);
                        locationOptions.setFrequency(600000);
                        GeoLocation.get().getCurrentLocation(new GeoLocationHandler() {
                            @Override
                            public void onError(PositionError error) {
                                MessageBox.alert("geolocation alınamadı");
                            }

                            @Override
                            public void onSuccess(Position position) {
                                KitchenSinkServiceAsync service = Util.getService();
                                LatLng latLng = new LatLng(position.getCoords().getLatitude(), position.getCoords().getLongitude());
                                service.setAyar(text, new Location(latLng.getLat(), latLng.getLng()), new AsyncCallback<Void>() {
                                    @Override
                                    public void onFailure(Throwable caught) {
                                        MessageBox.alert("ayar yapılamadı");
                                    }

                                    @Override
                                    public void onSuccess(Void result) {

                                    }
                                });
                            }
                        },locationOptions);

                    }
                });
            }
        });

    }


}