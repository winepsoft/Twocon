package winep.ir.mymemory.Presenter.ImageGallery;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 11/23/2016.
 */
public class ImageGalleryViewPagerAdapter extends PagerAdapter {

    private ArrayList<Integer> imageAddressList;
    private Activity activity;
    private View viewLayout;



    public ImageGalleryViewPagerAdapter(ArrayList<Integer> imagesAddresses, Activity activity){
        this.imageAddressList=imagesAddresses;
        this.activity=activity;

    }

    @Override
    public int getCount() {
        return imageAddressList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewLayout = inflater.inflate(R.layout.image_gallery_item, container, false);
        ImageView image=(ImageView)viewLayout.findViewById(R.id.img_image_gallery_item);
        image.setImageResource(R.drawable.app_icon);

        container.addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
