/*******************************************************************************
 * BarChartsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.charts.bar;

import com.emitrom.gwt4.touch.charts.client.Animation;
import com.emitrom.gwt4.touch.charts.client.Chart;
import com.emitrom.gwt4.touch.charts.client.ChartPanel;
import com.emitrom.gwt4.touch.charts.client.Legend;
import com.emitrom.gwt4.touch.charts.client.axis.CategoryAxis;
import com.emitrom.gwt4.touch.charts.client.axis.NumericAxis;
import com.emitrom.gwt4.touch.charts.client.handlers.ItemCompareInteractionHandler;
import com.emitrom.gwt4.touch.charts.client.handlers.ItemShowHandler;
import com.emitrom.gwt4.touch.charts.client.interactions.ChartItem;
import com.emitrom.gwt4.touch.charts.client.interactions.ItemCompare;
import com.emitrom.gwt4.touch.charts.client.interactions.ItemHighlight;
import com.emitrom.gwt4.touch.charts.client.interactions.ItemInfo;
import com.emitrom.gwt4.touch.charts.client.interactions.OffsetValue;
import com.emitrom.gwt4.touch.charts.client.interactions.PanZoom;
import com.emitrom.gwt4.touch.charts.client.interactions.Reset;
import com.emitrom.gwt4.touch.charts.client.interactions.ToggleStacked;
import com.emitrom.gwt4.touch.charts.client.series.BarSeries;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.containers.ToolBar;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.fx.layout.card.AnimationType;
import com.emitrom.gwt4.touch.client.fx.layout.card.Easing;
import com.emitrom.gwt4.touch.client.laf.Alignment;
import com.emitrom.gwt4.touch.client.laf.Position;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.client.utils.TouchIcons;
import com.emitrom.gwt4.touch.client.widgets.Button;
import com.alkac.mobile.client.models.charts.ChartsDataUtil;

public class BarChartsViewImpl extends Panel implements BarChartsView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private Store store = ChartsDataUtil.getStore(5, 20);
    private ChartPanel panel;

    public BarChartsViewImpl() {
        setLayout(new FitLayout());
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        final Chart chart = new Chart(store);
        chart.setAnimate(new Animation(750, Easing.BOUNCE_OUT));

        Legend legend = new Legend(Position.RIGHT);
        legend.setLabelFont("20 px Arial");
        chart.setLegend(legend);

        NumericAxis numericAxis = new NumericAxis();
        numericAxis.setPosition(Position.BOTTOM);
        numericAxis.setFields("2008", "2009", "2010");
        numericAxis.setTitle("Number of Hits");
        numericAxis.setMinimum(0);

        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.setPosition(Position.LEFT);
        categoryAxis.setFields("name");
        categoryAxis.setTitle("Month of the year");
        chart.setAxes(numericAxis, categoryAxis);

        BarSeries barSeries = new BarSeries();
        barSeries.setXField("name");
        barSeries.setYField("2008", "2009", "2010");
        barSeries.setAxis(Alignment.BOTTOM);
        barSeries.setHighlight(true);
        barSeries.setShowInLegend(true);
        chart.setSeries(barSeries);

        ItemCompare itemCompare = new ItemCompare();
        itemCompare.setOffset(new OffsetValue(-10));
        itemCompare.addInteractionHandler(new ItemCompareInteractionHandler() {
            @Override
            public void onShow(ItemCompare itemCompare) {
                ChartItem firstItem = itemCompare.getFirtsItem();
                ChartItem secondItem = itemCompare.getSecondItem();
                panel.setDescriptionTitle(firstItem.getValue().getString(0)
                                + " to "
                                + secondItem.getValue().getString(0)
                                + " : "
                                + Math.round((secondItem.getValue().getNumber(1) - firstItem.getValue().getNumber(1))
                                                / firstItem.getValue().getNumber(1) * 100) + "%");
                panel.getHeaderPanel().getLayout().setAnimation(AnimationType.SLIDE);
                panel.getHeaderPanel().setActiveItem(1);

            }

            @Override
            public void onHide() {
                panel.getHeaderPanel().getLayout().getAnimation().setReverse(true);
                panel.getHeaderPanel().setActiveItem(0);
            }
        });

        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setGesture("longpress");
        itemInfo.addItemShowHandler(new ItemShowHandler() {
            @Override
            public void onItemShow(ItemInfo itemInfo, ChartItem item, Panel panel) {
                panel.removeAll();

                ToolBar toolBar = new ToolBar("Details");
                panel.add(toolBar);

                String html = "<ul>";
                html += "<li><b>Month : </b>" + item.getValue().getString(0) + "</li>";
                html += "<li><b>Value : </b>" + item.getValue().getNumber(1) + "</li>";
                html += "</li>";
                panel.setHtml(html);
            }
        });
        chart.setInteractions(new Reset(), new ToggleStacked(), new PanZoom(), new ItemHighlight(), itemInfo,
                        itemCompare);

        Button button = new Button();
        button.setIconCls(TouchIcons.SHUFFLE);
        button.setIconMask(true);
        button.setUi(UI.PLAIN);
        button.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                store.setData(ChartsDataUtil.getColumnChartValues(5, 20));
            }
        });

        panel = new ChartPanel("Bar Chart", chart);
        panel.addButton(button);

        add(panel);

    }

}
