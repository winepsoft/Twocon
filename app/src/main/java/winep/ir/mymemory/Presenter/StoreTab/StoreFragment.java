package winep.ir.mymemory.Presenter.StoreTab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.Button;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.FlashCart;
import winep.ir.mymemory.Presenter.ServerConnectionHandler;
import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 10/1/2016.
 */
public class StoreFragment extends Fragment {

    private ArrayList<FlashCart> flashCarts;
    private Context context;
    private RecyclerView recyclerViewFlash;
    private StoreRecyclerViewAdapter adapterFlash;
    private ServerConnectionHandler serverConnectionHandler;
    private Button btnSelectGroupFilter;
    private TextView txtFlashGroupFilterValue;
    private Button btnSelectFilter;
    private TextView txtFilterValue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.store_fragment, container, false);
        context=getContext();
        serverConnectionHandler=new ServerConnectionHandler(context);
        flashCarts=new ArrayList<>();
        recyclerViewFlash=(RecyclerView) mainView.findViewById(R.id.recycler_view_flash);
        recyclerViewFlash.setLayoutManager(new LinearLayoutManager(context));
        adapterFlash=new StoreRecyclerViewAdapter(context,serverConnectionHandler.createFlashCart());
        recyclerViewFlash.setAdapter(adapterFlash);

        //Group Filter
        btnSelectGroupFilter=(Button)mainView.findViewById(R.id.btn_flash_tab_select_group);
        txtFlashGroupFilterValue =(TextView)mainView.findViewById(R.id.txt_flash_tab_select_group);
        btnSelectGroupFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogFilterOnGroup();
            }
        });

        //Filter
        btnSelectFilter =(Button)mainView.findViewById(R.id.btn_flash_tab_filter);
        txtFilterValue=(TextView)mainView.findViewById(R.id.txt_flash_tab_filter);
        btnSelectFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogFilter();
            }
        });

        return mainView;
    }


    private void showDialogFilterOnGroup(){
        final CharSequence[] filterSelectedTitle = {""};
        new MaterialDialog.Builder(context)
                .title(R.string.group)
                .items(serverConnectionHandler.createListOfGroupsTitle())
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        if (text.equals(getString(R.string.filter_all))) {
                            txtFlashGroupFilterValue.setText(text);
                            dialog.dismiss();
                        }
                        else {
                            filterSelectedTitle[0] =text;
                            new MaterialDialog.Builder(context)
                                    .title(R.string.sub_group)
                                    .items(serverConnectionHandler.createListOfSubGroupsOfAGroup())
                                    .itemsCallback(new MaterialDialog.ListCallback() {
                                        @Override
                                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            if (text.equals(getString(R.string.filter_all))) {
                                                txtFlashGroupFilterValue.setText(filterSelectedTitle[0]);
                                                dialog.dismiss();
                                            }
                                            else {
                                                filterSelectedTitle[0] =text;
                                                new MaterialDialog.Builder(context)
                                                        .title(R.string.course)
                                                        .items(serverConnectionHandler.createListOfCourseOfSubGroup())
                                                        .itemsCallback(new MaterialDialog.ListCallback() {
                                                            @Override
                                                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                                                if (text.equals(getString(R.string.filter_all)))
                                                                    txtFlashGroupFilterValue.setText(filterSelectedTitle[0]);
                                                                else
                                                                    txtFlashGroupFilterValue.setText(text);
                                                            }
                                                        })
                                                        .show();
                                            }

                                        }
                                    })
                                    .show();

                        }
                    }
                })
                .show();
    }
    private void showDialogFilter() {
        new MaterialDialog.Builder(context)
                .title(R.string.flash_cart_tab_btn_filter)
                .items(R.array.flash_cart_filter_item)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        txtFilterValue.setText(text);
                    }
                })
                .show();
    }

}
