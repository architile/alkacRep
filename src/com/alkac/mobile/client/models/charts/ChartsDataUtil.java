/**
 * ChartsDataUtil.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.models.charts;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.data.Store;

public class ChartsDataUtil {

    private static String[] months = { "January", "February", "March", "April", "May", "June", "July", "August",
                    "September", "October", "Novemver", "December" };

    public static List<ChartValue> getChartValues1(int n, int floor) {
        floor = 10;
        ArrayList<ChartValue> values = new ArrayList<ChartValue>();
        ChartValue value = null;
        for (int i = 0; i < n; i++) {
            value = new ChartValue();
            value.setName(months[i % 12]);
            value.setData1(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData2(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData3(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData4(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData5(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData6(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData7(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData8(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData9(Math.floor(Math.max((Math.random() * 100), floor)));
            values.add(value);
        }

        return values;
    }

    public static ArrayList<ColumnChartValue> getColumnChartValues(int n, int floor) {
        floor = 10;
        ArrayList<ColumnChartValue> values = new ArrayList<ColumnChartValue>();
        ColumnChartValue value = null;
        for (int i = 0; i < n; i++) {
            value = new ColumnChartValue();
            value.setName(months[i % 12]);
            value.setData1(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData2(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData3(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2003(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2004(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2005(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2006(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2007(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2008(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2009(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2010(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setIphone(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setAndroid(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setIpad(Math.floor(Math.max((Math.random() * 100), floor)));
            values.add(value);
        }

        return values;
    }

    public static List<ChartValue2> getChartValues2(int n, int floor) {
        floor = 10;
        ArrayList<ChartValue2> values = new ArrayList<ChartValue2>();
        ChartValue2 value = null;
        for (int i = 0; i < n; i++) {
            value = new ChartValue2();
            value.setName(months[i % 12]);
            value.setData1(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData2(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setData3(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2003(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2004(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2005(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2006(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2007(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2008(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2009(Math.floor(Math.max((Math.random() * 100), floor)));
            value.set2010(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setIphone(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setAndroid(Math.floor(Math.max((Math.random() * 100), floor)));
            value.setIpad(Math.floor(Math.max((Math.random() * 100), floor)));
            values.add(value);
        }

        return values;
    }

    public static Store getData() {
        return new Store(getChartValues1(6, 20));
    }

    public static Store getColumnChartData() {
        return new Store(getChartValues2(5, 20));
    }

    public static Store getStore(int n, int floor) {
        return new Store(getChartValues2(n, floor));
    }

}
