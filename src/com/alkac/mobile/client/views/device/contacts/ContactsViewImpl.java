/*******************************************************************************
 * ContactsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.device.contacts;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.config.XType;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.device.client.contacts.Contact;
import com.emitrom.gwt4.touch.device.client.contacts.ContactError;
import com.emitrom.gwt4.touch.device.client.contacts.ContactProperty;
import com.emitrom.gwt4.touch.device.client.contacts.Contacts;
import com.emitrom.gwt4.touch.device.client.core.handlers.contacts.ContactFindHandler;
import com.emitrom.gwt4.touch.ux.grid.client.Grid;
import com.emitrom.gwt4.touch.ux.grid.client.core.GridColumn;
import com.emitrom.gwt4.touch.ux.grid.client.core.GridColumnEditor;
import com.alkac.mobile.client.models.ui.DeviceModel;

public class ContactsViewImpl extends Panel implements ContactsView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private Grid grid;

    public ContactsViewImpl() {
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
        c.setHeader("Name");
        c.setWidth("40%");
        c.setStyle("padding-left: 1em;");
        c.setDataIndex("key");
        c.setEditor(new GridColumnEditor(XType.TEXT_FIELD));
        columns.add(c);

        c = new GridColumn();
        c.setHeader("Phone Number");
        c.setDataIndex("value");
        c.setStyle("text-align: center;");
        c.setWidth("60%");
        columns.add(c);

        // We'll define the fields here, since we don't have contacts yet.
        final Store store = new Store(DeviceModel.getContactsData(null), "key", "value");
        grid = new Grid(store, columns);
        grid.addTo(this);

        Contacts.get().find(new ContactFindHandler() {
            @Override
            public void onError(ContactError error) {
                MessageBox.alert("Error", error.toString());
            }

            @Override
            public void onSuccess(List<Contact> contacts) {
                store.setData(DeviceModel.getContactsData(contacts));
            }
        }, ContactProperty.NICKNAME, ContactProperty.PHONE_NUMBERS);
    }
}
