/*******************************************************************************
 * BottomTabsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.bottomtabs;

import com.emitrom.gwt4.touch.client.containers.TabPanel;
import com.emitrom.gwt4.touch.client.core.Scroller;
import com.emitrom.gwt4.touch.client.core.config.Dock;
import com.emitrom.gwt4.touch.client.laf.Direction;
import com.emitrom.gwt4.touch.client.layout.Pack;
import com.emitrom.gwt4.touch.client.tab.TabItem;
import com.emitrom.gwt4.touch.client.utils.TouchIcons;

public class BottomTabsViewImpl extends TabPanel implements BottomTabsView {

    @SuppressWarnings("unused")
    private Presenter presenter;

    public BottomTabsViewImpl() {
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        Scroller tabBarscroller = new Scroller();
        tabBarscroller.setDirection(Direction.HORIZONTAL);

        getTabBar().getLayout().setPack(Pack.CENTER);
        getTabBar().setScrollable(tabBarscroller);
        getTabBar().setDocked(Dock.BOTTOM);

        TabItem item = new TabItem("About");
        item.setIconCls(TouchIcons.INFO);
        item.setCls("containers light_gray");
        item.setHtml("<p>Docking tabs to the bottom will automatically change their style. The tabs below are ui=\"light\", though the standard type is dark. Badges (like the 4 below) can be added by setting <code>badgeText</code> when creating a tab/card or by using <code>setBadge()</code> on the tab later.</p>");
        add(item);

        item = new TabItem("Favorites");
        item.setIconCls(TouchIcons.FAVORITES);
        item.setCls("containers  blue");
        item.setHtml("Favorites Card");
        item.setBadgeText("4");
        add(item);

        item = new TabItem("Downloads");
        item.setIconCls(TouchIcons.DOWNLOAD);
        item.setCls("containers  green");
        item.setBadgeText("Text can go here too, but it will be cut off if it is too long.");
        item.setHtml("Downloads Card");
        add(item);
        
        item = new TabItem("Settings");
        item.setIconCls(TouchIcons.SETTINGS);
        item.setCls("containers  orange");
        item.setHtml("Settings Card");
        add(item);
        
        item = new TabItem("User");
        item.setIconCls(TouchIcons.USER);
        item.setCls("containers  purple");
        item.setHtml("User Card");
        add(item);

    }

}
