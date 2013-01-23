/*******************************************************************************
 * AnimationViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.animations;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.handlers.dataview.DataViewItemTapHandler;
import com.emitrom.gwt4.touch.client.core.handlers.list.ItemDisclosureHandler;
import com.emitrom.gwt4.touch.client.data.BaseModel;
import com.emitrom.gwt4.touch.client.data.Store;
import com.emitrom.gwt4.touch.client.dataview.DataView;
import com.emitrom.gwt4.touch.client.dataview.DisclosureList;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.alkac.mobile.client.models.animation.AnimationViewModel;
import com.alkac.mobile.client.views.AppPlace;
import com.alkac.mobile.client.views.animations.fade.FadeViewPlace;
import com.alkac.mobile.client.views.animations.flip.FlipViewPlace;
import com.alkac.mobile.client.views.animations.pop.PopViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideViewPlace;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

public class AnimationViewImpl extends Panel implements AnimationView {

	private Presenter presenter;
	
	private AppPlace slideViewPlace = new SlideViewPlace();
	private AppPlace fadeViewPlace = new FadeViewPlace();
	private AppPlace popViewPlace = new PopViewPlace();
	private AppPlace flipViewPlace = new FlipViewPlace();

	public AnimationViewImpl() {
		setLayout(new FitLayout());
		initialize();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	private void initialize() {

		final Store store = new Store(getAnimationViewModels());
		store.setSorter(AnimationViewModel.KEY);
		DisclosureList list = new DisclosureList(AnimationViewModel.TEMPLATE,
				store);
		list.setDeselectOnContainerClick(false);
		list.addItemTapHandler(new DataViewItemTapHandler() {

            @Override
            public void onItemTap(DataView dataView, int index, Element element, BaseModel record, Object eventObject,
                            Object eOpts) {
                goToPlace(index);                
            }
		});
		list.setOnItemDisclosure(new ItemDisclosureHandler() {

            @Override
            public void onItemDisclosure(BaseModel record, JavaScriptObject node, int index) {
                goToPlace(index);                
            }
		});

		add(list);

	}

	private List<AnimationViewModel> getAnimationViewModels() {

		String[] uiItems = { slideViewPlace.getToken(), 
		                     fadeViewPlace.getToken(),
		                     popViewPlace.getToken(),
		                     flipViewPlace.getToken()};

		List<AnimationViewModel> models = new ArrayList<AnimationViewModel>();

		for (String animationItem : uiItems) {
			models.add(new AnimationViewModel(animationItem));
		}

		return models;

	}

	private void goToPlace(int index) {
		switch (index) {
		case 0:
			presenter.goTo(slideViewPlace);
			break;
		case 1:
            presenter.goTo(fadeViewPlace);
            break;
		case 2:
		    presenter.goTo(popViewPlace);
			break;
		case 3:
		    presenter.goTo(flipViewPlace);
			break;

		default:
			break;
		}
	}

}
