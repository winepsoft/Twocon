package winep.ir.mymemory.Presenter.ObserverPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShaisteS on 11/19/2016.
 */
public class setShowTicketDialog {
    private static Boolean showTicketDialogStatus;
    private final static List<setShowTicketDialogListener> showTicketDialogListenerList =new ArrayList<>();

    public static Boolean getShowTicketDialogStatus() {
        return showTicketDialogStatus;
    }

    public static void setShowTicketDialogStatus(Boolean showTicketDialogStatus) {
        setShowTicketDialog.showTicketDialogStatus = showTicketDialogStatus;
        for (setShowTicketDialogListener showTicketDialogListener : showTicketDialogListenerList) {
            showTicketDialogListener.showTicketDialog();
        }
    }

    public static List<setShowTicketDialogListener> getShowTicketDialogListenerList() {
        return showTicketDialogListenerList;
    }

    public static void setShowTicketDialogListener(setShowTicketDialogListener click){
        showTicketDialogListenerList.add(click);
    }
}
