package com.nirmalya.irms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nirmalya.irms.R;
import com.nirmalya.irms.model.CandidateListModel;

import java.util.List;

public class ScannedlisAdapter extends RecyclerView.Adapter <ScannedlisAdapter.MyHolder>{

    private List<CandidateListModel> lists;
    Context context;

    public static class MyHolder extends RecyclerView.ViewHolder {
        private TextView  serialNotext,rollNoumbertext,barcodetext,entryAttendancetext,entryTimetext,hallAttendancetext,hallTimetext  ;

        private View viewLine;

        LinearLayout roomServicesPending;

        public MyHolder(View view) {

            super(view);
            serialNotext = (TextView) view.findViewById(R.id.serialNotext);
            rollNoumbertext = (TextView) view.findViewById(R.id.rollNoumbertext);
            entryAttendancetext = (TextView) view.findViewById(R.id.entryAttendancetext);
            entryTimetext = (TextView) view.findViewById(R.id.entryTimetext);
            hallAttendancetext = (TextView) view.findViewById(R.id.hallAttendancetext);
            hallTimetext = (TextView) view.findViewById(R.id.hallTimetext);
            barcodetext = (TextView) view.findViewById(R.id.barcodetext);

        }
    }


    public ScannedlisAdapter(List<CandidateListModel> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customize_scanned, parent, false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int listPosition) {

        holder.serialNotext.setText(String.valueOf(lists.get(listPosition).getSerialNo()));
        holder.rollNoumbertext.setText(lists.get(listPosition).getRollNoumber());
        holder.barcodetext.setText(lists.get(listPosition).getBarcode());
        holder.entryAttendancetext.setText(lists.get(listPosition).getEntryStatus());
        holder.entryTimetext.setText(lists.get(listPosition).getEntryScanTime());
        holder.hallAttendancetext.setText(lists.get(listPosition).getHallStatus());
        holder.hallTimetext.setText(lists.get(listPosition).getHallScanTime());

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
