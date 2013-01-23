/*******************************************************************************
 * RadarChartsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.charts.radar;

import com.emitrom.gwt4.touch.charts.client.Chart;
import com.emitrom.gwt4.touch.charts.client.Legend;
import com.emitrom.gwt4.touch.charts.client.axis.RadialAxis;
import com.emitrom.gwt4.touch.charts.client.interactions.PieRotate;
import com.emitrom.gwt4.touch.charts.client.interactions.Reset;
import com.emitrom.gwt4.touch.charts.client.laf.Label;
import com.emitrom.gwt4.touch.charts.client.series.RadarSeries;
import com.emitrom.gwt4.touch.charts.client.theme.Theme;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.containers.ToolBar;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.config.Dock;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.laf.Position;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.client.utils.TouchIcons;
import com.emitrom.gwt4.touch.client.widgets.Button;
import com.emitrom.gwt4.touch.client.widgets.Spacer;
import com.alkac.mobile.client.models.charts.ChartsDataUtil;

public class RadarChartsViewImpl extends Panel implements RadarChartsView {

    @SuppressWarnings("unused")
    private Presenter presenter;

    public RadarChartsViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        final Store store = ChartsDataUtil.getStore(12, 20);
        
        ToolBar toolBar = new ToolBar();
        toolBar.setTitle("Radar");
        toolBar.setDocked(Dock.BOTTOM);

        Button button = new Button();
        button.setIconCls(TouchIcons.SHUFFLE);
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                store.setData(ChartsDataUtil.getColumnChartValues(12, 20));
            }
        });
        toolBar.add(new Spacer());
        toolBar.add(button);

        Chart chart = new Chart(store);
        chart.setTheme(Theme.DEFAULT);
        chart.setThemeCls("radar1");
        chart.setAnimate(true);
        chart.setInsetPadding(20);
        chart.setLegend(new Legend(Position.LEFT));
        chart.setInteractions(new Reset(), new PieRotate());

        RadialAxis radialAxis = new RadialAxis();
        Label label = new Label();
        label.setDisplay(true);
        radialAxis.setLabel(label);
        chart.setAxes(radialAxis);

        RadarSeries radarSeries = new RadarSeries();
        radarSeries.setXField("name");
        radarSeries.setYField("2007");

        label = new Label();
        label.setDisplay("rotate");
        label.setField("2007");
        radarSeries.setLabel(label);

        RadarSeries radarSeries2 = new RadarSeries();
        radarSeries2.setXField("name");
        radarSeries2.setYField("2008");
        radarSeries2.setShowInLegend(true);

        RadarSeries radarSeries3 = new RadarSeries();
        radarSeries3.setXField("name");
        radarSeries3.setYField("2009");
        radarSeries3.setShowInLegend(true);

        chart.setSeries(radarSeries, radarSeries2, radarSeries3);
        
        add(toolBar);
        add(chart);

    }

}