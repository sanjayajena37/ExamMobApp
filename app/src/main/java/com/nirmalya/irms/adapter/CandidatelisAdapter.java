package com.nirmalya.irms.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
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

    //private final ClickListner clickListner;
    private List<CandidateListModel> lists;
    Context context;

    public static class MyHolder extends RecyclerView.ViewHolder {
        private TextView candidateIDtext,entryScanTime,rollNoumbertext,examDatetext,hallScanTime,examShifttext,
                candidateMobileNotext,entryStatustext,hallStatustext ,barcodetext  ;

        private View viewLine;

        LinearLayout roomServicesPending;

        public MyHolder(View view) {

            super(view);
            candidateIDtext = (TextView) view.findViewById(R.id.candidateIDtext);
            rollNoumbertext = (TextView) view.findViewById(R.id.rollNoumbertext);
            examDatetext = (TextView) view.findViewById(R.id.examDatetext);
            entryScanTime = (TextView) view.findViewById(R.id.entryScanTime);
            hallScanTime = (TextView) view.findViewById(R.id.hallScanTime);
            examShifttext = (TextView) view.findViewById(R.id.examShifttext);
            candidateMobileNotext = (TextView) view.findViewById(R.id.candidateMobileNotext);
            entryStatustext = (TextView) view.findViewById(R.id.entryStatustext);
            hallStatustext = (TextView) view.findViewById(R.id.hallStatustext);
            barcodetext = (TextView) view.findViewById(R.id.barcodetext);

        }
    }

/*    public interface ClickListner
    {
        void click();
    }*/
ProgressDialog dialog;

    public CandidatelisAdapter(List<CandidateListModel> lists, Context context/*,ClickListner clickListner*/, ProgressDialog dialog) {
        this.lists = lists;
        this.context = context;
        this.dialog = dialog;
      //  this.clickListner=clickListner;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customize_candidate, parent, false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int listPosition) {

        holder.candidateIDtext.setText(String.valueOf(lists.get(listPosition).getSerialNo()));
        holder.rollNoumbertext.setText(lists.get(listPosition).getRollNoumber());
        holder.barcodetext.setText(lists.get(listPosition).getBarcode());
        holder.entryStatustext.setText(lists.get(listPosition).getEntryStatus());
        holder.entryScanTime.setText(lists.get(listPosition).getEntryScanTime());
        holder.hallStatustext.setText(lists.get(listPosition).getHallStatus());
        holder.hallScanTime.setText(lists.get(listPosition).getHallScanTime());
        if(lists.size()==(listPosition+1)){dialog.dismiss();}
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
