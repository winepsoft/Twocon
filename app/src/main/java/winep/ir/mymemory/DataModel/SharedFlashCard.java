package winep.ir.mymemory.DataModel;

/**
 * Created by ShaisteS on 11/19/2016.
 */
public class SharedFlashCard {

    private String userName;
    private FlashCart flashCard;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public FlashCart getFlashCard() {
        return flashCard;
    }

    public void setFlashCard(FlashCart flashCard) {
        this.flashCard = flashCard;
    }
}
