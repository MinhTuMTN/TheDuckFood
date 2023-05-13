package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.theduckfood.merchant.R;
import com.theduckfood.merchant.databinding.ActivityWalletBinding;
import com.theduckfood.merchant.model.response.StoreGetStatisticResponse;
import com.theduckfood.merchant.model.response.StoreStatistic;
import com.theduckfood.merchant.presenter.WalletPresenter;
import com.theduckfood.merchant.presenter.contact.IWalletView;
import com.theduckfood.merchant.util.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends AppCompatActivity implements IWalletView {
    ActivityWalletBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WalletPresenter walletPresenter = new WalletPresenter(this, this);
        walletPresenter.getStatistic();
    }

    private void setChartData(List<StoreStatistic> storeStatistics) {
        BarChart barChart = findViewById(R.id.chart);
        barChart.setScaleEnabled(false);

        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for (int i = 0; i < storeStatistics.size(); i++) {
            entries.add(new BarEntry(i, storeStatistics.get(i).getAmount().floatValue()));
            labels.add(DateTimeUtil.formatShortDate(storeStatistics.get(i).getDate()));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Doanh thu");
        dataSet.setColor(ContextCompat.getColor(this, R.color.pink_1));
        dataSet.setValueTextSize(12f);

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setLabelCount(storeStatistics.size());
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getLegend().setEnabled(false);
        barChart.animateY(1000);
    }

    @Override
    public void getStatisticResponse(StoreGetStatisticResponse statisticResponse) {
        if (statisticResponse == null || statisticResponse.isError()) {
            Toast.makeText(this, "Đã có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.txtStoreName.setText(statisticResponse.getStore().getStoreName());
        binding.txtBalance.setText(DateTimeUtil.formatCurrency(String.valueOf(statisticResponse.getStore().getBalance())));
        setChartData(statisticResponse.getStatistics());
    }
}