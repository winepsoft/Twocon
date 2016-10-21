package winep.ir.twocon.Presenter.CreateQuestionPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.rey.material.widget.Spinner;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import winep.ir.twocon.R;
import winep.ir.twocon.Utility.Utilities;

/**
 * Created by ShaisteS on 10/17/2016.
 */
public class CreateQuestionActivity extends AppCompatActivity {

    private Context context;
    private Spinner spinnerGroup;
    private Spinner spinnerSubGroup;
    private Spinner spinnerCourse;
    private TextView txtQuestionNumber;
    private LinearLayout layoutDescription;
    private boolean showDescriptionCartStatus = false;
    private FloatingActionButton btnShowDescriptionCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.create_question_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.create_question_activity_title));

        spinnerGroup = (Spinner) findViewById(R.id.spinner_select_group);
        String[] GroupTitle = {getString(R.string.spinner_select_group), "پزشکی", "مهندسی پزشکی", "English Title"};
        ArrayAdapter<String> a = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GroupTitle);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(a);

        spinnerSubGroup = (Spinner) findViewById(R.id.spinner_select_sub_group);
        String[] subGroupTitle = {getString(R.string.spinner_select_sub_group), "فیزیولوژی", "دهان و دندان"};
        ArrayAdapter<String> b = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subGroupTitle);
        b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubGroup.setAdapter(b);

        spinnerCourse = (Spinner) findViewById(R.id.spinner_select_course);
        String[] courseTitle = {getString(R.string.spinner_select_course), "زبان", "دندان جلو"};
        ArrayAdapter<String> c = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseTitle);
        c.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(c);

        layoutDescription = (LinearLayout) findViewById(R.id.layout_description_create_question);
        layoutDescription.setVisibility(View.GONE);
        btnShowDescriptionCart = (FloatingActionButton) findViewById(R.id.btn_show_description_card_view);
        btnShowDescriptionCart.setSize(FloatingActionButton.SIZE_MINI);
        btnShowDescriptionCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!showDescriptionCartStatus) {
                    Utilities.getInstance().animateFAB(context, false, btnShowDescriptionCart);
                    layoutDescription.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInDown)
                            .duration(1000)
                            .playOn(layoutDescription);
                    showDescriptionCartStatus = !showDescriptionCartStatus;
                } else if (showDescriptionCartStatus) {
                    Utilities.getInstance().animateFAB(context, true, btnShowDescriptionCart);
                    layoutDescription.setVisibility(View.GONE);
                    /*YoYo.with(Techniques.Hinge)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    layoutDescription.setVisibility(View.GONE);

                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .duration(1000)
                            .playOn(cardViewDescription);*/
                    showDescriptionCartStatus = !showDescriptionCartStatus;
                }
            }
        });

        txtQuestionNumber = (TextView) findViewById(R.id.txt_question_number);
        Utilities.getInstance().setFontTextView(context, txtQuestionNumber);
        txtQuestionNumber.setText("24");
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Utilities.getInstance().setSettingLanguage(newBase);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.title_back_menu, menu);
        if(Utilities.getInstance().isRTL())
            menu.getItem(0).setIcon(R.mipmap.back_fa);
        else
            menu.getItem(0).setIcon(R.mipmap.back_en);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id==R.id.action_back)
            finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        finish();
    }
}