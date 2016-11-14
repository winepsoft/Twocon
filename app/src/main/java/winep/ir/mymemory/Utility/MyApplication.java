package winep.ir.mymemory.Utility;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 10/3/2016.
 */
@ReportsCrashes(formUri = "http://mbaas.ir/api/acra/1028afc6")
public class MyApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "A6Z21iFfQ9MJkuhYNdUPlNEa5";
    private static final String TWITTER_SECRET = "CrOF4snEYY0agLhPcG2QjkUlbMKPBMOGh1Kl0VbQd4OjbIKtez";


    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));
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
