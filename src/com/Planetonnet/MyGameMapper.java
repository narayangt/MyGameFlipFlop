package com.Planetonnet;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Random;
import java.util.Scanner;

public class MyGameMapper {

    protected int rowNum;
    protected int colNum;

    protected int[][] gameMap;      // holds game chars
    protected int[][] fieldMap;     // holds chars status. 0: not flipped, 1: flipped

    protected int[][] selection;    // holds user selection
                                    // [0]: first selection
                                    // [1]: second selection
                                    // [][0]: row
                                    // [][1]: col

    public MyGameMapper(int row, int col){
        this.rowNum         =   row;
        this.colNum         =   col;
        this.gameMap        =   new int[row][col];
        this.fieldMap       =   new int[row][col];
        this.selection      =   new int[2][2];
        this.initGame();
        this.displayGameMap();
    }
    public void play(){
        boolean play=true;
        boolean isFirst=true;
        while (play){
            if(isFirst)
                System.out.println("Enter First Selection");
            else
                System.out.println("Enter Second Selection");
            int row=getInput("Row: ");
            int col=getInput("Col: ");

            // row or col 1 means 0 in memory
            row--;
            col--;

            if(fieldMap[row][col]==0) {
                if (isFirst) {
                    // taking first selection
                    this.fieldMap[row][col] = 1;
                    this.selection[0][0] = row;
                    this.selection[0][1] = col;

                    // take next input as second selection
                    isFirst = false;
                } else {
                    // taking second selection.

                    this.selection[1][0] = row;
                    this.selection[1][1] = col;

                    int preSelRow = selection[0][0];
                    int preSelCol = selection[0][1];

                    if (gameMap[preSelRow][preSelCol] == gameMap[row][col]) {
                        //matched: flip cur sel
                        this.fieldMap[row][col] = 1;
                        System.out.println("Matched!!");
                    } else {
                        // not matched, unFlip previously flipped char
                        this.fieldMap[preSelRow][preSelCol] = 0;
                        System.out.println("Does not matched!!");
                    }
                    this.displayFieldMap();
                    int choice = getInput("Enter positive number to continue, below 0 to exit: ");
                    play = choice > 0 ? true : false;

                    // take next input as first selection
                    isFirst = true;
                }
            }
            else{
                System.out.println("Item already flipped. Enter Again");
            }
        }
    }






    /// protected methods


    // init game map;
    protected void initGame(){
        // min and max value
        int min=1, max=10;
        Random rnd=new Random();
        for (int i =0; i< this.rowNum;i++)
            for (int j=0;j<this.colNum;j++){
                this.gameMap[i][j]=rnd.nextInt(max-min)+min;
                this.fieldMap[i][j]=0;
            }
    }

    // display game map

    protected void displayGameMap(){
        System.out.println("Welcome to Flip Flop game");
        System.out.println("Displaying Game Map");
        for (int i =0; i< this.rowNum;i++) {
            for (int j = 0; j < this.colNum; j++) {
                System.out.print(this.gameMap[i][j]+ " ");
            }
            System.out.println();
        }
    }

    protected void displayFieldMap(){
        System.out.println("Displaying Game Field ");
        for (int i =0; i< this.rowNum;i++) {
            for (int j = 0; j < this.colNum; j++) {
                System.out.print(this.fieldMap[i][j]+ " ");
            }
            System.out.println();
        }
    }

    // take int input from input
    protected int getInput(String txToDisplay){
        Scanner input= new Scanner(System.in);
        System.out.print(txToDisplay);
        return input.nextInt();

    }

}
