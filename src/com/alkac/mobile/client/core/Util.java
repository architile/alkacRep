/*******************************************************************************
 * Util.java is part of the Kitchen Sink (for Touch4j 2.0)
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

import com.emitrom.gwt4.touch.device.client.core.Device;
import com.alkac.mobile.client.KitchenSinkService;
import com.alkac.mobile.client.KitchenSinkServiceAsync;
import com.alkac.mobile.client.views.animations.AnimationViewImpl;
import com.alkac.mobile.client.views.animations.fade.FadeViewImpl;
import com.alkac.mobile.client.views.animations.flip.FlipViewImpl;
import com.alkac.mobile.client.views.animations.pop.PopViewImpl;
import com.alkac.mobile.client.views.animations.slide.SlideViewImpl;
import com.alkac.mobile.client.views.charts.ChartsViewImpl;
import com.alkac.mobile.client.views.charts.area.AreaChartsViewImpl;
import com.alkac.mobile.client.views.charts.bar.BarChartsViewImpl;
import com.alkac.mobile.client.views.charts.column.ColumnChartsViewImpl;
import com.alkac.mobile.client.views.charts.gauge.GaugeChartsViewImpl;
import com.alkac.mobile.client.views.charts.line.LineChartsViewImpl;
import com.alkac.mobile.client.views.charts.pie.PieChartsViewImpl;
import com.alkac.mobile.client.views.charts.radar.RadarChartsViewImpl;
import com.alkac.mobile.client.views.charts.scatter.ScatterChartsViewImpl;
import com.alkac.mobile.client.views.data.DataViewImpl;
import com.alkac.mobile.client.views.data.gwtrpc.GwtRpcViewImpl;
import com.alkac.mobile.client.views.data.jsonp.JsonPViewImpl;
import com.alkac.mobile.client.views.device.DeviceViewImpl;
import com.alkac.mobile.client.views.device.accelerometer.AccelerometerViewImpl;
import com.alkac.mobile.client.views.device.contacts.ContactsViewImpl;
import com.alkac.mobile.client.views.device.general.GeneralViewImpl;
import com.alkac.mobile.client.views.device.geolocation.GeolocationViewImpl;
import com.alkac.mobile.client.views.device.network.NetworkViewImpl;
import com.alkac.mobile.client.views.media.MediaViewImpl;
import com.alkac.mobile.client.views.media.audio.AudioViewImpl;
import com.alkac.mobile.client.views.media.video.VideoViewImpl;
import com.alkac.mobile.client.views.navigation.NavigationViewImpl;
import com.alkac.mobile.client.views.themes.ThemeViewImpl;
import com.alkac.mobile.client.views.ui.UIViewImpl;
import com.alkac.mobile.client.views.ui.bottomtabs.BottomTabsViewImpl;
import com.alkac.mobile.client.views.ui.buttons.ButtonsViewImpl;
import com.alkac.mobile.client.views.ui.carousel.CarouselViewImpl;
import com.alkac.mobile.client.views.ui.form.FormsViewImpl;
import com.alkac.mobile.client.views.ui.grid.GridViewImpl;
import com.alkac.mobile.client.views.ui.icons.IconsViewImpl;
import com.alkac.mobile.client.views.ui.lists.ListsViewImpl;
import com.alkac.mobile.client.views.ui.maps.MapsViewImpl;
import com.alkac.mobile.client.views.ui.nestedlists.NestedListsViewImpl;
import com.alkac.mobile.client.views.ui.overlays.OverlaysViewImpl;
import com.alkac.mobile.client.views.ui.tabs.TabsViewImpl;
import com.alkac.mobile.client.views.ui.toolbars.ToolBarViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class Util {

    private static ClientFactory clientFactory = GWT.create(ClientFactory.class);
    private static AnimationViewImpl animationView;
    private static FadeViewImpl fadeView;
    private static FlipViewImpl flipView;
    private static PopViewImpl popView;
    private static SlideViewImpl slideView;
    private static AudioViewImpl audioView;
    private static VideoViewImpl videoView;
    private static MediaViewImpl mediaView;
    private static NavigationViewImpl navView;
    private static ThemeViewImpl themeView;
    private static UIViewImpl uiView;
    private static BottomTabsViewImpl bottomTabView;
    private static ButtonsViewImpl buttonsView;
    private static CarouselViewImpl carouselView;
    private static FormsViewImpl formView;
    private static GridViewImpl gridView;
    private static IconsViewImpl iconsView;
    private static ListsViewImpl listView;
    private static MapsViewImpl mapsView;
    private static NestedListsViewImpl nestedListView;
    private static OverlaysViewImpl overlayView;
    private static TabsViewImpl tabsView;
    private static ToolBarViewImpl toolbarView;
    private static DeviceViewImpl deviceView;
    private static DataViewImpl dataView;
    private static GwtRpcViewImpl gwtrpcView;
    private static JsonPViewImpl jsonpView;
    private static ChartsViewImpl chartsView;
    private static LineChartsViewImpl lineChartView;
    private static AreaChartsViewImpl areaChartView;
    private static BarChartsViewImpl barChartView;
    private static ColumnChartsViewImpl columnChartView;
    private static GaugeChartsViewImpl gaugeChartView;
    private static PieChartsViewImpl pieChartView;
    private static RadarChartsViewImpl radarChartView;
    private static ScatterChartsViewImpl scatterChartView;
    private static NetworkViewImpl networkView;
    private static GeneralViewImpl generalView;
    private static AccelerometerViewImpl accelerometerView;
    private static GeolocationViewImpl geolocationView;
    private static ContactsViewImpl contactsView;

    private Util() {
    }

    /**
     * @return the clientFactory
     */
    public static ClientFactory getClientFactory() {
        return clientFactory;
    }
    
    public static KitchenSinkServiceAsync getService() {
        KitchenSinkServiceAsync service = GWT.create(KitchenSinkService.class);
        
        // We must add this check so that the source is viewable from the device
        if (Device.isAvailable()) {
            ServiceDefTarget rpcTarget = (ServiceDefTarget) service;
            rpcTarget.setServiceEntryPoint("http://go-emitrom.rhcloud.com/touch4j/gwt4touch2_kitchensink/rpc");
        }
        
        return service;
    }

    public static AnimationViewImpl getAnimationView() {
        if (animationView == null) {
            animationView = new AnimationViewImpl();
        }
        return animationView;
    }
    
    public static FadeViewImpl getFadeView() {
        if (fadeView == null) {
            fadeView = new FadeViewImpl();
        }
        return fadeView;
    }
    
    public static FlipViewImpl getFlipView() {
        if (flipView == null) {
            flipView = new FlipViewImpl();
        }
        return flipView;
    }
    
    public static PopViewImpl getPopView() {
        if (popView == null) {
            popView = new PopViewImpl();
        }
        return popView;
    }

    public static SlideViewImpl getSlideView() {
        if (slideView == null) {
            slideView = new SlideViewImpl();
        }
        return slideView;
    }
    
    public static AudioViewImpl getAudioView() {
        if (audioView == null) {
            audioView = new AudioViewImpl();
        }
        return audioView;
    }
    
    public static VideoViewImpl getVideoView() {
        if (videoView == null) {
            videoView = new VideoViewImpl();
        }
        return videoView;
    }
    
    public static MediaViewImpl getMediaView() {
        if (mediaView == null) {
            mediaView = new MediaViewImpl();
        }
        return mediaView;
    }

    public static NavigationViewImpl getNavView() {
        if (navView == null) {
            navView = new NavigationViewImpl();
        }
        return navView;
    }
    
    public static ThemeViewImpl getThemeView() {
        if (themeView == null) {
            themeView = new ThemeViewImpl();
        }
        return themeView;
    }
    
    public static UIViewImpl getUIView() {
        if (uiView == null) {
            uiView = new UIViewImpl();
        }
        return uiView;
    }
    
    public static ButtonsViewImpl getButtonsView() {
        if (buttonsView == null) {
            buttonsView = new ButtonsViewImpl();
        }
        return buttonsView;
    }

    public static FormsViewImpl getFormsView() {
        if (formView == null) {
            formView = new FormsViewImpl();
        }
        return formView;
    }

    /**
     * @return the uiView
     */
    public static UIViewImpl getUiView() {
        if (uiView == null) {
            uiView = new UIViewImpl();
        }
        return uiView;
    }

    /**
     * @return the bottomTabView
     */
    public static BottomTabsViewImpl getBottomTabView() {
        if (bottomTabView == null) {
            bottomTabView = new BottomTabsViewImpl();
        }
        return bottomTabView;
    }

    /**
     * @return the carouselView
     */
    public static CarouselViewImpl getCarouselView() {
        if (carouselView == null) {
            carouselView = new CarouselViewImpl();
        }
        return carouselView;
    }

    /**
     * @return the formView
     */
    public static FormsViewImpl getFormView() {
        if (formView == null) {
            formView = new FormsViewImpl();
        }
        return formView;
    }

    /**
     * @return the gridView
     */
    public static GridViewImpl getGridView() {
        if (gridView == null) {
            gridView = new GridViewImpl();
        }
        return gridView;
    }

    /**
     * @return the iconsView
     */
    public static IconsViewImpl getIconsView() {
        if (iconsView == null) {
            iconsView = new IconsViewImpl();
        }
        return iconsView;
    }

    /**
     * @return the listView
     */
    public static ListsViewImpl getListView() {
        if (listView == null) {
            listView = new ListsViewImpl();
        }
        return listView;
    }

    /**
     * @return the mapsView
     */
    public static MapsViewImpl getMapsView() {
        if (mapsView == null) {
            mapsView = new MapsViewImpl();
        }
        return mapsView;
    }

    /**
     * @return the nestedListView
     */
    public static NestedListsViewImpl getNestedListView() {
        if (nestedListView == null) {
            nestedListView = new NestedListsViewImpl();
        }
        return nestedListView;
    }

    /**
     * @return the overlayView
     */
    public static OverlaysViewImpl getOverlayView() {
        if (overlayView == null) {
            overlayView = new OverlaysViewImpl();
        }
        return overlayView;
    }

    /**
     * @return the tabsView
     */
    public static TabsViewImpl getTabsView() {
        if (tabsView == null) {
            tabsView = new TabsViewImpl();
        }
        return tabsView;
    }

    /**
     * @return the toolbarView
     */
    public static ToolBarViewImpl getToolbarView() {
        if (toolbarView == null) {
            toolbarView = new ToolBarViewImpl();
        }
        return toolbarView;
    }
    
    /**
     * @return the deviceView
     */
    public static DeviceViewImpl getDeviceView() {
        if (deviceView == null) {
            deviceView = new DeviceViewImpl();
        }
        return deviceView;
    }
    
    public static DataViewImpl getDataView() {
        if (dataView == null) {
            dataView = new DataViewImpl();
        }
        return dataView;
    }
    
    public static GwtRpcViewImpl getGwtRpcView() {
        if (gwtrpcView == null) {
            gwtrpcView = new GwtRpcViewImpl();
        }
        return gwtrpcView;
    }
    
    public static JsonPViewImpl getJsonPView() {
        if (jsonpView == null) {
            jsonpView = new JsonPViewImpl();
        }
        return jsonpView;
    }
    
    public static ChartsViewImpl getChartsView() {
        if (chartsView == null) {
            chartsView = new ChartsViewImpl();
        }
        return chartsView;
    }
    
    public static LineChartsViewImpl getLineChartsView() {
        if (lineChartView == null) {
            lineChartView = new LineChartsViewImpl();
        }
        return lineChartView;
    }

    public static AreaChartsViewImpl getAreaChartsView() {
        if (areaChartView == null) {
            areaChartView = new AreaChartsViewImpl();
        }
        return areaChartView;
    }

    public static BarChartsViewImpl getBarChartsView() {
        if (barChartView == null) {
            barChartView = new BarChartsViewImpl();
        }
        return barChartView;
    }
    
    public static ColumnChartsViewImpl getColumnChartsView() {
        if (columnChartView == null) {
            columnChartView = new ColumnChartsViewImpl();
        }
        return columnChartView;
    }
    
    public static GaugeChartsViewImpl getGaugeChartsView() {
        if (gaugeChartView == null) {
            gaugeChartView = new GaugeChartsViewImpl();
        }
        return gaugeChartView;
    }
    
    public static PieChartsViewImpl getPieChartsView() {
        if (pieChartView == null) {
            pieChartView = new PieChartsViewImpl();
        }
        return pieChartView;
    }
    
    public static RadarChartsViewImpl getRadarChartsView() {
        if (radarChartView == null) {
            radarChartView = new RadarChartsViewImpl();
        }
        return radarChartView;
    }
    
    public static ScatterChartsViewImpl getScatterChartsView() {
        if (scatterChartView == null) {
            scatterChartView = new ScatterChartsViewImpl();
        }
        return scatterChartView;
    }
    
    public static NetworkViewImpl getNetworkView() {
        if (networkView == null) {
            networkView = new NetworkViewImpl();
        }
        return networkView; 
    }
    
    public static GeneralViewImpl getGeneralView() {
        if (generalView == null) {
            generalView = new GeneralViewImpl();
        }
        return generalView; 
    }
    
    public static AccelerometerViewImpl getAccelerometerView() {
        if (accelerometerView == null) {
            accelerometerView = new AccelerometerViewImpl();
        }
        return accelerometerView; 
    }
    
    public static GeolocationViewImpl getGeolocationView() {
        if (geolocationView == null) {
            geolocationView = new GeolocationViewImpl();
        }
        return geolocationView; 
    }
    
    public static ContactsViewImpl getContactsView() {
        if (contactsView == null) {
            contactsView = new ContactsViewImpl();
        }
        return contactsView;
    }
    
}