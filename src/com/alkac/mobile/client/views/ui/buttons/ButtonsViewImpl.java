/*******************************************************************************
 * ButtonsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.buttons;

import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.Align;
import com.emitrom.gwt4.touch.client.layout.HBoxLayout;
import com.emitrom.gwt4.touch.client.layout.Pack;
import com.emitrom.gwt4.touch.client.layout.VBoxLayout;
import com.emitrom.gwt4.touch.client.widgets.Button;


public class ButtonsViewImpl extends Panel implements ButtonsView {

    @SuppressWarnings("unused")
	private Presenter presenter;
    
    public ButtonsViewImpl() {
        VBoxLayout vboxLayout = new VBoxLayout();
        vboxLayout.setPack(Pack.CENTER);
        vboxLayout.setAlign(Align.STRETCH);
    	setLayout(vboxLayout);
    	setScrollable(true);
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    private void initialize() {
        
        Panel flexPanel = new Panel();
        flexPanel.setFlex(1);
        HBoxLayout hboxLayout = new HBoxLayout();
        hboxLayout.setAlign(Align.MIDDLE);
        hboxLayout.setPack(Pack.CENTER);
        flexPanel.setLayout(hboxLayout);
        flexPanel.setBorder(2);
        
        Button normal = new Button("Normal", UI.NORMAL);
        normal.setFlex(1);
        normal.setMargin(10);
        
        Button normalRound = new Button("Round", UI.ROUND);
        normalRound.setFlex(1);
        normalRound.setMargin(10);

        Button normalSmall = new Button("Small", UI.SMALL);
        normalSmall.setFlex(1);
        normalSmall.setMargin(10);

        flexPanel.add(normal);
        flexPanel.add(normalRound);
        flexPanel.add(normalSmall);
        
        Panel flexPanel1 = new Panel();
        flexPanel1.setFlex(1);
        HBoxLayout hboxLayout1 = new HBoxLayout();
        hboxLayout1.setAlign(Align.MIDDLE);
        hboxLayout1.setPack(Pack.CENTER);
        flexPanel1.setLayout(hboxLayout1);
        flexPanel1.setBorder(2);
        
        Button normal1 = new Button("Decline", UI.DECLINE);
        normal1.setFlex(1);
        normal1.setMargin(10);
        
        Button normalRound1 = new Button("Round", UI.DECLINE_ROUND);
        normalRound1.setFlex(1);
        normalRound1.setMargin(10);

        Button normalSmall1 = new Button("Small", UI.DECLINE_SMALL);
        normalSmall1.setFlex(1);
        normalSmall1.setMargin(10);

        flexPanel1.add(normal1);
        flexPanel1.add(normalRound1);
        flexPanel1.add(normalSmall1);
        
        Panel flexPanel2 = new Panel();
        flexPanel2.setFlex(1);
        HBoxLayout hboxLayout2 = new HBoxLayout();
        hboxLayout2.setAlign(Align.MIDDLE);
        hboxLayout2.setPack(Pack.CENTER);
        flexPanel2.setLayout(hboxLayout2);
        flexPanel2.setBorder(1);
        
        Button normal2 = new Button("Confirm", UI.CONFIRM);
        normal2.setFlex(1);
        normal2.setMargin(10);
        
        Button normalRound2 = new Button("Round", UI.CONFIRM_ROUND);
        normalRound2.setFlex(1);
        normalRound2.setMargin(10);

        Button normalSmall2 = new Button("Small", UI.CONFIRM_SMALL);
        normalSmall2.setFlex(1);
        normalSmall2.setMargin(10);
        
        Button back = new Button("Back", UI.BACK);
        back.setFlex(1);
        back.setMargin(10);

        flexPanel2.add(normal2);
        flexPanel2.add(normalRound2);
        flexPanel2.add(normalSmall2);
        flexPanel2.add(back);

        add(flexPanel);
        add(flexPanel1);
        add(flexPanel2);

    }
    
}