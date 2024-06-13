import java.util.Arrays;
import java.util.HashSet;

public class ProvinceProblem {
    public static void main(String[] args) {
        int[][] input = { { 1, 0, 0, 1 }, { 0, 1, 1, 0 }, { 0, 1, 1, 1 }, { 1, 0, 1, 1 } };
        System.out.println("Result is " + findCircleNum(input));
    }

    public static int findCircleNum(int[][] isConnected) {

        UnionFind unionFind = new UnionFind(isConnected.length);

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    // they are connected, call unionByRank
                    int element1 = i + 1;
                    int element2 = j + 1;

                    unionFind.unionByRank(element1, element2);
                }
            }
        }

        HashSet<Integer> provinces = new HashSet<>();
        // provinces.addAll(Arrays.asList(unionFind.getParents()));
        for (int i = 1; i < unionFind.getParents().length; i++) {
            provinces.add(unionFind.find(i));
        }
        return provinces.size();
    }
}

class UnionFind {
    private Integer[] parents;
    private Integer[] rank;

    public UnionFind(int i) {
        this.parents = new Integer[i + 1];
        this.rank = new Integer[i + 1];
        Arrays.fill(this.parents, 0);
        Arrays.fill(this.rank, 0);
        for (int x = 1; x < this.parents.length; x++) {
            this.parents[x] = x;
        }
    }

    public int find(int i) {
        if (parents[i] == i) {
            return i;
        }

        int result = find(parents[i]);

        return result;
    }

    public void unionByRank(int element1, int element2) {
        int parent1 = find(element1);
        int parent2 = find(element2);

        int rank1 = rank[parent1];
        int rank2 = rank[parent2];

        if (element1 == element2) {
            return;
        }

        if (rank1 > rank2) {
            // combine rank2 to rank1
            this.parents[parent2] = parent1;

        } else if (rank1 < rank2) {
            // combine rank1 to rank2
            this.parents[parent1] = parent2;

        } else {
            this.parents[parent2] = parent1;
            this.rank[parent1] = this.rank[parent1] + 1;
        }
    }

    public Integer[] getParents() {
        Integer[] result = new Integer[this.parents.length];
        System.arraycopy(this.parents, 0, result, 0, this.parents.length);
        return result;
    }
}