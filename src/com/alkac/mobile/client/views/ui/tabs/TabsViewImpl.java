/*******************************************************************************
 * TabsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.tabs;

import com.emitrom.gwt4.touch.client.containers.TabPanel;
import com.emitrom.gwt4.touch.client.laf.Alignment;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.Pack;
import com.emitrom.gwt4.touch.client.tab.TabItem;


public class TabsViewImpl extends TabPanel implements TabsView {

	@SuppressWarnings("unused")
    private Presenter presenter;
    
    public TabsViewImpl() {
        setTabBarPosition(Alignment.TOP);
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    private void initialize() {
        
        getTabBar().getLayout().setPack(Pack.CENTER);
        setUi(UI.DARK);
        
        TabItem tab = new TabItem("Tab 1");
        tab.setCls("containers purple");
        tab.setHtml("The tabs above are also sortable.<br/>(tap and hold)");
        add(tab);
        
        tab = new TabItem("Tab 2");
        tab.setCls("containers orange");
        tab.setHtml("Tab 2");
        add(tab);
        
        tab = new TabItem("Tab 3");
        tab.setCls("containers green");
        tab.setHtml("Tab 3");
        add(tab);
        
    }
    
}