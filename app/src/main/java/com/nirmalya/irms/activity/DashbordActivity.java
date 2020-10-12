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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
import com.nirmalya.irms.network.ApiConfig;
import com.nirmalya.irms.utility.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashbordActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Array List
    private ArrayList<StudentModel> studentLists;
    private Context context;
    private ActivityDashbordBinding binding;
    private ImageView navigationimg;
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView navigationView;
    ActionBarDrawerToggle mtoogle;
    List<StudentModel> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashbord);

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
        //ArrayList
        studentLists = new ArrayList<StudentModel>();
        context = this;
        binding.scanner.setOnClickListener(view -> requestCameraPermission());

        binding.totalCandidateCard.setOnClickListener(v -> {
            Intent intent = new Intent(DashbordActivity.this, CandidateListActivity.class);
            startActivity(intent);
        });

        binding.rbScanHall.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setItemsVisibility(View.VISIBLE);
            } else {
                setItemsVisibility(View.GONE);
            }
        });

        callStudentListAPI();
    }


    private void setItemsVisibility(int isVisible) {
        binding.txtAttendanceType.setVisibility(isVisible);
        binding.chooseAttendanceLayout.setVisibility(isVisible);
    }

    /*
     * GET the data from for booking list
     * @method GET
     * @return Service Array and object
     */

   void callData(){
        //= new ArrayList<StudentModel>();
        AppDatabase mdb=AppDatabase.getInstance(getApplicationContext());
       AppExecutors.getsInstance().databaseIO().execute(new Runnable() {
           @Override
           public void run() {
               lists=mdb.studentDao().allResorces();
               for(int i=0;i<lists.size();i++){
                   StudentModel model=lists.get(i);
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

                        final JSONArray student_array = jObj.getJSONArray("studentList");

                        if (student_array.length() == 0) {

                        } else {

                            new InsertDatabase(student_array).execute();
                            pd.dismiss();
                            Toast.makeText(context, "Exam given student list gets successfully",
                                    Toast.LENGTH_SHORT).show();
                                /*myBookingServiceListAdapter = new MyBookingServiceListAdapter(bookingListArrayList, CustomerMyBookingActivity.this);
                                myBookingServiceListAdapter.notifyDataSetChanged();
                                orderListRecycler.setAdapter(myBookingServiceListAdapter);
                                myBookingServiceListAdapter.notifyDataSetChanged();*/
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


            case R.id.myaccount:
                Intent intent = new Intent(DashbordActivity.this, CandidateListActivity.class);
                startActivity(intent);
                drawer_layout.closeDrawer(GravityCompat.START);
                break;
            case R.id.changepin:

                Intent intent1 = new Intent(DashbordActivity.this, ChangePinActivity.class);
                startActivity(intent1);
                drawer_layout.closeDrawer(GravityCompat.START);
                break;
            case R.id.logout:

                break;
        }
        return true;
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
            AppDatabase mDb = AppDatabase.getInstance(getApplicationContext());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject content = null;
                try {
                    content = jsonArray.getJSONObject(i);
                    Integer studentID = content.getInt("studentID");
                    String studentMobileNo = content.getString("studentMobileNo");
                    String examShift = content.getString("examShift");
                    String studentName = content.getString("studentName");
                    String studentImage = content.getString("studentImage");
                    String examDate = content.getString("examDate");
                    String examTime = content.getString("examTime");
                    String examCenter = content.getString("examCenter");
                    String studentRollNo = content.getString("studentRollNo");
                    mDb.studentDao().insertResource(new StudentModel(studentName, studentImage, examDate, examTime, examCenter, studentRollNo, false));
                    Log.println(i, "Student List" + i, mDb.studentDao().allResorces().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            Log.d("Student List", mDb.toString());
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
                        startActivity(intent);
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

}
