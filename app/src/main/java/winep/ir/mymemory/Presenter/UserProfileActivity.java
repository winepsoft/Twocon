package winep.ir.mymemory.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 11/14/2016.
 */
public class UserProfileActivity extends AppCompatActivity {

    private Context context;
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
    private ViewSwitcher viewSwitcherAccount;
    private TextView txtAccount;
    private EditText editTextAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);
        context = this;

        setTitle(getString(R.string.user_profile_title));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
        viewSwitcherAccount=(ViewSwitcher)findViewById(R.id.view_switcher_user_profile_account);
        txtAccount=(TextView)findViewById(R.id.txt_user_profile_account);
        editTextAccount=(EditText)findViewById(R.id.edit_txt_user_profile_account);

        txtLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherLastName.showNext();
            }
        });

        txtFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherFirstName.showNext();
            }
        });

        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherEmail.showNext();
            }
        });

        txtMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherMobile.showNext();
            }
        });

        txtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcherAccount.showNext();
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
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }
}
