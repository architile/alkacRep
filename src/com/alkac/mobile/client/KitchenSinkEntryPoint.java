/*******************************************************************************
 * KitchenSinkEntryPoint.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client;

import java.util.Stack;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.ModalPanel;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.containers.ToolBar;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.Scroller;
import com.emitrom.gwt4.touch.client.core.TouchEntryPoint;
import com.emitrom.gwt4.touch.client.core.ViewPort;
import com.emitrom.gwt4.touch.client.core.config.Dock;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.laf.Direction;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.utils.TouchIcons;
import com.emitrom.gwt4.touch.client.widgets.Button;
import com.emitrom.gwt4.touch.client.widgets.LoadMask;
import com.emitrom.gwt4.touch.client.widgets.Spacer;
import com.emitrom.gwt4.touch.device.client.core.Device;
import com.alkac.mobile.client.core.AppActivityMapper;
import com.alkac.mobile.client.core.AppPlaceHistoryMapper;
import com.alkac.mobile.client.core.ClientFactory;
import com.alkac.mobile.client.core.Util;
import com.alkac.mobile.client.core.events.SourceUpdateEvent;
import com.alkac.mobile.client.views.tutoni.TutoniViewPlace;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Sample code for GWT4Touch 2.0
 */
public class KitchenSinkEntryPoint extends TouchEntryPoint {

    private Place defaultPlace = new TutoniViewPlace(); //new NavigationViewPlace();
    private ViewPort vp;
    private static ToolBar toolBar = new ToolBar();
    private Button backButton;
    private static Button homeButton;
    private Button aboutButton;
    private Button sourceButton;
    private String currentView;
    private Panel srcPanel;
    private LoadMask loadMask;
    private String version;
    private Stack<Place> stack = new Stack<Place>();
    
    ClientFactory factory = GWT.create(ClientFactory.class);
    KitchenSinkServiceAsync service = Util.getService();

    @Override
    public void onLoad() {
        initialize();
        addHandler();
        int i=0;
        i++;
        loadTouch();
        addListeners();
    }

    @SuppressWarnings("deprecation")
    private void loadTouch() {

        ClientFactory clientFactory = GWT.create(ClientFactory.class);
        EventBus eventBus = clientFactory.getEventBus();
        PlaceController placeController = clientFactory.getPlaceController();

        // Start ActivityManager for the main widget with our ActivityMapper
        ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(vp);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        RootPanel.get().add(vp);
        // Goes to the place represented on URL else default place
        historyHandler.handleCurrentHistory();

    }
    
    private void initialize() {

        vp = ViewPort.get();
        
        srcPanel = new ModalPanel();
        srcPanel.setCentered(true);
        srcPanel.setWidth("85%");
        srcPanel.setHeight("85%");
        srcPanel.setStyleHtmlContent(true);
        Scroller scroller = new Scroller();
        scroller.setDirection(Direction.BOTH);
        srcPanel.setScrollable(scroller);
        srcPanel.setHideOnMaskTap(true);
        srcPanel.setModal(true);
        srcPanel.setZIndex(100);

        ToolBar srcToolBar = new ToolBar();
        srcToolBar.setDocked(Dock.TOP);
        srcToolBar.setTitle("Source Code");
        
        srcPanel.add(srcToolBar);

        ViewPort.get().add(srcPanel);
        srcPanel.hide();

        backButton = new Button("Back", UI.BACK);
        backButton.setIconMask(true);

        homeButton = new Button();
        homeButton.setUi(UI.ACTION);
        homeButton.setIconMask(true);
        homeButton.setIconCls(TouchIcons.HOME);

        sourceButton = new Button("Source", UI.ACTION);
        
        aboutButton = new Button();
        aboutButton.setUi(UI.PLAIN);
        aboutButton.setIconMask(true);
        aboutButton.setIconCls(TouchIcons.INFO);

        toolBar.setTitle("Home");
        
        // we'll only add the button when we are running in native mode.
        if (Device.isAvailable() || !GWT.isScript()) {
            toolBar.add(backButton);
        }
        
        toolBar.add(homeButton);
        toolBar.add(new Spacer());
        toolBar.add(sourceButton);
        toolBar.add(aboutButton);

        vp.add(toolBar);
        
        loadMask = new LoadMask();
        ViewPort.get().add(loadMask);
        loadMask.setIndicator(true);
        loadMask.setMessage("Loading...");

        loadMask.hide();
        backButton.setVisible(false);

    }

    /**
     * @return the homeButton
     */
    public static Button getBackButton() {
        return homeButton;
    }

    /**
     * @return the sourceButton
     */
    public Button getSourceButton() {
        return sourceButton;
    }

    /**
     * @return the toolBar
     */
    public static ToolBar getToolBar() {
        return toolBar;
    }

    private void addListeners() {
        
        backButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                // remove where we are
                stack.pop();
                
                // and then go to the previous place
                Place place = stack.pop();
                factory.getEventBus().fireEvent(new PlaceChangeEvent(place));
            }
        });
        
        sourceButton.addTapHandler(new TapHandler() {

            @Override
            public void onTap(Button button, EventObject event) {
                
                loadMask.show();
                
                service.getSource(currentView, new AsyncCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        loadMask.hide();
                        srcPanel.setHtml("");
                        String html = "<textarea name=\"code\" class=\"java:nocontrols\" rows=\"15\" cols=\"100\">";
                        html += result;
                        html += "</textarea>";
                        srcPanel.setHtml(html);
                        srcPanel.show();
                        highlight();
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        loadMask.hide();
                        System.out.println(caught.getMessage());
                    }
                });
            }
        });
        
        homeButton.addTapHandler(new TapHandler() {
            
            @Override
            public void onTap(Button button, EventObject event) {
                Window.open("http://www.emitrom.com", "Emitrom", "");
            }
        });

        aboutButton.addTapHandler(new TapHandler() {
            public void onTap(Button button, EventObject event) {
                if (version == null) {
                    loadMask.show();
                    
                    service.getVersion(new AsyncCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            loadMask.hide();
                            version = result;
                            MessageBox.alert("About", version);
                        }
        
                        @Override
                        public void onFailure(Throwable caught) {
                            loadMask.hide();
                            System.out.println(caught.getMessage());
                        }
                    });
                    
                } else {
                    MessageBox.alert("About", version);
                }
            }
        });
    }

    private void addHandler() {
        
        factory.getEventBus().addHandler(SourceUpdateEvent.TYPE,
                new SourceUpdateEvent.Handler() {
                    @Override
                    public void onSourceLocationUpdate(SourceUpdateEvent event) {
                        currentView = event.getSourceClass().replace(".", "/")
                                + ".java";

                    }
                });
        
        factory.getEventBus().addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
            
            @Override
            public void onPlaceChange(PlaceChangeEvent event) {
                stack.add(event.getNewPlace());
                backButton.setVisible(stack.size() > 1);
            }
        });
    }
    
    private native void highlight() /*-{
        $wnd.dp.SyntaxHighlighter.HighlightAll("code");
    }-*/;

}
