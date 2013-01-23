/*******************************************************************************
 * GeolocationViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.device.geolocation;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.config.XType;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.device.client.core.handlers.geolocation.GeoLocationHandler;
import com.emitrom.gwt4.touch.device.client.geolocation.GeoLocation;
import com.emitrom.gwt4.touch.device.client.geolocation.Position;
import com.emitrom.gwt4.touch.device.client.geolocation.PositionError;
import com.emitrom.gwt4.touch.ux.grid.client.Grid;
import com.emitrom.gwt4.touch.ux.grid.client.core.GridColumn;
import com.emitrom.gwt4.touch.ux.grid.client.core.GridColumnEditor;
import com.alkac.mobile.client.models.ui.DeviceModel;

public class GeolocationViewImpl extends Panel implements GeolocationView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private Grid grid;

    public GeolocationViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {
        List<GridColumn> columns = new ArrayList<GridColumn>();
        
        GridColumn c = new GridColumn();
        c.setHeader("Property");
        c.setWidth("40%");
        c.setStyle("padding-left: 1em;");
        c.setDataIndex("key");
        c.setEditor(new GridColumnEditor(XType.TEXT_FIELD));
        columns.add(c);

        c = new GridColumn();
        c.setHeader("Value");
        c.setDataIndex("value");
        c.setStyle("text-align: center;");
        c.setWidth("60%");
        columns.add(c);

        final Store store = new Store(DeviceModel.getGeolocationData(null));
        grid = new Grid(store, columns);
        grid.addTo(this);
        
        GeoLocation.get().getCurrentLocation(new GeoLocationHandler() {
            @Override
            public void onError(PositionError error) {
                MessageBox.alert("Error", error.getMessage());
            }
            
            @Override
            public void onSuccess(Position position) {
                store.removeAll();
                store.addAll(DeviceModel.getGeolocationData(position));
             }
        });
    }
    
}
