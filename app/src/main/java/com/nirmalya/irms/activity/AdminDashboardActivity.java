package com.nirmalya.irms.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.ActivityAdminDashboardBinding;
import com.nirmalya.irms.model.DistList;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AdminDashboardActivity extends AppCompatActivity {

    private ActivityAdminDashboardBinding binding;
    private Context context;
    private APIRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_dashboard);

        init();
        getDist();
    }

    private void init() {

        context = this;
        repo = new APIRepo();

        binding.edtChooseDist.setOnClickListener(v -> binding.spnChooseDist.performClick());

        binding.edtChooseCenter.setOnClickListener(v -> binding.spnChooseCenter.performClick());
    }

    private void getDist() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.getDistList(context)
                .observe(this, distResponse -> {
                    if (distResponse != null && distResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, distResponse.getMessage());

                        ArrayList<DistList> distArrayList = new ArrayList<>();
                        distArrayList.add(new DistList(
                                "Select Dist",
                                ""));

                        for (int i = 0; i < distResponse.getDistList().size(); i++) {
                            distArrayList.add(new DistList(
                                    distResponse.getDistList().get(i).getDistName(),
                                    distResponse.getDistList().get(i).getDistID()));
                        }
                        Osssc.getPrefs().setDistList(distArrayList);
                        setDataToSpinner();
                    }

                    pd.dismiss();
                });
    }

    private void setDataToSpinner() {
        binding.spnChooseDist.setAdapter(new DistListArrayAdapter(
                AdminDashboardActivity.this,
                R.layout.row_spinner_item_name,
                R.id.tv_item,
                Osssc.getPrefs().getDistList()));

        final int[] selectionCurrent = {binding.spnChooseDist.getSelectedItemPosition()};
        binding.spnChooseDist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (selectionCurrent[0] != position) {
                    binding.edtChooseDist.setText(Osssc.getPrefs().getDistList().get(position).getDistName());
                    //httpGetCities(LovCash.getPrefs().getProvinceList().get(position).getProvinceID());
                }
                selectionCurrent[0] = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public static class DistListArrayAdapter extends ArrayAdapter<DistList> {

        private DistListArrayAdapter(Context context,
                                         int row_dist_name, int tv_dist, ArrayList<DistList> items) {
            super(context, row_dist_name, tv_dist, items);
        }

        @Override
        public View getDropDownView(int position, View convertView, @NotNull ViewGroup parent) {
            return rowView(convertView, position);
        }

        @SuppressLint("InflateParams")
        private View rowView(View convertView, int position) {
            DistList rowItem = getItem(position);

            ViewHolder holder;
            View rowview = convertView;
            if (rowview == null) {
                holder = new ViewHolder();
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                assert layoutInflater != null;
                rowview = layoutInflater.inflate(R.layout.row_spinner_item_name, null, false);
                holder.tv_dist = rowview.findViewById(R.id.tv_item);
                rowview.setTag(holder);
            } else {
                holder = (ViewHolder) rowview.getTag();
            }

            if (position == 0) {
                holder.tv_dist.setBackgroundColor(getContext().getResources().getColor(R.color.aubergine));
                holder.tv_dist.setTextColor(getContext().getResources().getColor(R.color.white_opacity_50));
            } else {
                holder.tv_dist.setBackgroundColor(getContext().getResources().getColor(R.color.edit_text_activated));
                holder.tv_dist.setTextColor(getContext().getResources().getColor(R.color.white));
            }
            assert rowItem != null;
            holder.tv_dist.setText(rowItem.getDistName());

            return rowview;
        }

        @NotNull
        @Override
        public View getView(int position, View convertView, @NotNull ViewGroup parent) {
            return rowView(convertView, position);
        }

        private static class ViewHolder {
            TextView tv_dist;
        }
    }
}