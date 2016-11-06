package winep.ir.twocon.Presenter.SettingsPage;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.TimePickerDialog;
import com.rey.material.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.DataModel.Reminder;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.DividerItemDecorationRecyclerView;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/15/2016.
 */
public class SettingsActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView reminderRecyclerView;
    private SettingReminderRecyclerViewAdapter reminderAdapter;
    private ArrayList<Reminder> allReminders;
    private Reminder newreminder;
    private FloatingActionButton btnSelectColor;
    private Button btnMore;
    private LinearLayout layoutMore;
    private Spinner spinnerSelectDay;
    private Button btnSelectTime;
    private Button btnAddReminderOk;
    private Spinner spinnerSelectLanguage;
    private Spinner spinnerBackupFrequencyTime;
    private Spinner spinnerBackupEmail;
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

        allReminders=new ArrayList<>();
        reminderRecyclerView=(RecyclerView)findViewById(R.id.setting_reminder_recycler_view);
        reminderRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        reminderRecyclerView.addItemDecoration(new DividerItemDecorationRecyclerView(3));
        reminderAdapter=new SettingReminderRecyclerViewAdapter(context,getAllreminder());
        reminderRecyclerView.setAdapter(reminderAdapter);


        //Reminder
        btnMore =(Button)findViewById(R.id.btn_add_reminder);
        layoutMore=(LinearLayout)findViewById(R.id.linear_layout_more);
        layoutMore.setVisibility(View.GONE);
        spinnerSelectDay=(Spinner)findViewById(R.id.spinner_Select_Day);
        btnSelectTime=(Button)findViewById(R.id.btn_select_Time);
        btnAddReminderOk=(Button)findViewById(R.id.btn_new_reminder_ok);
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.days));
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectDay.setAdapter(dayAdapter);

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutMore.setVisibility(View.VISIBLE);
                newreminder=new Reminder();
            }
        });

        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();

            }
        });

        spinnerSelectDay.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner parent, View view, int position, long id) {
                newreminder.setDay(parent.getAdapter().getItem(position).toString());
            }
        });

        btnAddReminderOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (newreminder.getDay()==null)
                    Toast.makeText(context,getString(R.string.setting_message_select_day),Toast.LENGTH_SHORT).show();
                else if (newreminder.getHour()==null)
                    Toast.makeText(context,getString(R.string.setting_message_select_Time),Toast.LENGTH_SHORT).show();
                else if (newreminder.getDay()!=null && newreminder.getHour()!=null) {
                    reminderAdapter.addreminder(newreminder);
                    spinnerSelectDay.setSelection(0);
                    btnSelectTime.setText(getString(R.string.setting_button_select_time));
                    layoutMore.setVisibility(View.GONE);
                }
            }
        });

        //Backup
        spinnerBackupFrequencyTime=(Spinner)findViewById(R.id.spinner_settings_backup_time);
        ArrayAdapter<String> backupFrequencyTime = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.backup_frequency));
        backupFrequencyTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBackupFrequencyTime.setAdapter(backupFrequencyTime);

        spinnerBackupEmail=(Spinner)findViewById(R.id.spinner_email_backup);
        String[] backupEmail=new String[2];
        backupEmail[0]=getString(R.string.no);
        backupEmail[1]="iran8b@gmail.com";
        ArrayAdapter<String> emailAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,backupEmail);
        emailAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBackupEmail.setAdapter(emailAdapter);

        //Group
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


    private void showTimePickerDialog(){
        Dialog.Builder builder=new TimePickerDialog.Builder(R.style.Material_Widget_TimePicker_Light,24,00){
            @Override public void onPositiveActionClicked(    DialogFragment fragment){
                TimePickerDialog dialog=(TimePickerDialog)fragment.getDialog();
                newreminder.setHour(Integer.toString(dialog.getHour()));
                newreminder.setMinute(Integer.toString(dialog.getMinute()));
                btnSelectTime.setText(dialog.getFormattedTime(SimpleDateFormat.getTimeInstance()));
                super.onPositiveActionClicked(fragment);
            }
            @Override public void onNegativeActionClicked(    DialogFragment fragment){
                super.onNegativeActionClicked(fragment);
            }
        };
        builder.positiveAction(getString(R.string.save)).negativeAction(getString(R.string.cancel));
        DialogFragment fragment=DialogFragment.newInstance(builder);
        fragment.show(getSupportFragmentManager(),null);
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

    private ArrayList<Reminder> getAllreminder(){
        Reminder aReminder1=new Reminder();
        aReminder1.setDay("Saturday");
        aReminder1.setHour("10");
        aReminder1.setMinute("0");
        //aReminder1.setTimeType("pm");
        allReminders.add(aReminder1);

        Reminder aReminder2=new Reminder();
        aReminder2.setDay("Sunday");
        aReminder2.setHour("10");
        aReminder2.setMinute("0");
        //aReminder2.setTimeType("pm");
        allReminders.add(aReminder2);

        return allReminders;
    }

}
