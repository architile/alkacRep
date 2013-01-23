/*******************************************************************************
 * JsonPViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.data.jsonp;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.containers.ToolBar;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.ViewPort;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.client.widgets.Button;
import com.emitrom.gwt4.touch.client.widgets.LoadMask;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class JsonPViewImpl extends Panel implements JsonPView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private Button button;
    private LoadMask loadmask;

    public JsonPViewImpl() {
        setLayout(new FitLayout());
        initialize();
        addListeners();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        ToolBar toolbar = new ToolBar();
        toolbar.setTitle("JSONP");

        button = new Button("Load using JSONP", UI.ACTION);
        toolbar.add(button);

        loadmask = new LoadMask("Sending cross domain request ...");
        ViewPort.get().add(loadmask);
        loadmask.hide();

        add(toolbar);

    }

    private void addListeners() {

        final String url = "http://free.worldweatheronline.com/feed/weather.ashx" + "?key=23f6a0ab24185952101705"
                        + "&q=94301" + "&format=json" + "&num_of_days=5";

        button.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {

                loadmask.show();

                /**
                 * Typically called in the presenter. We are keeping it here so
                 * we can show how to retrieve the data.
                 */
                JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
                jsonp.requestObject(url, new AsyncCallback<JavaScriptObject>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        loadmask.hide();
                        MessageBox.alert(caught.getMessage());
                    }

                    @Override
                    public void onSuccess(JavaScriptObject result) {
                        loadmask.hide();
                        setHtml(new JSONObject(result).toString());
                    }

                });

            }
        });
    }

}
