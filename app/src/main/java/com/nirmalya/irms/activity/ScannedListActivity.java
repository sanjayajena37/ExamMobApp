package com.nirmalya.irms.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nirmalya.irms.R;
import com.nirmalya.irms.adapter.CandidatelisAdapter;
import com.nirmalya.irms.adapter.ScannedlisAdapter;
import com.nirmalya.irms.model.CandidateListModel;

import java.util.ArrayList;

public class ScannedListActivity extends AppCompatActivity {
    private ArrayList<CandidateListModel> candidateListModels;
    private ScannedlisAdapter scannedlisAdapter;
    private Toolbar toolbar;
    private ImageView imgArrow;
    private TextView txtTitle;
    RecyclerView recycler_view;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_scannedlist);
            toolbar = findViewById(R.id.toolbar);
            imgArrow = findViewById(R.id.imgArrow);
            txtTitle = findViewById(R.id.txtTitle);
            txtTitle.setText("Scanned List");
            imgArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

            candidateListModels = new ArrayList<>();
            candidateListModels = new ArrayList<>();
            candidateListModels.add(new CandidateListModel(1, "12345668", "1234567892", "A", "8:00 AM","P","1:00 AM"));
            candidateListModels.add(new CandidateListModel(2, "12345668", "1234567892", "A", "8:00 AM","P","1:00 AM"));
            candidateListModels.add(new CandidateListModel(3, "12345668", "1234567892", "A", "8:00 AM","P","1:00 AM"));
            candidateListModels.add(new CandidateListModel(4, "12345668", "1234567892", "A", "8:00 AM","P","1:00 AM"));
            candidateListModels.add(new CandidateListModel(5, "12345668", "1234567892", "A", "8:00 AM","P","1:00 AM"));
            candidateListModels.add(new CandidateListModel(6, "12345668", "1234567892", "A", "8:00 AM","P","1:00 AM"));
            candidateListModels.add(new CandidateListModel(7, "12345668", "1234567892", "A", "8:00 AM","p","1:00 AM"));

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycler_view.setLayoutManager(mLayoutManager);
            scannedlisAdapter = new ScannedlisAdapter ( candidateListModels, ScannedListActivity.this);
            recycler_view.setAdapter(scannedlisAdapter);


        }

}
