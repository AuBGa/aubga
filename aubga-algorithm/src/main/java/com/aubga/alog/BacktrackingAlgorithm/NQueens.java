package com.aubga.alog.BacktrackingAlgorithm;

import java.util.LinkedList;

/**
 * 八皇后-回溯算法
 */
public class NQueens {
    public static LinkedList<LinkedList<LinkedList<String>>> res = new LinkedList<>();

    public static void main(String[] args) {
        LinkedList<LinkedList<String>> board = new LinkedList<>();
        // 初始化棋盘，全部设为"."
        initialization(board, 8);
        // 回溯求解
        trackback(board, 0);
        // 打印输出
        res.forEach(s->{
            System.out.println(s);
        });
        System.out.println(res.size());
    }

    public static void initialization(LinkedList<LinkedList<String>> board, int n){
        for(int i = 0; i < n; i++){
            LinkedList<String> tmp = new LinkedList<>();
            for(int j = 0; j < n; j++){
                tmp.add(".");
            }
            board.add(tmp);
        }
    }

    public static void trackback(LinkedList<LinkedList<String>> board, int row){
        // 触发结束条件，注意使用深拷贝。
        if(row == board.size()){
            int n = board.size();
            LinkedList<LinkedList<String>> tmp = new LinkedList<>();
            for(int i = 0; i < n; i++){
                tmp.add(new LinkedList<String>());
            }
            for(int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tmp.get(i).add(board.get(i).get(j));
                }
            }
            res.add(tmp);
            return;
        }

        for(int i = 0; i < board.size(); i++){
            // 排除不合法的列
            if(!isValid(board, row, i)){
                continue;
            }
            // 做选择
            board.get(row).set(i, "Q");
            // 进入下一行决策
            trackback(board, row+1);
            // 撤销选择，回溯
            board.get(row).set(i, ".");
        }
    }

    public static boolean isValid(LinkedList<LinkedList<String>> board, int row, int col){
        // 由于是从0行开始添加皇后，所以有皇后的地方只可能出现在row行之前的行上，因此只需判断该列方向以及左上和右上是否有皇后
        // 判断该列上是否已有皇后
        for(int i = row-1; i >= 0; i--){
            if(board.get(i).get(col).equals("Q")){
                return false;
            }
        }

        // 判断左上是否有皇后
        int tmp = col;
        for(int i = row-1; i >= 0; i--){
            if(col == 0){
                break;
            }
            if(board.get(i).get(--col).equals("Q")){
                return false;
            }
        }

        //判断右上是否有皇后

        for(int i = row-1; i >= 0; i--){
            if(tmp == board.size()-1){
                break;
            }
            if(board.get(i).get(++tmp).equals("Q")){
                return false;
            }
        }

        return true;
    }
}