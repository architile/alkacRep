/*******************************************************************************
 * RankModel.java is part of the Kitchen Sink (for Touch4j 2.0)
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
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.field.SelectOption;

public class RankModel extends BaseModel {

    public static final String TITLE = "title";
    public static final String RANK = "rank";

    public RankModel() {
        setTitle("");
        setRank("");
    }

    private void setRank(String string) {
        set(RANK, string);
    }

    public RankModel(String rank) {
        setTitle(rank);
    }

    public RankModel(String title, String rank) {
        setTitle(title);
        setRank(rank);
    }

    private void setTitle(String rank) {
        set(TITLE, rank);
    }

    public static ArrayList<RankModel> createRanks() {
        ArrayList<RankModel> ranks = new ArrayList<RankModel>();
        ranks.add(new RankModel("Master", "master"));
        ranks.add(new RankModel("Student", "padawan"));
        ranks.add(new RankModel("Instructor", "teacher"));
        ranks.add(new RankModel("Assistant", "aid"));
        return ranks;
    }

    public static ArrayList<SelectOption> getOptions() {
        ArrayList<SelectOption> ranks = new ArrayList<SelectOption>();
        ranks.add(new SelectOption("Master", "master"));
        ranks.add(new SelectOption("Student", "padawan"));
        ranks.add(new SelectOption("Instructor", "teacher"));
        ranks.add(new SelectOption("Assistant", "aid"));
        return ranks;
    }

    public static Store getStore() {
        return new Store(createRanks());
    }

}
