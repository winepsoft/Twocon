package winep.ir.mymemory.DataModel;

/**
 * Created by ShaisteS on 11/15/2016.
 */
public class FlashCart {
    private String title;
    private String downloadSize;
    private String mainPrice;
    private String purchasePrice;
    private String numberOfVisit;
    private String numberOfDownload;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDownloadSize() {
        return downloadSize;
    }

    public void setDownloadSize(String downloadSize) {
        this.downloadSize = downloadSize;
    }

    public String getMainPrice() {
        return mainPrice;
    }

    public void setMainPrice(String mainPrice) {
        this.mainPrice = mainPrice;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getNumberOfVisit() {
        return numberOfVisit;
    }

    public void setNumberOfVisit(String numberOfVisit) {
        this.numberOfVisit = numberOfVisit;
    }

    public String getNumberOfDownload() {
        return numberOfDownload;
    }

    public void setNumberOfDownload(String numberOfDownload) {
        this.numberOfDownload = numberOfDownload;
    }
}
