import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public static void main(String[] args) {
        Random fortuna = new Random();
        int arraySizeRandom = fortuna.nextInt(20) + 2; // +2 because random can also return 0
        int[] arrayRandom = new int[arraySizeRandom];
        for (int i = 0; i < arrayRandom.length; i++) {
            arrayRandom[i] = fortuna.nextInt(100);
        }
        //TEST
        //int[] arrayRandom = {22, 0, 1, 39, 99, 51, 83, 7, 85, 55};

        System.out.println("Random Array: ");
        System.out.println(Arrays.toString(arrayRandom));

        arrayRandom = MergeSort(arrayRandom);

        System.out.println("Sorted Array: ");
        System.out.println(Arrays.toString(arrayRandom));
        if (isSorted(arrayRandom)) {
            System.out.println("Now it is sorted!");
        } else {
            System.out.println("Error! Array are not sorted!");
        }
    }

    public static boolean isSorted(int[] inputArray) {
        if (inputArray.length < 1)
            return true;
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i - 1] > inputArray[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] addElement(int[] inputArray, int inputElement) {
        int[] help = new int[inputArray.length + 1];

        for (int i = 0; i < inputArray.length; i++) {
            help[i] = inputArray[i];
        }
        help[help.length - 1] = inputElement;
        return help;
    }

    public static int[] removeFirstElement(int[] inputArray) {
        if (inputArray.length > 0) {
            int[] help = new int[inputArray.length - 1];
            if (inputArray.length > 0) {
                for (int i = 1; i < inputArray.length; i++) {
                    help[i - 1] = inputArray[i];
                }
            }
            return help;
        }
        return new int[0];
    }

    public static int[] MergeSort(int[] inputArray) {
        if (inputArray.length <= 1) {
            return inputArray;
        }

        int leftSize = inputArray.length / 2;
        int[] leftArray = new int[leftSize];
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = inputArray[i];
        }

        int[] rightArray = new int[inputArray.length - leftSize];
        for (int i = leftSize; i < inputArray.length; i++) {
            rightArray[i - leftSize] = inputArray[i];
        }
        return merge(MergeSort(leftArray), MergeSort(rightArray));
    }

    public static int[] merge(int[] leftArray, int[] rightArray) {
        int[] mergeArray = new int[0];

        // its always the first element of the Array because i remove the first element oft the source after it is put in the mergeArray
        while (leftArray.length != 0 && rightArray.length != 0) {
            if (leftArray[0] < rightArray[0]) {
                mergeArray = addElement(mergeArray, leftArray[0]);
                leftArray = removeFirstElement(leftArray);
            } else {
                mergeArray = addElement(mergeArray, rightArray[0]);
                rightArray = removeFirstElement(rightArray);
            }
        }
        while (leftArray.length != 0) {
            mergeArray = addElement(mergeArray, leftArray[0]);
            leftArray = removeFirstElement(leftArray);
        }
        while (rightArray.length != 0) {
            mergeArray = addElement(mergeArray, rightArray[0]);
            rightArray = removeFirstElement(rightArray);
        }
        return mergeArray;
    }
}