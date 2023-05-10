package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.StoreAccountEndpoint;
import com.theduckfood.merchant.model.response.GetStoreProfileResponse;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.presenter.contact.IHomeView;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private IHomeView iHomeView;
    private Context context;
    final SharedPreferenceManager sharedPreferenceManager;
    public HomePresenter(IHomeView iHomeView, Context context) {
        this.iHomeView = iHomeView;
        this.context = context;
        sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void loadProfile() {
        iHomeView.isLoading(true);
        StoreAccountEndpoint storeAccountEndpoint = APIUtil.getRetrofit().create(StoreAccountEndpoint.class);
        Call<GetStoreProfileResponse> call = storeAccountEndpoint.getStoreProfile(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY));
        call.enqueue(new Callback<GetStoreProfileResponse>() {
            @Override
            public void onResponse(Call<GetStoreProfileResponse> call, Response<GetStoreProfileResponse> response) {
                GetStoreProfileResponse storeProfileResponse = response.body();
                iHomeView.loadProfile(storeProfileResponse);
                iHomeView.isLoading(false);
            }

            @Override
            public void onFailure(Call<GetStoreProfileResponse> call, Throwable t) {
                Log.e("MainActivity", t.getMessage());
                iHomeView.loadProfile(null);
                iHomeView.isLoading(false);
            }
        });
    }

    public void changeStatus(boolean status) {
        StoreAccountEndpoint storeAccountEndpoint = APIUtil.getRetrofit().create(StoreAccountEndpoint.class);
        Call<SimpleMessageResponse> call = storeAccountEndpoint.changeStatus(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                status);
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                SimpleMessageResponse simpleMessageResponse = response.body();

                if (simpleMessageResponse == null || simpleMessageResponse.isError())
                    Toast.makeText(context, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra lại kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
