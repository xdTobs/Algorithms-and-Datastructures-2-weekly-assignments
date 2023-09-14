package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Robots_on_a_Grid {

    public static int[][]cache;
    public static boolean[][]cacheCalculated;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Character>> map = new ArrayList<>();
        while(br.ready()){
            String line = br.readLine();
            List<Character> characterLine = new ArrayList<>();
            for(char c : line.toCharArray()){
                characterLine.add(c);
            }
            map.add(characterLine);
        }
        cache = new int[n][n];
        cacheCalculated = new boolean[n][n];
        for(int[] arr : cache){
            Arrays.fill(arr,0);
        }
        for(boolean[] arr : cacheCalculated){
            Arrays.fill(arr, false);
        }

        System.out.println(paths(map));
    }

    public static int paths(List<List<Character>> map){
        return recursiveStep(map,0,0);
    }

    public static int recursiveStep(List<List<Character>> map, int x, int y){
        if(cacheCalculated[x][y]) return cache[x][y];

        if (x == map.size()-1 && y== map.size()-1) cache[x][y] = 1;
        else if(x == map.size()-1) cache[x][y] = recursiveStep(map,x,y+1);
        else if (y== map.size()-1) cache[x][y] = recursiveStep(map,x+1,y);
        else cache[x][y] = recursiveStep(map,x+1,y)+recursiveStep(map,x,y+1);


        cacheCalculated[x][y] = true;
        return cache[x][y];
    }
}