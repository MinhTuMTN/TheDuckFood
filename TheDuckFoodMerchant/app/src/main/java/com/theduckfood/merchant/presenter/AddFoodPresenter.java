package com.theduckfood.merchant.presenter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.theduckfood.merchant.api.APIUtil;
import com.theduckfood.merchant.api.FoodEndPoint;
import com.theduckfood.merchant.model.response.SimpleMessageResponse;
import com.theduckfood.merchant.presenter.contact.IAddFoodView;
import com.theduckfood.merchant.util.RealPathUtil;
import com.theduckfood.merchant.util.SharedPreferenceManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFoodPresenter {
    private IAddFoodView iAddFoodView;
    private Context context;
    SharedPreferenceManager sharedPreferenceManager;

    public AddFoodPresenter(IAddFoodView iAddFoodView, Context context) {
        this.iAddFoodView = iAddFoodView;
        this.context = context;
        this.sharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public void addFood(String foodName, String description, float price, Uri imageUri) {
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), foodName);
        RequestBody foodDescription = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody foodPrice = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(price));

        String imagePath = RealPathUtil.getRealPath(context, imageUri);
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part images = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        FoodEndPoint foodEndPoint = APIUtil.getRetrofit().create(FoodEndPoint.class);
        Call<SimpleMessageResponse> call = foodEndPoint.addFood(
                "Bearer " + sharedPreferenceManager.getStringValue(SharedPreferenceManager.AUTH_TOKEN_KEY),
                name,
                foodDescription,
                foodPrice,
                images
        );
        call.enqueue(new Callback<SimpleMessageResponse>() {
            @Override
            public void onResponse(Call<SimpleMessageResponse> call, Response<SimpleMessageResponse> response) {
                SimpleMessageResponse simpleMessageResponse = response.body();
                iAddFoodView.addFoodResponse(simpleMessageResponse);
            }

            @Override
            public void onFailure(Call<SimpleMessageResponse> call, Throwable t) {
                Log.e("AddFoodActivity", t.getMessage());
                Toast.makeText(context, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
