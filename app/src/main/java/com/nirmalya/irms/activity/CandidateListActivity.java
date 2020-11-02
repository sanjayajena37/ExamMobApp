package com.nirmalya.irms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.ActivityCandidatelistBinding;
import com.nirmalya.irms.model.CandidateListModel;
import com.nirmalya.irms.adapter.CandidatelisAdapter;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import java.util.ArrayList;

public class CandidateListActivity extends AppCompatActivity {

    private ActivityCandidatelistBinding binding;
    private ArrayList<CandidateListModel> candidateListModels;
    private CandidatelisAdapter candidatelisAdapter;
    private Context context;
    private APIRepo repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_candidatelist);

        context = this;
        repo = new APIRepo();

        ImageView reload = findViewById(R.id.reload);
        ImageView imgArrow = findViewById(R.id.imgArrow);
        imgArrow.setOnClickListener(v -> onBackPressed());

        reload.setOnClickListener(v -> callCandidateList());

        candidateListModels = new ArrayList<>();

        callCandidateList();

    }

    private void setData() {
        binding.custNumTxt.setText(Osssc.getPrefs().getScannerData().getScannerName());
        binding.custMobileNo.setText(Osssc.getPrefs().getScannerData().getScannerMobileNo());
    }

    private void callCandidateList() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.getCandidateAttendanceList(context)
                .observe(this, candidateAttendanceResponse -> {
                    if (candidateAttendanceResponse != null && candidateAttendanceResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, candidateAttendanceResponse.getMessage());
                        String examName = candidateAttendanceResponse.getTestName() + " (" + candidateAttendanceResponse.getSubjectName() + ")";

                        binding.strExamDateTime.setText(candidateAttendanceResponse.getExamDate());
                        binding.strExamShift.setText(candidateAttendanceResponse.getExamShift());
                        binding.distCodeTxt.setText(candidateAttendanceResponse.getDistrictName());
                        binding.centerCode.setText(candidateAttendanceResponse.getCentreName());
                        binding.strTestName.setText(examName);
                        int j = 1;
                        candidateListModels.clear();
                        for(int i = 0; i < candidateAttendanceResponse.getCandidateList().size(); i++) {

                            candidateListModels.add(new CandidateListModel(j,
                                    candidateAttendanceResponse.getCandidateList().get(i).getRollNumber(),
                                    candidateAttendanceResponse.getCandidateList().get(i).getBarCode(),
                                    candidateAttendanceResponse.getCandidateList().get(i).getEntryStatus(),
                                    candidateAttendanceResponse.getCandidateList().get(i).getEntryScanTime(),
                                    candidateAttendanceResponse.getCandidateList().get(i).getHallStatus(),
                                    candidateAttendanceResponse.getCandidateList().get(i).getHallScanTime()));
                            j++;
                        }

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        binding.recyclerView.setLayoutManager(mLayoutManager);
                        candidatelisAdapter = new CandidatelisAdapter(candidateListModels, CandidateListActivity.this,pd);
                        binding.recyclerView.setAdapter(candidatelisAdapter);
                        binding.recyclerView.setNestedScrollingEnabled(false);
                        setData();
                    } else {
                        pd.dismiss();
                    }
                });
    }
}
