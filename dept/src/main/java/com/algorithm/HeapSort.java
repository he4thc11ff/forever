package com.algorithm;

public class HeapSort {

    public static void main(String[] args) {
//        int[] origin = {1, 6, 7, 2, 3, 4, 5};
        int[] origin = {1, 6, 7, 2, 3, 4, 5, 1213,121,3,2,3,1,4,5,6,4,7,454,4,55,34,32,2,1};

        MaxHeap maxHeap = new MaxHeap();
        maxHeap.createMaxHeap(origin);

        for (int i = 0; i < maxHeap.array.length; i++) {
            System.out.print(maxHeap.array[i] + " ");
        }
        System.out.println();

//        maxHeap.heapSort();
//        for (int i = 0; i < maxHeap.length; i++) {
//            System.out.print(maxHeap.array[i] + " ");
//        }

        for (int i = 0; i < maxHeap.length; i++) {
            System.out.println(maxHeap.poll());
        }
    }

    private static class MaxHeap {
        private int readLimit = -1;
        private int length = -1;
        private int[] array = null;

        public int[] createMaxHeap(int[] array) {
            this.array = array;
            this.length = array.length;
            this.readLimit = array.length - 1;

            for (int i = array.length / 2; i >= 0; i--) {
                maxHeapify(i, length - 1);
            }
            return array;
        }

        public void swap(int i, int j) {
            if (i == j) return;
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        public void maxHeapify(int parent, int length) {
            int left = parent * 2 + 1;
            int right = parent * 2 + 2;
            if (left > length) left = parent;
            if (right > length) right = parent;

            if (array[left] >= array[right] && array[left] > array[parent]) {
                swap(left, parent);
                maxHeapify(left, length);
            }
            if (array[right] >= array[left] && array[right] > array[parent]) {
                swap(right, parent);
                maxHeapify(right, length);
            }
        }

        public void heapSort() {
            for (int i = length - 1; i > 0; i--) {
                swap(0, i);
                maxHeapify(0, i - 1);
            }
        }

        public int poll() {
            if (readLimit < 0) {
                throw new ArrayIndexOutOfBoundsException(readLimit);
            }
            int result = array[0];

            swap(0, readLimit--);
            maxHeapify(0, readLimit);

            return result;
        }
    }
}
