package winep.ir.mymemory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
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
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.mymemory.Presenter.FlashTab.Flashfragment;
import winep.ir.mymemory.Presenter.GroupsTab.GroupsFragment;
import winep.ir.mymemory.Presenter.MemoryBankTab.MemoryBankFragment;
import winep.ir.mymemory.Presenter.OnlineDictionaryTab.OnlineDictionaryFragment;
import winep.ir.mymemory.Presenter.PagerAdapter;
import winep.ir.mymemory.Presenter.SettingsPage.SettingsActivity;
import winep.ir.mymemory.Presenter.SharedCardsPage.SharedCardsActivity;
import winep.ir.mymemory.Presenter.StatisticsTotalPage.StatisticsTotalActivity;
import winep.ir.mymemory.Presenter.UserProfileActivity;
import winep.ir.mymemory.Utility.StaticParameters;
import winep.ir.mymemory.Utility.Utilities;

public class MainPage extends AppCompatActivity
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
        displayWindow();
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
        viewPager.setOffscreenPageLimit(4);
    }


    private void displayWindow() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
            StaticParameters.getInstance().screenWidth=size.x;
            StaticParameters.getInstance().screenHeight =size.y;
        }
    }



        private void setupViewPager() {

       if (Utilities.getInstance().isRTL()) {
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
        if(Utilities.getInstance().isRTL()){
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

        /*FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("MainPage", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("MainPage", "nothing on backstack, calling super");
            super.onBackPressed();
        }*/

        /*FragmentManager fm = getSupportFragmentManager();
        for (Fragment frag : fm.getFragments()) {
            if (frag.isVisible()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                childFm.popBackStack();
                return;
            }
        }*/
        //super.onBackPressed();
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
        else if(id==R.id.action_arabic_language){
            Utilities.getInstance().setLocale(context,"ar");
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

        else if(id==R.id.nav_my_activities){
            Intent intent=new Intent(this, StatisticsTotalActivity.class);
            startActivity(intent);
        }
        else if(id==R.id.nav_user_profile){
            Intent intent=new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }

        else if(id==R.id.nav_shared_flashcard){
            Intent intent=new Intent(this, SharedCardsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }
}
