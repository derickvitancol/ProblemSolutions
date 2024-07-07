public class RangeSumMutable {
    public static void main(String[] args) {
        SegmentTree sTree = new SegmentTree();
        int[] arr = { 1, 3, 5, 7, 10, 1 };
        sTree.init(arr);
        System.out.println(sTree.tree[0]);
    }

}

class SegmentTree {
    private int[] input;
    public int[] tree;

    public void init(int[] inputArray) {
        this.input = new int[inputArray.length];
        int treeSize = 2 * inputArray.length - 1;
        this.tree = new int[treeSize];
        System.arraycopy(inputArray, 0, this.input, 0, inputArray.length);
        build(0, 0, input.length - 1);
    }

    public void build(int node, int left, int right) {
        System.out.println("node: " + node + "left: " + left + " right: " + right);
        if (left == right) {
            this.tree[node] = input[left];
            return;
        }

        int mid = (left + right) / 2;
        int leftChildIndex = 2 * node + 1;
        int rightChildIndex = 2 * node + 2;
        build(leftChildIndex, left, mid);
        build(rightChildIndex, mid + 1, right);
        tree[node] = tree[leftChildIndex] + tree[rightChildIndex];
    }
}