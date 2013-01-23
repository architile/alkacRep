/*******************************************************************************
 * AppActivityMapper.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.core;

import com.alkac.mobile.client.KitchenSinkEntryPoint;
import com.alkac.mobile.client.views.AppPlace;
import com.alkac.mobile.client.views.animations.AnimationActivity;
import com.alkac.mobile.client.views.animations.AnimationViewPlace;
import com.alkac.mobile.client.views.animations.fade.FadeActivity;
import com.alkac.mobile.client.views.animations.fade.FadeViewPlace;
import com.alkac.mobile.client.views.animations.flip.FlipActivity;
import com.alkac.mobile.client.views.animations.flip.FlipViewPlace;
import com.alkac.mobile.client.views.animations.pop.PopActivity;
import com.alkac.mobile.client.views.animations.pop.PopViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideActivity;
import com.alkac.mobile.client.views.animations.slide.SlideDownViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideLeftViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideRightViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideUpViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideViewPlace;
import com.alkac.mobile.client.views.charts.ChartsActivity;
import com.alkac.mobile.client.views.charts.ChartsViewPlace;
import com.alkac.mobile.client.views.charts.area.AreaChartsActivity;
import com.alkac.mobile.client.views.charts.area.AreaChartsViewPlace;
import com.alkac.mobile.client.views.charts.bar.BarChartsActivity;
import com.alkac.mobile.client.views.charts.bar.BarChartsViewPlace;
import com.alkac.mobile.client.views.charts.column.ColumnChartsActivity;
import com.alkac.mobile.client.views.charts.column.ColumnChartsViewPlace;
import com.alkac.mobile.client.views.charts.gauge.GaugeChartsActivity;
import com.alkac.mobile.client.views.charts.gauge.GaugeChartsViewPlace;
import com.alkac.mobile.client.views.charts.line.LineChartsActivity;
import com.alkac.mobile.client.views.charts.line.LineChartsViewPlace;
import com.alkac.mobile.client.views.charts.pie.PieChartsActivity;
import com.alkac.mobile.client.views.charts.pie.PieChartsViewPlace;
import com.alkac.mobile.client.views.charts.radar.RadarChartsActivity;
import com.alkac.mobile.client.views.charts.radar.RadarChartsViewPlace;
import com.alkac.mobile.client.views.charts.scatter.ScatterChartsActivity;
import com.alkac.mobile.client.views.charts.scatter.ScatterChartsViewPlace;
import com.alkac.mobile.client.views.data.DataActivity;
import com.alkac.mobile.client.views.data.DataViewPlace;
import com.alkac.mobile.client.views.data.gwtrpc.GwtRpcActivity;
import com.alkac.mobile.client.views.data.gwtrpc.GwtRpcViewPlace;
import com.alkac.mobile.client.views.data.jsonp.JsonPActivity;
import com.alkac.mobile.client.views.data.jsonp.JsonPViewPlace;
import com.alkac.mobile.client.views.device.DeviceActivity;
import com.alkac.mobile.client.views.device.DeviceViewPlace;
import com.alkac.mobile.client.views.device.accelerometer.AccelerometerActivity;
import com.alkac.mobile.client.views.device.accelerometer.AccelerometerViewPlace;
import com.alkac.mobile.client.views.device.contacts.ContactsActivity;
import com.alkac.mobile.client.views.device.contacts.ContactsViewPlace;
import com.alkac.mobile.client.views.device.general.GeneralActivity;
import com.alkac.mobile.client.views.device.general.GeneralViewPlace;
import com.alkac.mobile.client.views.device.geolocation.GeolocationActivity;
import com.alkac.mobile.client.views.device.geolocation.GeolocationViewPlace;
import com.alkac.mobile.client.views.device.network.NetworkActivity;
import com.alkac.mobile.client.views.device.network.NetworkViewPlace;
import com.alkac.mobile.client.views.media.MediaActivity;
import com.alkac.mobile.client.views.media.MediaViewPlace;
import com.alkac.mobile.client.views.media.audio.AudioActivity;
import com.alkac.mobile.client.views.media.audio.AudioViewPlace;
import com.alkac.mobile.client.views.media.video.VideoActivity;
import com.alkac.mobile.client.views.media.video.VideoViewPlace;
import com.alkac.mobile.client.views.navigation.NavigationActivity;
import com.alkac.mobile.client.views.navigation.NavigationViewPlace;
import com.alkac.mobile.client.views.themes.ThemeActivity;
import com.alkac.mobile.client.views.themes.ThemeViewPlace;
import com.alkac.mobile.client.views.tutoni.TutoniActivity;
import com.alkac.mobile.client.views.tutoni.TutoniViewPlace;
import com.alkac.mobile.client.views.tutoni.mapped.TutMapActivity;
import com.alkac.mobile.client.views.tutoni.mapped.TutMapViewPlace;
import com.alkac.mobile.client.views.ui.UIActivity;
import com.alkac.mobile.client.views.ui.UIViewPlace;
import com.alkac.mobile.client.views.ui.bottomtabs.BottomTabsActivity;
import com.alkac.mobile.client.views.ui.bottomtabs.BottomTabsViewPlace;
import com.alkac.mobile.client.views.ui.buttons.ButtonsActivity;
import com.alkac.mobile.client.views.ui.buttons.ButtonsViewPlace;
import com.alkac.mobile.client.views.ui.carousel.CarouselActivity;
import com.alkac.mobile.client.views.ui.carousel.CarouselViewPlace;
import com.alkac.mobile.client.views.ui.form.FormsActivity;
import com.alkac.mobile.client.views.ui.form.FormsViewPlace;
import com.alkac.mobile.client.views.ui.grid.GridActivity;
import com.alkac.mobile.client.views.ui.grid.GridViewPlace;
import com.alkac.mobile.client.views.ui.icons.IconsActivity;
import com.alkac.mobile.client.views.ui.icons.IconsViewPlace;
import com.alkac.mobile.client.views.ui.lists.ListsActivity;
import com.alkac.mobile.client.views.ui.lists.ListsViewPlace;
import com.alkac.mobile.client.views.ui.maps.MapsActivity;
import com.alkac.mobile.client.views.ui.maps.MapsViewPlace;
import com.alkac.mobile.client.views.ui.nestedlists.NestedListsActivity;
import com.alkac.mobile.client.views.ui.nestedlists.NestedListsViewPlace;
import com.alkac.mobile.client.views.ui.overlays.OverlaysActivity;
import com.alkac.mobile.client.views.ui.overlays.OverlaysViewPlace;
import com.alkac.mobile.client.views.ui.tabs.TabsActivity;
import com.alkac.mobile.client.views.ui.tabs.TabsViewPlace;
import com.alkac.mobile.client.views.ui.toolbars.ToolBarActivity;
import com.alkac.mobile.client.views.ui.toolbars.ToolBarViewPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

    private ClientFactory clientFactory;

    public AppActivityMapper(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
    }

    @Override
    public Activity getActivity(Place place) {

        KitchenSinkEntryPoint.getToolBar().setTitle(((AppPlace) place).getToken());
        
        if (place instanceof NavigationViewPlace)
            return new NavigationActivity((NavigationViewPlace) place, clientFactory);
        if (place instanceof TutoniViewPlace)
            return new TutoniActivity((TutoniViewPlace) place, clientFactory);
        if (place instanceof TutMapViewPlace)
            return new TutMapActivity((TutMapViewPlace) place, clientFactory);
        if (place instanceof UIViewPlace)
            return new UIActivity((UIViewPlace) place, clientFactory);
        if (place instanceof AnimationViewPlace)
            return new AnimationActivity((AnimationViewPlace) place, clientFactory);
        if (place instanceof DataViewPlace)
            return new DataActivity((DataViewPlace) place, clientFactory);
        if (place instanceof GwtRpcViewPlace)
            return new GwtRpcActivity((GwtRpcViewPlace) place, clientFactory);
        if (place instanceof JsonPViewPlace)
            return new JsonPActivity((JsonPViewPlace) place, clientFactory);
        if (place instanceof ListsViewPlace)
            return new ListsActivity((ListsViewPlace) place, clientFactory);
        if (place instanceof FormsViewPlace)
            return new FormsActivity((FormsViewPlace) place, clientFactory);
        if (place instanceof MapsViewPlace)
            return new MapsActivity((MapsViewPlace) place, clientFactory);
        if (place instanceof ButtonsViewPlace)
            return new ButtonsActivity((ButtonsViewPlace) place, clientFactory);
        if (place instanceof ThemeViewPlace)
            return new ThemeActivity((ThemeViewPlace) place, clientFactory);
        if (place instanceof BottomTabsViewPlace)
            return new BottomTabsActivity((BottomTabsViewPlace) place, clientFactory);
        if (place instanceof CarouselViewPlace)
            return new CarouselActivity((CarouselViewPlace) place, clientFactory);
        if (place instanceof IconsViewPlace)
            return new IconsActivity((IconsViewPlace) place, clientFactory);
        if (place instanceof NestedListsViewPlace)
            return new NestedListsActivity((NestedListsViewPlace) place, clientFactory);
        if (place instanceof OverlaysViewPlace)
            return new OverlaysActivity((OverlaysViewPlace) place, clientFactory);
        if (place instanceof TabsViewPlace)
            return new TabsActivity((TabsViewPlace) place, clientFactory);
        if (place instanceof ToolBarViewPlace)
            return new ToolBarActivity((ToolBarViewPlace) place, clientFactory);
        if (place instanceof MediaViewPlace)
            return new MediaActivity((MediaViewPlace) place, clientFactory);
        if (place instanceof VideoViewPlace)
            return new VideoActivity((VideoViewPlace) place, clientFactory);
        if (place instanceof AudioViewPlace)
            return new AudioActivity((AudioViewPlace) place, clientFactory);
        if (place instanceof SlideViewPlace)
            return new SlideActivity((SlideViewPlace) place, clientFactory);
        if (place instanceof SlideLeftViewPlace)
            return new SlideActivity((SlideLeftViewPlace) place, clientFactory);
        if (place instanceof SlideRightViewPlace)
            return new SlideActivity((SlideRightViewPlace) place, clientFactory);
        if (place instanceof SlideUpViewPlace)
            return new SlideActivity((SlideUpViewPlace) place, clientFactory);
        if (place instanceof SlideDownViewPlace)
            return new SlideActivity((SlideDownViewPlace) place, clientFactory);
        if (place instanceof GridViewPlace)
            return new GridActivity((GridViewPlace) place, clientFactory);
        if (place instanceof FadeViewPlace)
            return new FadeActivity((FadeViewPlace) place, clientFactory);
        if (place instanceof FlipViewPlace)
            return new FlipActivity((FlipViewPlace) place, clientFactory);
        if (place instanceof PopViewPlace)
            return new PopActivity((PopViewPlace) place, clientFactory);
        if (place instanceof DeviceViewPlace)
            return new DeviceActivity((DeviceViewPlace) place, clientFactory);
        if (place instanceof ChartsViewPlace)
            return new ChartsActivity((ChartsViewPlace) place, clientFactory);
        if (place instanceof AreaChartsViewPlace)
            return new AreaChartsActivity((AreaChartsViewPlace) place, clientFactory);
        if (place instanceof BarChartsViewPlace)
            return new BarChartsActivity((BarChartsViewPlace) place, clientFactory);
        if (place instanceof ColumnChartsViewPlace)
            return new ColumnChartsActivity((ColumnChartsViewPlace) place, clientFactory);
        if (place instanceof GaugeChartsViewPlace)
            return new GaugeChartsActivity((GaugeChartsViewPlace) place, clientFactory);
        if (place instanceof LineChartsViewPlace)
            return new LineChartsActivity((LineChartsViewPlace) place, clientFactory);
        if (place instanceof PieChartsViewPlace)
            return new PieChartsActivity((PieChartsViewPlace) place, clientFactory);
        if (place instanceof RadarChartsViewPlace)
            return new RadarChartsActivity((RadarChartsViewPlace) place, clientFactory);
        if (place instanceof ScatterChartsViewPlace)
            return new ScatterChartsActivity((ScatterChartsViewPlace) place, clientFactory);
        if (place instanceof NetworkViewPlace)
            return new NetworkActivity((NetworkViewPlace) place, clientFactory);
        if (place instanceof GeneralViewPlace)
            return new GeneralActivity((GeneralViewPlace) place, clientFactory);
        if (place instanceof AccelerometerViewPlace)
            return new AccelerometerActivity((AccelerometerViewPlace) place, clientFactory);
        if (place instanceof GeolocationViewPlace)
            return new GeolocationActivity((GeolocationViewPlace) place, clientFactory);
        if (place instanceof ContactsViewPlace)
            return new ContactsActivity((ContactsViewPlace) place, clientFactory);
        
            return null;

    }

}
