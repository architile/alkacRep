/*******************************************************************************
 * DeviceModel.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.models.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.utils.OS;
import com.emitrom.gwt4.touch.device.client.accelerometer.Acceleration;
import com.emitrom.gwt4.touch.device.client.contacts.Contact;
import com.emitrom.gwt4.touch.device.client.contacts.ContactField;
import com.emitrom.gwt4.touch.device.client.core.Device;
import com.emitrom.gwt4.touch.device.client.geolocation.Coordinates;
import com.emitrom.gwt4.touch.device.client.geolocation.Position;

public class DeviceModel extends BaseModel {
    private static final String NA = "Not Available";

    public DeviceModel() {
    }

    public DeviceModel(String key, String value) {
        setKey(key);
        setValue(value);
    }

    public void setKey(String key) {
        set("key", key);
    }

    public void setValue(String value) {
        set("value", value);
    }

    /**
     * Gets general device information.
     * 
     * @return List<DeviceModel>
     */
    public static List<DeviceModel> getGeneralData() {
        Device device = Device.get();
        OS os = OS.get();

        List<DeviceModel> list = new ArrayList<DeviceModel>();
        list.add(new DeviceModel("UUID", device.getUuId()));
        list.add(new DeviceModel("Name", device.getName()));
        list.add(new DeviceModel("Platform", os.getDeviceType()));
        list.add(new DeviceModel("OS", os.getName()));
        list.add(new DeviceModel("PhoneGap Version", device.getPhoneGapVersion()));

        return list;
    }

    /**
     * Gets acceleration data. If the argument is null, this method returns
     * "Not Available" for all data points.
     * 
     * @param acceleration
     * @return List<DeviceModel>
     */
    public static List<DeviceModel> getAccelerationData(Acceleration acceleration) {
        List<DeviceModel> list = new ArrayList<DeviceModel>();
        String x = NA;
        String y = NA;
        String z = NA;

        try {
            if (acceleration != null) {
                x = String.valueOf(acceleration.getX());
                y = String.valueOf(acceleration.getY());
                z = String.valueOf(acceleration.getZ());
            }
        } catch (Exception ex) {
            MessageBox.alert("Error", ex.getMessage());
        }

        list.add(new DeviceModel("X-Axis", x));
        list.add(new DeviceModel("Y-Axis", y));
        list.add(new DeviceModel("Z-Axis", z));

        return list;
    }

    /**
     * Gets geolocation data. If the argument is null, this method returns
     * "Not Available" for all data points.
     * 
     * @param position
     * @return List<DeviceModel>
     */
    public static List<DeviceModel> getGeolocationData(Position position) {
        List<DeviceModel> list = new ArrayList<DeviceModel>();
        String longitude = NA;
        String latitude = NA;
        String speed = NA;
        String heading = NA;
        String altitude = NA;
        String altitudeAccuracy = NA;
        String accuracy = NA;
        long timestamp = System.currentTimeMillis();

        try {
            if (position != null) {
                Coordinates coords = position.getCoords();
                longitude = String.valueOf(coords.getLongitude());
                latitude = String.valueOf(coords.getLatitude());
                speed = String.valueOf(coords.getSpeed()) + " MPH";
                heading = String.valueOf(coords.getHeading());
                accuracy = String.valueOf(coords.getAccuracy()) + "%";
                altitude = String.valueOf(coords.getAltitude());
                altitudeAccuracy = String.valueOf(coords.getAltitudeAccuracy()) + "%";
                timestamp = position.getTimeStamp();
            }
        } catch (Exception ex) {
            MessageBox.alert("Error", ex.getMessage());
        }

        list.add(new DeviceModel("Timestamp", new Date(timestamp).toString()));
        list.add(new DeviceModel("Longitude", longitude));
        list.add(new DeviceModel("Latitude", latitude));
        list.add(new DeviceModel("Speed", speed));
        list.add(new DeviceModel("Heading", heading));
        list.add(new DeviceModel("Location Accuracy", accuracy));
        list.add(new DeviceModel("Altitude", altitude));
        list.add(new DeviceModel("Altitude Accuracy", altitudeAccuracy));

        return list;
    }

    public static List<DeviceModel> getContactsData(List<Contact> contacts) {
        final List<DeviceModel> list = new ArrayList<DeviceModel>();
        List<ContactField> phoneNumbers;

        if (contacts != null) {
            for (Contact contact : contacts) {
                phoneNumbers = contact.getPhoneNumbers();
                String phoneNumber = "N/A";
                if (!phoneNumbers.isEmpty()) {
                    phoneNumber = phoneNumbers.get(0).getValue();
                }
                list.add(new DeviceModel(contact.getDisplayName(), phoneNumber));
            }
        }

        return list;
    }
}
