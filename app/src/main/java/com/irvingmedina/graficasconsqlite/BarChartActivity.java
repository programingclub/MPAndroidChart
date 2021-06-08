package com.irvingmedina.graficasconsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    DatosDB datosDB;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        BarChart barChart = findViewById(R.id.barChart);
        BarDataSet barDataSet = new BarDataSet(getDatosBar(), "datos");

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Ejemplo");
        barChart.animateY(2000);

    }

    private ArrayList<BarEntry> getDatosBar(){

        datosDB = new DatosDB(this);
        SQLiteDatabase db = datosDB.getWritableDatabase();
        ArrayList<BarEntry> dataVals = new ArrayList<>();

        Cursor cursor = datosDB.getDatosBar();
        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            dataVals.add(new BarEntry(cursor.getInt(0), cursor.getInt(1)));
        }

        cursor.close();
        db.close();

        return dataVals;
    }
}