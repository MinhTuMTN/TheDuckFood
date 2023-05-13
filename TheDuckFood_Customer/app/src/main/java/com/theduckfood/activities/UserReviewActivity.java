package com.theduckfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.theduckfood.databinding.ActivityReviewBinding;
import com.theduckfood.model.Order;
import com.theduckfood.model.request.UserReviewRequest;
import com.theduckfood.model.respone.OrderResponse;
import com.theduckfood.model.respone.SimpleMessageResponse;
import com.theduckfood.presenter.UserReviewPresenter;
import com.theduckfood.presenter.contact.IUserReviewView;

public class UserReviewActivity extends AppCompatActivity implements IUserReviewView {
    ActivityReviewBinding binding;
    OrderResponse orderDetail;
    float rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentData();

        String orderId = "Mã đơn hàng: " + orderDetail.getOrder().getOrderId().toString();
        binding.txtOrderId.setText(orderId);

        addEvents();

    }
    private void getIntentData() {
        orderDetail = (OrderResponse) getIntent().getSerializableExtra("orderDetail");
        Toast.makeText(this, orderDetail.getStore().getStoreId().toString(), Toast.LENGTH_SHORT).show();
    }
    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> switchToMainActivity());
        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = rating;
            }
        });
        String review = binding.txtReview.getText().toString();

        UserReviewRequest userReviewRequest = new UserReviewRequest(rate, review, orderDetail.getOrder().getOrderId());
        binding.btnSend.setOnClickListener(v -> btnSendClick(userReviewRequest));
    }

    private void btnSendClick(UserReviewRequest userReviewRequest) {
        UserReviewPresenter userReviewPresenter = new UserReviewPresenter(this, UserReviewActivity.this);
        userReviewPresenter.review(userReviewRequest);
    }

    public void switchToMainActivity() {
        Intent intent = new Intent(UserReviewActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void review(SimpleMessageResponse simpleMessageResponse) {
        if (simpleMessageResponse == null) {
            Toast.makeText(this, "Đơn hàng đã được đánh giá!", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, simpleMessageResponse.getMessage(), Toast.LENGTH_SHORT).show();
        switchToMainActivity();
    }
}
