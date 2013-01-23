/*******************************************************************************
 * FormsViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.form;

import com.emitrom.gwt4.touch.client.containers.FormPanel;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.containers.TabPanel;
import com.emitrom.gwt4.touch.client.containers.ToolBar;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.config.Dock;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.field.CheckBox;
import com.emitrom.gwt4.touch.client.field.Email;
import com.emitrom.gwt4.touch.client.field.Password;
import com.emitrom.gwt4.touch.client.field.Radio;
import com.emitrom.gwt4.touch.client.field.Search;
import com.emitrom.gwt4.touch.client.field.Select;
import com.emitrom.gwt4.touch.client.field.Slider;
import com.emitrom.gwt4.touch.client.field.Spinner;
import com.emitrom.gwt4.touch.client.field.Text;
import com.emitrom.gwt4.touch.client.field.TextArea;
import com.emitrom.gwt4.touch.client.field.Toggle;
import com.emitrom.gwt4.touch.client.field.Url;
import com.emitrom.gwt4.touch.client.form.FieldSet;
import com.emitrom.gwt4.touch.client.laf.Alignment;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.Align;
import com.emitrom.gwt4.touch.client.layout.Pack;
import com.emitrom.gwt4.touch.client.layout.VBoxLayout;
import com.emitrom.gwt4.touch.client.tab.TabItem;
import com.emitrom.gwt4.touch.client.widgets.Button;
import com.emitrom.gwt4.touch.client.widgets.Spacer;
import com.alkac.mobile.client.models.ui.RankModel;

public class FormsViewImpl extends TabPanel implements FormsView {

    @SuppressWarnings("unused")
	private Presenter presenter;
    
    public FormsViewImpl() {
        setTabBarPosition(Alignment.TOP);
        getTabBar().getLayout().setPack(Pack.CENTER);
        initialize();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    
    private void initialize() {

        TabItem basicFormTabItem = new TabItem("Basic");
        TabItem sliderTabItem = new TabItem("Sliders");
        TabItem toolBarsTabItem = new TabItem("ToolBar");

        final FormPanel form = new FormPanel();

        ToolBar toolBar = new ToolBar(Dock.TOP);
        toolBar.add(new Spacer());
        
        Button resetButton = new Button("Reset", UI.ACTION);
        final Button disableButton = new Button("Disable", UI.ACTION);
        
        toolBar.add(resetButton);
        toolBar.add(disableButton);
        form.add(toolBar);
        
        disableButton.addTapHandler(new TapHandler() {
            
            @Override
            public void onTap(Button button, EventObject event) {
                if (button.getText().equals("Disable")) {
                    form.disable();
                    disableButton.setText("Enable");
                } else {
                    form.enable();
                    disableButton.setText("Disable");
                }
            }
        });
        
        resetButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                form.reset();
            }
        });
        

        FieldSet personalInfoFieldSet = new FieldSet("Personal Info");
        personalInfoFieldSet.setInstructions("Please enter the information above");

        Text text = new Text();
        text.setName("name");
        text.setLabel("Name");
        text.setRequired(true);
        text.setClearIcon(true);
        text.setAutoCapitalize(false);
        personalInfoFieldSet.add(text);

        Password passWord = new Password();
        passWord.setName("password");
        passWord.setLabel("Password");
        passWord.setRequired(true);
        personalInfoFieldSet.add(passWord);

        Password repeatPassword = new Password();
        repeatPassword.setName("passwordRepeat");
        repeatPassword.setLabel("Disabled");
        repeatPassword.setDisabled(true);
        repeatPassword.setClearIcon(true);
        personalInfoFieldSet.add(repeatPassword);

        Email eMail = new Email();
        eMail.setName("Email");
        eMail.setLabel("Email");
        eMail.setPlaceHolder("you@emitrom.com");
        eMail.setClearIcon(true);
        personalInfoFieldSet.add(eMail);

        Url url = new Url();
        url.setName("url");
        url.setLabel("Url");
        url.setPlaceHolder("http://emitrom.com");
        personalInfoFieldSet.add(url);

        CheckBox checkBox = new CheckBox();
        checkBox.setLabel("Cool");
        checkBox.setName("cool");
        checkBox.setValue(true);
        personalInfoFieldSet.add(checkBox);

        Spinner spinner = new Spinner();
        spinner.setName("spinner");
        spinner.setLabel("Spinner");
        personalInfoFieldSet.add(spinner);

        Select select = new Select();
        select.setOptions(RankModel.getOptions());
        select.setName("rank");
        select.setLabel("Rank");
        personalInfoFieldSet.add(select);

        TextArea textArea = new TextArea();
        textArea.setName("bio");
        textArea.setLabel("Bio");
        textArea.setMaxLength(60);
        textArea.setMaxRows(10);
        personalInfoFieldSet.add(textArea);

        Slider slider = new Slider();
        slider.setName("slider");
        slider.setLabel("Slider");
        personalInfoFieldSet.add(slider);

        Toggle toggle = new Toggle();
        toggle.setLabel("Security Mode");
        toggle.setName("toggle");
        personalInfoFieldSet.add(toggle);

        Radio radio = new Radio();
        radio.setName("blue");
        radio.setLabel("Blue Team");
        personalInfoFieldSet.add(radio);

        radio = new Radio();
        radio.setName("red");
        radio.setLabel("Red Team");
        personalInfoFieldSet.add(radio);
        
        /**
         * Favorite Color
         */
        FieldSet favoriteColorFieldSet = new FieldSet("Favorite Color");
        
        radio = new Radio();
        radio.setLabel("Red");
        radio.setName("favorite_color");
        favoriteColorFieldSet.add(radio);
        
        radio = new Radio();
        radio.setLabel("Blue");
        radio.setName("favorite_color");
        favoriteColorFieldSet.add(radio);
        
        radio = new Radio();
        radio.setLabel("Green");
        radio.setName("favorite_color");
        favoriteColorFieldSet.add(radio);
        
        radio = new Radio();
        radio.setLabel("Purple");
        radio.setName("favorite_color");
        favoriteColorFieldSet.add(radio);
        
        form.add(personalInfoFieldSet);
        form.add(favoriteColorFieldSet);
        
        /**
         * Sliders
         */
        FormPanel slidersFormPanel = new FormPanel();
        VBoxLayout sliderLayout = new VBoxLayout();
        sliderLayout.setAlign(Align.STRETCH);
        slidersFormPanel.setLayout(sliderLayout);
        
        FieldSet sliderFieldSet = new FieldSet();
        
        Slider singleThumbSlider = new Slider();
        singleThumbSlider.setLabelWidth("35%");
        singleThumbSlider.setName("thumb");
        singleThumbSlider.setLabel("Single Thumb");
        singleThumbSlider.setLabelAlign(Alignment.TOP);
        
        Slider multiThumbSlider = new Slider();
        multiThumbSlider.setLabelWidth("35%");
        multiThumbSlider.setName("multithumb");
        multiThumbSlider.setLabel("Multiple Thumbs");
        int[] values = new int[2];
        values[0] = 2;
        values[1] = 5;
        multiThumbSlider.setSliderValues(values);
        multiThumbSlider.setLabelAlign(Alignment.TOP);

        Toggle toggleField = new Toggle();
        toggleField.setLabelWidth("35%");
        toggleField.setName("toggle");
        toggleField.setLabel("Toggle");
        toggleField.setLabelAlign(Alignment.TOP);
        
        sliderFieldSet.add(singleThumbSlider);
        sliderFieldSet.add(multiThumbSlider);
        sliderFieldSet.add(toggleField);
        
        slidersFormPanel.add(sliderFieldSet);
        
        /**
         * ToolBars
         */
        Panel toolBarPanel = new Panel();

        ToolBar searchToolBar = new ToolBar();
        searchToolBar.setDocked(Dock.TOP);
        
        Search searchField = new Search();
        searchField.setPlaceHolder("Search");
        searchField.setName("searchfield");
        
        searchToolBar.add(searchField);
        
        ToolBar textToolBar = new ToolBar();
        textToolBar.setDocked(Dock.TOP);
        
        Text textField = new Text();
        textField.setPlaceHolder("Text");
        textField.setName("searchfield");
        
        textToolBar.add(textField);
        
        ToolBar selectToolBar = new ToolBar();
        selectToolBar.setDocked(Dock.TOP);
        
        Select selectField = new Select();
        selectField.setPlaceHolder("Text");
        selectField.setName("rank");
        selectField.setOptions(RankModel.getOptions());
        
        selectToolBar.add(selectField);

        toolBarPanel.add(searchToolBar);
        toolBarPanel.add(textToolBar);
        toolBarPanel.add(selectToolBar);
        
        basicFormTabItem.add(form);
        sliderTabItem.add(slidersFormPanel);
        toolBarsTabItem.add(toolBarPanel);
        
        add(basicFormTabItem);
        add(sliderTabItem);
        add(toolBarsTabItem);
        
    }
    
}