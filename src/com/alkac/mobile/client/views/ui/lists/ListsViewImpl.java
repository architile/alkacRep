/*******************************************************************************
 * ListsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.lists;

import java.util.ArrayList;
import java.util.Date;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.containers.TabPanel;
import com.emitrom.gwt4.touch.client.core.handlers.list.ItemDisclosureHandler;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.data.Grouper;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.dataview.DisclosureList;
import com.emitrom.gwt4.touch.client.dataview.ListDataView;
import com.emitrom.gwt4.touch.client.dataview.RoundedList;
import com.emitrom.gwt4.touch.client.laf.Alignment;
import com.emitrom.gwt4.touch.client.layout.Align;
import com.emitrom.gwt4.touch.client.layout.HBoxLayout;
import com.emitrom.gwt4.touch.client.layout.Pack;
import com.emitrom.gwt4.touch.client.layout.VBoxLayout;
import com.emitrom.gwt4.touch.client.tab.TabItem;
import com.emitrom.gwt4.touch.client.utils.OS;
import com.emitrom.gwt4.touch.ux.dataview.pullrefresh.client.PullRefresh;
import com.emitrom.gwt4.touch.ux.dataview.pullrefresh.client.PullRefreshHandler;
import com.alkac.mobile.client.models.ui.UserModel;
import com.google.gwt.core.client.JavaScriptObject;

public class ListsViewImpl extends TabPanel implements ListsView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    TabItem simpleListTabItem;
    TabItem groupedListTabItem;
    TabItem disclosureListTabItem;
    TabItem pullRefreshListTabItem;

    public ListsViewImpl() {
        setTabBarPosition(Alignment.TOP);
        HBoxLayout hbox = new HBoxLayout(Pack.CENTER);
        hbox.setAlign(Align.CENTER);
        getTabBar().setLayout(hbox);
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        getTabBar().getLayout().setPack(Pack.CENTER);
        String TEMPLATE = "{" + UserModel.FIRSTNAME + "} - {" + UserModel.LASTNAME + "}";

        simpleListTabItem = new TabItem("Simple");
        groupedListTabItem = new TabItem("Grouped");
        disclosureListTabItem = new TabItem("Disclosure");
        pullRefreshListTabItem = new TabItem("Pull Refresh");

        Store store = new Store(UserModel.createUserList());
        store.setSorter(UserModel.FIRSTNAME);
        store.setGrouper(new Grouper() {
            @Override
            public String onGroup(BaseModel record) {
                String firstLetter = record.get(UserModel.FIRSTNAME).charAt(0) + "";
                return firstLetter;

            }
        });

        /**
         * Simple ListDataView
         */
        ListDataView userList = new ListDataView(TEMPLATE, store);
        
        /**
         * Grouped ListDataView, but this time lets create is with an empty data model
         */
        store = new Store(new ArrayList<UserModel>(), UserModel.FIELDS);
        store.setSorter(UserModel.FIRSTNAME);
        store.setGrouper(new Grouper() {
            @Override
            public String onGroup(BaseModel record) {
                String firstLetter = record.get(UserModel.FIRSTNAME).charAt(0) + "";
                return firstLetter;
            }
        });
        
        // Now lets add some data to try things out
        store.addAll(UserModel.createUserList());
        
        ListDataView userGroupedList = new ListDataView(TEMPLATE, store);
        userGroupedList.setGrouped(true);
        userGroupedList.setIndexBar(true);

        /**
         * Disclosure ListDataView
         */
        store = new Store(UserModel.createUserList());
        store.setSorter(UserModel.FIRSTNAME);
        store.setGrouper(new Grouper() {

            @Override
            public String onGroup(BaseModel record) {
                String firstLetter = record.get(UserModel.FIRSTNAME).charAt(0) + "";
                return firstLetter;
            }
        });
        DisclosureList userDisclosureList = new DisclosureList(TEMPLATE, store) {
            @Override
            protected native JavaScriptObject create(JavaScriptObject config) /*-{
        		config.onItemDisclosure = {};
        		config.ui = 'round';
        		return new $wnd.Ext.dataview.List(config);
            }-*/;
        };
        userDisclosureList.setOnItemDisclosure(new ItemDisclosureHandler() {

            @Override
            public void onItemDisclosure(BaseModel record, JavaScriptObject node, int index) {
                String firstName = record.get(UserModel.FIRSTNAME);
                MessageBox.alert("Disclose more info for: " + firstName);
            }
        });
        userDisclosureList.setPinHeaders(false);
        userDisclosureList.setGrouped(true);
        
        final Store prStore = new Store(new ArrayList<UserModel>(), UserModel.FIELDS);
        for (int i = 0; i < 10; i++) {
            prStore.add(new UserModel("First." + (int) (Math.random() * 1000), "Last." + (int) (Math.random() * 1000)));
        }
        
        RoundedList pullRefreshList = new RoundedList(TEMPLATE, prStore);
        pullRefreshList.setPinHeaders(true);
        pullRefreshList.setDisableSelection(true);

        final PullRefresh pr = new PullRefresh();
        pr.setPullRefreshText("Pull me");
        pr.setReleaseRefreshText("Release me");
        pr.setLastUpdatedText("List not updated yet");
        pr.addPullRefreshHandler(new PullRefreshHandler() {
            @SuppressWarnings("deprecation")
            @Override
            public void onRefresh() {
                prStore.removeAll();
                for (int i = 0; i < 10; i++) {
                    prStore.add(new UserModel("First." + (int) (Math.random() * 1000), "Last." + (int) (Math.random() * 1000)));
                }
                pr.setLastUpdatedText("Last Update : " + new Date().toLocaleString());
            }
        });
        pullRefreshList.setPlugins(pr);

        if (OS.get().getDeviceType() != null && !OS.get().getDeviceType().equals("Phone")) {

            Panel panel = new Panel();
            VBoxLayout vboxLayout = new VBoxLayout(Pack.CENTER);
            vboxLayout.setAlign(Align.CENTER);
            panel.setLayout(vboxLayout);
            userList.setWidth(300);
            userList.setHeight(500);
            panel.add(userList);
            simpleListTabItem.add(panel);

            panel = new Panel();
            vboxLayout = new VBoxLayout(Pack.CENTER);
            vboxLayout.setAlign(Align.CENTER);
            panel.setLayout(vboxLayout);
            userGroupedList.setWidth(300);
            userGroupedList.setHeight(500);
            panel.add(userGroupedList);
            groupedListTabItem.add(panel);

            panel = new Panel();
            vboxLayout = new VBoxLayout(Pack.CENTER);
            vboxLayout.setAlign(Align.CENTER);
            panel.setLayout(vboxLayout);
            userDisclosureList.setWidth(300);
            userDisclosureList.setHeight(500);
            panel.add(userDisclosureList);
            disclosureListTabItem.add(panel);
            
            panel = new Panel();
            vboxLayout = new VBoxLayout(Pack.CENTER);
            vboxLayout.setAlign(Align.CENTER);
            panel.setLayout(vboxLayout);
            pullRefreshList.setWidth(300);
            pullRefreshList.setHeight(500);
            panel.add(pullRefreshList);
            pullRefreshListTabItem.add(panel);

        } else {
            simpleListTabItem.add(userList);
            groupedListTabItem.add(userGroupedList);
            disclosureListTabItem.add(userDisclosureList);
            pullRefreshListTabItem.add(pullRefreshList);
        }

        add(simpleListTabItem);
        add(groupedListTabItem);
        add(disclosureListTabItem);
        add(pullRefreshListTabItem);

    }

}
