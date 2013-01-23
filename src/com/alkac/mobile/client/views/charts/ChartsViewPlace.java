package com.alkac.mobile.client.views.charts;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ChartsViewPlace extends AppPlace {

    private String token = "Charts";

    public ChartsViewPlace() {
    }

    public ChartsViewPlace(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<ChartsViewPlace> {

        @Override
        public String getToken(ChartsViewPlace place) {
            return place.getToken();
        }

        @Override
        public ChartsViewPlace getPlace(String token) {
            return new ChartsViewPlace(token);
        }
    }
}
