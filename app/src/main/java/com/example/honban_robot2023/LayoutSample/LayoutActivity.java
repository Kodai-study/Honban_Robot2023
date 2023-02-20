package com.example.honban_robot2023.LayoutSample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.widget.Button;

import com.example.honban_robot2023.R;

public class LayoutActivity extends AppCompatActivity {

    Button[] dialogCreateButtons = new Button[4];
    DialogFragment[] testDialogs = new DialogFragment[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        dialogCreateButtons[0] = findViewById(R.id.button_testResultDialog);
        dialogCreateButtons[1] = findViewById(R.id.button_testPieDialog);
        dialogCreateButtons[2] = findViewById(R.id.button_testStatisticsDialog);
        dialogCreateButtons[3] = findViewById(R.id.button_testUtilizationDialog);

        for (int i = 0; i < dialogCreateButtons.length; i++) {

            testDialogs[i] = new TestDialogs(i);
            int finalI = i;
            dialogCreateButtons[i].setOnClickListener(view -> {
                testDialogs[finalI].show(getSupportFragmentManager(),"sampleDialog");
            });
        }

    }

}