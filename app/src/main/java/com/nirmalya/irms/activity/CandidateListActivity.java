package com.nirmalya.irms.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nirmalya.irms.R;
import com.nirmalya.irms.model.CandidateListModel;
import com.nirmalya.irms.adapter.CandidatelisAdapter;

import java.util.ArrayList;

public class CandidateListActivity extends AppCompatActivity {
    private ArrayList<CandidateListModel> candidateListModels;
    private CandidatelisAdapter candidatelisAdapter;
    private Toolbar toolbar;
    private ImageView imgArrow;
    RecyclerView recycler_view;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_candidatelist);
            toolbar = findViewById(R.id.toolbar);
            imgArrow = findViewById(R.id.imgArrow);
            imgArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

            candidateListModels = new ArrayList<>();
            candidateListModels.add(new CandidateListModel("1", "12345668", "18-10-20", "8:00 AM", "1:00 AM","CEB College, BBSR","1st shift","9765432108","A","p","1234567892"));
            candidateListModels.add(new CandidateListModel("2", "12345668", "18-10-20", "8:00 AM", "1:00 AM","CEB College, BBSR","1st shift","9765432108","A","p","1234567892"));
            candidateListModels.add(new CandidateListModel("3", "12345668", "18-10-20", "8:00 AM", "1:00 AM","CEB College, BBSR","1st shift","9765432108","A","p","1234567892"));
            candidateListModels.add(new CandidateListModel("4", "12345668", "18-10-20", "8:00 AM", "1:00 AM","CEB College, BBSR","1st shift","9765432108","A","p","1234567892"));
            candidateListModels.add(new CandidateListModel("5", "12345668", "18-10-20", "8:00 AM", "1:00 AM","CEB College, BBSR","1st shift","9765432108","A","p","1234567892"));
            candidateListModels.add(new CandidateListModel("6", "12345668", "18-10-20", "8:00 AM", "1:00 AM","CEB College, BBSR","1st shift","9765432108","A","p","1234567892"));
            candidateListModels.add(new CandidateListModel("7", "12345668", "18-10-20", "8:00 AM", "1:00 AM","CEB College, BBSR","1st shift","9765432108","A","p","1234567892"));


            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycler_view.setLayoutManager(mLayoutManager);
            candidatelisAdapter = new CandidatelisAdapter( candidateListModels, CandidateListActivity.this);
            recycler_view.setAdapter(candidatelisAdapter);


        }

}
