package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.ReviewEndpoint;
import com.theduckfood.merchant.model.response.StoreGetAllReviewsResponse;
import com.theduckfood.merchant.presenter.contact.IReviewView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewPresenter {
    IReviewView iReviewView;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public ReviewPresenter(IReviewView iReviewView, Context context) {
        this.iReviewView = iReviewView;
        this.context = context;
        sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void getAllReview() {
        ReviewEndpoint reviewEndpoint = APIUtil.getRetrofit().create(ReviewEndpoint.class);
        Call<StoreGetAllReviewsResponse> call = reviewEndpoint.getAllReviews(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<StoreGetAllReviewsResponse>() {
            @Override
            public void onResponse(Call<StoreGetAllReviewsResponse> call, Response<StoreGetAllReviewsResponse> response) {
                iReviewView.getAllReviewResponse(response.body());
            }

            @Override
            public void onFailure(Call<StoreGetAllReviewsResponse> call, Throwable t) {
                Log.e("ReviewActivity", t.getMessage());
                iReviewView.getAllReviewResponse(null);
            }
        });
    }
}
