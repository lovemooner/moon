package love.moon.algorithm.sort.select;

import love.moon.algorithm.sort.ISort;

public class TreeSelectSort implements ISort {

    public  void sort(int[] arr) {
        int len = arr.length;
        int treeSize = 2 * len - 1;  //完全二叉树的节点数
        int low = 0;
        int[] tree = new int[treeSize];   //临时的树存储空间
        //填充叶子节点
        for (int i = len - 1, j = 0; i >= 0; --i, j++) {
            tree[treeSize - 1 - j] = arr[i];
        }
        //填充非终端节点
        for (int i = treeSize - 1; i > 0; i -= 2) {
            tree[(i - 1) / 2] = ((Comparable) tree[i - 1]).compareTo(tree[i]) < 0 ? tree[i - 1] : tree[i];
        }
        //不断移走最小节点
        int minIndex;
        while (low < len) {
            int min = tree[0];  //最小值
            arr[low++] = min;
            minIndex = treeSize - 1;
            //找到最小值的索引
            while (((Comparable) tree[minIndex]).compareTo(min) != 0) {
                minIndex--;
            }
            tree[minIndex] = Integer.MAX_VALUE;  //设置一个最大值标志
            //找到其兄弟节点
            while (minIndex > 0) {  //如果其还有父节点
                if (minIndex % 2 == 0) {  //如果是右节点
                    tree[(minIndex - 1) / 2] = ((Comparable) tree[minIndex - 1]).compareTo(tree[minIndex])
                            < 0 ? tree[minIndex - 1] : tree[minIndex];
                    minIndex = (minIndex - 1) / 2;
                } else {  //如果是左节点
                    tree[minIndex / 2] = ((Comparable) tree[minIndex]).compareTo(tree[minIndex + 1])
                            < 0 ? tree[minIndex] : tree[minIndex + 1];
                    minIndex = minIndex / 2;
                }
            }

        }
    }

}
