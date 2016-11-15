package winep.ir.mymemory.Presenter.FlashTab;

import android.content.Context;
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
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.RectangleView;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 11/15/2016.
 */
public class FlashRecyclerViewAdapter extends RecyclerView.Adapter<FlashRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<FlashCart> allFlashCarts;

    public FlashRecyclerViewAdapter(Context context,ArrayList<FlashCart> flashCarts){
        this.context=context;
        allFlashCarts=flashCarts;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.flash_fragment_recycler_view_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FlashCart aFlashCart=allFlashCarts.get(position);
        holder.txtFlashTitle.setText(aFlashCart.getTitle()+"\n"+aFlashCart.getDownloadSize());
        if (!aFlashCart.getMainPrice().equals("0"))
            holder.txtFlashMainPrice.setText(aFlashCart.getMainPrice());
        holder.btnFlashPurchase.setText(aFlashCart.getPurchasePrice());
    }

    @Override
    public int getItemCount() {
        return allFlashCarts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFlashPic;
        private TextView txtFlashTitle;
        private TextView txtFlashMainPrice;
        private Button btnFlashPurchase;
        private RectangleView colorSquare;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgFlashPic=(ImageView)itemView.findViewById(R.id.image_flash);
            txtFlashTitle=(TextView)itemView.findViewById(R.id.txt_flash_title);
            Utilities.getInstance().setFontTextView(context,txtFlashTitle);
            txtFlashMainPrice=(TextView)itemView.findViewById(R.id.txt_flash_main_price);
            Utilities.getInstance().setFontTextView(context,txtFlashMainPrice);
            txtFlashMainPrice.setPaintFlags(txtFlashMainPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            btnFlashPurchase=(Button)itemView.findViewById(R.id.btn_flash_purchase);
            Utilities.getInstance().setFontButtonView(context,btnFlashPurchase);
            colorSquare = (RectangleView) itemView.findViewById(R.id.colorSquare);
        }
    }
}
