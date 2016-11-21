package winep.ir.mymemory.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import winep.ir.mymemory.Presenter.ExamPage.ExamActivity;
import winep.ir.mymemory.Presenter.ServerConnectionHandler;
import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 11/15/2016.
 */
public class Dialogs {
    private static Dialogs dialog = new Dialogs();

    public static Dialogs getInstance() {
        if (dialog != null) {
            return dialog;
        } else return new Dialogs();
    }

    public void showActivationDialog(final Context context){
        MyApplication myApplication=new MyApplication();
        boolean wrapInScrollView = true;
        MaterialDialog dialog=new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_activation, wrapInScrollView)
                .typeface(myApplication.getRTLFontNameForDialog(),null)
                .build();
        RadioButton radioButtonPurchaseOneYear=(RadioButton)dialog.findViewById(R.id.radio_button_activation_page_purchase_one_year);
        RadioButton radioButtonPurchaseContinual=(RadioButton)dialog.findViewById(R.id.radio_button_activation_page_purchase_continual);
        final TextView txtPurchaseDescription=(TextView)dialog.findViewById(R.id.txt_activation_page_purchase_description);
        final com.rey.material.widget.Button btnPurchase=(com.rey.material.widget.Button)dialog.findViewById(R.id.btn_activation_page_purchase);
        //ImageButton imgButtonTelegram=(ImageButton)dialog.findViewById(R.id.activation_page_telegram);
        radioButtonPurchaseOneYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPurchase.setText(context.getString(R.string.activation_page_purchase_one_year));
                txtPurchaseDescription.setText(context.getString(R.string.activation_page_purchase_one_year_description));
            }
        });

        radioButtonPurchaseContinual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPurchase.setText(context.getString(R.string.activation_page_purchase_continual));
            }
        });
        dialog.show();

    }

    public void showDescriptionFlash(final Context context){
        MyApplication myApplication=new MyApplication();
        boolean wrapInScrollView = true;
        MaterialDialog dialog=new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_flash_description, wrapInScrollView)
                .typeface(myApplication.getRTLFontNameForDialog(),null)
                .build();
        TextView flashDescription=(TextView)dialog.findViewById(R.id.dialog_flash_description_value);
        Utilities.getInstance().setFontTextView(context,flashDescription);
        TextView flashAuthor=(TextView)dialog.findViewById(R.id.dialog_flash_description_author_value);
        TextView flashSize=(TextView)dialog.findViewById(R.id.dialog_flash_description_download_size_value);
        Utilities.getInstance().setFontTextView(context,flashSize);
        TextView flashCreatedTime=(TextView)dialog.findViewById(R.id.dialog_flash_description_create_time_value);
        Utilities.getInstance().setFontTextView(context,flashCreatedTime);
        TextView flashLastModified=(TextView)dialog.findViewById(R.id.dialog_flash_description_last_modified_value);
        Utilities.getInstance().setFontTextView(context,flashLastModified);
        TextView flashCards=(TextView)dialog.findViewById(R.id.dialog_flash_description_cards_value);
        Utilities.getInstance().setFontTextView(context,flashCards);
        TextView flashViewed=(TextView)dialog.findViewById(R.id.dialog_flash_description_viewed_value);
        Utilities.getInstance().setFontTextView(context,flashViewed);
        TextView flashDownload=(TextView)dialog.findViewById(R.id.dialog_flash_description_download_value);
        Utilities.getInstance().setFontTextView(context,flashDownload);
        dialog.show();
    }

    public void showExamDialog(final Context context){
        MyApplication myApplication=new MyApplication();
        boolean wrapInScrollView = true;
        MaterialDialog dialog=new MaterialDialog.Builder(context)
                .customView(R.layout.exam_dialog, wrapInScrollView)
                .positiveText(R.string.save)
                .positiveColor(ContextCompat.getColor(context, R.color.dialog_save_color))
                .negativeText(R.string.cancel)
                .negativeColor(ContextCompat.getColor(context, R.color.dialog_cancel_color))
                .typeface(myApplication.getRTLFontNameForDialog(),null)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        EditText editTextExamNumber=(EditText)dialog.findViewById(R.id.edit_text_exam_number);
                        int examNumber;
                        if (editTextExamNumber.getText().toString().equals(""))
                            examNumber=0;
                        else
                            examNumber =Integer.parseInt(editTextExamNumber.getText().toString());
                        Intent intent=new Intent(context, ExamActivity.class);
                        intent.putExtra("examQuestionNumber",examNumber);
                        context.startActivity(intent);
                        if (context==StaticParameters.getInstance().examResultContext)
                            ((Activity)context).finish();

                    }
                }).build();
        EditText editTextExamNumber=(EditText)dialog.findViewById(R.id.edit_text_exam_number);
        Utilities.getInstance().setFontEditTextView(context,editTextExamNumber);
        TextView txtAllQuestionCourse=(TextView)dialog.findViewById(R.id.text_all_question_course);
        txtAllQuestionCourse.setText("20");
        Utilities.getInstance().setFontTextView(context,txtAllQuestionCourse);
        dialog.show();
    }

    public void showSharedFlashCardsDescriptionDialog(final Context context){
        MyApplication myApplication=new MyApplication();
        boolean wrapInScrollView = true;
        final MaterialDialog dialog=new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_shared_cards, wrapInScrollView)
                .typeface(myApplication.getRTLFontNameForDialog(),null)
                .build();
        TextView SharedCardsDescription=(TextView)dialog.findViewById(R.id.dialog_shared_cards_description_value);
        Utilities.getInstance().setFontTextView(context,SharedCardsDescription);
        TextView SharedCardsAuthor=(TextView)dialog.findViewById(R.id.dialog_shared_cards_author_value);
        TextView SharedCardsSize=(TextView)dialog.findViewById(R.id.dialog_shared_cards_download_size_value);
        Utilities.getInstance().setFontTextView(context,SharedCardsSize);
        TextView SharedCardsCreatedTime=(TextView)dialog.findViewById(R.id.dialog_shared_cards_create_time_value);
        Utilities.getInstance().setFontTextView(context,SharedCardsCreatedTime);
        TextView SharedCardsLastModified=(TextView)dialog.findViewById(R.id.dialog_shared_cards_last_modified_value);
        Utilities.getInstance().setFontTextView(context,SharedCardsLastModified);
        TextView SharedCards=(TextView)dialog.findViewById(R.id.dialog_shared_cards_cards_value);
        Utilities.getInstance().setFontTextView(context,SharedCards);
        TextView SharedCardsViewed=(TextView)dialog.findViewById(R.id.dialog_shared_cards_viewed_value);
        Utilities.getInstance().setFontTextView(context,SharedCardsViewed);
        TextView SharedCardsDownload=(TextView)dialog.findViewById(R.id.dialog_shared_cards_download_value);
        Utilities.getInstance().setFontTextView(context,SharedCardsDownload);
        ImageButton btnSharedCardsDownload=(ImageButton) dialog.findViewById(R.id.btn_dialog_shared_cards_downloads);
        btnSharedCardsDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                showGroupAndSubGroupListDialog(context);
            }
        });
        dialog.show();
    }

    public void showGroupAndSubGroupListDialog(final Context context){
        final ServerConnectionHandler serverConnectionHandler=new ServerConnectionHandler(context);
        new MaterialDialog.Builder(context)
                .title(R.string.new_sub_group_dialog_title)
                .items(serverConnectionHandler.createListOfGroupsTitle())
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        new MaterialDialog.Builder(context)
                                .title(R.string.new_course_dialog_title)
                                .items(serverConnectionHandler.createListOfSubGroupsOfAGroup())
                                .itemsCallback(new MaterialDialog.ListCallback() {
                                    @Override
                                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                    }
                                })
                                .show();
                    }
                })
                .show();
    }

    public void showTicketDialog(final Context context){
        MyApplication myApplication=new MyApplication();
        boolean wrapInScrollView = true;
        final MaterialDialog dialog=new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_ticket, wrapInScrollView)
                .typeface(myApplication.getRTLFontNameForDialog(),null)
                .build();
        dialog.show();

    }
}
