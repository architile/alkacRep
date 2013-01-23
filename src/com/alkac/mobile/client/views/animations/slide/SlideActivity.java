/*******************************************************************************
 * SlideActivity.java is part of the Kitchen Sink (for Touch4j 2.0)
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

import com.emitrom.gwt4.touch.client.fx.layout.card.Direction;
import com.emitrom.gwt4.touch.client.fx.layout.card.Slide;
import com.alkac.mobile.client.activity.KitchenSinkActivity;
import com.alkac.mobile.client.core.ClientFactory;
import com.alkac.mobile.client.core.Util;
import com.alkac.mobile.client.core.events.SourceUpdateEvent;
import com.alkac.mobile.client.views.animations.slide.SlideView.Presenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class SlideActivity extends KitchenSinkActivity implements Presenter {

    SlidePlace place;

    public SlideActivity(SlidePlace place, ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        this.place = place;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {

        final SlideViewImpl view = Util.getSlideView();
        view.setPresenter(this);
        panel.setWidget(view);

        if (place instanceof SlideViewPlace)
            view.setActiveItem(0);
        if (place instanceof SlideLeftViewPlace)
            view.animateActiveItem(1, new Slide(Direction.LEFT));
        if (place instanceof SlideRightViewPlace)
            view.animateActiveItem(2, new Slide(Direction.RIGHT));
        if (place instanceof SlideUpViewPlace)
            view.animateActiveItem(3, new Slide(Direction.UP));
        if (place instanceof SlideDownViewPlace)
            view.animateActiveItem(4, new Slide(Direction.DOWN));

        eventBus.fireEvent(new SourceUpdateEvent(view.getClass().getName()));

    }

}