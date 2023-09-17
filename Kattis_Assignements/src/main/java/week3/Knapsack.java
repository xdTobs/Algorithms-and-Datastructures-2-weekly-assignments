package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knapsack {
    static List<KeyValue> input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<KnapsackPath> results = new ArrayList<>();

        do {
            int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            input = new ArrayList<>();
            int weightCapacity = n[0];
            int inputSize = n[1];
            for (int i = 0; i < inputSize; i++) {
                int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                input.add(new KeyValue(line[1],line[0]));
            }
            results.add(knapsack(inputSize-1,weightCapacity));

        } while(br.ready());

        for (KnapsackPath path : results){
            System.out.println(path.totalCost);
            for(KeyValue kv : path.indexes){
                System.out.print(kv.value+ " ");
            }
            System.out.println();
        }

    }



    public static KnapsackPath knapsack(int index,int size){
        if(index<0)
            return new KnapsackPath(0);


        if(size >= input.get(index).weight){
            KnapsackPath p1 = knapsack(index-1, size);
            KnapsackPath p2 = knapsack(index-1,size-input.get(index).weight);
            if(p1.totalCost> p2.totalCost){
                return p1;
            }
            else{
                p2.indexes.add(input.get(index));
                p2.totalCost+=p2.indexes.get(p2.indexes.size()-1).value;
                return p2;
            }

        }
        return knapsack(index-1, size);
    }

    static class KeyValue{
        public int weight;
        public int value;

        public KeyValue(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
    static class KnapsackPath{
        int totalCost;

        List<KeyValue>indexes;

        public KnapsackPath(int totalCost) {
            this.totalCost = totalCost;
            this.indexes = new ArrayList<>();
        }
    }
}
