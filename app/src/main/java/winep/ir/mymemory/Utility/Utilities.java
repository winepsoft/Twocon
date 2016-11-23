package winep.ir.mymemory.Utility;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;

import java.util.Locale;

import winep.ir.mymemory.R;

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
        Locale myLocale = new Locale(settingsManager.getLanguageSetting());
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
        settingsManager.setLanguageSetting(lang);
        context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
        Intent i = context.getPackageManager()
                .getLaunchIntentForPackage( context.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }

    public  boolean isSelectedALTRLanguage(Context context){
        SettingsManager settingsManager=new SettingsManager(context);
        boolean status;
        if(settingsManager.getLanguageSetting().equals("fa") ||settingsManager.getLanguageSetting().equals("ar") ) {
            status=true;
        }
        else
            status=false;
        return status;
    }

    public void setFontTextView(Context context,TextView textview){
        if (isSelectedALTRLanguage(context)){
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFarsiFontName());
            textview.setTypeface(face);
        }
        else {
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFontName());
            textview.setTypeface(face);

        }
    }

    public void setFontEditTextView(Context context,EditText textview){
        if (isSelectedALTRLanguage(context)){
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFarsiFontName());
            textview.setTypeface(face);
        }
        else {
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFontName());
            textview.setTypeface(face);

        }
    }

    public void setFontEditTextView(Context context, android.widget.EditText textview){
        if (isSelectedALTRLanguage(context)){
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFarsiFontName());
            textview.setTypeface(face);
        }
        else {
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFontName());
            textview.setTypeface(face);

        }
    }

    public void setFontButtonView(Context context,Button button){
        if (isSelectedALTRLanguage(context)){
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFarsiFontName());
            button.setTypeface(face);
        }
        else {
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFontName());
            button.setTypeface(face);

        }
    }

    public void setFontButtonView(Context context, android.widget.Button button){
        if (isSelectedALTRLanguage(context)){
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFarsiFontName());
            button.setTypeface(face);
        }
        else {
            MyApplication myApplication =new MyApplication();
            Typeface face = Typeface.createFromAsset(context.getAssets(), myApplication.getFontName());
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

    public void animateFAB(Context context,boolean isFabOpen,ImageButton buttun){

        Animation rotate_forward = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.rotate_forward);
        Animation rotate_backward = AnimationUtils.loadAnimation(context.getApplicationContext(),R.anim.rotate_backward);
        if(isFabOpen){

            buttun.startAnimation(rotate_backward);

        } else {

            buttun.startAnimation(rotate_forward);

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

    public void showColorPickerDialog(Context context){
        ColorPickerDialogBuilder
                .with(context)
                .setTitle(R.string.color_picker_dialog_title)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .showAlphaSlider(false)
                .density(10)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                    }
                })
                .setPositiveButton(R.string.save, new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .build()
                .show();
    }

    public String getLanguge(Context context){
        String language=context.getString(R.string.english);
        SettingsManager settingsManager=new SettingsManager(context);
        if (settingsManager.getLanguageSetting().equals("fa"))
            language=context.getString(R.string.persian);
        else if (settingsManager.getLanguageSetting().equals("ar"))
            language=context.getString(R.string.arabic);
        return language;
    }

    public String[] getDefaultLanguageListTitle(Context context){
        String language=getLanguge(context);
        if (language.equals(context.getString(R.string.arabic)))
            return getAllLanguageTitleDefaultArabic(context);
        else if (language.equals(context.getString(R.string.persian)))
            return getAllLanguageTitleDefaultPersian(context);
        else
            return getAllLanguageTitleDefaultEnglish(context);
    }

    public String[] getAllLanguageTitle(Context context){
        String[] languageTitle = {
                context.getString(R.string.english),
                context.getString(R.string.persian),
                context.getString(R.string.arabic)};
        return languageTitle;
    }

    public String[] getAllLanguageTitleDefaultEnglish(Context context){
        String[] languageTitle = {
                context.getString(R.string.english),
                context.getString(R.string.persian),
                context.getString(R.string.arabic)};
        return languageTitle;
    }

    public String[] getAllLanguageTitleDefaultPersian(Context context){
        String[] languageTitle = {
                context.getString(R.string.persian),
                context.getString(R.string.english),
                context.getString(R.string.arabic)};
        return languageTitle;
    }

    public String[] getAllLanguageTitleDefaultArabic(Context context){
        String[] languageTitle = {
                context.getString(R.string.arabic),
                context.getString(R.string.persian),
                context.getString(R.string.english)};
        return languageTitle;
    }
}
