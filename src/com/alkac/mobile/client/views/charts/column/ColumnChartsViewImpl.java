/*******************************************************************************
 * ColumnChartsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
 * 
 * Copyright (c) 2012 Emitrom LLC. All rights reserved.
 * 
 * The Kitchen Sink is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * The Kitchen Sink is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * the Kitchen Sink. If not, see http://www.emitrom.com/gpl_license
 * 
 * For licensing questions, please contact us at licensing@emitrom.com
 * 
 ******************************************************************************/
package com.alkac.mobile.client.views.charts.column;

import com.emitrom.gwt4.touch.charts.client.Animation;
import com.emitrom.gwt4.touch.charts.client.Chart;
import com.emitrom.gwt4.touch.charts.client.axis.CategoryAxis;
import com.emitrom.gwt4.touch.charts.client.axis.NumericAxis;
import com.emitrom.gwt4.touch.charts.client.laf.BarAttribute;
import com.emitrom.gwt4.touch.charts.client.laf.Gradient;
import com.emitrom.gwt4.touch.charts.client.laf.Label;
import com.emitrom.gwt4.touch.charts.client.series.ColumnSeries;
import com.emitrom.gwt4.touch.charts.client.series.renderers.SeriesRenderer;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.containers.ToolBar;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.config.Dock;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.draw.Sprite;
import com.emitrom.gwt4.touch.client.fx.layout.card.Easing;
import com.emitrom.gwt4.touch.client.laf.Color;
import com.emitrom.gwt4.touch.client.laf.Position;
import com.emitrom.gwt4.touch.client.laf.RGB;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.client.utils.TouchIcons;
import com.emitrom.gwt4.touch.client.widgets.Button;
import com.emitrom.gwt4.touch.client.widgets.Spacer;
import com.alkac.mobile.client.models.charts.ChartsDataUtil;

public class ColumnChartsViewImpl extends Panel implements ColumnChartsView {

    private Store store = ChartsDataUtil.getStore(5, 20);

    @SuppressWarnings("unused")
    private Presenter presenter;

    public ColumnChartsViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        Chart chart = new Chart(store);
        chart.setAnimate(new Animation(750, Easing.BOUNCE_OUT));

        Gradient gradient1 = new Gradient("v-1", 0);
        gradient1.setStops(new RGB(212, 40, 40), new RGB(117, 14, 14));

        Gradient gradient2 = new Gradient();
        gradient2.setId("v-2");
        gradient2.setAngle(0);
        gradient2.setStops(new RGB(180, 216, 42), new RGB(94, 114, 13));

        Gradient gradient3 = new Gradient();
        gradient3.setId("v-3");
        gradient3.setAngle(0);
        gradient3.setStops(new RGB(43, 221, 115), new RGB(14, 117, 56));

        Gradient gradient4 = new Gradient();
        gradient4.setId("v-4");
        gradient4.setAngle(0);
        gradient4.setStops(new RGB(45, 117, 226), new RGB(14, 56, 117));

        Gradient gradient5 = new Gradient();
        gradient5.setId("v-5");
        gradient5.setAngle(0);
        gradient5.setStops(new RGB(187, 45, 222), new RGB(85, 10, 103));

        chart.setGradients(gradient1, gradient2, gradient3, gradient4, gradient5);

        NumericAxis axis = new NumericAxis();
        axis.setPosition(Position.LEFT);
        axis.setFields("2009");
        axis.setMinimum(0);
        axis.setMaximum(100);
        axis.setTitle("Number of Hits");
        axis.setGrid(new Color("#555"), new Color("#777"));

        CategoryAxis axis2 = new CategoryAxis();
        axis2.setPosition(Position.BOTTOM);
        axis2.setFields("name");
        axis2.setTitle("Month of the Year");

        chart.setAxes(axis, axis2);

        final Color[] colors = {gradient1, gradient2, gradient3, gradient4, gradient5};

        final ColumnSeries serie = new ColumnSeries();
        serie.setAxis("left");
        serie.setHighlight(true);
        serie.setXField("name");
        serie.setYField("2009");

        Label label = new Label();
        label.setField("2009");
        serie.setLabel(label);
        serie.setRenderer(new SeriesRenderer() {
            @Override
            public void onRender(Sprite sprite, BaseModel record, BarAttribute attributes, int index, Store store) {
                attributes.setFill(colors[index % colors.length]);
            }
        });
        chart.setSeries(serie);

        ToolBar toolBar = new ToolBar();
        toolBar.setDocked(Dock.BOTTOM);
        toolBar.add(new Spacer());

        Button reloadButton = new Button();
        reloadButton.setIconCls(TouchIcons.SHUFFLE);
        reloadButton.setIconMask(true);
        reloadButton.setUi(UI.PLAIN);
        reloadButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                store.setData(ChartsDataUtil.getColumnChartValues(5, 20));
            }
        });
        toolBar.add(reloadButton);

        add(toolBar);
        add(chart);

    }

}