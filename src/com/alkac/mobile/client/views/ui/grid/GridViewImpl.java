/*******************************************************************************
 * GridViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.grid;

import java.util.ArrayList;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.config.XType;
import com.emitrom.gwt4.touch.client.core.handlers.dataview.DataViewSelectHandler;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.dataview.DataView;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.ux.grid.client.Grid;
import com.emitrom.gwt4.touch.ux.grid.client.core.GridColumn;
import com.emitrom.gwt4.touch.ux.grid.client.core.GridColumnEditor;
import com.emitrom.gwt4.touch.ux.grid.client.core.GridColumnRenderer;
import com.emitrom.gwt4.touch.ux.grid.client.core.GridFeature;
import com.alkac.mobile.client.models.ui.CompanyModel;

public class GridViewImpl extends Panel implements GridView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private Grid grid;

    public GridViewImpl() {
        setLayout(new FitLayout());
        initialize();
        addListeners();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        ArrayList<GridColumn> columns = new ArrayList<GridColumn>();

        GridColumn c = new GridColumn();
        c.setHeader("Company");
        c.setWidth("40%");
        c.setStyle("padding-left: 1em;");
        c.setDataIndex("name");
        c.setEditor(new GridColumnEditor(XType.TEXT_FIELD));
        columns.add(c);

        c = new GridColumn();
        c.setHeader("Price");
        c.setDataIndex("price");
        c.setStyle("text-align: center;");
        c.setWidth("15%");
        columns.add(c);

        c = new GridColumn();
        c.setHeader("Change");
        c.setDataIndex("change");
        c.setStyle("text-align: center;");
        c.setWidth("15%");
        c.setRenderer(new GridColumnRenderer() {
            @Override
            public String onRender(String value) {
                Double d = Double.parseDouble(value);
                String color = (d > 0) ? "009933" : "FF0000";
                return "<span style='color: #" + color + ";'>" + value + "</span>";
            }
        });
        columns.add(c);

        c = new GridColumn();
        c.setHeader(" $ Change");
        c.setDataIndex("pct");
        c.setStyle("text-align: center;");
        c.setWidth("15%");
        c.setRenderer(new GridColumnRenderer() {
            @Override
            public String onRender(String value) {
                Double d = Double.parseDouble(value);
                String color = (d > 0) ? "00993" : "FF0000";
                return "<span style='color: #" + color + ";'>" + value + "</span>";
            }
        });
        columns.add(c);

        c = new GridColumn();
        c.setHeader("Last Updated");
        c.setDataIndex("updated");
        c.setStyle("text-align: right; padding-right: 1em;");
        c.setWidth("15%");
        c.setSortable(false);
        columns.add(c);

        grid = new Grid(new Store(CompanyModel.getList()), columns, GridFeature.PAGING, GridFeature.EDITABLE);
        grid.addTo(this);

    }
    
    private void addListeners() {
        
        grid.getView().addSelectHandler(new DataViewSelectHandler() {

            @Override
            public void onSelect(DataView dataView, BaseModel record, Object eOpts) {
                StringBuilder displayRow = new StringBuilder();
                displayRow.append("<div>");
                displayRow.append("Company: " + record.get("name") + "</br>");
                displayRow.append("Price: " + record.get("price") + "</br>");
                displayRow.append("Change: " + record.get("change") + "</br>");
                displayRow.append("$ Change: " + record.get("pct") + "</br>");
                displayRow.append("Last Updated: " + record.get("updated") + "</br>");
                displayRow.append("</div>");
                MessageBox.alert(displayRow.toString());
            }
            
        });
    }

}
