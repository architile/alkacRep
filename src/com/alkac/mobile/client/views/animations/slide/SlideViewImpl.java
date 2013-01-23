/*******************************************************************************
 * SlideViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.animations.slide;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.handlers.dataview.DataViewItemTapHandler;
import com.emitrom.gwt4.touch.client.core.handlers.list.ItemDisclosureHandler;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.dataview.DataView;
import com.emitrom.gwt4.touch.client.dataview.DisclosureList;
import com.emitrom.gwt4.touch.client.layout.CardLayout;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.alkac.mobile.client.models.slide.SlideViewModel;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class SlideViewImpl extends Panel implements SlideView {

	private Presenter presenter;

	public SlideViewImpl() {
		setLayout(new CardLayout());
		initialize();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	private void initialize() {

		final Store store = new Store(getSlideViewModels());
		store.setSorter(SlideViewModel.KEY);
		DisclosureList list = new DisclosureList(SlideViewModel.TEMPLATE, store);
		list.setDeselectOnContainerClick(false);
		list.addItemTapHandler(new DataViewItemTapHandler() {
            @Override
            public void onItemTap(DataView dataView, int index, Element element, BaseModel record, Object eventObject,
                            Object eOpts) {
                activate(++index);                
            }
		});
		list.setOnItemDisclosure(new ItemDisclosureHandler() {

            @Override
            public void onItemDisclosure(BaseModel record, JavaScriptObject node, int index) {
                activate(++index);                
            }
		});

		add(list);

		Panel slidePanel = new Panel();
		slidePanel.setLayout(new FitLayout());
		slidePanel.setCls("containers light_gray");
		slidePanel.setHtml("Slide Left Animation");
		add(slidePanel);

		slidePanel = new Panel();
		slidePanel.setLayout(new FitLayout());
		slidePanel.setCls("containers blue");
		slidePanel.setHtml("Slide Right Animation");
		add(slidePanel);

		slidePanel = new Panel();
		slidePanel.setLayout(new FitLayout());
		slidePanel.setCls("containers green");
		slidePanel.setHtml("Slide Up Animation");
		add(slidePanel);

		slidePanel = new Panel();
		slidePanel.setLayout(new FitLayout());
		slidePanel.setCls("containers orange");
		slidePanel.setHtml("Slide Down Animation");
		add(slidePanel);

	}

	private List<SlideViewModel> getSlideViewModels() {

		String[] slideItems = { "Slide Left", "Slide Right", "Slide Up",
				"Slide Down" };

		List<SlideViewModel> models = new ArrayList<SlideViewModel>();

		for (String slideItem : slideItems) {
			models.add(new SlideViewModel(slideItem));
		}

		return models;

	}

	private void activate(int index) {

		switch (index) {

			case 1:
				presenter.goTo(new SlideLeftViewPlace());
				break;
			case 2:
				presenter.goTo(new SlideRightViewPlace());
				break;
			case 3:
				presenter.goTo(new SlideUpViewPlace());
				break;
			case 4:
				presenter.goTo(new SlideDownViewPlace());
				break;
	
			default:
				break;

		}

	}

}
