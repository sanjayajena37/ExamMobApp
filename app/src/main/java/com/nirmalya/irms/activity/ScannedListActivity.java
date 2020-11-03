package com.nirmalya.irms.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.adapter.ScannedlisAdapter;
import com.nirmalya.irms.databinding.ActivityScannedlistBinding;
import com.nirmalya.irms.model.CandidateListModel;
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
    private Context context;
    private APIRepo repo;
    List<CandidateAttendanceList> lists;
    private String DataShowType = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scannedlist);

        context = this;
        repo = new APIRepo();

        if (getIntent() != null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                DataShowType = extras.getString("screen");
            }
        }

        ImageView imgArrow = findViewById(R.id.imgArrow);
        TextView txtTitle = findViewById(R.id.txtTitle);

        if (DataShowType.equalsIgnoreCase("gateScannedList")) {
            txtTitle.setText("Gate Scanned List");
            binding.scannerNameLayout.setVisibility(View.GONE);
            binding.scannerMobileLayout.setVisibility(View.GONE);
        } else if (DataShowType.equalsIgnoreCase("hallScannedList")){
            txtTitle.setText("Hall Scanned List");
            binding.scannerNameLayout.setVisibility(View.GONE);
            binding.scannerMobileLayout.setVisibility(View.GONE);
        } else if (DataShowType.equalsIgnoreCase("scannerGateScannedList")){
            txtTitle.setText("Gate Scanned List");
            binding.scannerNameLayout.setVisibility(View.VISIBLE);
            binding.scannerMobileLayout.setVisibility(View.VISIBLE);
        } else if (DataShowType.equalsIgnoreCase("scannerHallScannedList")){
            txtTitle.setText("Hall Scanned List");
            binding.scannerNameLayout.setVisibility(View.VISIBLE);
            binding.scannerMobileLayout.setVisibility(View.VISIBLE);
        }
        imgArrow.setOnClickListener(v -> onBackPressed());

        ImageView reload = findViewById(R.id.reload);
        reload.setOnClickListener(v -> callCandidateList());

        candidateListModels = new ArrayList<>();

        callCandidateList();

    }

    private void setData() {
        binding.strExamDateTime.setText(Osssc.getPrefs().getExamDateTime());
        binding.txtScannerName.setText(Osssc.getPrefs().getScannerData().getScannerName());
        binding.txtScannerMobile.setText(Osssc.getPrefs().getScannerData().getScannerMobileNo());
    }

    private void callCandidateList() {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        repo.getCandidateAttendanceList(context)
                .observe(this, candidateAttendanceResponse -> {
                    lists = new ArrayList<>();
                    if (candidateAttendanceResponse != null && candidateAttendanceResponse.getSuccess()) {
                        binding.distCodeTxt.setText(candidateAttendanceResponse.getDistrictName());
                        binding.centerCode.setText(candidateAttendanceResponse.getCentreName());
                        binding.shiftTextview.setText(candidateAttendanceResponse.getExamShift());

                        String examName = candidateAttendanceResponse.getTestName() + " (" + candidateAttendanceResponse.getSubjectName() + ")";
                        binding.testName.setText(examName);


                        if (candidateAttendanceResponse.getTotalCandidateGateList() == 0 ||
                                candidateAttendanceResponse.getTotalCandidateHallList() == 0) {
                            MessageUtils.showFailureMessage(context, "No Scan Data Found");
                        } else {
                            MessageUtils.showSuccessMessage(context, candidateAttendanceResponse.getMessage());
                        }

                        if (DataShowType.equalsIgnoreCase("gateScannedList")) {
                            lists.addAll(candidateAttendanceResponse.getCandidateEntryList());
                        } else if (DataShowType.equalsIgnoreCase("hallScannedList")) {
                            lists.addAll(candidateAttendanceResponse.getCandidateHallList());
                        } else if (DataShowType.equalsIgnoreCase("scannerHallScannedList")) {
                            lists.addAll(candidateAttendanceResponse.getScannerScanHallList());
                        } else if (DataShowType.equalsIgnoreCase("scannerGateScannedList")) {
                            lists.addAll(candidateAttendanceResponse.getScannerScanGateList());
                        }

                        candidateListModels.clear();
                        for (int i = 0; i < lists.size(); i++) {
                            candidateListModels.add(new CandidateListModel(i + 1,
                                    lists.get(i).getRollNumber(),
                                    lists.get(i).getBarCode(),
                                    lists.get(i).getEntryStatus(),
                                    lists.get(i).getEntryScanTime(),
                                    lists.get(i).getHallStatus(),
                                    lists.get(i).getHallScanTime()));
                        }

                        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        binding.recyclerView.setLayoutManager(mLayoutManager);
                        scannedlisAdapter = new ScannedlisAdapter(candidateListModels, ScannedListActivity.this);
                        binding.recyclerView.setAdapter(scannedlisAdapter);
                        setData();
                    }
                    pd.dismiss();
                });
    }
}
