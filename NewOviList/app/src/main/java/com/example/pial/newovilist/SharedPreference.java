package com.example.pial.newovilist;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Pial on 20-Nov-16.
 */

public class SharedPreference {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    static final String NAME_KEY ="user_name";

    static final String DEFAULT_VALUE ="";

    Context context;
    public SharedPreference(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("my preference file", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveData(String name) {
        editor.putString(NAME_KEY, name);

    }


    /*public String getUserMail(){
        return sharedPreferences.getString(EMAIL_KEY,DEFAULT_VALUE);
    }
    public String getPassword(){
        return sharedPreferences.getString(PASSWORD_KEY,DEFAULT_VALUE);
    }*/

}
