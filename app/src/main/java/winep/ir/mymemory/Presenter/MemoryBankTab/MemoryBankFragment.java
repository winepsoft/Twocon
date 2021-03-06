package winep.ir.mymemory.Presenter.MemoryBankTab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.afollestad.dragselectrecyclerview.DragSelectRecyclerView;
import com.afollestad.dragselectrecyclerview.DragSelectRecyclerViewAdapter;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;

import winep.ir.mymemory.DataModel.MemoryBankItem;
import winep.ir.mymemory.Presenter.QuestionPage.EditQuestionActivity;
import winep.ir.mymemory.Presenter.QuestionPage.QuestionActivity;
import winep.ir.mymemory.R;
import winep.ir.mymemory.Utility.MyApplication;

/**
 * Created by ShaisteS on 10/1/2016.
 */
public class MemoryBankFragment extends Fragment implements
        MemoryBankRecyclerViewAdapter.ClickListener, DragSelectRecyclerViewAdapter.SelectionListener{

    private DragSelectRecyclerView recyclerViewMemoryBank;
    private ArrayList<MemoryBankItem> allItems;
    private MemoryBankRecyclerViewAdapter adapter;
    private Context context;
    private View mainToolBar;
    private View multiSelectedItemToolBar;
    private boolean multiSelectedStatus=false;
   // private Spinner spinnerSelectGroupFilter;
    private Button btnSelectGroupFilter;
    private Spinner spinnerSelectPriorityFilter;
    private ImageButton btnQuestionSeen;
    private ImageButton btnAnswerSeen;
    private ImageButton btnQuestionTrue;
    private ImageButton btnQuestionWrong;
    private MyApplication myApplication;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.memory_bank_fragment, container, false);
        context=getContext();
        myApplication =new MyApplication();
        recyclerViewMemoryBank=(DragSelectRecyclerView) mainView.findViewById(R.id.recycler_view_memory_bank);
        recyclerViewMemoryBank.setLayoutManager(new LinearLayoutManager(context));
        //recyclerViewMemoryBank.addItemDecoration(new DividerItemDecorationRecyclerView(10));
        mainToolBar= mainView.findViewById(R.id.memoryBankToolBar);
        multiSelectedItemToolBar= mainView.findViewById(R.id.memoryBankToolBarMultiSelectedItem);
        setMainToolBar();

        //spinnerSelectGroupFilter=(Spinner)mainView.findViewById(R.id.spinner_select_group_filter);
        //spinnerSelectGroupFilter.setAdapter(setSpinnerGroupFilter());
        btnSelectGroupFilter=(Button)mainView.findViewById(R.id.btn_select_group_filter);
        btnSelectGroupFilter.setText(getString(R.string.filter_all));
        btnSelectGroupFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogFilterOnGroup();
            }
        });
        spinnerSelectPriorityFilter=(Spinner)mainView.findViewById(R.id.spinner_select_priority_filter);
        spinnerSelectPriorityFilter.setAdapter(setSpinnerPriorityFilter());
        btnQuestionSeen=(ImageButton)mainView.findViewById(R.id.btn_question_seen);
        btnAnswerSeen=(ImageButton)mainView.findViewById(R.id.btn_question_answer);
        btnQuestionTrue=(ImageButton)mainView.findViewById(R.id.btn_question_true);
        btnQuestionWrong=(ImageButton)mainView.findViewById(R.id.btn_question_wrong);

        allItems=new ArrayList<>();
        for (int i=0; i<10; i++){
            MemoryBankItem item=new MemoryBankItem();
            item.setLessonTitle("lesson");
            item.setSubGroupTitle("SubGroup");
            item.setGroupTitle("Group");
            allItems.add(item);
        }
        adapter=new MemoryBankRecyclerViewAdapter(this,context,allItems);
        recyclerViewMemoryBank.setAdapter(adapter);

        btnQuestionSeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,context.getString(R.string.m_sorted_by_question_seen_message),Toast.LENGTH_SHORT).show();
            }
        });

        btnAnswerSeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,context.getString(R.string.m_sorted_by_answer_seen_message),Toast.LENGTH_SHORT).show();
            }
        });

        btnQuestionTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,context.getString(R.string.m_sorted_by_question_true_message),Toast.LENGTH_SHORT).show();

            }
        });

        btnQuestionWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,context.getString(R.string.m_sorted_by_question_wrong_message),Toast.LENGTH_SHORT).show();

            }
        });

        /*btnCloseMultiSelectedToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeMultiSelectedItemToolBar();
            }
        });

        btnOKMultiSelectedToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String positionSelected="";
                for (Integer index : adapter.getSelectedIndices()) {
                    positionSelected=positionSelected+Integer.toString(index)+"-";
                }

                Toast.makeText(context,positionSelected,Toast.LENGTH_SHORT).show();
                closeMultiSelectedItemToolBar();

            }
        });*/
       //getFragmentManager().beginTransaction().addToBackStack(null).commit();
        return mainView;
    }

    private void showDialogFilterOnGroup(){
        final CharSequence[] filterSelectedTitle = {""};
        new MaterialDialog.Builder(context)
                .title(R.string.group)
                .items(createListOfGroupsTitle())
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        if (text.equals(getString(R.string.filter_all))) {
                            btnSelectGroupFilter.setText(text);
                            dialog.dismiss();
                        }
                        else {
                            filterSelectedTitle[0] =text;
                            new MaterialDialog.Builder(context)
                                    .title(R.string.sub_group)
                                    .items(createListOfSubGroupsOfAGroup())
                                    .itemsCallback(new MaterialDialog.ListCallback() {
                                        @Override
                                        public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                            if (text.equals(getString(R.string.filter_all))) {
                                               btnSelectGroupFilter.setText(filterSelectedTitle[0]);
                                                dialog.dismiss();
                                            }
                                            else {
                                                filterSelectedTitle[0] =text;
                                                new MaterialDialog.Builder(context)
                                                        .title(R.string.course)
                                                        .items(createListOfCourseOfSubGroup())
                                                        .itemsCallback(new MaterialDialog.ListCallback() {
                                                            @Override
                                                            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                                                                if (text.equals(getString(R.string.filter_all)))
                                                                    btnSelectGroupFilter.setText(filterSelectedTitle[0]);
                                                                else
                                                                    btnSelectGroupFilter.setText(text);
                                                            }
                                                        })
                                                        .show();
                                            }

                                        }
                                    })
                                    .show();

                        }
                    }
                })
                .show();
    }

    private String[] createListOfGroupsTitle(){
        String[] groupsTitle=new String[4];
        groupsTitle[0]=getString(R.string.filter_all);
        groupsTitle[1]="پزشکی";
        groupsTitle[2]="مهندسی پزشکی";
        groupsTitle[3]="English title";
        return groupsTitle;
    }

    private String[] createListOfSubGroupsOfAGroup(){
        String[] subGroupTitle=new String[3];
        subGroupTitle[0]=getString(R.string.filter_all);
        subGroupTitle[1]="فیزیولوژی";
        subGroupTitle[2]="دهان و دندان";
        return subGroupTitle;
    }

    private String[] createListOfCourseOfSubGroup(){
        String[] courseTitle=new String[3];
        courseTitle[0]=getString(R.string.filter_all);
        courseTitle[1]="زبان";
        courseTitle[2]="دندان جلو";
        return courseTitle;
    }

    private ArrayAdapter<String> setSpinnerPriorityFilter(){
        String[] GroupTitle = {getString(R.string.filter_all),getString(R.string.m_hard),getString(R.string.m_favourite)};
        ArrayAdapter<String> a =new ArrayAdapter<>(context,android.R.layout.simple_spinner_item, GroupTitle);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return a;
    }

    private void setMainToolBar(){
        mainToolBar.setVisibility(View.VISIBLE);
        multiSelectedItemToolBar.setVisibility(View.GONE);
    }
    private void setMultiSelectedItemToolBar(){
        mainToolBar.setVisibility(View.GONE);
        multiSelectedItemToolBar.setVisibility(View.VISIBLE);
    }
    private void closeMultiSelectedItemToolBar(){
        adapter.clearSelected();
        setMainToolBar();
        multiSelectedStatus=false;
    }

    @Override
    public void onClick(int index) {
        if (multiSelectedStatus ) {
            adapter.toggleSelected(index);
            if (adapter.getSelectedCount()==0) {
                closeMultiSelectedItemToolBar();
            }
        }
        else {
            Intent intent=new Intent(context, QuestionActivity.class);
            context.startActivity(intent);
        }
    }
    @Override
    public void onLongClick(int menuIndex,int index) {
        if (menuIndex==R.id.memory_bank_multi_selected && !multiSelectedStatus){
            recyclerViewMemoryBank.setDragSelectActive(false, index);
            setMultiSelectedItemToolBar();
            multiSelectedStatus=true;
        }
        else if (menuIndex==R.id.memory_bank_edit_question) {
            Intent intent=new Intent();
            Bundle bundle=new Bundle();
            bundle.putParcelable("Question",allItems.get(index).getQuestion());
            intent.putExtras(bundle);
            intent.setClass(context, EditQuestionActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        //getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if (adapter.getSelectedCount() > 0) {
            adapter.clearSelected();
            setMainToolBar();
        }
        else
            onBackPressed();

    }

    @Override
    public void onDragSelectionChanged(int count) {
    }
}
