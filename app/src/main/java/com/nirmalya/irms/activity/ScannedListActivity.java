package com.nirmalya.irms.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.adapter.CandidatelisAdapter;
import com.nirmalya.irms.adapter.ScannedlisAdapter;
import com.nirmalya.irms.databinding.ActivityScannedlistBinding;
import com.nirmalya.irms.model.CandidateListModel;
import com.nirmalya.irms.model.request.CandidateRequestData;
import com.nirmalya.irms.model.response.CandidateAttendanceList;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import java.util.ArrayList;
import java.util.List;

public class ScannedListActivity extends AppCompatActivity {
    private ActivityScannedlistBinding binding;
    private ArrayList<CandidateListModel> candidateListModels;
    private ScannedlisAdapter scannedlisAdapter;
    private ImageView imgArrow;
    private TextView txtTitle;
    private Context context;
    private APIRepo repo;
    List<CandidateAttendanceList> lists;

        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = DataBindingUtil.setContentView(this, R.layout.activity_scannedlist);

            context = this;
            repo = new APIRepo();

            imgArrow = findViewById(R.id.imgArrow);
            txtTitle = findViewById(R.id.txtTitle);
            txtTitle.setText("Scanned List");
            imgArrow.setOnClickListener(v -> onBackPressed());

            candidateListModels = new ArrayList<>();

            setData();
            callCandidateList();

        }

    private void setData() {
        binding.strExamDateTime.setText(Osssc.getPrefs().getExamDateTime());
    }

    private void callCandidateList() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.getCandidateAttendanceList(context)
                .observe(this, candidateAttendanceResponse -> {
                   lists = new ArrayList<>();
                    if (candidateAttendanceResponse != null && candidateAttendanceResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, candidateAttendanceResponse.getMessage());
                        binding.distCodeTxt.setText(candidateAttendanceResponse.getDistrictName());
                        binding.centerCode.setText(candidateAttendanceResponse.getCentreName());
                        int j = 1;
                        candidateListModels.clear();
                        for(int i = 0; i < candidateAttendanceResponse.getCandidateEntryList().size(); i++) {
                            candidateListModels.add(new CandidateListModel(j,
                                    candidateAttendanceResponse.getCandidateEntryList().get(i).getRollNumber(),
                                    candidateAttendanceResponse.getCandidateEntryList().get(i).getBarCode(),
                                    candidateAttendanceResponse.getCandidateEntryList().get(i).getEntryStatus(),
                                    candidateAttendanceResponse.getCandidateEntryList().get(i).getEntryScanTime(),
                                    candidateAttendanceResponse.getCandidateEntryList().get(i).getHallStatus(),
                                    candidateAttendanceResponse.getCandidateEntryList().get(i).getHallScanTime()));
                            j++;
                        }

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        binding.recyclerView.setLayoutManager(mLayoutManager);
                        scannedlisAdapter = new ScannedlisAdapter ( candidateListModels, ScannedListActivity.this);
                        binding.recyclerView.setAdapter(scannedlisAdapter);
                    }
                    pd.dismiss();
                });
    }
}
