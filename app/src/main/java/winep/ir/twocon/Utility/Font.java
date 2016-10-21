package winep.ir.twocon.Utility;

import android.app.Application;
import android.content.Context;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import winep.ir.twocon.R;

/**
 * Created by ShaisteS on 10/3/2016.
 */
public class Font extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/iransans_mobile_font_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public String getFarsiFontName(){
        return "fonts/iransans.ttf";
    }

    public String getFontName(){
        return "fonts/iransans_mobile_font_regular.ttf";
    }

    public String getRTLFontNameForDialog(){
        return "iransans.ttf";
    }
}
