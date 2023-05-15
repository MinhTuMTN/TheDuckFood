package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.respone.UserGetListStore;
import com.theduckfood.presenter.contact.IHomeView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    IHomeView iHomeView;
    Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public HomePresenter(IHomeView iHomeView, Context context) {
        this.iHomeView = iHomeView;
        this.context = context;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void getListStore(String sortParam, String sortType, int page, int limit) {
        sortParam = sortParam.equals("date") || sortParam.equals("rate") ? sortParam : "rate";
        sortType = sortType.equals("ASC") ? sortType : "DESC";
        page = Math.max(page, 0);
        limit = limit > 0 ? limit : 7;

        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<UserGetListStore> call = userAccountEndpoint.getListStore(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                page,
                limit,
                sortParam,
                sortType
        );

        String finalSortParam = sortParam;
        call.enqueue(new Callback<UserGetListStore>() {
            @Override
            public void onResponse(Call<UserGetListStore> call, Response<UserGetListStore> response) {
                UserGetListStore userGetListStore = null;
                if (response.isSuccessful()) {
                    userGetListStore = response.body();
                }

                if (finalSortParam.equals("rate")) iHomeView.getRecommendStores(userGetListStore);
                else  iHomeView.getNewestStores(userGetListStore);
            }

            @Override
            public void onFailure(Call<UserGetListStore> call, Throwable t) {
                iHomeView.getNewestStores(null);
            }
        });
    }
}
