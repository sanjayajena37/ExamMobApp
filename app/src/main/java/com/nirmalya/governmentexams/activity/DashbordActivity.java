package com.nirmalya.governmentexams.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.nirmalya.governmentexams.R;
import com.nirmalya.governmentexams.RoomDB.AppDatabase;
import com.nirmalya.governmentexams.RoomDB.AppExecutors;
import com.nirmalya.governmentexams.RoomDB.StudentModel;
import com.nirmalya.governmentexams.model.responce.StudentList;
import com.nirmalya.governmentexams.model.responce.StudentResponse;
import com.nirmalya.governmentexams.network.ApiConfig;
import com.nirmalya.governmentexams.network.ApiController;
import com.nirmalya.governmentexams.network.NetworkConnectionInterceptor;
import com.nirmalya.governmentexams.repository.APIRepo;
import com.nirmalya.governmentexams.utility.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashbordActivity extends AppCompatActivity {
    LinearLayout scannLayout;
    //Array List
    private ArrayList<StudentModel> studentLists;
    private Context context;

    List<StudentModel> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);
        scannLayout = findViewById(R.id.scannLayout);
        //ArrayList
        studentLists = new ArrayList<StudentModel>();
        context = this;
        scannLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestCameraPermission();

            }
        });

        callStudentListAPI();
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
        ApiController.getInstance().addToRequestQueue(strReq);
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
