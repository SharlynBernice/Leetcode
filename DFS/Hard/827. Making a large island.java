class Solution {
    HashMap<Integer,Integer> map=new HashMap<>();
    int R,C;
    int diff[][]={{0,1},{1,0},{0,-1},{-1,0}};
    public int largestIsland(int[][] grid) {
        R=grid.length;
        C=grid[0].length;
        int max=0;
        int id=2;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(grid[i][j]==1){
                    int size=dfs(i,j,grid,id);
                    map.put(id,size);
                    max=Math.max(max,size);
                    id++;
                }
            }
        }
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(grid[i][j]==0){
                    int size=1;
                    Set<Integer> count=new HashSet<>();
                    for(int[] d:diff){
                        int nr=d[0]+i;
                        int nc=d[1]+j;
                        if(nr<R && nr>=0 && nc<C && nc>=0 && grid[nr][nc]>1){
                            count.add(grid[nr][nc]);
                        }
                    }
                    for(int idx:count){
                        size+=map.get(idx);
                    }
                    max=Math.max(size,max);
                }
            }
        }
        return max==0 ? R*C : max;
    }
    private int dfs(int row,int col,int[][] grid,int id){
        if(row<0 || row>=R || col<0 || col>=C || grid[row][col]!=1){
            return 0;
        }
        grid[row][col]=id;
        int size=1;
        for(int[] d:diff){
            int newRow=d[0]+row;
            int newCol=d[1]+col;
            if(newRow<R && newRow>=0 && newCol<C && newCol>=0 && grid[newRow][newCol]==1){
                size+=dfs(newRow,newCol,grid,id);
            }
        }
        return size;
    }
}
