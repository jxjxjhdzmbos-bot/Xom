package com.xo.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlayVsAI = findViewById(R.id.btnPlayVsAI);
        Button btnPlayLocal = findViewById(R.id.btnPlayLocal);

        btnPlayVsAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(true);
            }
        });

        btnPlayLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(false);
            }
        });
    }

    private void startGame(boolean vsAI) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("VS_AI", vsAI);
        startActivity(intent);
    }
}
