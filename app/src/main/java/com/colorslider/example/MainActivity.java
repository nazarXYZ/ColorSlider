package com.colorslider.example;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.naz013.colorslider.ColorSlider;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ColorSlider.OnColorSelectedListener mListener = new ColorSlider.OnColorSelectedListener() {
        @Override
        public void onColorChanged(int position, int color) {
            updateView(color);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ColorSlider slider = findViewById(R.id.color_slider);
        slider.setSelectorColor(Color.BLUE);
        slider.setListener(mListener);

        ColorSlider sliderGradient = findViewById(R.id.color_slider_gradient);
        sliderGradient.setListener(mListener);

        ColorSlider sliderArray = findViewById(R.id.color_slider_array);
        sliderArray.setListener(mListener);

        ColorSlider sliderHexArray = findViewById(R.id.color_slider_colors);
        sliderHexArray.setListener(mListener);

        ColorSlider sliderGradientArray = findViewById(R.id.color_slider_gradient_array);
        sliderGradientArray.setGradient(new int[]{Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED}, 200);
        sliderGradientArray.setListener(mListener);

        updateView(slider.getSelectedColor());
    }

    private void updateView(@ColorInt int color) {
        toolbar.setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(color);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_more) {
            showGithub();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showGithub() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://github.com/naz013/ColorSlider"));
        startActivity(intent);
    }
}
