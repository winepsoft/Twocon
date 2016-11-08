package winep.ir.twocon.Presenter.CreateQuestionPage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.rey.material.widget.EditText;
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
    private EditText eTextNewQuestionTitle;
    private EditText eTextNewQuestionAnswer;
    private EditText eTextAddPronunciation;
    private EditText eTextAddSynonym;
    private EditText eTextAddAntonym;
    private EditText eTextAddExample;
    private ExpandableLayout layoutDescription;
    private boolean showDescriptionCartStatus = false;
    private ImageButton btnShowDescriptionCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.create_question_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.create_question_activity_title));

        spinnerGroup = (Spinner) findViewById(R.id.spinner_select_group);
        spinnerSubGroup = (Spinner) findViewById(R.id.spinner_select_sub_group);
        spinnerCourse = (Spinner) findViewById(R.id.spinner_select_course);
        layoutDescription = (ExpandableLayout) findViewById(R.id.layout_description_create_question);
        //layoutDescription.setVisibility(View.GONE);
        btnShowDescriptionCart = (ImageButton) findViewById(R.id.btn_show_description_card_view);
        //btnShowDescriptionCart.setSize(FloatingActionButton.SIZE_MINI);
        txtQuestionNumber = (TextView) findViewById(R.id.txt_question_number);
        Utilities.getInstance().setFontTextView(context, txtQuestionNumber);
        eTextNewQuestionTitle=(EditText)findViewById(R.id.txt_new_question_title);
        Utilities.getInstance().setFontEditTextView(context,eTextNewQuestionTitle);
        eTextNewQuestionAnswer=(EditText)findViewById(R.id.txt_new_question_answer);
        Utilities.getInstance().setFontEditTextView(context,eTextNewQuestionAnswer);
        eTextAddPronunciation=(EditText)findViewById(R.id.txt_add_pronunciation);
        Utilities.getInstance().setFontEditTextView(context,eTextAddPronunciation);
        eTextAddSynonym=(EditText)findViewById(R.id.txt_add_synonym);
        Utilities.getInstance().setFontEditTextView(context,eTextAddSynonym);
        eTextAddAntonym=(EditText)findViewById(R.id.txt_add_antonym);
        Utilities.getInstance().setFontEditTextView(context,eTextAddAntonym);
        eTextAddExample=(EditText)findViewById(R.id.txt_add_example);
        Utilities.getInstance().setFontEditTextView(context,eTextAddExample);
        final ScrollView scrollViewDescriptionPart=(ScrollView)findViewById(R.id.scroll_description_part);

        String[] GroupTitle = {"پزشکی", "مهندسی پزشکی", "English Title"};
        ArrayAdapter<String> a = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GroupTitle);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(a);

        String[] subGroupTitle = {"فیزیولوژی", "دهان و دندان"};
        ArrayAdapter<String> b = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subGroupTitle);
        b.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubGroup.setAdapter(b);

        String[] courseTitle = {"زبان", "دندان جلو"};
        ArrayAdapter<String> c = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseTitle);
        c.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourse.setAdapter(c);

       btnShowDescriptionCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!showDescriptionCartStatus) {
                    layoutDescription.expand();
                   // Utilities.getInstance().animateFAB(context, false, btnShowDescriptionCart);
                    /*layoutDescription.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.SlideInDown)
                            .withListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {

                                    scrollViewDescriptionPart.fullScroll(View.FOCUS_DOWN);

                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .duration(1000)
                            .playOn(layoutDescription);*/
                    showDescriptionCartStatus = !showDescriptionCartStatus;
                } else if (showDescriptionCartStatus) {
                    layoutDescription.collapse();
                    //Utilities.getInstance().animateFAB(context, true, btnShowDescriptionCart);
                    //layoutDescription.setVisibility(View.GONE);
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

        layoutDescription.setListener(new ExpandableLayoutListener() {
            @Override
            public void onAnimationStart() {
                if (!showDescriptionCartStatus) {
                    Utilities.getInstance().animateFAB(context, false, btnShowDescriptionCart);
                }
                else {
                    Utilities.getInstance().animateFAB(context, true, btnShowDescriptionCart);

                }

            }

            @Override
            public void onAnimationEnd() {

            }

            @Override
            public void onPreOpen() {

            }

            @Override
            public void onPreClose() {

            }

            @Override
            public void onOpened() {

            }

            @Override
            public void onClosed() {

            }
        });



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
        if(Utilities.getInstance().isRTL()) {
            menu.getItem(0).setIcon(R.mipmap.back_fa);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        else {
            menu.getItem(0).setVisible(false);
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
    public void onBackPressed() {
        finish();
    }
}