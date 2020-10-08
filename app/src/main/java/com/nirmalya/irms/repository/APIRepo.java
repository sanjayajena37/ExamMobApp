package com.nirmalya.irms.repository;

import android.content.Context;
import android.widget.Toast;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.model.responce.StudentResponse;
import com.nirmalya.irms.network.ApiInterface;
import com.nirmalya.irms.network.ApiManager;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIRepo {
    private Context context;

    public APIRepo() {
        context = Osssc.getInstance().getApplicationContext();
    }

    public APIRepo(Context context) {
        this.context = context;
    }

    // ApiInterface with Application Json
    private ApiInterface getApiInterface() {
        return ApiManager.getInstance(context).createService();
    }

    // Get StudentList Api Network call
    public LiveData<StudentResponse> getStudentData() {
        final MutableLiveData<StudentResponse> liveStudentData = new MutableLiveData<>();

        getApiInterface().getStudentList()
                .enqueue(new Callback<StudentResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<StudentResponse> call, @NotNull Response<StudentResponse> response) {
                        StudentResponse responseBody = response.body();
                        if (responseBody != null) {
                            liveStudentData.setValue(responseBody);
                            Toast.makeText(context, "Exam given student list gets successfully",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            if(response.code() != 502) {
                                Toast.makeText(context, "Some error occurred!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Bad get way",
                                        Toast.LENGTH_SHORT).show();
                            }
                            liveStudentData.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<StudentResponse> call, @NotNull Throwable t) {
                        liveStudentData.setValue(null);
                    }
                });

        return liveStudentData;
    }
}
