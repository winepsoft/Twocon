package winep.ir.mymemory.Presenter.StoreTab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.FlashCart;
import winep.ir.mymemory.Presenter.ImageGallery.ImageGalleryActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Dialogs;
import winep.ir.mymemory.Utility.RectangleView;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 11/15/2016.
 */
class StoreRecyclerViewAdapter extends RecyclerView.Adapter<StoreRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<FlashCart> allFlashCarts;

    public StoreRecyclerViewAdapter(Context context, ArrayList<FlashCart> flashCarts){
        this.context=context;
        allFlashCarts=flashCarts;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.store_fragment_recycler_view_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FlashCart aFlashCart=allFlashCarts.get(position);
        holder.txtFlashTitle.setText(aFlashCart.getTitle());
        if (!aFlashCart.getMainPrice().equals("0")) {
            holder.txtFlashMainPrice.setText(aFlashCart.getMainPrice());
            holder.txtFlashDiscountPercent.setText("50%");
        }
        else {
            holder.txtFlashDiscountPercent.setVisibility(View.GONE);
        }
        holder.btnFlashPurchase.setText(aFlashCart.getPurchasePrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogs.getInstance().showDescriptionFlash(context);
            }
        });
        holder.imgFlashPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ImageGalleryActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allFlashCarts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFlashPic;
        private TextView txtFlashTitle;
        private TextView txtFlashMainPrice;
        private TextView txtFlashDiscountPercent;
        private TextView txtFlashDownloadSize;
        private Button btnFlashPurchase;
        private RectangleView colorSquare;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgFlashPic=(ImageView)itemView.findViewById(R.id.image_flash);
            txtFlashTitle=(TextView)itemView.findViewById(R.id.txt_flash_title);
            Utilities.getInstance().setFontTextView(context,txtFlashTitle);
            txtFlashDownloadSize=(TextView)itemView.findViewById(R.id.txt_flash_download_size);
            Utilities.getInstance().setFontTextView(context,txtFlashDownloadSize);
            txtFlashMainPrice=(TextView)itemView.findViewById(R.id.txt_flash_main_price);
            Utilities.getInstance().setFontTextView(context,txtFlashMainPrice);
            txtFlashDiscountPercent=(TextView)itemView.findViewById(R.id.txt_flash_main_price_off);
            Utilities.getInstance().setFontTextView(context,txtFlashDiscountPercent);
            txtFlashMainPrice.setPaintFlags(txtFlashMainPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            btnFlashPurchase=(Button)itemView.findViewById(R.id.btn_flash_purchase);
            Utilities.getInstance().setFontButtonView(context,btnFlashPurchase);
            colorSquare = (RectangleView) itemView.findViewById(R.id.colorSquare);
        }
    }
}
