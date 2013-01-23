/*******************************************************************************
 * IconsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.icons;

import com.emitrom.gwt4.touch.client.containers.TabPanel;
import com.emitrom.gwt4.touch.client.containers.ToolBar;
import com.emitrom.gwt4.touch.client.core.Scroller;
import com.emitrom.gwt4.touch.client.core.config.Dock;
import com.emitrom.gwt4.touch.client.laf.Direction;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.Pack;
import com.emitrom.gwt4.touch.client.tab.TabItem;
import com.emitrom.gwt4.touch.client.utils.TouchIcons;
import com.emitrom.gwt4.touch.client.widgets.Button;

public class IconsViewImpl extends TabPanel implements IconsView {

    @SuppressWarnings("unused")
    private Presenter presenter;

    public IconsViewImpl() {
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        ToolBar topToolBar = new ToolBar();
        
        topToolBar.setUi(UI.LIGHT);
        topToolBar.setDocked(Dock.TOP);
        topToolBar.setHeight(47);
        topToolBar.getLayout().setPack(Pack.CENTER);

        Scroller toolBarscroller = new Scroller();
        toolBarscroller.setDirection(Direction.HORIZONTAL);

        topToolBar.setScrollable(toolBarscroller);

        Button button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.ACTION);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.ADD);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.ARROW_UP);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.ARROW_RIGHT);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.ARROW_DOWN);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.ARROW_LEFT);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.COMPOSE);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.DELETE);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.REFRESH);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.REPLY);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.SEARCH);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.STAR);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.HOME);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.LOCATE);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.MAPS);
        topToolBar.add(button);

        button = new Button();
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.setIconCls(TouchIcons.TRASH);
        topToolBar.add(button);

        add(topToolBar);

        /**
         * Bottom Tab Items
         */
        Scroller tabBarscroller = new Scroller();
        tabBarscroller.setDirection(Direction.HORIZONTAL);

        getTabBar().getLayout().setPack(Pack.CENTER);
        getTabBar().setScrollable(tabBarscroller);
        getTabBar().setDocked(Dock.BOTTOM);

        TabItem item = new TabItem("BookMarks");
        item.setIconCls(TouchIcons.BOOKMARKS);
        item.setCls("card containers green");
        item.setHtml("Both toolbars and tabbars have built-in, ready to use icons. Styling custom icons is no hassle.<p><small>If you can&#8217;t see all of the buttons below, try scrolling left/right.</small></p>");
        add(item);

        item = new TabItem("Download");
        item.setIconCls(TouchIcons.DOWNLOAD);
        item.setCls("card containers green");
        item.setHtml("Pressed Download");
        add(item);

        item = new TabItem("Favorites");
        item.setIconCls(TouchIcons.FAVORITES);
        item.setCls("card containers green");
        item.setHtml("Pressed Favorites");
        add(item);

        item = new TabItem("Info");
        item.setIconCls(TouchIcons.INFO);
        item.setCls("card containers green");
        item.setHtml("Pressed Info");
        add(item);

        item = new TabItem("More");
        item.setIconCls(TouchIcons.MORE);
        item.setCls("card containers green");
        item.setHtml("Pressed More");
        add(item);

        item = new TabItem("Search");
        item.setIconCls(TouchIcons.SEARCH);
        item.setCls("card containers green");
        item.setHtml("Pressed Search");
        add(item);

        item = new TabItem("Settings");
        item.setIconCls(TouchIcons.SETTINGS);
        item.setCls("card containers green");
        item.setHtml("Pressed Settings");
        add(item);

        item = new TabItem("Team");
        item.setIconCls(TouchIcons.TEAM);
        item.setCls("card containers green");
        item.setHtml("Pressed Team");
        add(item);

        item = new TabItem("Time");
        item.setIconCls(TouchIcons.TIME);
        item.setCls("card containers green");
        item.setHtml("Pressed Time");
        add(item);

        item = new TabItem("User");
        item.setIconCls(TouchIcons.USER);
        item.setCls("card containers green");
        item.setHtml("Pressed User");
        add(item);

    }

}
