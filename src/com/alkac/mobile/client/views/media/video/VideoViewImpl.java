/*******************************************************************************
 * VideoViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.media.video;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.layout.FitLayout;
import com.emitrom.gwt4.touch.client.utils.Has;
import com.emitrom.gwt4.touch.client.widgets.Video;
import com.google.gwt.core.client.GWT;

public class VideoViewImpl extends Panel implements VideoView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private Video video;
    
    public VideoViewImpl() {
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    private void initialize() {
        this.setLayout(new FitLayout());
        String baseUrl = GWT.getHostPageBaseURL();
        
        video = new Video();
        
        if (Has.get().video()) {
            video.setUrl("http://www.emitrom.com/sites/default/files/videos/BigBuck.mp4");
            video.setLoop(true);
            video.setEnableControls(true);
            video.setPosterUrl(baseUrl + "gwt4touch2_kitchensink/video/poster_rodents_big.jpg");
            this.add(video);
        } else {
            MessageBox.alert("Error", "The HTML5 &lt;video&gt; tag is not available on this device.");
        }
    }
    
    public Video getVideo() {
        return video;
    }
}
