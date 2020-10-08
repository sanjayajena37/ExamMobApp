package com.nirmalya.irms.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nirmalya.irms.R;
import com.nirmalya.irms.model.CandidateListModel;

import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class CandidatelisAdapter extends RecyclerView.Adapter <CandidatelisAdapter.MyHolder>{

    private List<CandidateListModel> lists;
    Context context;

    public static class MyHolder extends RecyclerView.ViewHolder {
        private TextView candidateIDtext,examStartTimetext,rollNoumbertext,examDatetext,examEndTimetext,examShifttext,
                candidateMobileNotext,entryStatustext,hallStatustext ,barcodetext  ;

        private View viewLine;

        LinearLayout roomServicesPending;

        public MyHolder(View view) {

            super(view);
            candidateIDtext = (TextView) view.findViewById(R.id.candidateIDtext);

            rollNoumbertext = (TextView) view.findViewById(R.id.rollNoumbertext);
            examDatetext = (TextView) view.findViewById(R.id.examDatetext);
            examStartTimetext = (TextView) view.findViewById(R.id.examStartTimetext);
            examEndTimetext = (TextView) view.findViewById(R.id.examEndTimetext);
            examShifttext = (TextView) view.findViewById(R.id.examShifttext);
            candidateMobileNotext = (TextView) view.findViewById(R.id.candidateMobileNotext);
            entryStatustext = (TextView) view.findViewById(R.id.entryStatustext);
            hallStatustext = (TextView) view.findViewById(R.id.hallStatustext);
            barcodetext = (TextView) view.findViewById(R.id.barcodetext);

        }
    }


    public CandidatelisAdapter(List<CandidateListModel> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customize_candidate, parent, false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int listPosition) {

        holder.candidateIDtext.setText(lists.get(listPosition).getCandidateID());
        holder.rollNoumbertext.setText(lists.get(listPosition).getRollNoumber());
        holder.examDatetext.setText(lists.get(listPosition).getExamDate());
        holder.examStartTimetext.setText(lists.get(listPosition).getExamStartTime());
        holder.examEndTimetext.setText(lists.get(listPosition).getExamEndTime());
        holder.examShifttext.setText(lists.get(listPosition).getExamShift());
        holder.candidateMobileNotext.setText(lists.get(listPosition).getCandidateMobileNo());
        holder.entryStatustext.setText(lists.get(listPosition).getEntryStatus());
        holder.hallStatustext.setText(lists.get(listPosition).getHallStatus());
        holder.barcodetext.setText(lists.get(listPosition).getBarcode());
    }


    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                /*Intent intent = new Intent(context, RestaurantDetailsActivity.class);
                intent.putExtra("resID", lists.get(position).getRestID());
                intent.putExtra("ActivityFrom", "AllRestaurantListActivity");
                Activity activity = (Activity) context;
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*/
                // activity.finish();
            }

        };
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

}
