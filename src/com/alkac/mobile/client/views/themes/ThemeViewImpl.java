/*******************************************************************************
 * ThemeViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.themes;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.ViewPort;
import com.emitrom.gwt4.touch.client.core.handlers.dataview.DataViewItemTapHandler;
import com.emitrom.gwt4.touch.client.core.handlers.list.ItemDisclosureHandler;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.dataview.DataView;
import com.emitrom.gwt4.touch.client.dataview.DisclosureList;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.client.widgets.LoadMask;
import com.alkac.mobile.client.core.CSS;
import com.alkac.mobile.client.models.themes.ThemeModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class ThemeViewImpl extends Panel implements ThemeView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private static int lastIndex = 0;
    private LoadMask loadMask;

    public ThemeViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {
        
        final Store store = new Store(getThemeModels());
        store.setSorter(ThemeModel.THEME);
        DisclosureList list = new DisclosureList(ThemeModel.TEMPLATE, store);
        list.setDeselectOnContainerClick(false);
        list.addItemTapHandler(new DataViewItemTapHandler() {
            @Override
            public void onItemTap(DataView dataView, int index, Element element, BaseModel record, Object eventObject,
                            Object eOpts) {
                setStyleSheet(index);                
            }
        });
        list.setOnItemDisclosure(new ItemDisclosureHandler() {

            @Override
            public void onItemDisclosure(BaseModel record, JavaScriptObject node, int index) {
                setStyleSheet(index);                
            }
        });
        
        add(list);
        
        loadMask = new LoadMask();
        ViewPort.get().add(loadMask);
        loadMask.setIndicator(true);
        loadMask.hide();

    }

    private List<ThemeModel> getThemeModels() {

        String[] list = { "Sencha", "Cupertino", "Mountain View", "Toronto" };

        List<ThemeModel> models = new ArrayList<ThemeModel>();
        for (String item : list) {
            models.add(new ThemeModel(item));
        }

        return models;

    }

    private void setStyleSheet(int index) {
        
        loadMask.show();
        CSS.removeStyleSheet(String.valueOf(lastIndex));
        
        switch(index) {
            case 0:
                CSS.addStyleSheet("0", GWT.getHostPageBaseURL() + "gwt4touch2_kitchensink/touch/resources/css/sencha-touch.css");
                break;
            case 1:
                CSS.addStyleSheet("1", GWT.getHostPageBaseURL() + "gwt4touch2_kitchensink/touch/resources/css/apple.css");
                break;
            case 2:
                CSS.addStyleSheet("2", GWT.getHostPageBaseURL() + "gwt4touch2_kitchensink/touch/resources/css/android.css");
                break;
            case 3:
                CSS.addStyleSheet("3", GWT.getHostPageBaseURL() + "gwt4touch2_kitchensink/touch/resources/css/bb6.css");
                break;
             default:
               break;
        }
        
        loadMask.hide();
        lastIndex = index;
        
    }

}