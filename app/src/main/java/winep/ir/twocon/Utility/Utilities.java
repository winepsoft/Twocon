package winep.ir.twocon.Utility;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import java.util.Locale;

import winep.ir.twocon.R;

/**
 * Created by ShaisteS on 10/4/2016.
 */
public class Utilities {

    private static Utilities utility = new Utilities();

    public static Utilities getInstance() {
        if (utility != null) {
            return utility;
        } else return new Utilities();
    }

    public void customView(View v, int backgroundColor, int borderColor)
    {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setSize(60,60);
        shape.setColor(backgroundColor);
        shape.setStroke(2, borderColor);
        v.setBackground(shape);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setSettingLanguage(Context context) {
        SettingsManager settingsManager=new SettingsManager(context);
        Locale myLocale = new Locale(settingsManager.getLangugeSetting());
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        Locale.setDefault(myLocale);
        context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setLocale(Context context,String lang) {
        SettingsManager settingsManager=new SettingsManager(context);
        Locale myLocale = new Locale(lang);
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        Locale.setDefault(myLocale);
        settingsManager.setLangugeSetting(lang);
        context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
        Intent i = context.getPackageManager()
                .getLaunchIntentForPackage( context.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

    public  boolean isFarsiSelected(Context context){
        SettingsManager settingsManager=new SettingsManager(context);
        boolean status;
        if(settingsManager.getLangugeSetting().equals("fa")) {
            status=true;
        }
        else
            status=false;
        return status;
    }

    public void setFontTextView(Context context,TextView textview){
        if (isFarsiSelected(context)){
            Font font=new Font();
            Typeface face = Typeface.createFromAsset(context.getAssets(),font.getFarsiFontName());
            textview.setTypeface(face);
        }
        else {
            Font font=new Font();
            Typeface face = Typeface.createFromAsset(context.getAssets(),font.getFontName());
            textview.setTypeface(face);

        }
    }

    public void setFontEditTextView(Context context,EditText textview){
        if (isFarsiSelected(context)){
            Font font=new Font();
            Typeface face = Typeface.createFromAsset(context.getAssets(),font.getFarsiFontName());
            textview.setTypeface(face);
        }
        else {
            Font font=new Font();
            Typeface face = Typeface.createFromAsset(context.getAssets(),font.getFontName());
            textview.setTypeface(face);

        }
    }

    public void setFontButtonView(Context context,Button button){
        if (isFarsiSelected(context)){
            Font font=new Font();
            Typeface face = Typeface.createFromAsset(context.getAssets(),font.getFarsiFontName());
            button.setTypeface(face);
        }
        else {
            Font font=new Font();
            Typeface face = Typeface.createFromAsset(context.getAssets(),font.getFontName());
            button.setTypeface(face);

        }
    }
    public void animateFAB(Context context,boolean isFabOpen,FloatingActionButton fab){

        Animation rotate_forward = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.rotate_forward);
        Animation rotate_backward = AnimationUtils.loadAnimation(context.getApplicationContext(),R.anim.rotate_backward);
        if(isFabOpen){

            fab.startAnimation(rotate_backward);

        } else {

            fab.startAnimation(rotate_forward);

        }
    }

    public boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    public boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

}
