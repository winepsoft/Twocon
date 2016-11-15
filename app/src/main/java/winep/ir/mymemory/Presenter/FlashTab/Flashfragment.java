package winep.ir.mymemory.Presenter.FlashTab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.FlashCart;
import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 10/1/2016.
 */
public class Flashfragment extends Fragment {

    private ArrayList<FlashCart> flashCarts;
    private Context context;
    private RecyclerView recyclerViewFlash;
    private FlashRecyclerViewAdapter adapterFlash;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.flash_fragment, container, false);
        context=getContext();
        flashCarts=new ArrayList<>();
        recyclerViewFlash=(RecyclerView) mainView.findViewById(R.id.recycler_view_flash);
        recyclerViewFlash.setLayoutManager(new LinearLayoutManager(context));
        adapterFlash=new FlashRecyclerViewAdapter(context,createFlashCart());
        recyclerViewFlash.setAdapter(adapterFlash);
        return mainView;
    }

    private ArrayList<FlashCart> createFlashCart(){
        FlashCart flashCart=new FlashCart();
        flashCart.setTitle("دندانپزشکی پیشرفته");
        flashCart.setDownloadSize("حجم: 1M");
        flashCart.setMainPrice("0");
        flashCart.setPurchasePrice("1000 تومان");
        flashCarts.add(flashCart);

        FlashCart flashCart1=new FlashCart();
        flashCart1.setTitle("نام حیوانات");
        flashCart1.setDownloadSize("حجم: 1M");
        flashCart1.setMainPrice("1000 تومان");
        flashCart1.setPurchasePrice("500 تومان");
        flashCarts.add(flashCart1);

        FlashCart flashCart2=new FlashCart();
        flashCart2.setTitle("world people");
        flashCart2.setDownloadSize("حجم: 1M");
        flashCart2.setMainPrice("1000 تومان");
        flashCart2.setPurchasePrice("500 تومان");
        flashCarts.add(flashCart2);
        return flashCarts;
    }
}
