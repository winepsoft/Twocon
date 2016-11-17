package winep.ir.mymemory.Presenter;

import android.content.Context;

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
}
