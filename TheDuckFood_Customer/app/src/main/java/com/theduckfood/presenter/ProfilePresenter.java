package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.UserAccount;
import com.theduckfood.model.UserProfile;
import com.theduckfood.model.respone.GetProfileResponse;
import com.theduckfood.presenter.contact.IProfileView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private IProfileView iProfileView;
    private Context context;
//    private UserAccount userAccount;
//    private UserProfile userProfile;

    public ProfilePresenter(IProfileView iProfileView, Context context) {
        this.iProfileView = iProfileView;
        this.context = context;
//        this.userAccount = userAccount;
//        this.userProfile = userProfile;
    }

//    public UserAccount getUserAccount() {
//        return userAccount;
//    }
//
//    public void setUserAccount(UserAccount userAccount) {
//        this.userAccount = userAccount;
//    }
//
//    public UserProfile getUserProfile() {
//        return userProfile;
//    }
//
//    public void setUserProfile(UserProfile userProfile) {
//        this.userProfile = userProfile;
//    }

    public void getProfile() {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<GetProfileResponse> call = userAccountEndpoint.getProfile(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY));
        call.enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                GetProfileResponse getProfileResponse = response.body();
                iProfileView.getProfile(getProfileResponse);

//                if(getProfileResponse != null){
//                    userAccount = getProfileResponse.getUserAccount();
//                    userProfile = getProfileResponse.getUserProfile();
//
//                }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {

            }
        });
    }
}
