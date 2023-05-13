package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.request.UserReviewRequest;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.presenter.contact.IUserReviewView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserReviewPresenter {
    private IUserReviewView iUserReviewView;
    private Context context;

    public UserReviewPresenter(IUserReviewView iUserReviewView, Context context) {
        this.iUserReviewView = iUserReviewView;
        this.context = context;
    }
    public void review(UserReviewRequest userReviewRequest){
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<SimpleMessageResponse> call = userAccountEndpoint.review(
                userReviewRequest,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                SimpleMessageResponse simpleMessageResponse = response.body();
                iUserReviewView.review(simpleMessageResponse);
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {

            }
        });
    }
}
