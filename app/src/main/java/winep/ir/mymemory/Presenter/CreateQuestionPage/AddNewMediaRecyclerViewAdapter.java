package winep.ir.mymemory.Presenter.CreateQuestionPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.MediaItem;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.Utilities;

/**
 * Created by ShaisteS on 11/9/2016.
 */
public class AddNewMediaRecyclerViewAdapter extends RecyclerView.Adapter<AddNewMediaRecyclerViewAdapter.MyViewHolder> {

    private ArrayList<MediaItem> allMedia;
    private Context context;

    public AddNewMediaRecyclerViewAdapter(Context context, ArrayList<MediaItem> medias){
        this.context=context;
        allMedia=medias;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_question_recycler_view_add_new_media_item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        MediaItem mediaItem=new MediaItem();
        mediaItem=allMedia.get(position);
        if(mediaItem.getMediaItemType()==0)
            holder.imgeMediaIcon.setImageResource(R.mipmap.picture_gray);
        else if(mediaItem.getMediaItemType()==1)
            holder.imgeMediaIcon.setImageResource(R.mipmap.voice_gray);
        holder.txtMediaName.setText(mediaItem.getMediaItemName());

        holder.btnMediaDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMedia(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allMedia.size();
    }

    public void addNewMedia(MediaItem newItem){
        allMedia.add(newItem);
        notifyDataSetChanged();
    }

    public void deleteMedia(int position){
        allMedia.remove(position);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imgeMediaIcon;
        private TextView txtMediaName;
        private ImageButton btnMediaDelete;
        public MyViewHolder(View itemView) {
            super(itemView);
            imgeMediaIcon=(ImageButton)itemView.findViewById(R.id.image_media_icon);
            txtMediaName=(TextView)itemView.findViewById(R.id.txt_media_name);
            Utilities.getInstance().setFontTextView(context,txtMediaName);
            btnMediaDelete=(ImageButton)itemView.findViewById(R.id.btn_delete_media);
        }
    }
}
