package love.moon.algorithm.leetcode.array;

import love.moon.util.Assert;

import java.util.Arrays;

public class CheckStraightLine1232 {


    public static void main(String[] args) {
        CheckStraightLine1232 sol=new CheckStraightLine1232();
        int[][] coordinates={{0,0},{0,1},{0,-1}};
        Assert.assertTrue(sol.checkStraightLine(coordinates));
    }

    public boolean checkStraightLine(int[][] coordinates) {
        if(coordinates.length<2){
            return true;
        }
        for (int i = 1; i < coordinates.length; i++) {

            Arrays.sort(coordinates);
        }

        if (coordinates[1][0] == coordinates[0][0]) {
            for (int i = 1; i < coordinates.length; i++) {
                if (coordinates[i][1] - coordinates[i - 1][1] != 1) {
                    return false;
                }
            }
        } else if (coordinates[1][1] == coordinates[0][1]) {
            for (int i = 1; i < coordinates.length; i++) {
                if (coordinates[i][0] - coordinates[i - 1][0] != 1) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < coordinates.length; i++) {
                if (coordinates[i][0] - coordinates[i - 1][0] != 1 || coordinates[i][1] - coordinates[i - 1][1] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
