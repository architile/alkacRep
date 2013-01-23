/*******************************************************************************
 * CarouselViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.carousel;

import com.emitrom.gwt4.touch.client.containers.Carousel;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.laf.Direction;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.Align;
import com.emitrom.gwt4.touch.client.layout.VBoxLayout;

public class CarouselViewImpl extends Panel implements CarouselView {

    @SuppressWarnings("unused")
    private Presenter presenter;

    public CarouselViewImpl() {
        VBoxLayout vl = new VBoxLayout();
        vl.setAlign(Align.STRETCH);
        setLayout(vl);
        initialize();

    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        Carousel carousel = new Carousel();
        carousel.setFlex(1);

        Panel panel = new Panel();
        panel.setHtml("<p>Navigate the carousel on this page by swiping left/right.</p>");
        panel.setCls("containers gray_light");
        carousel.add(panel);

        panel = new Panel();
        panel.setHtml("<p>Clicking on either side of the indicators below</p>");
        panel.setCls("containers blue");
        carousel.add(panel);

        panel = new Panel();
        panel.setHtml("Card #3");
        panel.setCls("containers green");
        carousel.add(panel);

        add(carousel);
        
        carousel = new Carousel();
        carousel.setFlex(1);
        carousel.setUi(UI.LIGHT);
        carousel.setDirection(Direction.VERTICAL);

        panel = new Panel();
        panel.setHtml("<p>Carousels can be vertical and given a UI of Light or Dark.</p>");
        panel.setCls("containers orange");
        carousel.add(panel);

        panel = new Panel();
        panel.setHtml("<p>Clicking on either side of the indicators below</p>");
        panel.setCls("containers purple");
        carousel.add(panel);

        panel = new Panel();
        panel.setHtml("Card #3");
        panel.setCls("containers gray");
        carousel.add(panel);

        add(carousel);


    }
    
}
