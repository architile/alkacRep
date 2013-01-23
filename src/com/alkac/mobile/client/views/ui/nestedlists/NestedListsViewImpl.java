/*******************************************************************************
 * NestedListsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.nestedlists;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.Container;
import com.emitrom.gwt4.touch.client.core.handlers.component.RenderChangeHandler;
import com.emitrom.gwt4.touch.client.data.TreeModel;
import com.emitrom.gwt4.touch.client.data.TreeStore;
import com.emitrom.gwt4.touch.client.dataview.NestedListDataView;
import com.emitrom.gwt4.touch.client.layout.FitLayout;

public class NestedListsViewImpl extends Panel implements NestedListsView {

    @SuppressWarnings("unused")
	private Presenter presenter;
    private NestedListDataView nestedList;
    
    public NestedListsViewImpl() {
        setLayout(new FitLayout());
        initialize();
        addListeners();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    private void initialize() {
        
        NestedTreeModel parent = new NestedTreeModel("Groceries");
        List<NestedTreeModel> parentModels = new ArrayList<NestedTreeModel>();

        /**
         * Drinks
         */
        NestedTreeModel drinkParentModel = new NestedTreeModel("Drinks");
        List<NestedTreeModel> drinkModels = new ArrayList<NestedTreeModel>();
        
        NestedTreeModel drinkModel = new NestedTreeModel("Water");
        
        List<NestedTreeModel> waterModels = new ArrayList<NestedTreeModel>();
        
        NestedTreeModel sparklingModel = new NestedTreeModel("Sparkling");
        sparklingModel.setLeaf(true);
        waterModels.add(sparklingModel);
        
        NestedTreeModel stillModel = new NestedTreeModel("Still");
        stillModel.setLeaf(true);
        waterModels.add(stillModel);
        
        drinkModel.setItems(waterModels);
        
        drinkModels.add(drinkModel);
        
        drinkModel = new NestedTreeModel("Coffee");
        drinkModel.setLeaf(true);
        drinkModels.add(drinkModel);
        
        drinkModel = new NestedTreeModel("Espresso");
        drinkModel.setLeaf(true);
        drinkModels.add(drinkModel);
        
        drinkModel = new NestedTreeModel("Redbull");
        drinkModel.setLeaf(true);
        drinkModels.add(drinkModel);
        
        drinkModel = new NestedTreeModel("Coke");
        drinkModel.setLeaf(true);
        drinkModels.add(drinkModel);
        
        drinkModel = new NestedTreeModel("Diet Coke");
        drinkModel.setLeaf(true);
        drinkModels.add(drinkModel);
        
        drinkParentModel.setItems(drinkModels);
        
        parentModels.add(drinkParentModel);
        
        /**
         * Fruits
         */
        NestedTreeModel fruitParentModel = new NestedTreeModel("Fruit");
        List<NestedTreeModel> fruitModels = new ArrayList<NestedTreeModel>();
        
        NestedTreeModel fruitModel = new NestedTreeModel("Banana");
        fruitModel.setLeaf(true);
        fruitModels.add(fruitModel);
        
        fruitModel = new NestedTreeModel("Lemon");
        fruitModel.setLeaf(true);
        fruitModels.add(fruitModel);
        
        fruitParentModel.setItems(fruitModels);
        
        parentModels.add(fruitParentModel);
        
        /**
         * Snacks
         */
        NestedTreeModel snackParentModel = new NestedTreeModel("Snacks");
        List<NestedTreeModel> snackModels = new ArrayList<NestedTreeModel>();
        
        NestedTreeModel snackModel = new NestedTreeModel("Nuts");
        snackModel.setLeaf(true);
        snackModels.add(snackModel);
        
        snackModel = new NestedTreeModel("Pretzels");
        snackModel.setLeaf(true);
        snackModels.add(snackModel);
        
        snackModel = new NestedTreeModel("Wasabi Peas");
        snackModel.setLeaf(true);
        snackModels.add(snackModel);

        snackParentModel.setItems(snackModels);
        
        parentModels.add(snackParentModel);

        parent.setItems(parentModels);
        
        nestedList = new NestedListDataView(new TreeStore(parent));
        nestedList.setDisplayField("text");
        nestedList.setFullScreen(true);
        nestedList.setTitle("Groceries");
        
    }
    
    public static class NestedTreeModel extends TreeModel {
        
        public NestedTreeModel() {}
        
        public NestedTreeModel(String text) {
            super(text);
        }
        
    }
    
    private void addListeners() {
        
        addRenderChangeHandler(new RenderChangeHandler() {
            
            @Override
            public void onRenderChange(Container container, Object item, boolean rendered, Object eOpts) {
                add(nestedList);
            }
        });
    }
    
}