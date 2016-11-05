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
    private Spinner spinnerSelectLanguage;
    private FloatingActionButton btnColor1;
    private FloatingActionButton btnColor2;
    private FloatingActionButton btnColor3;
    private FloatingActionButton btnColor4;
    private FloatingActionButton btnColor5;
    private FloatingActionButton btnColor6;
    private FloatingActionButton btnColor7;
    private FloatingActionButton btnColor8;
    private FloatingActionButton btnColor9;
    private FloatingActionButton btnColor10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.settings_activity_title));
        context=this;
        btnColor1=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_1);
        btnColor2=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_2);
        btnColor3=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_3);
        btnColor4=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_4);
        btnColor5=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_5);
        btnColor6=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_6);
        btnColor7=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_7);
        btnColor8=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_8);
        btnColor9=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_9);
        btnColor10=(FloatingActionButton)findViewById(R.id.btn_settings_group_color_10);

        btnSelectColor=(FloatingActionButton)findViewById(R.id.btn_settings_app_color);
        btnSelectColor.setSize(com.getbase.floatingactionbutton.FloatingActionButton.SIZE_MINI);
        btnSelectColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnSelectColor);

            }
        });

        btnColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor1);
            }
        });

        btnColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor2);
            }
        });

        btnColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor3);
            }
        });

        btnColor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor4);
            }
        });

        btnColor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor5);
            }
        });

        btnColor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor6);
            }
        });

        btnColor7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor7);
            }
        });

        btnColor8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor8);
            }
        });

        btnColor9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor9);
            }
        });

        btnColor10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorPickerDialog(btnColor10);
            }
        });


        spinnerSelectLanguage =(Spinner)findViewById(R.id.spinner_settings_language);
        String[] languageTitle = {getString(R.string.select_language), getString(R.string.english),getString(R.string.persian)};
        ArrayAdapter<String> a = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languageTitle);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectLanguage.setAdapter(a);
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

    private void showColorPickerDialog(final FloatingActionButton buttonSelected){
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
                        buttonSelected.setColorNormal(selectedColor);
                        buttonSelected.setColorPressed(selectedColor);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buttonSelected.setColorNormal(ContextCompat.getColor(context,R.color.colorPrimary));
                        buttonSelected.setColorPressed(ContextCompat.getColor(context,R.color.colorPrimary));
                    }
                })
                .build()
                .show();
    }

}
