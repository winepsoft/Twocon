package winep.ir.twocon.Presenter.SettingsPage;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rey.material.widget.Spinner;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/15/2016.
 */
public class SettingsActivity extends AppCompatActivity {

    private Context context;
    private FloatingActionButton btnSelectColor;
    private Spinner spinnerSelectLanguge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.settings_activity_title));
        context=this;
        btnSelectColor=(FloatingActionButton)findViewById(R.id.btn_settings_app_color);
        btnSelectColor.setSize(com.getbase.floatingactionbutton.FloatingActionButton.SIZE_MINI);
        btnSelectColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                                btnSelectColor.setColorNormal(selectedColor);
                                btnSelectColor.setColorPressed(selectedColor);
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btnSelectColor.setColorNormal(ContextCompat.getColor(context,R.color.colorPrimary));
                                btnSelectColor.setColorPressed(ContextCompat.getColor(context,R.color.colorPrimary));
                            }
                        })
                        .build()
                        .show();

            }
        });

        spinnerSelectLanguge=(Spinner)findViewById(R.id.spinner_settings_language);
        String[] languageTitle = {getString(R.string.select_language), getString(R.string.english),getString(R.string.persian)};
        ArrayAdapter<String> a = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languageTitle);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectLanguge.setAdapter(a);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.setting_page_menu, menu);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }

}
