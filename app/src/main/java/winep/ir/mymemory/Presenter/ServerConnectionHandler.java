package winep.ir.mymemory.Presenter;

import android.content.Context;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.FlashCart;
import winep.ir.mymemory.DataModel.SharedFlashCard;
import winep.ir.mymemory.R;

/**
 * Created by ShaisteS on 11/17/2016.
 */
public class ServerConnectionHandler {

    private Context context;

    public ServerConnectionHandler(Context context){
        this.context=context;

    }

    public String[] createListOfGroupsTitle(){
        String[] groupsTitle=new String[4];
        groupsTitle[0]=context.getString(R.string.filter_all);
        groupsTitle[1]="پزشکی";
        groupsTitle[2]="مهندسی پزشکی";
        groupsTitle[3]="English title";
        return groupsTitle;
    }

    public String[] createListOfSubGroupsOfAGroup(){
        String[] subGroupTitle=new String[3];
        subGroupTitle[0]=context.getString(R.string.filter_all);
        subGroupTitle[1]="فیزیولوژی";
        subGroupTitle[2]="دهان و دندان";
        return subGroupTitle;
    }

    public String[] createListOfCourseOfSubGroup(){
        String[] courseTitle=new String[3];
        courseTitle[0]=context.getString(R.string.filter_all);
        courseTitle[1]="زبان";
        courseTitle[2]="دندان جلو";
        return courseTitle;
    }

    public ArrayList<FlashCart> createFlashCart(){
        ArrayList<FlashCart> flashCarts=new ArrayList<>();
        FlashCart flashCart=new FlashCart();
        flashCart.setTitle("دندانپزشکی پیشرفته");
        flashCart.setDownloadSize("حجم: 1M");
        flashCart.setMainPrice("0");
        flashCart.setPurchasePrice("1000 تومان");
        flashCarts.add(flashCart);

        FlashCart flashCart1=new FlashCart();
        flashCart1.setTitle("نام حیوانات");
        flashCart1.setDownloadSize("حجم: 1M");
        flashCart1.setMainPrice("1000 تومان");
        flashCart1.setPurchasePrice("500 تومان");
        flashCarts.add(flashCart1);

        FlashCart flashCart2=new FlashCart();
        flashCart2.setTitle("world people");
        flashCart2.setDownloadSize("حجم: 1M");
        flashCart2.setMainPrice("1000 تومان");
        flashCart2.setPurchasePrice("500 تومان");
        flashCarts.add(flashCart2);
        return flashCarts;
    }

    public ArrayList<SharedFlashCard> createSharedFlashCards(){
        ArrayList<SharedFlashCard> sharedFlashCards=new ArrayList<>();
        SharedFlashCard sharedFlashCard=new SharedFlashCard();
        sharedFlashCard.setUserName("علی");
        FlashCart flashCart=new FlashCart();
        flashCart.setTitle("دندانپزشکی پیشرفته");
        flashCart.setDownloadSize("حجم: 1M");
        flashCart.setMainPrice("0");
        flashCart.setPurchasePrice("1000 تومان");
        sharedFlashCard.setFlashCard(flashCart);
        sharedFlashCards.add(sharedFlashCard);

        SharedFlashCard sharedFlashCard2=new SharedFlashCard();
        sharedFlashCard2.setUserName("Mohammad");
        FlashCart flashCart2=new FlashCart();
        flashCart2.setTitle("world people");
        flashCart2.setDownloadSize("حجم: 1M");
        flashCart2.setMainPrice("1000 تومان");
        flashCart2.setPurchasePrice("500 تومان");
        sharedFlashCard2.setFlashCard(flashCart2);
        sharedFlashCards.add(sharedFlashCard2);
        return sharedFlashCards;
    }

}
