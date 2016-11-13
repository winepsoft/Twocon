package winep.ir.mymemory.Utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ShaisteS on 8/21/2016.
 */
public class SettingsManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;
    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "SettingData";
    private static final String LANGUAGE_SETTING = "languageSetting";


    public SettingsManager(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLangugeSetting(String languge){
        editor.putString(LANGUAGE_SETTING,languge);
        editor.commit();
    }
    public String getLangugeSetting(){
        return pref.getString(LANGUAGE_SETTING,"en");
    }
}
