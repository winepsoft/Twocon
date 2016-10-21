package winep.ir.twocon;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.Presenter.FlashTab.Flashfragment;
import winep.ir.twocon.Presenter.GroupsTab.GroupsFragment;
import winep.ir.twocon.Presenter.MemoryBankTab.MemoryBankFragment;
import winep.ir.twocon.Presenter.OnlineDictionaryTab.OnlineDictionaryFragment;
import winep.ir.twocon.Presenter.PagerAdapter;
import winep.ir.twocon.Presenter.SettingsPage.SettingsActivity;
import winep.ir.twocon.Utility.StaticParameters;
import winep.ir.twocon.Utility.Utilities;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    private DrawerLayout drawer;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
        StaticParameters.getInstance().mainActivityContext=this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.app_name));

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public static boolean isRTL() {
        return isRTL(Locale.getDefault());
    }

    public static boolean isRTL(Locale locale) {
        final int directionality = Character.getDirectionality(locale.getDisplayName().charAt(0));
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
                directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC;
    }

   private void setupViewPager() {

       if (isRTL()) {
           // The view has LTR layout
           pagerAdapter.addFragment(new Flashfragment(), getString(R.string.tab_four));
           pagerAdapter.addFragment(new MemoryBankFragment(),getString(R.string.tab_three));
           pagerAdapter.addFragment(new OnlineDictionaryFragment(),getString(R.string.tab_two));
           pagerAdapter.addFragment(new GroupsFragment(),getString(R.string.tab_one));
           viewPager.setAdapter(pagerAdapter);
           viewPager.setCurrentItem(pagerAdapter.getCount()-1);

        } else {
           // The view has RTL layout
           pagerAdapter.addFragment(new GroupsFragment(), getString(R.string.tab_one));
           pagerAdapter.addFragment(new OnlineDictionaryFragment(),getString(R.string.tab_two));
           pagerAdapter.addFragment(new MemoryBankFragment(),getString(R.string.tab_three));
           pagerAdapter.addFragment(new Flashfragment(),getString(R.string.tab_four));
           viewPager.setAdapter(pagerAdapter);
           viewPager.setCurrentItem(0);
       }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if(isRTL()){
            if (viewPager.getCurrentItem()<pagerAdapter.getCount()-1)
                viewPager.setCurrentItem(pagerAdapter.getCount()-1);
            else
                super.onBackPressed();
        }
        else {
           if(viewPager.getCurrentItem()>0)
                viewPager.setCurrentItem(0);
           else
               super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_persian_languge) {
            Utilities.getInstance().setLocale(context,"fa");
            return true;
        }
        else if(id==R.id.action_english_languge){
            Utilities.getInstance().setLocale(context,"en");
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting_app) {
            Intent intent=new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }
}
