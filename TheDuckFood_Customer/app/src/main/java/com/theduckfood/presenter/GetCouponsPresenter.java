package com.theduckfood.presenter;

import android.content.Context;

import com.theduckfood.api.APIUtil;
import com.theduckfood.api.UserAccountEndpoint;
import com.theduckfood.model.respone.GetCouponsResponse;
import com.theduckfood.presenter.contact.IGetCouponsView;
import com.theduckfood.util.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCouponsPresenter {
    private IGetCouponsView iGetCouponsView;
    private Context context;

    public GetCouponsPresenter(IGetCouponsView iGetCouponsView, Context context) {
        this.iGetCouponsView = iGetCouponsView;
        this.context = context;
    }
    public void getCoupons(Long storeId) {
        final SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(context);
        UserAccountEndpoint userAccountEndpoint = APIUtil.getRetrofit().create(UserAccountEndpoint.class);
        Call<GetCouponsResponse> call = userAccountEndpoint.getCoupons(
                storeId,
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY)
        );
        call.enqueue(new Callback<GetCouponsResponse>() {
            @Override
            public void onResponse(Call<GetCouponsResponse> call, Response<GetCouponsResponse> response) {
                iGetCouponsView.getCoupons(response.body());
            }

            @Override
            public void onFailure(Call<GetCouponsResponse> call, Throwable t) {

            }
        });
    }
}
