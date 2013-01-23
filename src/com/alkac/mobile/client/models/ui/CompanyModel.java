/*******************************************************************************
 * CompanyModel.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.models.ui;

import java.util.ArrayList;

import com.emitrom.gwt4.touch.client.data.BaseModel;

public class CompanyModel extends BaseModel {

    public CompanyModel() {
    }

    public CompanyModel(String name, String price) {
        setName(name);
        setPrice(price);
    }

    public CompanyModel(String name, String price, String change, String pct, String updated) {
        setName(name);
        setPrice(price);
        setChange(change);
        setPct(pct);
        setUpdated(updated);
    }

    public void setName(String name) {
        set("name", name);
    }

    public void setChange(String name) {
        set("change", name);
    }

    public void setPct(String name) {
        set("pct", name);
    }

    public void setUpdated(String name) {
        set("updated", name);
    }

    public void setPrice(String price) {
        set("price", price);
    }

    public static ArrayList<CompanyModel> getList() {
        
        ArrayList<CompanyModel> list = new ArrayList<CompanyModel>();
        list.add(new CompanyModel("3m Co", "71.72", "0.02", "0.03", "9/1/2010"));
        list.add(new CompanyModel("Alcoa Inc", "29.01", "0.42", "1.47", "9/1/2010"));
        list.add(new CompanyModel("Altria Group Inc", "83.81", "0.28", "0.34", "9/1/2010"));
        list.add(new CompanyModel("American Express Company", "52.55", "0.01", "0.02", "9/1/2010"));
        list.add(new CompanyModel("American International Group, Inc.", "64.13", "0.31", "0.49", "9/1/2010"));
        list.add(new CompanyModel("AT&#38;T Inc.", "31.61", "-0.48", "-1.54", "9/1/2010"));
        list.add(new CompanyModel("Boeing Co.", "75.43", "0.53", "0.71", "9/1/2010"));
        list.add(new CompanyModel("Caterpillar Inc.", "67.27", "0.92", "1.39", "9/1/2010"));
        list.add(new CompanyModel("Citigroup, Inc.", "49.37", "0.02", "0.04", "9/1/2010"));
        list.add(new CompanyModel("E.I. du Pont de Nemours and Company", "40.48", "0.51", "1.28", "9/1/2010"));
        list.add(new CompanyModel("Exxon Mobil Corp", "68.1", "-0.43", "-0.64", "9/1/2010"));
        
        return list;
    }
    
}
