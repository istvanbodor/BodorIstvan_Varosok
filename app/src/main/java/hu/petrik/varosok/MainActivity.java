package hu.petrik.varosok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button listazogomb;
    private Button felvevogomb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        listazogomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
                finish();
            }
        });

        felvevogomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
                finish();
            }
        });





    }


    private void init() {

        listazogomb = findViewById(R.id.listazogomb);
        felvevogomb = findViewById(R.id.felvevogomb);

    }
}