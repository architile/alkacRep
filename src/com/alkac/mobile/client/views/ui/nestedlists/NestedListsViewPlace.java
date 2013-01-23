/*******************************************************************************
 * NestedListsViewPlace.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.views.ui.nestedlists;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NestedListsViewPlace extends AppPlace {
    
    private String token = "Nested Lists";
    
    public NestedListsViewPlace() {}
    
    public NestedListsViewPlace(String token) {
        this.token = token;
    }
    
    @Override
    public String getToken() {
        return token;
    }
    
    public static class Tokenizer implements PlaceTokenizer<NestedListsViewPlace> {
        
        @Override
        public String getToken(NestedListsViewPlace place) {
            return place.getToken();
        }

        @Override
        public NestedListsViewPlace getPlace(String token) {
            return new NestedListsViewPlace(token);
        }
    }

}
