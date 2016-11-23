package winep.ir.mymemory.Presenter.ImageGallery;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 11/23/2016.
 */
public class ImageGalleryActivity extends AppCompatActivity {

    private ViewPager viewPagerImageGallery;
    private LinearLayout layoutAddImageGallery;
    private Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery_activity);
        context = this;

        viewPagerImageGallery = (ViewPager) findViewById(R.id.image_gallery_view_pager);
        layoutAddImageGallery = (LinearLayout) findViewById(R.id.linear_add_image);

        ArrayList<Integer> imageAddresses=new ArrayList<>();
        imageAddresses.add(1);
        imageAddresses.add(2);
        imageAddresses.add(3);
        imageAddresses.add(4);
        imageAddresses.add(5);
        viewPagerImageGallery.setAdapter(new ImageGalleryViewPagerAdapter(imageAddresses,this));
        for (int i = 0; i < 5; i++) {
            final ImageView imageView = new ImageView(context);
            final int position=i;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            imageView.setLayoutParams(layoutParams);
            imageView.setId(i - 1);
            imageView.setPadding(1, 1, 1, 0);
            imageView.setImageResource(R.drawable.app_icon);
            layoutAddImageGallery.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPagerImageGallery.setCurrentItem(position);
                }
            });
        }
    }
}
