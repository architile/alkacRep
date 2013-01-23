/*******************************************************************************
 * NavigationViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.navigation;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.handlers.dataview.DataViewItemTapHandler;
import com.emitrom.gwt4.touch.client.core.handlers.list.ItemDisclosureHandler;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.dataview.DataView;
import com.emitrom.gwt4.touch.client.dataview.DisclosureList;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.device.client.core.Device;
import com.alkac.mobile.client.models.navigation.NavigationViewModel;
import com.alkac.mobile.client.views.AppPlace;
import com.alkac.mobile.client.views.animations.AnimationViewPlace;
import com.alkac.mobile.client.views.charts.ChartsViewPlace;
import com.alkac.mobile.client.views.data.DataViewPlace;
import com.alkac.mobile.client.views.device.DeviceViewPlace;
import com.alkac.mobile.client.views.media.MediaViewPlace;
import com.alkac.mobile.client.views.themes.ThemeViewPlace;
import com.alkac.mobile.client.views.ui.UIViewPlace;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class NavigationViewImpl extends Panel implements NavigationView {

    private Presenter presenter;
    private AppPlace uiViewPlace = new UIViewPlace();
    private AppPlace animationViewPlace = new AnimationViewPlace();
    private DataViewPlace dataViewPlace = new DataViewPlace();
    private AppPlace themeViewPlace = new ThemeViewPlace();
    private AppPlace mediaViewPlace = new MediaViewPlace();
    private AppPlace deviceViewPlace = new DeviceViewPlace();
    private AppPlace chartsPlace = new ChartsViewPlace();

    public NavigationViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        final Store store = new Store(getMainViewModels());
        store.setSorter(NavigationViewModel.KEY);
        DisclosureList list = new DisclosureList(NavigationViewModel.TEMPLATE, store);
        list.setDeselectOnContainerClick(false);
        list.addItemTapHandler(new DataViewItemTapHandler() {

            @Override
            public void onItemTap(DataView dataView, int index, Element element, BaseModel record, Object eventObject,
                            Object eOpts) {
                goToPlace(index);
            }
        });
        list.setOnItemDisclosure(new ItemDisclosureHandler() {

            @Override
            public void onItemDisclosure(BaseModel record, JavaScriptObject node, int index) {
                goToPlace(index);
            }
        });

        add(list);

    }

    private List<NavigationViewModel> getMainViewModels() {

        List<String> modelNames = new ArrayList<String>();
        modelNames.add(uiViewPlace.getToken());
        modelNames.add(animationViewPlace.getToken());
        modelNames.add(dataViewPlace.getToken());
        modelNames.add(mediaViewPlace.getToken());
        modelNames.add(themeViewPlace.getToken());
        modelNames.add(chartsPlace.getToken());

        // We want to see how the UI looks in dev mode also
        if (Device.isAvailable()) {
            modelNames.add(deviceViewPlace.getToken());
        }

        String[] list = modelNames.toArray(new String[0]);

        List<NavigationViewModel> models = new ArrayList<NavigationViewModel>();
        for (String item : list) {
            models.add(new NavigationViewModel(item));
        }

        return models;

    }

    private void goToPlace(int index) {
        switch (index) {
            case 0:
                presenter.goTo(uiViewPlace);
                break;
            case 1:
                presenter.goTo(animationViewPlace);
                break;
            case 2:
                presenter.goTo(dataViewPlace);
                break;
            case 3:
                presenter.goTo(mediaViewPlace);
                break;
            case 4:
                presenter.goTo(themeViewPlace);
                break;
            case 5:
                presenter.goTo(chartsPlace);
                break;
            case 6:
                presenter.goTo(deviceViewPlace);
                break;

            default:
                break;
        }

    }

}