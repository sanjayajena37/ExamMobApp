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
            imgArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            candidateListModels = new ArrayList<>();

            setData();

        }

    private void setData() {
        binding.distCodeTxt.setText(Osssc.getPrefs().getScannerData().getDistCode());
        binding.centerCode.setText(Osssc.getPrefs().getScannerData().getCenterCode());
    }

    private void callCandidateList() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.getCandidateAttendanceList(context)
                .observe(this, candidateAttendanceResponse -> {
                   lists = new ArrayList<>();
                    if (candidateAttendanceResponse != null && candidateAttendanceResponse.getSuccess()) {
                        MessageUtils.showSuccessMessage(context, candidateAttendanceResponse.getMessage());
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
                        scannedlisAdapter = new ScannedlisAdapter ( candidateListModels, ScannedListActivity.this);
                        binding.recyclerView.setAdapter(scannedlisAdapter);
                    } else {

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
                            lists.add(candidateAttendanceResponse.getCandidateList().get(i));
                        }
                        Osssc.getPrefs().setAttendanceListData(lists);
                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        binding.recyclerView.setLayoutManager(mLayoutManager);
                        scannedlisAdapter = new ScannedlisAdapter ( candidateListModels, ScannedListActivity.this);
                        binding.recyclerView.setAdapter(scannedlisAdapter);
                    }
                    pd.dismiss();
                });
    }
}
