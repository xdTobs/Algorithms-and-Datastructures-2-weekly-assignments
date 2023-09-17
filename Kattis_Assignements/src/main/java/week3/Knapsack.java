package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Knapsack {
    static List<Integer> inputWeights;
    static List<Integer> inputValues;

    static int[][] memo;

    static List<Integer>[][] memoPaths;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int results;

        do {
            int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            inputWeights = new ArrayList<>();
            inputValues = new ArrayList<>();
            int weightCapacity = n[0];
            int inputSize = n[1];
            memo = new int[inputSize+1][weightCapacity+1];
            memoPaths = new ArrayList[inputSize+1][weightCapacity+1];
            for(List<Integer>[] arr : memoPaths) Arrays.fill(arr, new ArrayList<>());
            for(int[] arr : memo) Arrays.fill(arr, -1);
            for (int i = 0; i < inputSize; i++) {
                int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                inputValues.add(line[0]);
                inputWeights.add(line[1]);

            }
            results = (knapsack(inputSize-1,weightCapacity));
            System.out.println(results);
            //List<Integer> path = memoPaths[0][weightCapacity];
            //System.out.println(path.size());
            //System.out.println(path.stream().map(Objects::toString).collect(Collectors.joining(" ")));


        } while(br.ready());

    }



    public static int knapsack(int index,int size){
        if(index==0){
            memo[index][size] = 0;
            return memo[index][size];
        }

        if(memo[index][size] != -1) return memo[index][size];
        if(size >= inputWeights.get(index)){
            memo[index][size] = knapsack(index-1, size);
            memoPaths[index][size] = new ArrayList<>(memoPaths[index - 1][size]);


            memo[index][size-inputWeights.get(index)] = knapsack(index-1, size-inputWeights.get(index))+inputValues.get(index);
            memoPaths[index][size - inputWeights.get(index)] = new ArrayList<>(memoPaths[index - 1][size - inputWeights.get(index)]);
            memoPaths[index][size - inputWeights.get(index)].add(index);
            int opt1 = memo[index][size];
            int opt2 = memo[index][size-inputWeights.get(index)];

            return Math.max(opt1,opt2);
        }
        memo[index][size] = knapsack(index-1, size);
        memoPaths[index][size] = new ArrayList<>(memoPaths[index - 1][size]);
        return memo[index][size];
    }

}
