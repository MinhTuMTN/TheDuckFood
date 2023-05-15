package com.theduckfood.presenter;

import android.content.Context;
import android.util.Log;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.respone.SearchResponse;
import com.theduckfood.presenter.contact.ISearchView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {
    ISearchView iSearchView;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public SearchPresenter(ISearchView iSearchView, Context context) {
        this.iSearchView = iSearchView;
        this.context = context;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void search(String searchParam) {
        iSearchView.loading(true);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<SearchResponse> call = userAccountEndpoint.search(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                0,
                10,
                searchParam
        );
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                iSearchView.searchResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.e("SearchActivity", t.getMessage());
                iSearchView.searchResponse(null);
            }
        });
    }
}
