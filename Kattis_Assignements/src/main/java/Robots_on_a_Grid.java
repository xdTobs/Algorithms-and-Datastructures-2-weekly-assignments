import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


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
        int res = paths(map);
        boolean possible = dfs(map);
        System.out.println(res!=0 ? res :possible ? "THE GAME IS A LIE" : "INCONCEIVABLE");
    }

    public static int paths(List<List<Character>> map){
        return recursiveStep(map,0,0);
    }

    public static int recursiveStep(List<List<Character>> map, int x, int y){
        if(cacheCalculated[x][y]) return cache[x][y];

        if(map.get(x).get(y)=='#') cache[x][y] = 0;
        else if (x == map.size()-1 && y== map.size()-1) cache[x][y] = 1;
        else if(x == map.size()-1) cache[x][y] = recursiveStep(map,x,y+1);
        else if (y== map.size()-1) cache[x][y] = recursiveStep(map,x+1,y);
        else cache[x][y] = recursiveStep(map,x+1,y)+recursiveStep(map,x,y+1);


        cacheCalculated[x][y] = true;
        return cache[x][y];
    }
    public static boolean dfs(List<List<Character>> map){
        boolean[][] visited = new boolean[map.size()][map.size()];
        for(boolean[] arr : visited){
            Arrays.fill(arr, false);
        }

        Stack<Point> stack = new Stack<>();
        stack.add(new Point(0,0));
        visited[0][0] = true;
        while(!stack.isEmpty()){
            Point p = stack.pop();
            if(p.x!=map.size()-1 && map.get(p.x+1).get(p.y)=='.'&&!visited[p.x+1][p.y]){
                stack.add(new Point(p.x+1,p.y));
                visited[p.x+1][p.y] = true;
            }
            if(p.y!=map.size() -1 && map.get(p.x).get(p.y+1)=='.'&&!visited[p.x][p.y+1]){
                stack.add(new Point(p.x,p.y+1));
                visited[p.x][p.y+1] = true;
            }
            if(p.x!=0 && map.get(p.x-1).get(p.y)=='.'&&!visited[p.x-1][p.y]){
                stack.add(new Point(p.x-1,p.y));
                visited[p.x-1][p.y] = true;
            }
            if(p.y!=0 && map.get(p.x).get(p.y-1)=='.'&&!visited[p.x][p.y-1]){
                stack.add(new Point(p.x,p.y-1));
                visited[p.x][p.y-1] = true;
            }
        }
        return visited[map.size()-1][map.size()-1];
    }
    static class Point{
        public int x;
        public int y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}