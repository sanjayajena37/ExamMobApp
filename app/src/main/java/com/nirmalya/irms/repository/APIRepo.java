package com.nirmalya.irms.repository;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.nirmalya.irms.Osssc;
import com.nirmalya.irms.model.request.SetPinRequest;
import com.nirmalya.irms.model.request.SignInRequest;
import com.nirmalya.irms.model.request.SignupSendMobileRequest;
import com.nirmalya.irms.model.request.ValidateOTPRequest;
import com.nirmalya.irms.model.response.CommonResponse;
import com.nirmalya.irms.model.response.SignInResponse;
import com.nirmalya.irms.model.response.StudentResponse;
import com.nirmalya.irms.network.ApiInterface;
import com.nirmalya.irms.network.ApiManager;
import com.nirmalya.irms.utility.MessageUtils;
import com.nirmalya.irms.utility.Utils;

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

    public LiveData<CommonResponse> sendOTP(SignupSendMobileRequest signupSendMobileRequest) {
        final MutableLiveData<CommonResponse> liveDataSendOTPResponse = new MutableLiveData<>();
        getApiInterface()
                .postSendOTP(signupSendMobileRequest)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CommonResponse> call, @NotNull Response<CommonResponse> response) {
                        CommonResponse responseBody = response.body();
                        if (responseBody != null) {
                            liveDataSendOTPResponse.setValue(responseBody);

                            if (!responseBody.getSuccess())
                                MessageUtils.showFailureMessage(context, responseBody.getMessage());
                        } else {
                            if (response.code() != 502) {
                                Toast.makeText(context, "Some error occurred!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Bad get way",
                                        Toast.LENGTH_SHORT).show();
                            }
                            liveDataSendOTPResponse.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<CommonResponse> call, @NotNull Throwable t) {
                        liveDataSendOTPResponse.setValue(null);
                    }
                });

        return liveDataSendOTPResponse;
    }

    public LiveData<CommonResponse> validateOTP(final Context context, ValidateOTPRequest validateOTPRequest) {
        final ProgressDialog pd = Utils.createProgressDialog(context);
        pd.show();

        final MutableLiveData<CommonResponse> liveDataValidateOTPResponse = new MutableLiveData<>();
        getApiInterface()
                .validateOTP(validateOTPRequest)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CommonResponse> call, @NotNull Response<CommonResponse> response) {
                        CommonResponse responseBody = response.body();
                        if (responseBody != null) {
                            liveDataValidateOTPResponse.setValue(responseBody);

                            if (!responseBody.getSuccess())
                                MessageUtils.showFailureMessage(context, responseBody.getMessage());
                        } else {
                            if (response.code() != 502) {
                                Toast.makeText(context, "Some error occurred!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Bad get way",
                                        Toast.LENGTH_SHORT).show();
                            }
                            liveDataValidateOTPResponse.setValue(null);
                        }
                        pd.dismiss();
                    }

                    @Override
                    public void onFailure(@NotNull Call<CommonResponse> call, @NotNull Throwable t) {
                        liveDataValidateOTPResponse.setValue(null);
                        pd.dismiss();
                    }
                });

        return liveDataValidateOTPResponse;
    }

    public LiveData<CommonResponse> reSendOTP(SignupSendMobileRequest signupSendMobileRequest) {
        final MutableLiveData<CommonResponse> liveDataReSendOTPResponse = new MutableLiveData<>();
        getApiInterface()
                .reSendOTP(signupSendMobileRequest)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CommonResponse> call, @NotNull Response<CommonResponse> response) {
                        CommonResponse responseBody = response.body();
                        if (responseBody != null) {
                            liveDataReSendOTPResponse.setValue(responseBody);

                            if (!responseBody.getSuccess())
                                MessageUtils.showFailureMessage(context, responseBody.getMessage());
                        } else {
                            if (response.code() != 502) {
                                Toast.makeText(context, "Some error occurred!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Bad get way",
                                        Toast.LENGTH_SHORT).show();
                            }
                            liveDataReSendOTPResponse.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<CommonResponse> call, @NotNull Throwable t) {
                        liveDataReSendOTPResponse.setValue(null);
                    }
                });

        return liveDataReSendOTPResponse;
    }

    public LiveData<CommonResponse> setUpPin(SetPinRequest setPinRequest) {
        final MutableLiveData<CommonResponse> liveDataSetUpPinResponse = new MutableLiveData<>();
        getApiInterface()
                .setUpPin(setPinRequest)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CommonResponse> call, @NotNull Response<CommonResponse> response) {
                        CommonResponse responseBody = response.body();
                        if (responseBody != null) {
                            liveDataSetUpPinResponse.setValue(responseBody);

                            if (!responseBody.getSuccess())
                                MessageUtils.showFailureMessage(context, responseBody.getMessage());
                        } else {
                            if (response.code() != 502) {
                                Toast.makeText(context, "Some error occurred!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Bad get way",
                                        Toast.LENGTH_SHORT).show();
                            }
                            liveDataSetUpPinResponse.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<CommonResponse> call, @NotNull Throwable t) {
                        liveDataSetUpPinResponse.setValue(null);
                    }
                });

        return liveDataSetUpPinResponse;
    }

    public LiveData<CommonResponse> forgotPin(SignupSendMobileRequest signupSendMobileRequest) {
        final MutableLiveData<CommonResponse> liveDataSendOTPResponse = new MutableLiveData<>();
        getApiInterface()
                .forgotPin(signupSendMobileRequest)
                .enqueue(new Callback<CommonResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CommonResponse> call, @NotNull Response<CommonResponse> response) {
                        CommonResponse responseBody = response.body();
                        if (responseBody != null) {
                            liveDataSendOTPResponse.setValue(responseBody);

                            if (!responseBody.getSuccess())
                                MessageUtils.showFailureMessage(context, responseBody.getMessage());
                        } else {
                            if (response.code() != 502) {
                                Toast.makeText(context, "Some error occurred!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Bad get way",
                                        Toast.LENGTH_SHORT).show();
                            }
                            liveDataSendOTPResponse.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<CommonResponse> call, @NotNull Throwable t) {
                        liveDataSendOTPResponse.setValue(null);
                    }
                });

        return liveDataSendOTPResponse;
    }

    public LiveData<SignInResponse> signIn(SignInRequest signInRequest) {
        final MutableLiveData<SignInResponse> liveDataSignInResponse = new MutableLiveData<>();
        getApiInterface()
                .login(signInRequest)
                .enqueue(new Callback<SignInResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<SignInResponse> call, @NotNull Response<SignInResponse> response) {
                        SignInResponse responseBody = response.body();
                        if (responseBody != null) {
                            liveDataSignInResponse.setValue(responseBody);

                            if (!responseBody.getSuccess())
                                MessageUtils.showFailureMessage(context, responseBody.getMessage());
                        } else {
                            if (response.code() != 502) {
                                Toast.makeText(context, "Some error occurred!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Bad get way",
                                        Toast.LENGTH_SHORT).show();
                            }
                            liveDataSignInResponse.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<SignInResponse> call, @NotNull Throwable t) {
                        liveDataSignInResponse.setValue(null);
                    }
                });

        return liveDataSignInResponse;
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
                            if (response.code() != 502) {
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
