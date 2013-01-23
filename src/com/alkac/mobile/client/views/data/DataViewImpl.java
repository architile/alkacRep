/*******************************************************************************
 * DataViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.data;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.handlers.dataview.DataViewItemTapHandler;
import com.emitrom.gwt4.touch.client.core.handlers.list.ItemDisclosureHandler;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.dataview.DisclosureList;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.alkac.mobile.client.models.data.DataViewModel;
import com.alkac.mobile.client.models.navigation.NavigationViewModel;
import com.alkac.mobile.client.views.data.gwtrpc.GwtRpcViewPlace;
import com.alkac.mobile.client.views.data.jsonp.JsonPViewPlace;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class DataViewImpl extends Panel implements DataView {

    private Presenter presenter;
    private GwtRpcViewPlace rpcViewPlace = new GwtRpcViewPlace();
    private JsonPViewPlace jsonpViewPlace = new JsonPViewPlace();

    public DataViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        final Store store = new Store(getDataViewModels());
        store.setSorter(NavigationViewModel.KEY);
        DisclosureList list = new DisclosureList(DataViewModel.TEMPLATE, store);
        list.setDeselectOnContainerClick(false);
        list.addItemTapHandler(new DataViewItemTapHandler() {
            @Override
            public void onItemTap(com.emitrom.gwt4.touch.client.dataview.DataView dataView, int index, Element element,
                            BaseModel record, Object eventObject, Object eOpts) {
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

    private List<DataViewModel> getDataViewModels() {

        List<String> modelNames = new ArrayList<String>();
        modelNames.add(rpcViewPlace.getToken());
        modelNames.add(jsonpViewPlace.getToken());
        
        String[] list = modelNames.toArray(new String[0]);

        List<DataViewModel> models = new ArrayList<DataViewModel>();
        for (String item : list) {
            models.add(new DataViewModel(item));
        }

        return models;

    }

    private void goToPlace(int index) {
        switch (index) {
        case 0:
            presenter.goTo(rpcViewPlace);
            break;
        case 1:
            presenter.goTo(jsonpViewPlace);
            break;
        default:
            break;
        }

    }

}