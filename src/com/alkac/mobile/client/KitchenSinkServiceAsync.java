/*******************************************************************************
 * KitchenSinkServiceAsync.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client;

import com.alkac.mobile.client.views.tutoni.UserInfo;
import com.alkac.mobile.shared.Ferman;
import com.alkac.mobile.shared.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>KitchenSinkService</code>.
 */
public interface KitchenSinkServiceAsync {
    void getSource(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
    void getVersion(AsyncCallback<String> callback);

    void getFerman(UserInfo userInfo, Location location, AsyncCallback<Ferman> async);

    void setAyar(String kapishMesafe, Location location, AsyncCallback<Void> async);

    void initGame(AsyncCallback<Void> async);
}
