import java.util.Arrays;

public class SmoothSort {

    // Define the Leonardo numbers
    // Определение чисел Леонардо
    static int leonardo(int k)
    {
        if (k < 2) {
            return 1;
        }
        return leonardo(k - 1) + leonardo(k - 2) + 1;
    }

    // Построение кучи Леонардо путем слияния
    // пар соседних деревьев
    static void heapify(int[] arr, int start, int end)
    {
        int i = start;
        int j = 0;
        int k = 0;

        while (k < end - start + 1) {
            if ((k & 2) != 0) {
                j = j + i;
                i = i/2 ;
            }
            else {
                i = i + j;
                j = j/2;
            }

            k += 1;
        }

        while (i > 0) {
            j = j/2;
            k = i + j;
            while (k < end) {
                if (arr[k] > arr[k - i]) {
                    break;
                }
                int temp = arr[k];
                arr[k] = arr[k - i];
                arr[k - i] = temp;
                k = k + i;
            }

            i = j;
        }
    }

    // Функция сглаживающей сортировки
    static int[] smoothSort(int[] arr)
    {
        int n = arr.length;

        int p = n - 1;//индекс последнего элемента
        int q = p;//представляет верхнюю границу диапазона элементов, которые должны быть учтены при построении кучи
        int r = 0; // текущий индекс в последовательности чисел Леонардо

        // Построение кучи Леонардо путем слияния
        // пар соседних деревьев
        while (p > 0) {
            if ((r & 4) == 0) {
                heapify(arr, r, q);
            }

            if (leonardo(r) == p) {
                r = r + 1;
            }
            else {
                r = r - 1;
                q = q - leonardo(r);
                heapify(arr, r, q);
                q = r - 1;
                r = r + 1;
            }

            int temp = arr[0];
            arr[0] = arr[p];
            arr[p] = temp;
            p = p - 1;
        }

        // Преобразование кучи Леонардо обратно в массив
        for (int i = 0; i < n - 1; i++) {
            int j = i + 1;
            while (j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j = j - 1;
            }
        }

        return arr;
    }

    // Главный метод
    public static void main(String[] args)
    {
        int[] arr = { 1, 7, 8, 2, 3, 5, 4, 6 };

        // Исходный массив
        System.out.print("Input: ");
        System.out.println(Arrays.toString(arr));

        // Вызов функции сортировки
        arr = smoothSort(arr);

        // Отсортированный массив
        System.out.print("Output: ");
        System.out.println(Arrays.toString(arr));
    }
}
