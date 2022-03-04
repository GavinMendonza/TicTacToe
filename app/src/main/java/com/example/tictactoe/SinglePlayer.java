package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SinglePlayer extends AppCompatActivity {


    /*
        playersTurn(){
            status.getText("its your turn, click to play!");

        }


        while gameActive{
            if playersTurn{
                playersTurn();
            }else{
                computersTurn();
            }
        }
     */
    int _computersChoicePositionId=0;
    int _timeTakenByTheComputerInThinking;
    boolean gameActive = true;
    boolean computerIsThinking=false;
    // Player representation
    // 0 - X
    // 1 - O
    boolean flag=false;         //will be used to restart game
    int activePlayer = 0;
    boolean playersTurn=true;
    int[] gameState = {2, 2 , 2, 2, 2, 2, 2, 2, 2};
    //    State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Handler handler=new Handler(); //used to add a small delay before computer makes its decision

        //start of linking image views(slots in the grid)

        ImageView _imgView0=findViewById(R.id.imageView0);
        ImageView _imgView1=findViewById(R.id.imageView1);
        ImageView _imgView2=findViewById(R.id.imageView2);
        ImageView _imgView3=findViewById(R.id.imageView3);
        ImageView _imgView4=findViewById(R.id.imageView4);
        ImageView _imgView5=findViewById(R.id.imageView5);
        ImageView _imgView6=findViewById(R.id.imageView6);
        ImageView _imgView7=findViewById(R.id.imageView7);
        ImageView _imgView8=findViewById(R.id.imageView8);

        //end of linking image views(slots in the grid)

        //start of setting up click listeners
        _imgView0.setOnClickListener(view -> playerTap(view,-1));
        _imgView1.setOnClickListener(view -> playerTap(view,-1));
        _imgView2.setOnClickListener(view -> playerTap(view,-1));
        _imgView3.setOnClickListener(view -> playerTap(view,-1));
        _imgView4.setOnClickListener(view -> playerTap(view,-1));
        _imgView5.setOnClickListener(view -> playerTap(view,-1));
        _imgView6.setOnClickListener(view -> playerTap(view,-1));
        _imgView7.setOnClickListener(view -> playerTap(view,-1));
        _imgView8.setOnClickListener(view -> playerTap(view,-1));

        //end of setting up click listners

    }
    public int computersTurn(int[] gameState){
//        int[] _emptySlotsForComputersTurn=new int[9];
        int _randomNumber=0;
        while (gameState[_randomNumber]!=2){
            _randomNumber= (int) (Math.random() * (8 + 1) + 0);
        }
        return _randomNumber;
    }
    public boolean checkForDraw(int[] gameState){
        boolean emptySquare=false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        return emptySquare;
    }
    public void playerTap(View view, int _computersMove){
        if (computerIsThinking && _computersMove==-1 && gameActive) {
            return;
        }
        computerIsThinking=false;
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (_computersMove!=-1){
            tappedImage= _computersMove;
            switch (_computersMove){
                case 0:
                    _computersChoicePositionId=R.id.imageView0;
                    break;
                case 1:
                    _computersChoicePositionId=R.id.imageView1;break;
                case 2:
                    _computersChoicePositionId=R.id.imageView2;break;
                case 3:
                    _computersChoicePositionId=R.id.imageView3;break;
                case 4:
                    _computersChoicePositionId=R.id.imageView4;break;
                case 5:
                    _computersChoicePositionId=R.id.imageView5;break;
                case 6:
                    _computersChoicePositionId=R.id.imageView6;break;
                case 7:
                    _computersChoicePositionId=R.id.imageView7;break;
                case 8:
                    _computersChoicePositionId=R.id.imageView8;break;


            }
            img=findViewById(_computersChoicePositionId);
        }

        if(!gameActive){
            gameReset(view);
        }else {
            if (gameState[tappedImage] == 2) {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                } else {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                }
                img.animate().translationYBy(1000f).setDuration(300);//probably will have to call this before handler...
                TextView status = findViewById(R.id.status);
                if (playersTurn) {
                    if (checkForDraw(gameState)) {
                        playersTurn = false;
                        status.setText("Computer is thinking...");
                        computerIsThinking=true;
                        if (checkForWin()) return;
                    }
                }else{
                    playersTurn=true;
                    status.setText("Your Turn!");
                }

                if (!playersTurn){
                    int _computersChoice=computersTurn(gameState);
//                    if (checkForDraw(gameState)) {
                    _timeTakenByTheComputerInThinking = (int)(Math.random()*(3000-1000+1)+1000);
                    new Handler().postDelayed(() -> playerTap(view, _computersChoice), _timeTakenByTheComputerInThinking);
//                    playerTap(view, _computersChoice);
//                    }else playersTurn=true;
                }
            }
            // Check if any player has won(I HAVE REPLACED THIS WITH A FUNCTION)
//            for (int[] winPosition : winPositions) {
//                if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
//                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
//                        gameState[winPosition[0]] != 2) {
//                    // Somebody has won! - Find out who!
//                    String winnerStr;
//                    gameActive = false;
//                    if (gameState[winPosition[0]] == 0) {
//                        winnerStr = "X has won.. click to restart!";
//                    } else {
//                        winnerStr = "O has won.. click to restart!";
//                    }
//                    // Update the status bar for winner announcement
//                    TextView status = findViewById(R.id.status);
//                    status.setText(winnerStr);
//
//                }
//            }
            checkForWin();
            //function here
            boolean emptySquare;
            emptySquare=checkForDraw(gameState);
//            for (int squareState : gameState) {
//                if (squareState == 2) {
//                    emptySquare = true;
//                    break;
//                }
//            }
            if (!emptySquare && gameActive) {
                // Game is a draw
                gameActive = false;
                String winnerStr;
                winnerStr = "No one won... click to restart match";
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }

    public boolean checkForWin(){
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                // Somebody has won! - Find out who!
                String winnerStr;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "You have won!! click to restart!";
                } else {
                    winnerStr = "Computer has won! click to restart!";
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }
        }
        if (!gameActive) {
            return true;
        } else return false;
    }

    public void gameReset(View view) {
        activePlayer = 0;
        computerIsThinking=false;
        playersTurn=true;
        flag=true;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        if (flag){
            TextView status = findViewById(R.id.status);
            status.setText("X's Turn - Tap to play");
            gameActive=true;
        }

    }

}

