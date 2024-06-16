import java.util.*;

public class RedundantConnection {
    public static void main(String[] args) {
        RedundantConnection sol = new RedundantConnection();

        int[][] edges = { { 1, 2 }, { 1, 3 }, { 2, 3 } };
        int[] res = sol.findRedundantConnection(edges);
        System.out.println(res[0] + "," + res[1]);

        int[][] edges2 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } };
        res = sol.findRedundantConnection(edges2);
        System.out.println(res[0] + "," + res[1]);

        int[][] edges3 = { { 1, 3 }, { 3, 4 }, { 1, 5 }, { 3, 5 }, { 2, 3 } };
        res = sol.findRedundantConnection(edges3);
        System.out.println(res[0] + "," + res[1]);
    }

    private int[] parent;
    private int[] rank;

    public int[] findRedundantConnection(int[][] edges) {
        this.parent = new int[edges.length + 1];
        this.rank = new int[edges.length + 1];
        int[] result = new int[2];
        for (int i = 0; i < parent.length; i++) {
            this.parent[i] = i;
        }
        Arrays.fill(this.rank, 0);

        for (int[] edge : edges) {
            if (!mergeByRank(edge[0], edge[1])) {
                System.arraycopy(edge, 0, result, 0, edge.length);
            }
        }
        return result;
    }

    public int find(int element) {
        if (parent[element] == element) {
            return element;
        }

        return find(parent[element]);
    }

    public boolean mergeByRank(int elementA, int elementB) {
        int parentA = find(elementA);
        int parentB = find(elementB);

        if (parentA == parentB) {
            return false;
        }

        if (rank[parentA] > rank[parentB]) {
            parent[parentB] = parentA;
        } else if (rank[parentA] < rank[parentB]) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
            rank[parentA] += 1;
        }

        return true;
    }
}

/*
 * 
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 */