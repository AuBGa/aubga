package com.aubga.alog.BacktrackingAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class EightQueen {

    List<String[][]>  list =new ArrayList<>();

    // 初始化棋盘，全部设为"."
    void initialization(String[][] board,int row) {
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                board[i][j] = ".";
            }
        }
    }

    public void backTrack(String[][] board,int row) {
        if(row == board.length) {

            String[][] tmp = new String[8][8];
           for(int i=0;i<board.length;i++) {
                for(int j=0;j<board[0].length;j++) {
                   tmp[i][j] = board[i][j];
                }

            }

            list.add(tmp);
            return;
        }

        int n = board[row].length;

        for(int col = 0;col < n ; col ++) {
            //排查不合法
            if(!isValid(board,row,col)) {
                continue;
            }
            //做选择
            board[row][col] = "Q";
            //进入下一行决策
            backTrack(board,row+1);
            //撤消选择
            board[row][col] = ".";
        }
    }

    /**
     * 判断放置Q的节点是否合适
     * @param board
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(String[][] board, int row, int col) {
        int n = board.length;
        //检查列是否有皇后互相冲突
        for(int i=0;i<n;i++) {
            if(board[i][col] == "Q") {
                return false;
            }
        }
        //检查右上角是否有皇后互相冲突
        for(int i=row-1,j=col+1;i>=0&&j<n;i--,j++) {
            if(board[i][j] == "Q") {
                return false;
            }
        }
        //检查左上角是否有皇后互相冲突
        for(int i=row-1,j=col-1;i>=0&&j>=0;i--,j--) {
            if(board[i][j] == "Q") {
                return false;
            }
        }


        return true;
    }

    public static void main(String[] args) {
        EightQueen eq = new EightQueen();

        String[][] board = new String[8][8];


        eq.initialization(board,8);
        eq.backTrack(board,0);

        System.out.println(eq.list.size());

        eq.list.forEach(s->{

            for(int i=0;i<s.length;i++) {
                for(int j=0;j<s[0].length;j++) {
                    System.out.print(s[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        });
    }
}
