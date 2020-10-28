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

        setData();

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

        repo.getCandidateList(context)
                .observe(this, candidateResponse -> {
                    if (candidateResponse != null && candidateResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, candidateResponse.getMessage());

                        String[] dateTime = candidateResponse.getExamDate().split(",");

                        String date = dateTime[0];
                        String time = dateTime[1];

                        String statusDateTime = date + " (" + time + " )";
                        String examName = candidateResponse.getTestName() + " (" + candidateResponse.getSubjectName() + ")";

                        binding.strExamDateTime.setText(statusDateTime);
                        binding.strExamShift.setText(candidateResponse.getExamShift());
                        binding.distCodeTxt.setText(candidateResponse.getDistrictName());
                        binding.centerCode.setText(candidateResponse.getCentreName());
                        binding.strTestName.setText(examName);
                        int j = 1;
                        candidateListModels.clear();
                        for(int i = 0; i < candidateResponse.getCandidateList().size(); i++) {

                            candidateListModels.add(new CandidateListModel(j,
                                    candidateResponse.getCandidateList().get(i).getRollNumber(),
                                    candidateResponse.getCandidateList().get(i).getBarCode(),
                                    "", "", "", ""));
                            j++;
                        }

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        binding.recyclerView.setLayoutManager(mLayoutManager);
                        candidatelisAdapter = new CandidatelisAdapter(candidateListModels, CandidateListActivity.this);
                        binding.recyclerView.setAdapter(candidatelisAdapter);
                        binding.recyclerView.setNestedScrollingEnabled(false);
                    }
                    pd.dismiss();
                });
    }
}
