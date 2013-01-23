/*******************************************************************************
 * DeviceViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.device;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.handlers.dataview.DataViewItemTapHandler;
import com.emitrom.gwt4.touch.client.core.handlers.list.ItemDisclosureHandler;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.dataview.DataView;
import com.emitrom.gwt4.touch.client.dataview.DisclosureList;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.device.client.core.Device;
import com.emitrom.gwt4.touch.device.client.core.handlers.storage.DatabaseHandler;
import com.emitrom.gwt4.touch.device.client.storage.Database;
import com.emitrom.gwt4.touch.device.client.storage.SQLError;
import com.emitrom.gwt4.touch.device.client.storage.SQLTransaction;
import com.emitrom.gwt4.touch.device.client.storage.Storage;
import com.alkac.mobile.client.models.device.DeviceViewModel;
import com.alkac.mobile.client.views.device.accelerometer.AccelerometerViewPlace;
import com.alkac.mobile.client.views.device.contacts.ContactsViewPlace;
import com.alkac.mobile.client.views.device.general.GeneralViewPlace;
import com.alkac.mobile.client.views.device.geolocation.GeolocationViewPlace;
import com.alkac.mobile.client.views.device.network.NetworkViewPlace;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class DeviceViewImpl extends Panel implements DeviceView {

    private Presenter presenter;
    private AccelerometerViewPlace accelerometerViewPlace = new AccelerometerViewPlace();
    private ContactsViewPlace contactsViewPlace = new ContactsViewPlace();
    private GeneralViewPlace generalViewPlace = new GeneralViewPlace();
    private NetworkViewPlace networkViewPlace = new NetworkViewPlace();
    private GeolocationViewPlace geolocationViewPlace = new GeolocationViewPlace();

    public DeviceViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        final Store store = new Store(getDeviceViewModels());
        store.setSorter(DeviceViewModel.KEY);
        DisclosureList list = new DisclosureList(DeviceViewModel.TEMPLATE, store);
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

    private List<DeviceViewModel> getDeviceViewModels() {
        List<String> modelNames = new ArrayList<String>();
        modelNames.add(accelerometerViewPlace.getToken());
        modelNames.add(contactsViewPlace.getToken());
        modelNames.add(geolocationViewPlace.getToken());
        modelNames.add(generalViewPlace.getToken());
        modelNames.add(networkViewPlace.getToken());
        modelNames.add("Notification");
        modelNames.add("Storage");

        String[] list = modelNames.toArray(new String[0]);

        List<DeviceViewModel> models = new ArrayList<DeviceViewModel>();
        for (String item : list) {
            models.add(new DeviceViewModel(item));
        }

        return models;
        
    }

    private void goToPlace(int index) {
        switch (index) {
        case 0:
            presenter.goTo(accelerometerViewPlace);
            break;
            
        case 1:
            presenter.goTo(contactsViewPlace);
            break;
            
        case 2:
            presenter.goTo(geolocationViewPlace);
            break;
            
        case 3:
            presenter.goTo(generalViewPlace);
            break;
            
        case 4:
            presenter.goTo(networkViewPlace);
            break;
            
        case 5:
            displayNotification();
            break;
            
        case 6:
            persistAndGreet();
            
        default:
            break;    
            
        }
    }
    
    /**
     * Displays a native notification window.
     */
    private void displayNotification() {
        if (!Device.isAvailable()) {
            MessageBox.alert("Alert", "Device is not available.");
            return;
        } 

        Device.alert("Alert", "Hello from Emitrom!");
    }
    
    /**
     * Displays an input dialog and stores the text entered in the database.
     * If a name has been previously entered, the user will be greeted by
     * name.
     */
    private void persistAndGreet() {
        if (!Device.isAvailable()) {
            MessageBox.alert("Alert", "Device storage not available");
            return;
        }
        
        Database db = Storage.get().createDatabase("Database", "1.0", "Kitchen Sink Database", 10);
        
        db.transaction(new DatabaseHandler() {
            @Override
            public void execute(SQLTransaction tx) {
                tx.executeSql("DROP TABLE IF EXISTS DEMO");
                tx.executeSql("CREATE TABLE IF NOT EXISTS DEMO (id unique, name)");
                tx.executeSql("INSERT INTO DEMO (id, name) VALUES (1, \"User 1\")");
                tx.executeSql("INSERT INTO DEMO (id, name) VALUES (2, \"User 2\")");
            }
            
            @Override
            public void onSuccess() {
                Device.alert("Drop-Create-Insert Success!");
            }
            
            @Override
            public void onError(SQLError error) {
                Device.alert("Error", error.getMessage());
            }
        });
    }
}
