/*******************************************************************************
 * ToolBarViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.toolbars;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.containers.SegmentedButton;
import com.emitrom.gwt4.touch.client.containers.ToolBar;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.Scroller;
import com.emitrom.gwt4.touch.client.core.config.Dock;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.laf.Direction;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.client.widgets.Button;
import com.emitrom.gwt4.touch.client.widgets.Spacer;

public class ToolBarViewImpl extends Panel implements ToolBarView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private Panel innerPanel;
    private List<Button> buttons;

    public ToolBarViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {
        
        buttons = new ArrayList<Button>();
        
        innerPanel = new Panel();
        innerPanel.setCls("containers gray");
        innerPanel.setHtml("Pick a button, any button. <br /><small>By using SASS, all of the buttons on this screen can be restyled dynamically. The only images used are masks.</small>");
        
        ToolBar toolBar = new ToolBar();
        toolBar.setUi(UI.LIGHT);
        toolBar.setDocked(Dock.TOP);
        
        Scroller scroller = new Scroller();
        scroller.setDirection(Direction.HORIZONTAL);
        
        toolBar.setScrollable(scroller);
        
        Button button = new Button("Back");
        button.setUi(UI.BACK);
        buttons.add(button);
        toolBar.add(button);
        
        button = new Button("Default");
        button.setBadgeText("2");
        buttons.add(button);
        toolBar.add(button);
        
        button = new Button("Round");
        button.setUi(UI.ROUND);
        buttons.add(button);
        toolBar.add(button);
        
        toolBar.add(new Spacer());
        
        SegmentedButton segmentedButton = new SegmentedButton();
        segmentedButton.allowDepress();
        
        button = new Button("Option 1");
        button.setUi(UI.ROUND);
        buttons.add(button);
        segmentedButton.add(button);
        
        button = new Button("Option 2");
        button.setUi(UI.ROUND);
        buttons.add(button);
        segmentedButton.add(button);
        
        List<Button> pressedButtons = new ArrayList<Button>();
        pressedButtons.add(button);
        segmentedButton.setPressedButtons(pressedButtons);

        button = new Button("Option 3");
        button.setUi(UI.ROUND);
        buttons.add(button);
        segmentedButton.add(button);

        toolBar.add(segmentedButton);
        
        toolBar.add(new Spacer());
        
        button = new Button("Action");
        button.setUi(UI.ACTION);
        buttons.add(button);
        toolBar.add(button);

        button = new Button("Forward");
        button.setUi(UI.FORWARD);
        buttons.add(button);
        toolBar.add(button);

        add(toolBar);
        add(innerPanel);
        
        addListeners();
        
    }
    
    private void addListeners() {
        for (Button button : buttons) {
            button.addTapHandler(new TapHandler() {
                @Override
                public void onTap(Button button, EventObject event) {
                    innerPanel.setHtml("User tapped the '" + button.getText() + "' button.");
                }
            });
        }
    }

}
