package winep.ir.twocon.Utility;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ShaisteS on 5/25/2016.
 */
public class DividerItemDecorationRecyclerView extends RecyclerView.ItemDecoration {

    private final int mVerticalSpaceHeight;

    public DividerItemDecorationRecyclerView(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }
}