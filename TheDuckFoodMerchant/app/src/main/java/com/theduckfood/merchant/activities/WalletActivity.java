package com.theduckfood.merchant.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;

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

import java.util.ArrayList;

public class WalletActivity extends AppCompatActivity {
    ActivityWalletBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setData();
    }

    private void setData() {
        BarChart barChart = findViewById(R.id.chart);
        barChart.setScaleEnabled(false);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 1000)); // Doanh thu ngày 1
        entries.add(new BarEntry(1, 1500)); // Doanh thu ngày 2
        entries.add(new BarEntry(2, 2000)); // Doanh thu ngày 3
        entries.add(new BarEntry(3, 1800)); // Doanh thu ngày 4
        entries.add(new BarEntry(4, 2500)); // Doanh thu ngày 5
        entries.add(new BarEntry(5, 3000)); // Doanh thu ngày 6
        entries.add(new BarEntry(6, 35000)); // Doanh thu ngày 7

        BarDataSet dataSet = new BarDataSet(entries, "Doanh thu");
        dataSet.setColor(ContextCompat.getColor(this, R.color.pink_1));
        dataSet.setValueTextSize(12f);

        ArrayList<String> labels = new ArrayList<>();
        labels.add("15/03");
        labels.add("16/03");
        labels.add("17/03");
        labels.add("18/03");
        labels.add("19/03");
        labels.add("20/03");
        labels.add("21/03");

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getDescription().setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getLegend().setEnabled(false);
        barChart.animateY(1000);
    }
}