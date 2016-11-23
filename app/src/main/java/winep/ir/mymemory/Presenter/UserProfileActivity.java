package winep.ir.mymemory.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.Presenter.SettingsPage.SettingsActivity;
import winep.ir.mymemory.Presenter.StatisticsTotalPage.StatisticsTotalActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Dialogs;
import winep.ir.mymemory.Utility.MyApplication;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 11/14/2016.
 */
public class UserProfileActivity extends AppCompatActivity {

    private Context context;
    private MyApplication myApplication;
    private ViewSwitcher viewSwitcherUserName;
    private TextView txtUserName;
    private EditText editTextUserName;
    private ViewSwitcher viewSwitcherLastName;
    private TextView txtLastName;
    private EditText editTextLastName;
    private ViewSwitcher viewSwitcherFirstName;
    private TextView txtFirstName;
    private EditText editTextFirstName;
    private ViewSwitcher viewSwitcherEmail;
    private TextView txtEmail;
    private EditText editTextEmail;
    private ViewSwitcher viewSwitcherMobile;
    private TextView txtMobile;
    private EditText editTextMobile;
    private TextView txtAccountTitle;
    private TextView txtAccount;
    private Button btnUpgrade;
    private ImageView imgUserProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);
        context = this;
        myApplication =new MyApplication();

        setTitle(getString(R.string.user_profile_title));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imgUserProfile=(ImageView)findViewById(R.id.image_user_profile);
        viewSwitcherUserName=(ViewSwitcher)findViewById(R.id.view_switcher_user_profile_user_name);
        txtUserName=(TextView)findViewById(R.id.txt_user_profile_user_name);
        editTextUserName=(EditText)findViewById(R.id.edit_txt_user_profile_user_name);
        viewSwitcherLastName=(ViewSwitcher)findViewById(R.id.view_switcher_user_profile_last_name);
        txtLastName=(TextView)findViewById(R.id.txt_user_profile_last_name);
        editTextLastName=(EditText)findViewById(R.id.edit_txt_user_profile_last_name);
        viewSwitcherFirstName=(ViewSwitcher)findViewById(R.id.view_switcher_user_profile_first_name);
        txtFirstName=(TextView)findViewById(R.id.txt_user_profile_first_name);
        editTextFirstName=(EditText)findViewById(R.id.edit_txt_user_profile_first_name);
        viewSwitcherEmail=(ViewSwitcher)findViewById(R.id.view_switcher_user_profile_email);
        txtEmail=(TextView)findViewById(R.id.txt_user_profile_email);
        editTextEmail=(EditText)findViewById(R.id.edit_txt_user_profile_email);
        viewSwitcherMobile=(ViewSwitcher)findViewById(R.id.view_switcher_user_profile_mobile);
        txtMobile=(TextView)findViewById(R.id.txt_user_profile_mobile);
        editTextMobile=(EditText)findViewById(R.id.edit_txt_user_profile_mobile);
        txtAccountTitle=(TextView)findViewById(R.id.txt_user_profile_account_title);
        txtAccount=(TextView)findViewById(R.id.txt_user_profile_account);
        btnUpgrade=(Button)findViewById(R.id.btn_user_profile_upgrade);


        imgUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utilities.getInstance().showImageGalleryOrCammera(context);
            }
        });

        //UserName
        txtUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherUserName.showNext();
            }
        });
        editTextUserName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    txtUserName.setText(editTextUserName.getText());
                    viewSwitcherUserName.showPrevious();
                }
            }
        });

        //LastName
        txtLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherLastName.showNext();
            }
        });

        editTextLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    txtLastName.setText(editTextLastName.getText());
                    viewSwitcherLastName.showPrevious();
                }
            }
        });

        //FirstName
        txtFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherFirstName.showNext();
            }
        });
        editTextFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    txtFirstName.setText(editTextFirstName.getText());
                    viewSwitcherFirstName.showPrevious();
                }
            }
        });

        //Email
        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherEmail.showNext();
            }
        });
        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    txtEmail.setText(editTextEmail.getText());
                    viewSwitcherEmail.showPrevious();
                }
            }
        });

        //Mobile
        txtMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherMobile.showNext();
            }
        });
        editTextMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    txtMobile.setText(editTextMobile.getText());
                    viewSwitcherMobile.showPrevious();
                }
            }
        });

        txtAccountTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, StatisticsTotalActivity.class);
                startActivity(intent);
            }
        });

        btnUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.getInstance().showActivationDialog(context);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile_menu, menu);
        if(Utilities.getInstance().isRTL()) {
            menu.getItem(1).setIcon(R.mipmap.back_fa);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        else {
            menu.getItem(1).setVisible(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==android.R.id.home)
            finish();
        if (id==R.id.action_back)
            finish();
        if (id==R.id.action_settings){
            Intent intent=new Intent(context, SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }
}
