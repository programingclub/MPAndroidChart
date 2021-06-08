package com.irvingmedina.graficasconsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    DatosDB datosDB;
    SQLiteDatabase sqLiteDatabase;

    PieDataSet pieDataSet = new PieDataSet(null, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChart pieChart = findViewById(R.id.pieChart);

        datosDB = new DatosDB(this);
        sqLiteDatabase = datosDB.getWritableDatabase();

        pieDataSet.setValues(getDataValues());
        pieDataSet.setLabel("Descripcion");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("desc Pie");
        pieChart.animate();

        pieDataSet.setFormLineWidth(4);

    }

    private ArrayList<PieEntry> getDataValues(){

        ArrayList<PieEntry> dataValues = new ArrayList<>();
        Cursor cursor = datosDB.getValues();

        for (int i= 0; i<cursor.getCount(); i++){
            cursor.moveToNext();
            dataValues.add(new PieEntry(cursor.getFloat(0), String.valueOf(cursor.getString(1))));
        }

        return dataValues;

    }


}