package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.theduckfood.merchant.adapter.ReviewAdapter;
import com.theduckfood.merchant.databinding.ActivityReviewBinding;
import com.theduckfood.merchant.model.response.StoreGetAllReviewsResponse;
import com.theduckfood.merchant.presenter.ReviewPresenter;
import com.theduckfood.merchant.presenter.contact.IReviewView;

import java.text.DecimalFormat;

public class ReviewActivity extends AppCompatActivity implements IReviewView {
    ActivityReviewBinding binding;
    ReviewPresenter reviewPresenter;
    ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
        getData();
        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(v -> onBackPressed());
    }

    private void initialize() {
        reviewPresenter = new ReviewPresenter(this, this);

    }

    private void getData() {
        reviewPresenter.getAllReview();
    }

    @Override
    public void getAllReviewResponse(StoreGetAllReviewsResponse reviewsResponse) {
        if (reviewsResponse == null || reviewsResponse.isError()) {
            Toast.makeText(this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            return;
        }

        String reviewCount = reviewsResponse.getReviewCount() + " đánh giá";
        binding.txtRatingCount.setText(reviewCount);

        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String result = decimalFormat.format(reviewsResponse.getAverageRating());
        binding.txtRate.setText(result);

        binding.ratingBar.setRating(reviewsResponse.getAverageRating() / 5);
        binding.txtNumStar1.setText(String.valueOf(reviewsResponse.getOneStart()));
        binding.txtNumStar2.setText(String.valueOf(reviewsResponse.getTwoStart()));
        binding.txtNumStar3.setText(String.valueOf(reviewsResponse.getThreeStart()));
        binding.txtNumStar4.setText(String.valueOf(reviewsResponse.getFourStart()));
        binding.txtNumStar5.setText(String.valueOf(reviewsResponse.getFiveStart()));

        binding.progressBar1.setProgress((int) (reviewsResponse.getOneStart() / (float) reviewsResponse.getReviewCount() * 100));
        binding.progressBar2.setProgress((int) (reviewsResponse.getTwoStart() / (float) reviewsResponse.getReviewCount() * 100));
        binding.progressBar3.setProgress((int) (reviewsResponse.getThreeStart() / (float) reviewsResponse.getReviewCount() * 100));
        binding.progressBar4.setProgress((int) (reviewsResponse.getFourStart() / (float) reviewsResponse.getReviewCount() * 100));
        binding.progressBar5.setProgress((int) (reviewsResponse.getFiveStart() / (float) reviewsResponse.getReviewCount() * 100));

        reviewAdapter = new ReviewAdapter(this, reviewsResponse.getReviews());
        binding.listReview.setAdapter(reviewAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.listReview.setLayoutManager(linearLayoutManager);

    }
}