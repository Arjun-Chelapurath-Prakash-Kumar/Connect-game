package com.example.arjuncp.connectgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activeplayer=0; //red=0 and blue=1 , empty=2

    boolean gameactive = true;
    boolean a= true;

    int[] gameState={2,2,2,2,2,2,2,2,2};

    int[][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void comein (View view) {

        ImageView count = (ImageView) view;

        int tappedplace = Integer.parseInt(count.getTag().toString());

        if(gameState[tappedplace]==2 && gameactive) {

            gameState[tappedplace] = activeplayer;

            count.setTranslationY(-2000);

            if (activeplayer == 0) {
                activeplayer = 1;
                count.setImageResource(R.drawable.red);

            } else {
                activeplayer = 0;
                count.setImageResource(R.drawable.blue);

            }
            count.animate().translationYBy(2000).rotation(3600).setDuration(500);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    gameactive=false;
                    a=false;
                    String winner = "";
                    if (activeplayer == 1) {
                        winner = "Red";
                    } else {
                        winner = "Blue";
                    }
                    Button playButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winText = (TextView) findViewById(R.id.winTextView);

                    winText.setText(winner +" Has Won!!!");
                    playButton.setVisibility(view.VISIBLE);
                    winText.setVisibility(view.VISIBLE);
                }

                if(a && gameState[0] != 2 && gameState[1] != 2 &&gameState[2] != 2 &&gameState[3] != 2 &&gameState[4] != 2 &&gameState[5] != 2 &&gameState[6] != 2 &&gameState[7] != 2 &&gameState[8] != 2 ) {

                    a=false;

                    Button playButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winText = (TextView) findViewById(R.id.winTextView);
                    winText.setText(" The Game is a Draw!!!");
                    playButton.setVisibility(view.VISIBLE);
                    winText.setVisibility(view.VISIBLE);
                }
            }
        }


    }

    public void playAgain(View view) {

        Button playButton = (Button) findViewById(R.id.playAgainButton);
        TextView winText = (TextView) findViewById(R.id.winTextView);
        playButton.setVisibility(view.INVISIBLE);
        winText.setVisibility(view.INVISIBLE);

        GridLayout gridLay = (GridLayout) findViewById(R.id.gridLay);

        for(int i = 0; i < gridLay.getChildCount(); i++) {
            ImageView child = (ImageView) gridLay.getChildAt(i);
            child.setImageDrawable(null);
        }

        activeplayer=0;

        gameactive = true;

        for(int i=0 ; i<gameState.length ; i++)
        {
            gameState[i]=2;
        }




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
