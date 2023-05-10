package com.theduckfood.presenter;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.UserAccount;
import com.theduckfood.model.UserProfile;
import com.theduckfood.model.respone.GetProfileResponse;
import com.theduckfood.presenter.contact.IProfileView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    private IProfileView iProfileView;
    private UserAccount userAccount;
    private UserProfile userProfile;

    public ProfilePresenter(IProfileView iProfileView, UserAccount userAccount, UserProfile userProfile) {
        this.iProfileView = iProfileView;
        this.userAccount = userAccount;
        this.userProfile = userProfile;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void getProfile() {
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<GetProfileResponse> call = userAccountEndpoint.getProfile();
        call.enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                GetProfileResponse getProfileResponse = response.body();
                iProfileView.getProfile(getProfileResponse);

                if(getProfileResponse != null){
                    userAccount = getProfileResponse.getUserAccount();
                    userProfile = getProfileResponse.getUserProfile();
                }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {

            }
        });
    }
}
