/*******************************************************************************
 * AppPlaceHistoryMapper.java is part of the Kitchen Sink (for Touch4j 2.0)
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

import com.alkac.mobile.client.views.animations.AnimationViewPlace;
import com.alkac.mobile.client.views.animations.fade.FadeViewPlace;
import com.alkac.mobile.client.views.animations.flip.FlipViewPlace;
import com.alkac.mobile.client.views.animations.pop.PopViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideDownViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideLeftViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideRightViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideUpViewPlace;
import com.alkac.mobile.client.views.animations.slide.SlideViewPlace;
import com.alkac.mobile.client.views.charts.ChartsViewPlace;
import com.alkac.mobile.client.views.charts.area.AreaChartsViewPlace;
import com.alkac.mobile.client.views.charts.bar.BarChartsViewPlace;
import com.alkac.mobile.client.views.charts.column.ColumnChartsViewPlace;
import com.alkac.mobile.client.views.charts.gauge.GaugeChartsViewPlace;
import com.alkac.mobile.client.views.charts.line.LineChartsViewPlace;
import com.alkac.mobile.client.views.charts.pie.PieChartsViewPlace;
import com.alkac.mobile.client.views.charts.radar.RadarChartsViewPlace;
import com.alkac.mobile.client.views.charts.scatter.ScatterChartsViewPlace;
import com.alkac.mobile.client.views.data.DataViewPlace;
import com.alkac.mobile.client.views.data.gwtrpc.GwtRpcViewPlace;
import com.alkac.mobile.client.views.data.jsonp.JsonPViewPlace;
import com.alkac.mobile.client.views.device.DeviceViewPlace;
import com.alkac.mobile.client.views.device.accelerometer.AccelerometerViewPlace;
import com.alkac.mobile.client.views.device.contacts.ContactsViewPlace;
import com.alkac.mobile.client.views.device.general.GeneralViewPlace;
import com.alkac.mobile.client.views.device.geolocation.GeolocationViewPlace;
import com.alkac.mobile.client.views.device.network.NetworkViewPlace;
import com.alkac.mobile.client.views.media.MediaViewPlace;
import com.alkac.mobile.client.views.media.audio.AudioViewPlace;
import com.alkac.mobile.client.views.media.video.VideoViewPlace;
import com.alkac.mobile.client.views.navigation.NavigationViewPlace;
import com.alkac.mobile.client.views.themes.ThemeViewPlace;
import com.alkac.mobile.client.views.tutoni.TutoniViewPlace;
import com.alkac.mobile.client.views.tutoni.mapped.TutMapViewPlace;
import com.alkac.mobile.client.views.ui.UIViewPlace;
import com.alkac.mobile.client.views.ui.bottomtabs.BottomTabsViewPlace;
import com.alkac.mobile.client.views.ui.buttons.ButtonsViewPlace;
import com.alkac.mobile.client.views.ui.carousel.CarouselViewPlace;
import com.alkac.mobile.client.views.ui.form.FormsViewPlace;
import com.alkac.mobile.client.views.ui.grid.GridViewPlace;
import com.alkac.mobile.client.views.ui.icons.IconsViewPlace;
import com.alkac.mobile.client.views.ui.lists.ListsViewPlace;
import com.alkac.mobile.client.views.ui.maps.MapsViewPlace;
import com.alkac.mobile.client.views.ui.nestedlists.NestedListsViewPlace;
import com.alkac.mobile.client.views.ui.overlays.OverlaysViewPlace;
import com.alkac.mobile.client.views.ui.tabs.TabsViewPlace;
import com.alkac.mobile.client.views.ui.toolbars.ToolBarViewPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({
                // Main view
                NavigationViewPlace.Tokenizer.class,
                TutoniViewPlace.Tokenizer.class,
        TutMapViewPlace.Tokenizer.class,
                // User interface
                UIViewPlace.Tokenizer.class, 
                ListsViewPlace.Tokenizer.class, 
                FormsViewPlace.Tokenizer.class, 
                MapsViewPlace.Tokenizer.class,
                ButtonsViewPlace.Tokenizer.class, 
                ThemeViewPlace.Tokenizer.class, 
                BottomTabsViewPlace.Tokenizer.class,
                CarouselViewPlace.Tokenizer.class, 
                IconsViewPlace.Tokenizer.class,
                NestedListsViewPlace.Tokenizer.class, 
                OverlaysViewPlace.Tokenizer.class, 
                TabsViewPlace.Tokenizer.class,
                ToolBarViewPlace.Tokenizer.class,
                GridViewPlace.Tokenizer.class,
                
                // Media
                MediaViewPlace.Tokenizer.class, 
                AudioViewPlace.Tokenizer.class, 
                VideoViewPlace.Tokenizer.class,
                
                // Animation
                AnimationViewPlace.Tokenizer.class,
                SlideViewPlace.Tokenizer.class, 
                SlideLeftViewPlace.Tokenizer.class,
                SlideRightViewPlace.Tokenizer.class, 
                SlideUpViewPlace.Tokenizer.class,
                SlideDownViewPlace.Tokenizer.class,
                FadeViewPlace.Tokenizer.class,
                FlipViewPlace.Tokenizer.class,
                PopViewPlace.Tokenizer.class, 
                
                // Data
                DataViewPlace.Tokenizer.class, 
                GwtRpcViewPlace.Tokenizer.class, 
                JsonPViewPlace.Tokenizer.class,
                
                // Charts
                ChartsViewPlace.Tokenizer.class, 
                LineChartsViewPlace.Tokenizer.class,
                AreaChartsViewPlace.Tokenizer.class, 
                BarChartsViewPlace.Tokenizer.class,
                ColumnChartsViewPlace.Tokenizer.class, 
                GaugeChartsViewPlace.Tokenizer.class,
                PieChartsViewPlace.Tokenizer.class, 
                RadarChartsViewPlace.Tokenizer.class,
                ScatterChartsViewPlace.Tokenizer.class, 
                
                // Device places
                DeviceViewPlace.Tokenizer.class,
                AccelerometerViewPlace.Tokenizer.class,
                ContactsViewPlace.Tokenizer.class,
                NetworkViewPlace.Tokenizer.class, 
                GeneralViewPlace.Tokenizer.class,
                GeolocationViewPlace.Tokenizer.class
                })

public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
