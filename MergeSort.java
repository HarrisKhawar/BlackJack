//Created by Harris Mahmood Khawar on 17/11/2017.

public class MergeSort {
    public static void main(String[] args) {
        double [] array = {1,3,5,3,8,5,6,2,3};
        mergeSort(array);

        for( int i = 0; i<array.length;i++){
            System.out.print(array[i]+" ");
        }
    }
    public static  double [] mergeSort(double [] A) {
        int size = A.length;
        int mid = size / 2;
        int leftSize = mid;
        int rightSize = size - mid;
        double[] left = new double[leftSize];
        double[] right = new double[rightSize];

        if (size > 2) {
            for (int i = 0; i < mid; i++) {
                left[i] = A[i];

            }
            for (int i = mid; i < size; i++) {
                right[i - mid] = A[i];
            }
            mergeSort(left);
            mergeSort(right);
        }
        return merge(left, right, A);

    }

    public static double [] merge(double[] left, double[] right, double[] arr) {
        int leftSize = left.length;
        int rightSize = right.length;
        int i = 0, j = 0, k = 0;
        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
                k++;
            } else {
                arr[k] = right[j];
                k++;
                j++;
            }
        }
        while (i < leftSize) {
            arr[k] = left[i];
            k++;
            i++;
        }
        while (j < leftSize) {
            arr[k] = right[j];
            k++;
            j++;
        }
        return arr;
    }
}
