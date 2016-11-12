package winep.ir.twocon.Utility;

import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import winep.ir.twocon.R;

/**
 * Created by ShaisteS on 10/3/2016.
 */
@ReportsCrashes(formUri = "http://mbaas.ir/api/acra/1028afc6")
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
        ACRA.init(this);
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
