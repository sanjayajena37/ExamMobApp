package com.nirmalya.irms.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.databinding.ActivityCandidatelistBinding;
import com.nirmalya.irms.model.CandidateListModel;
import com.nirmalya.irms.adapter.CandidatelisAdapter;
import com.nirmalya.irms.model.request.SignInRequest;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import java.util.ArrayList;

public class CandidateListActivity extends AppCompatActivity {

    private ActivityCandidatelistBinding binding;
    private ArrayList<CandidateListModel> candidateListModels;
    private CandidatelisAdapter candidatelisAdapter;
    private Toolbar toolbar;
    private ImageView imgArrow, reload;
    private Context context;
    private APIRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_candidatelist);

        context = this;
        repo = new APIRepo();

        reload = findViewById(R.id.reload);
        imgArrow = findViewById(R.id.imgArrow);
        imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCandidateList();
            }
        });

        setData();

        candidateListModels = new ArrayList<>();

        callCandidateList();

    }

    private void setData() {
        binding.distCodeTxt.setText(Osssc.getPrefs().getScannerData().getDistCode());
        binding.centerCode.setText(Osssc.getPrefs().getScannerData().getCenterCode());
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
                        binding.strExamDateTime.setText(candidateResponse.getExamDate());
                        binding.strExamShift.setText(candidateResponse.getExamShift());
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
                    }
                    pd.dismiss();
                });
    }
}
