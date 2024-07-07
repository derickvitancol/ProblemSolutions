public class SegmentTree {

    private int treeSize;
    public int[] tree;
    private int[] inputArray;

    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree();
        int[] sample = { 1, 3, 7, 9, 11 };
        segmentTree.init(sample);
        segmentTree.build(0, 0, sample.length - 1);
        System.out.println(segmentTree.tree[0]);
        System.out.println(segmentTree.queryRangeSum(0, 0, 4, 1, 3));
        segmentTree.update(0, 0, 4, 2, 10);
        System.out.println(segmentTree.queryRangeSum(0, 0, 4, 1, 3));
    }

    public void init(int[] input) {
        this.treeSize = 2 * input.length - 1;

        this.tree = new int[treeSize];
        this.inputArray = new int[input.length];
        System.arraycopy(input, 0, this.inputArray, 0, input.length);
    }

    public void build(int node, int start, int end) {
        if (start == end) {
            // Leaf node will have a single element
            this.tree[node] = this.inputArray[start];
        } else {
            int mid = (start + end) / 2;
            build(2 * node + 1, start, mid);
            // Recurse on the right child
            build(2 * node + 2, mid + 1, end);
            // Internal node will have the sum of both of its children
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            // Leaf node
            inputArray[idx] = val;
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (start <= idx && idx <= mid) {
                // If idx is in the left child, recurse on the left child
                update(2 * node + 1, start, mid, idx, val);
            } else {
                // if idx is in the right child, recurse on the right child
                update(2 * node + 2, mid + 1, end, idx, val);
            }
            // Internal node will have the sum of both of its children
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    public int queryRangeSum(int node, int start, int end, int requiredStart, int requiredEnd) {
        if (end < requiredStart || start > requiredEnd) {
            return 0;
        }
        // left + right
        if (requiredStart <= start && end <= requiredEnd) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int leftSum = queryRangeSum(2 * node + 1, start, mid, requiredStart, requiredEnd);
        int rightSum = queryRangeSum(2 * node + 2, mid + 1, end, requiredStart, requiredEnd);
        return leftSum + rightSum;
    }
}