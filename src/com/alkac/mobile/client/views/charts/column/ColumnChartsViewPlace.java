package com.alkac.mobile.client.views.charts.column;

import com.alkac.mobile.client.views.AppPlace;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ColumnChartsViewPlace extends AppPlace {

    private String token = "Column";

    public ColumnChartsViewPlace() {
    }

    public ColumnChartsViewPlace(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    public static class Tokenizer implements PlaceTokenizer<ColumnChartsViewPlace> {

        @Override
        public String getToken(ColumnChartsViewPlace place) {
            return place.getToken();
        }

        @Override
        public ColumnChartsViewPlace getPlace(String token) {
            return new ColumnChartsViewPlace(token);
        }
    }
}
