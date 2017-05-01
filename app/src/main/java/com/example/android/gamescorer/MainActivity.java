package com.example.android.gamescorer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        //Find the view that shows the Ascension card
        TextView ascension = (TextView) findViewById(R.id.ascension);
        //Set a click listener on that view
        ascension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new Intent to open the {@link AscensionActivity}
                Intent AscensionIntent = new Intent(MainActivity.this, AscensionActivity.class);
                //Start the new activity
                startActivity(AscensionIntent);
            }
        });

        //Find the view that shows the Caverna card
        TextView caverna = (TextView) findViewById(R.id.caverna);
        //Set a click listener on that view
        caverna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new Intent to open the {@link CavernaActivity}
                Intent CavernaIntent = new Intent(MainActivity.this, CavernaActivity.class);
                //Start the new activity
                startActivity(CavernaIntent);
            }
        });

        //Find the view that shows the Zombie Dice card
        TextView zombiedice = (TextView) findViewById(R.id.zombieDice);
        //Set a click listener on that view
        zombiedice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new Intent to open the {@link ZombieDiceActivity}
                Intent ZombiediceIntent = new Intent(MainActivity.this, ZombieDiceActivity.class);
                //Start the new activity
                startActivity(ZombiediceIntent);
            }
        });

        //Find the view that shows the 7 Wonders card
        TextView sevenWonders = (TextView) findViewById(R.id._7Wonders);
        //Set a click listener on that view
        sevenWonders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new Intent to open the {@link SevenWondersActivity}
                Intent SevenWondersIntent = new Intent(MainActivity.this, SevenWondersActivity.class);
                //Start the new activity
                startActivity(SevenWondersIntent);
            }
        });


    }

}