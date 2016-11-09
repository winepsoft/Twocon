package winep.ir.twocon.DataModel;

/**
 * Created by ShaisteS on 11/9/2016.
 */
public class MediaItem {
    private String mediaItemName;
    private int mediaItemType; //0=picture 1=voice

    public String getMediaItemName() {
        return mediaItemName;
    }

    public void setMediaItemName(String mediaItemName) {
        this.mediaItemName = mediaItemName;
    }

    public int getMediaItemType() {
        return mediaItemType;
    }

    public void setMediaItemType(int mediaItemType) {
        this.mediaItemType = mediaItemType;
    }
}
