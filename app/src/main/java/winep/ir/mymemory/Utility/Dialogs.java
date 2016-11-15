package winep.ir.mymemory.Utility;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

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
        ImageButton imgButtonTelegram=(ImageButton)dialog.findViewById(R.id.activation_page_telegram);
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

}
