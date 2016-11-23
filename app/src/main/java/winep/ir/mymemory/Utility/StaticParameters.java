package winep.ir.mymemory.Utility;

import android.content.Context;

/**
 * Created by ShaisteS on 10/13/2016.
 */
public class StaticParameters {

    private static StaticParameters staticParameters = new StaticParameters();

    public static StaticParameters getInstance() {
        if (staticParameters != null) {
            return staticParameters;
        } else return new StaticParameters();
    }

    public Context mainActivityContext;
    public int screenWidth=0;
    public int screenHeight =0;
    public Context examResultContext;

    public int SELECT_PICTURE_FROM_GALLERY_OR_CAMERA_RESULT_CODE=100;
}
