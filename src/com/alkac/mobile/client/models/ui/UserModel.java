/*******************************************************************************
 * UserModel.java is part of the Kitchen Sink (for Touch4j 2.0)
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
package com.alkac.mobile.client.models.ui;

import java.util.ArrayList;
import java.util.List;

import com.emitrom.gwt4.touch.client.data.BaseModel;

public class UserModel extends BaseModel {

    public static final String LASTNAME = "lastname";
    public static final String FIRSTNAME = "firstname";
    public static final String[] FIELDS = new String[]{LASTNAME, FIRSTNAME};

    public UserModel() {
        setFirstName("");
        setLastName("");
    }

    public UserModel(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setLastName(String lastName) {
        set(LASTNAME, lastName);
    }

    public void setFirstName(String firstName) {
        set(FIRSTNAME, firstName);
    }

    public static List<UserModel> createUserList() {
        List<UserModel> users = new ArrayList<UserModel>();
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Ape", "Evilias"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Ape", "Evilias"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Ape", "Evilias"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Ape", "Evilias"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Alfredo", "Quiroga"));
        users.add(new UserModel("David", "La Motta"));
        users.add(new UserModel("Alain", "Ekambi"));
        users.add(new UserModel("Randy", "Komisar"));
        users.add(new UserModel("Steve", "Jobs"));
        users.add(new UserModel("Stephen", "Walsh"));
        users.add(new UserModel("Zed", "Zacharias"));
        return users;
    }
}
