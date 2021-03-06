package com.nirmalya.irms.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LifecycleOwner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.R;
import com.nirmalya.irms.RoomDB.AppDatabase;
import com.nirmalya.irms.RoomDB.AppExecutors;
import com.nirmalya.irms.RoomDB.StudentModel;
import com.nirmalya.irms.databinding.ActivityDashbordBinding;
import com.nirmalya.irms.model.request.CandidateRequestData;
import com.nirmalya.irms.model.request.ScanDataRequest;
import com.nirmalya.irms.network.ApiConfig;
import com.nirmalya.irms.repository.APIRepo;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashbordActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Array List
    private List<StudentModel> studentLists;
    private Context context;
    private ActivityDashbordBinding binding;
    private ImageView navigationimg;
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView navigationView;
    ActionBarDrawerToggle mtoogle;
    List<StudentModel> lists;
    private int totalLength = 0;
    private boolean isEntryStatus = true, isHallStatus = false;
    AppDatabase mDb;
    long count = 0;
    List<StudentModel> updateList = new ArrayList<>();
    private APIRepo repo;
    private ProgressDialog dialog;
    private TextView centerCode;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashbord);

        context = getApplicationContext();
        repo = new APIRepo();
        dialog = new ProgressDialog(getApplicationContext());

        mDb = AppDatabase.getInstance(getApplicationContext());
        toolbar = findViewById(R.id.toolbarDashboard);
        navigationimg = findViewById(R.id.navigationimg);
        drawer_layout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        drawer_layout = findViewById(R.id.drawer_layout);
        mtoogle = new ActionBarDrawerToggle(this, drawer_layout, R.string.Open, R.string.Close);
        mtoogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationimg.setOnClickListener(view -> {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START);
            } else {
                drawer_layout.openDrawer(GravityCompat.START);

            }
        });

        View headerview = navigationView.getHeaderView(0);
        centerCode = headerview.findViewById(R.id.centerCode);

        TextView scannerName = headerview.findViewById(R.id.scannerName);
        scannerName.setText(Osssc.getPrefs().getScannerData().getScannerName());

        TextView scannerMobile = headerview.findViewById(R.id.scannerMobile);
        scannerMobile.setText(Osssc.getPrefs().getScannerData().getScannerMobileNo()
        );

        //ArrayList
        studentLists = new ArrayList<StudentModel>();

        if (Osssc.getPrefs().getSelectEntryStatus()) {
            binding.rbScanGate.setChecked(true);
            setItemsVisibility(View.GONE);
        } else {
            binding.rbScanHall.setChecked(true);
            setItemsVisibility(View.VISIBLE);
        }

        if (Osssc.getPrefs().getGateScanCount().equalsIgnoreCase("")) {
            binding.nowScanGateCount.setText("00");
        } else {
            binding.nowScanGateCount.setText(Osssc.getPrefs().getGateScanCount());
            binding.nowScanGateCount.setVisibility(View.VISIBLE);
            binding.nowScanHallCount.setVisibility(View.GONE);
        }

        if (Osssc.getPrefs().getHallScanCount().equalsIgnoreCase("")) {
            binding.nowScanHallCount.setText("00");
        } else {
            binding.nowScanHallCount.setText(Osssc.getPrefs().getHallScanCount());
            binding.nowScanGateCount.setVisibility(View.GONE);
            binding.nowScanHallCount.setVisibility(View.VISIBLE);
        }

        binding.radAbsent.setChecked(true);

        binding.radPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.radAbsent.setChecked(true);
            }
        });

        binding.scanner.setOnClickListener(view -> {
            if (binding.rbScanGate.isChecked()) {
                Osssc.getPrefs().setSelectEntryStatus(true);
                requestCameraPermission();
            } else {
                Osssc.getPrefs().setSelectEntryStatus(false);
                if (binding.radPresent.isChecked()) {
                    Osssc.getPrefs().setSelectHallAttendance(true);
                    requestCameraPermission();
                } else {
                    Osssc.getPrefs().setSelectHallAttendance(false);
                    requestCameraPermission();
                }
            }
        });

        binding.totalCandidateCard.setOnClickListener(v -> {
            Intent intent = new Intent(DashbordActivity.this, CandidateListActivity.class);
            startActivity(intent);
        });

        binding.scanInGateCard.setOnClickListener(v -> {
            Intent intent = new Intent(DashbordActivity.this, ScannedListActivity.class);
            intent.putExtra("screen", "gateScannedList");
            startActivity(intent);
        });

        binding.scanInHallCard.setOnClickListener(v -> {
            Intent intent = new Intent(DashbordActivity.this, ScannedListActivity.class);
            intent.putExtra("screen", "hallScannedList");
            startActivity(intent);
        });

        binding.scannerEntryLayout.setOnClickListener(v -> {
            Intent intent = new Intent(DashbordActivity.this, ScannedListActivity.class);
            intent.putExtra("screen", "scannerGateScannedList");
            startActivity(intent);
        });

        binding.scannerHallLayout.setOnClickListener(v -> {
            Intent intent = new Intent(DashbordActivity.this, ScannedListActivity.class);
            intent.putExtra("screen", "scannerHallScannedList");
            startActivity(intent);
        });

        binding.rbScanHall.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setItemsVisibility(View.VISIBLE);
            } else {
                setItemsVisibility(View.GONE);
            }
        });

        if (totalLength == 0) {
            callStudentListAPI();
        }

        binding.sendScanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLocalData();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (Osssc.getPrefs().getGateScanCount().equalsIgnoreCase("")) {
            binding.nowScanGateCount.setText("00");
        } else {
            binding.nowScanGateCount.setText(Osssc.getPrefs().getGateScanCount());
            binding.nowScanGateCount.setVisibility(View.VISIBLE);
            binding.nowScanHallCount.setVisibility(View.GONE);
        }

        if (Osssc.getPrefs().getHallScanCount().equalsIgnoreCase("")) {
            binding.nowScanHallCount.setText("00");
        } else {
            binding.nowScanHallCount.setText(Osssc.getPrefs().getHallScanCount());
            binding.nowScanGateCount.setVisibility(View.GONE);
            binding.nowScanHallCount.setVisibility(View.VISIBLE);
        }
    }

    private void setItemsVisibility(int isVisible) {
        binding.txtAttendanceType.setVisibility(isVisible);
        binding.chooseAttendanceLayout.setVisibility(isVisible);
    }


    void callData() {
        //= new ArrayList<StudentModel>();
        AppDatabase mdb = AppDatabase.getInstance(getApplicationContext());
        AppExecutors.getsInstance().databaseIO().execute(new Runnable() {
            @Override
            public void run() {
                lists = mdb.studentDao().allResorces();
                for (int i = 0; i < lists.size(); i++) {
                    StudentModel model = lists.get(i);
                    System.out.println(model.toString());
                }
            }
        });
    }

    private void callStudentListAPI() {

        final ProgressDialog pd = Utils.createProgressDialog(this);
        pd.show();

        // StringRequest for making Request
        StringRequest strReq = new StringRequest(Request.Method.GET,
                ApiConfig.STUDENT_LIST_URL, new Response.Listener<String>() {

            /*
             * Response getting from server
             */
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()) {

                    try {
                        // fetch Response as JSONObject
                        JSONObject jObj = new JSONObject(response);

                        boolean success = jObj.getBoolean("Success");
                        int code = jObj.getInt("Code");
                        String message = jObj.getString("Message");

                        if (success) {
                            String totalCandidate = jObj.getString("TotalCandidate");
                            String examDate = jObj.getString("Exam_Date");
                            String examShift = jObj.getString("Exam_Shift");

                            /*String[] dateTime = examDate.split(",");

                            String date = dateTime[0];
                            String time = dateTime[1];

                            String statusDateTime = examShift + " on "+ date + " (" + time + " )";*/

                            Osssc.getPrefs().setExamDateTime(examDate);
                            Osssc.getPrefs().setExamShift(examShift);

                            binding.txtTotalCandidateNo.setText(totalCandidate);
                            binding.examDate.setText(examDate);

                            callCandidateList();

                            /*final JSONArray student_array = jObj.getJSONArray("CandidateList");

                            if (student_array.length() != 0) {
                                totalLength = student_array.length();
                                new InsertDatabase(student_array).execute();
                                Toast.makeText(context, message,
                                        Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }*/
                            pd.dismiss();

                        } else {
                            MessageUtils.showFailureMessage(context, message);
                            pd.dismiss();
                        }

                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            // Server related error
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Volley Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Basic Authentication
                //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);

                headers.put("Authorization", "Bearer " + Osssc.getPrefs().getScannerData().getAssessToken());
                return headers;
            }
        };
        //cache disabled
        strReq.setShouldCache(false);
        // Adding request to request queue
        //RequestQueue requestQueue;
        Osssc.getInstance().addToRequestQueue(strReq);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {


            case R.id.candidatelist:
                Intent intent = new Intent(DashbordActivity.this, CandidateListActivity.class);
                startActivity(intent);
                drawer_layout.closeDrawer(GravityCompat.START);
                break;
            case R.id.changepin:
                Intent intent1 = new Intent(DashbordActivity.this, ChangePinActivity.class);
                startActivity(intent1);
                drawer_layout.closeDrawer(GravityCompat.START);
                break;
            case R.id.gateScannedList:
                Intent intent2 = new Intent(DashbordActivity.this, ScannedListActivity.class);
                intent2.putExtra("screen", "gateScannedList");
                startActivity(intent2);
                drawer_layout.closeDrawer(GravityCompat.START);
                break;
            case R.id.hallScannedList:
                Intent intent3 = new Intent(DashbordActivity.this, ScannedListActivity.class);
                intent3.putExtra("screen", "hallScannedList");
                startActivity(intent3);
                drawer_layout.closeDrawer(GravityCompat.START);
                break;
            case R.id.logout:

                if (Osssc.getPrefs().getGateScanCount().equalsIgnoreCase("") &&
                        Osssc.getPrefs().getHallScanCount().equalsIgnoreCase("")) {
                    deleteDb();
                } else {
                    checkLocalData();
                }
                break;
        }
        return true;
    }

    private void gotoLogout() {
        Osssc.getPrefs().clearPreference();
        Intent intent = new Intent(DashbordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void callCandidateList() {
        final ProgressDialog pd = Utils.createProgressDialog(this);
        pd.show();

        // StringRequest for making Request
        StringRequest strReq = new StringRequest(Request.Method.GET,
                ApiConfig.CANDIDATE_LIST_URL, new Response.Listener<String>() {

            /*
             * Response getting from server
             */
            @Override
            public void onResponse(String response) {

                if (!response.isEmpty()) {

                    try {
                        // fetch Response as JSONObject
                        JSONObject jObj = new JSONObject(response);

                        if (jObj.has("Success")) {
                            boolean success = jObj.getBoolean("Success");
                            int code = jObj.getInt("Code");
                            String message = jObj.getString("Message");

                            if (success) {
                                String totalCandidate = jObj.getString("TotalCandidate");
                                String totalCandidateGateList = jObj.getString("TotalCandidateGateList");
                                String totalCandidateHallList = jObj.getString("TotalCandidateHallList");
                                String hallAttendScannerwiseCount = jObj.getString("HallAttendScannerwiseCount");
                                String gateAttendScannerwiseCount = jObj.getString("GateAttendScannerwiseCount");
                                String centreName = jObj.getString("CentreName");
                                String districtName = jObj.getString("DistrictName");
                                String subjectName = jObj.getString("Subject_Name");
                                String postName = jObj.getString("PostName");
                                String testName = jObj.getString("TestName");

                                binding.txtTotalGateScanNo.setText(totalCandidateGateList);
                                binding.txtTotalHallScanNo.setText(totalCandidateHallList);
                                binding.scannerHallCount.setText(hallAttendScannerwiseCount);
                                binding.scannerGateCount.setText(gateAttendScannerwiseCount);

                                Osssc.getPrefs().setExamCenter(centreName);
                                centerCode.setText(centreName);
                                String centName = centreName + ", " + districtName;
                                String examName = testName + " (" + subjectName + ") "
                                        + "for the post of " + postName;

                                binding.centerName.setText(centName);
                                binding.testName.setText(examName);

                                binding.txtTotalCandidateNo.setText(totalCandidate);

                                final JSONArray student_array = jObj.getJSONArray("CandidateList");

                                if (student_array.length() != 0) {
                                    totalLength = student_array.length();
                                    new InsertDatabase(student_array).execute();
                                    Toast.makeText(context, message,
                                            Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }

                            } else {
                                MessageUtils.showFailureMessage(context, message);
                                pd.dismiss();
                            }

                        } else {
                            String message = jObj.getString("Message");
                            MessageUtils.showFailureMessage(context, message);
                        }

                    } catch (JSONException e) {
                        // JSON error
                        e.printStackTrace();
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            // Server related error
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Volley Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Basic Authentication
                //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);

                headers.put("Authorization", "Bearer " + Osssc.getPrefs().getScannerData().getAssessToken());
                return headers;
            }
        };
        //cache disabled
        strReq.setShouldCache(false);
        // Adding request to request queue
        //RequestQueue requestQueue;
        Osssc.getInstance().addToRequestQueue(strReq);
    }

    private class InsertDatabase extends AsyncTask<String, String, String> {

        JSONArray jsonArray;

        public InsertDatabase(JSONArray jsonArray) {
            this.jsonArray = jsonArray;
        }

        @SuppressLint("WrongConstant")
        @Override
        protected String doInBackground(String... strings) {
            //List<StudentModel> ii = new ArrayList<>();

            count = mDb.studentDao().getCount();

            if (count == 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject content = null;
                    try {
                        content = jsonArray.getJSONObject(i);
                        String rollNoumber = content.getString("RollNumber");
                        String barcode = content.getString("BarCode");
                        String entryScanTime = content.getString("EntryScanTime");
                        String hallScanTime = content.getString("HallScanTime");
                        String entryStatus = content.getString("EntryStatus");
                        String hallStatus = content.getString("HallStatus");
                        int scannerId = content.getInt("ScannerId");
                        mDb.studentDao().insertResource(new StudentModel(rollNoumber, barcode, entryStatus, entryScanTime, hallStatus, hallScanTime, scannerId));
                        Log.println(i, "Student List" + i, mDb.studentDao().allResorces().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                Log.d("Student List", mDb.toString());
            }
            return "Success";
        }

        @Override
        protected void onPostExecute(String bitmap) {
            super.onPostExecute(bitmap);
            callData();
            Toast.makeText(context, bitmap, Toast.LENGTH_SHORT).show();
        }
    }

    private void requestCameraPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(DashbordActivity.this, Home_Scanner.class);
                        startActivityForResult(intent, 200);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            Toast.makeText(getApplicationContext(), "Permission deny", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(
                            PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void checkLocalData() {

        List<CandidateRequestData> lists = new ArrayList<>();
        AppExecutors.getsInstance().databaseIO().execute(new Runnable() {
            @Override
            public void run() {
                count = mDb.studentDao().getCount();
                if (count > 0) {
                    updateList = mDb.studentDao().filterResources();
                    //updateList.forEach();
                    for (StudentModel num : updateList) {
                        //System.out.println("DATA>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+num.theString());
                        CandidateRequestData candidateRequestData = new CandidateRequestData(num.getStRollNo(),
                                num.getStBarcode(), num.getEntryScanTime(),
                                num.getHallScanTime(), num.getEntryStatus(), num.getHallStatus(),
                                num.getScannerId());
                        lists.add(candidateRequestData);
                    }
                    String type = "";
                    if (Osssc.getPrefs().getSelectEntryStatus()) {
                        type = "Gate";
                    } else {
                        type = "Hall";
                    }

                    ScanDataRequest scanDataRequest = new ScanDataRequest(type, lists);

                    if (!Utils.isNullOrEmpty(Osssc.getPrefs().getGateScanCount())) {
                        callSendScanData(scanDataRequest);
                    } else if (!Utils.isNullOrEmpty(Osssc.getPrefs().getHallScanCount())) {
                        callSendScanData(scanDataRequest);
                    }
                }
            }
        });
    }

    private void callSendScanData(ScanDataRequest scanDataRequest) {
        AppExecutors.getsInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                final ProgressDialog pd = Utils.createProgressDialog(DashbordActivity.this);
                pd.show();
                repo.sendScanData(scanDataRequest)
                        .observe(DashbordActivity.this, commonResponse -> {
                            if (commonResponse != null && commonResponse.getSuccess()) {
                                MessageUtils.showSuccessMessage(context, commonResponse.getMessage());
                                deleteDb();
                            }
                            pd.dismiss();
                        });
            }
        });
    }

    void deleteDb() {
        AppExecutors.getsInstance().databaseIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.studentDao().deleteAll();
                gotoLogout();
            }
        });
    }

}
