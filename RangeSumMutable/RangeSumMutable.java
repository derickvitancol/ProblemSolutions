public class RangeSumMutable {
    public static void main(String[] args) {
        SegmentTree sTree = new SegmentTree();
<<<<<<< HEAD
        int[] arr = { 1, 3, 5 };
        sTree.init(arr);
        System.out.println(sTree.rangeSum(1, 1, arr.length, 1, 3));
        sTree.update(1, 1, arr.length, 3, 10);
        System.out.println(sTree.tree[1]);

        System.out.println(sTree.rangeSum(1, 1, arr.length, 2, 4));
=======
        int[] arr = { 1, 3, 5, 7, 10, 1 };
        sTree.init(arr);
        System.out.println(sTree.tree[0]);
>>>>>>> 5128345000631fcbfa8dc3df63edb79d91a3cdd7
    }

}

class SegmentTree {
    private int[] input;
    public int[] tree;

    public void init(int[] inputArray) {
<<<<<<< HEAD
        this.input = new int[inputArray.length + 1];
        int treeSize = 2 * inputArray.length;
        this.tree = new int[treeSize];
        System.arraycopy(inputArray, 0, this.input, 1, inputArray.length);
        build(1, 1, inputArray.length);
    }

    public void build(int node, int left, int right) {
=======
        this.input = new int[inputArray.length];
        int treeSize = 2 * inputArray.length - 1;
        this.tree = new int[treeSize];
        System.arraycopy(inputArray, 0, this.input, 0, inputArray.length);
        build(0, 0, input.length - 1);
    }

    public void build(int node, int left, int right) {
        System.out.println("node: " + node + "left: " + left + " right: " + right);
>>>>>>> 5128345000631fcbfa8dc3df63edb79d91a3cdd7
        if (left == right) {
            this.tree[node] = input[left];
            return;
        }

        int mid = (left + right) / 2;
<<<<<<< HEAD
        int leftChildIndex;
        int rightChildIndex;
        leftChildIndex = 2 * node;
        rightChildIndex = 2 * node + 1;

=======
        int leftChildIndex = 2 * node + 1;
        int rightChildIndex = 2 * node + 2;
>>>>>>> 5128345000631fcbfa8dc3df63edb79d91a3cdd7
        build(leftChildIndex, left, mid);
        build(rightChildIndex, mid + 1, right);
        tree[node] = tree[leftChildIndex] + tree[rightChildIndex];
    }
<<<<<<< HEAD

    public void update(int node, int left, int right, int index, int value) {
        if (left > index || right < index) {
            return;
        }

        if (left == right) {
            if (index == left) {
                input[index] = value;
                tree[node] = value;
                return;
            }
        }

        int mid = (left + right) / 2;
        int leftChildIndex;
        int rightChildIndex;
        leftChildIndex = 2 * node;
        rightChildIndex = 2 * node + 1;

        update(leftChildIndex, left, mid, index, value);
        update(rightChildIndex, mid + 1, right, index, value);
        tree[node] = tree[leftChildIndex] + tree[rightChildIndex];
    }

    public int rangeSum(int node, int left, int right, int requiredStart, int requiredEnd) {
        if (right < requiredStart || left > requiredEnd) {
            return 0;
        }

        if (left >= requiredStart && right <= requiredEnd) {
            return tree[node];
        }

        int mid = (left + right) / 2;
        int leftChildIndex;
        int rightChildIndex;
        leftChildIndex = 2 * node;
        rightChildIndex = 2 * node + 1;

        int leftSum = rangeSum(leftChildIndex, left, mid, requiredStart, requiredEnd);
        int rightSum = rangeSum(rightChildIndex, mid + 1, right, requiredStart, requiredEnd);
        return leftSum + rightSum;
    }
=======
>>>>>>> 5128345000631fcbfa8dc3df63edb79d91a3cdd7
}