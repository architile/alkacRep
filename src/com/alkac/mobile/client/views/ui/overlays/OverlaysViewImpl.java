/*******************************************************************************
 * OverlaysViewImpl.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.overlays;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.containers.ActionSheet;
import com.emitrom.gwt4.touch.client.containers.MessageBox;
import com.emitrom.gwt4.touch.client.containers.MessageBox.ConfirmCallback;
import com.emitrom.gwt4.touch.client.containers.MessageBox.PromptCallback;
import com.emitrom.gwt4.touch.client.containers.ModalPanel;
import com.emitrom.gwt4.touch.client.containers.Panel;
import com.emitrom.gwt4.touch.client.containers.ToolBar;
import com.emitrom.gwt4.touch.client.core.EventObject;
import com.emitrom.gwt4.touch.client.core.Scroller;
import com.emitrom.gwt4.touch.client.core.config.Dock;
import com.emitrom.gwt4.touch.client.core.handlers.button.TapHandler;
import com.emitrom.gwt4.touch.client.data.PickerSlot;
import com.emitrom.gwt4.touch.client.data.PickerSlotData;
import com.emitrom.gwt4.touch.client.laf.Direction;
import com.emitrom.gwt4.touch.client.laf.UI;
import com.emitrom.gwt4.touch.client.layout.Align;
import com.emitrom.gwt4.touch.client.layout.Pack;
import com.emitrom.gwt4.touch.client.layout.VBoxLayout;
import com.emitrom.gwt4.touch.client.picker.Picker;
import com.emitrom.gwt4.touch.client.widgets.Button;
import com.emitrom.gwt4.touch.client.widgets.Spacer;

public class OverlaysViewImpl extends Panel implements OverlaysView {

    @SuppressWarnings("unused")
    private Presenter presenter;
    private Button actionButton;
    private Button overlayButton;
    private Button alertButton;
    private Button promptButton;
    private Button confirmButton;
    private Button pickerButton;
    private ActionSheet actionSheet;
    private Panel overlayPanel;
    private Picker picker;
    private Button pickerCancelButton;
    private Button pickerDoneButton;

    public OverlaysViewImpl() {
        setScrollable(true);
        setPadding(20);
        VBoxLayout layout = new VBoxLayout();
        layout.setAlign(Align.STRETCH);
        layout.setPack(Pack.CENTER);
        setLayout(layout);
        initialize();
        addListeners();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize() {

        actionButton = new Button("Action Sheet", UI.ROUND);
        actionButton.setMargin(10);

        overlayButton = new Button("Overlay", UI.ROUND);
        overlayButton.setMargin(10);

        alertButton = new Button("Alert", UI.ROUND);
        alertButton.setMargin(10);

        promptButton = new Button("Prompt", UI.ROUND);
        promptButton.setMargin(10);

        confirmButton = new Button("Confirm", UI.ROUND);
        confirmButton.setMargin(10);

        pickerButton = new Button("Picker", UI.ROUND);
        pickerButton.setMargin(10);

        pickerCancelButton = new Button("Cancel");
        pickerDoneButton = new Button("Done");

        /**
         * Action Sheet
         */

        overlayPanel = new ModalPanel();
        overlayPanel.setCentered(true);
        overlayPanel.setSize(300, 200);
        overlayPanel.setStyleHtmlContent(true);
        Scroller scroller = new Scroller();
        scroller.setDirection(Direction.BOTH);
        overlayPanel.setScrollable(scroller);
        overlayPanel.setHideOnMaskTap(true);
        overlayPanel.setModal(true);
        overlayPanel.setHtml("<p>This is a modal, centered and floating mainContainer. "
                        + "hideOnMaskTap is true by default so we can tap anywhere outside the overlay to hide it.<br/></p>");

        ToolBar toolBar = new ToolBar();
        toolBar.setDocked(Dock.TOP);
        toolBar.setTitle("Overlay Title");

        overlayPanel.add(toolBar);

        add(overlayPanel);
        overlayPanel.hide();

        add(actionButton);
        add(overlayButton);
        add(alertButton);
        add(promptButton);
        add(confirmButton);
        add(pickerButton);

    }

    private void addListeners() {

        actionButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                displayActionSheet();
            }
        });

        overlayButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                overlayPanel.show();
            }
        });

        alertButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                MessageBox.alert("Title", "The quick brown fox jumped over the lazy dog");
            }
        });

        promptButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                MessageBox.prompt("Welcome", "What is your first name?", new PromptCallback() {
                    @Override
                    public void execute(String btnID, String text) {
                        MessageBox.alert(text + " pressed " + btnID);
                    }
                });
            }
        });

        confirmButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                MessageBox.confirm("Confirmation", "Are you sure you want to do that?", new ConfirmCallback() {
                    @Override
                    public void execute(String btnID) {
                        MessageBox.alert("You pressed " + btnID);
                    }
                });
            }
        });

        pickerButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                displayPicker();
            }
        });

        pickerCancelButton.addTapHandler(new TapHandler() {
            @Override
            public void onTap(Button button, EventObject event) {
                picker.hide();
            }
        });

        pickerDoneButton.addTapHandler(new TapHandler() {

            @Override
            public void onTap(Button button, EventObject event) {
                picker.hide();
                List<PickerSlotData> slots = picker.getValues();
                String result = "";
                for (PickerSlotData slot : slots) {
                    String text = slot.getText();
                    String value = slot.getValue();
                    result += "<div>Slot: " + text + " Value: " + value + "</div>";
                }
                MessageBox.alert(result);
            }

        });

    }

    private List<PickerSlotData> getHourSlot() {

        List<PickerSlotData> hour = new ArrayList<PickerSlotData>();
        for (int i = 0; i <= 23; i++) {
            String value = String.valueOf(i).length() == 1 ? 0 + String.valueOf(i) : String.valueOf(i);
            hour.add(new PickerSlotData(value, value));
        }
        return hour;

    }

    private List<PickerSlotData> getMinutesSlot() {

        List<PickerSlotData> minutes = new ArrayList<PickerSlotData>();
        for (int i = 0; i <= 60; i++) {
            String value = String.valueOf(i).length() == 1 ? 0 + String.valueOf(i) : String.valueOf(i);
            minutes.add(new PickerSlotData(value, value));
        }

        return minutes;

    }

    private void displayPicker() {
        if (picker == null) {
            picker = new Picker();
            picker.setHideOnMaskTap(true);
            picker.setDoneButton(false);
            picker.setCancelButton(false);
            picker.setUseTitles(true);

            ToolBar toolbar = new ToolBar();
            toolbar.add(pickerCancelButton);
            toolbar.add(new Spacer());
            toolbar.add(pickerDoneButton);

            picker.setToolBar(toolbar);

            List<PickerSlot> pickerSlots = new ArrayList<PickerSlot>();
            PickerSlot slot = new PickerSlot();
            slot.setTitle("Hour");
            slot.setName("hour");
            slot.setData(getHourSlot());

            pickerSlots.add(slot);

            slot = new PickerSlot();
            slot.setTitle("Minute");
            slot.setName("minutes");
            slot.setData(getMinutesSlot());

            pickerSlots.add(slot);
            picker.setSlots(pickerSlots);

            add(picker);
            
        }

        picker.show();
    }

    private void displayActionSheet() {

        if (actionSheet == null) {
            actionSheet = new ActionSheet();

            Button button = new Button("Delete Draft", UI.DECLINE, new TapHandler() {
                @Override
                public void onTap(Button button, EventObject event) {
                    actionSheet.hide();
                }
            });
            actionSheet.add(button);

            button = new Button("Save Draft", UI.NORMAL, new TapHandler() {
                @Override
                public void onTap(Button button, EventObject event) {
                    actionSheet.hide();
                }
            });
            actionSheet.add(button);

            button = new Button("Cancel Draft", UI.NORMAL, new TapHandler() {
                @Override
                public void onTap(Button button, EventObject event) {
                    actionSheet.hide();
                }
            });
            actionSheet.add(button);

            add(actionSheet);
        }

        actionSheet.show();
    }

}
