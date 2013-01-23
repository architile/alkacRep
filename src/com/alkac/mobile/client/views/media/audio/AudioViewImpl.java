/*******************************************************************************
 * AudioViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.media.audio;

import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.Align;
import com.emitrom.gwt4.touch.client.layout.Pack;
import com.emitrom.gwt4.touch.client.layout.VBoxLayout;
import com.emitrom.gwt4.touch.client.utils.Has;
import com.emitrom.gwt4.touch.client.utils.OS;
import com.emitrom.gwt4.touch.client.widgets.Audio;
import com.emitrom.gwt4.touch.client.widgets.Button;

public class AudioViewImpl extends Panel implements AudioView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private Audio audio;
    private Button androidButton;

    public AudioViewImpl() {
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        VBoxLayout vboxLayout = new VBoxLayout();
        vboxLayout.setPack(Pack.CENTER);
        vboxLayout.setAlign(Align.CENTER);
        setLayout(vboxLayout);

        audio = new Audio();
        audio.setUrl("http://dc128.4shared.com/img/440737810/dd39edce/dlink__2Fdownload_2FG3Weh4C7_3Ftsid_3D00000000-00000-00000000/preview.mp3");
        audio.setLoop(true);
        audio.setWidth("40%");
        audio.setCls("myAudio");

        if (Has.get().audio()) {

            if (OS.get().isAndroid()) {
                
                audio.setEnableControls(false);
                androidButton = new Button("Play audio", UI.ACTION);
                androidButton.setMargin(20);
                
                add(audio);
                add(androidButton);
                
                androidButton.addTapHandler(new TapHandler() {
                    
                    @Override
                    public void onTap(Button button, EventObject event) {
                        if (audio.isPlaying()) {
                            audio.pause();
                            androidButton.setText("Play audio");
                        } else {
                            audio.play();
                            androidButton.setText("Pause audio");
                        }
                    }
                });
                
            } else {
                audio.setEnableControls(true);
                add(audio);
            }
            
        } else {
            MessageBox.alert("Error", "The HTML5 &lt;audio&gt; tag is not available on this device.");
        }

    }

    public Audio getAudio() {
        return audio;
    }
}
