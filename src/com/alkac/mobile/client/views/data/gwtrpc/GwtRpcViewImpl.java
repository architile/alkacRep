/*******************************************************************************
 * GwtRpcViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.data.gwtrpc;

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
import com.alkac.mobile.client.KitchenSinkServiceAsync;
import com.alkac.mobile.client.core.Util;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class GwtRpcViewImpl extends Panel implements GwtRpcView {

    private GwtRpcActivity presenter;
    private Button button;
    private LoadMask loadMask;
    private KitchenSinkServiceAsync service = Util.getService();

    public GwtRpcViewImpl() {
        setLayout(new FitLayout());
        setScrollable(true);
        initialize();
        addListeners();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = (GwtRpcActivity) presenter;
    }

    private void initialize() {

        ToolBar toolBar = new ToolBar();
        toolBar.setTitle("GWT-RPC");
        button = new Button("Get Src", UI.ACTION);
        toolBar.add(button);
        add(toolBar);
        
        loadMask = new LoadMask();
        ViewPort.get().add(loadMask);
        loadMask.setIndicator(true);
        loadMask.setMessage("Loading source ...");
        loadMask.hide();
        
    }
    
    private void addListeners() {
        
        button.addTapHandler(new TapHandler() {
            
            @Override
            public void onTap(Button button, EventObject event) {

                loadMask.show();
                /**
                 * This Service call would typically be found in the presenter.
                 * We are keeping it here in the view to show the simple GWT-RPC call.
                 */
                service.getSource(GwtRpcViewImpl.class.getName().replace(".", "/") + ".java", new AsyncCallback<String>() {
                    
                    @Override
                    public void onSuccess(String result) {
                        loadMask.hide();
                        presenter.setHtml(result);
                        presenter.highlight();
                    }
                    
                    @Override
                    public void onFailure(Throwable caught) {
                        loadMask.hide();
                        MessageBox.alert("GWT-RPC failed " + caught.getMessage());
                    }
                });
                
            }
        });
        
    }

}