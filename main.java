import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

   // метод шелла
   public static void shellSort(int[] array) {
       int n = array.length;

       for (int gap = n / 2; gap > 0; gap /= 2) {
           for (int i = gap; i < n; i++) {
               int temp = array[i];
               int j;
               for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                   array[j] = array[j - gap];
               }
               array[j] = temp;
           }
       }
   }


   // генерирует рандомные элементы массива
   public static int[] generateRandomArray(int size, int maxValue) {
       Random rand = new Random();
       int[] array = new int[size];
       for (int i = 0; i < size; i++) {
           array[i] = rand.nextInt(maxValue + 1);
       }
       return array;
   }

   // читает элементы массива которые ввел пользователь
   public static int[] readArrayFromInput(Scanner scanner) {
       System.out.println("Введите элементы массива через пробел:");
       String[] parts = scanner.nextLine().trim().split("\\s+");
       int[] array = new int[parts.length];

       try {
           for (int i = 0; i < parts.length; i++) {
               array[i] = Integer.parseInt(parts[i]);
           }
       } catch (NumberFormatException e) {
           System.out.println("Ошибка: убедитесь, что вы ввели только целые числа.");
           return null;
       }

       return array;
   }

   // функция для замера времени и памяти
   public static void measureTime(Runnable task) {
       System.gc();
       long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
       long start = System.nanoTime();

       task.run();

       long end = System.nanoTime();
       long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

       System.out.println("Время выполнения: " + (end - start) / 1_000_000.0 + " мс");
       System.out.println("Использовано памяти: " + (memAfter - memBefore) + " байт");
   }

   // оценка времени
   public static void estimateTime(int n) {
       double estimatedTime = Math.pow(n, 1.5) * 0.00001;
       System.out.printf("Оценка времени для n = %d: ~%.4f мс%n", n, estimatedTime);
   }

   // оценка памяти
   public static void estimateMemory(int n) {
       int memoryBytes = n * 4 + 64;
       System.out.printf("Оценка памяти для n = %d: %d байт%n", n, memoryBytes);
   }

   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int[] array = null;

       while (true) {
           // меню управления
           System.out.println("\nCopyright: Naniak Pavlo 123903\n");
           System.out.println("\nМеню управления:");
           System.out.println("1. Сгенерировать массив и отсортировать методом Шелла");
           System.out.println("2. Показать текущий массив");
           System.out.println("3. Оценить время сортировки");
           System.out.println("4. Оценить использование памяти");
           System.out.println("5. Выход");
           System.out.println("6. Ввести массив вручную");
           System.out.print("Выберите действие: ");


           // проверяем input на ошибку
           int choice;
           try {
               choice = Integer.parseInt(scanner.nextLine());
           } catch (NumberFormatException e) {
               System.out.println("Ошибка: введите корректное число!");
               continue;
           }

           switch (choice) {
               // выполняем код когда выбрано 1
               case 1 -> {
                   System.out.print("Введите размер массива: ");
                   int n = Integer.parseInt(scanner.nextLine());
                   array = generateRandomArray(n, 1000);
                   System.out.println("Исходный массив:");
                   System.out.println(Arrays.toString(array));
                   System.out.println("Отсортированный массив:");
                   System.out.println(Arrays.toString(array));

                   int[] finalArray = array;
                   measureTime(() -> shellSort(finalArray));
               }
               // выполняем код когда выбрано 2
               case 2 -> {
                   if (array == null) {
                       System.out.println("Сначала создайте или введите массив!");
                   } else {
                       System.out.println("Текущий массив:");
                       System.out.println(Arrays.toString(array));
                   }
               }
               // выполняем код когда выбрано 3
               case 3 -> {
                   System.out.print("Введите размер массива для оценки: ");
                   int n = Integer.parseInt(scanner.nextLine());
                   estimateTime(n);
               }
               // выполняем код когда выбрано 4
               case 4 -> {
                   System.out.print("Введите размер массива для оценки: ");
                   int n = Integer.parseInt(scanner.nextLine());
                   estimateMemory(n);
               }
               // выполняем код когда выбрано 5
               case 5 -> {
                   System.out.println("Выход из программы...");
                   return;
               }
               // выполняем код когда выбрано 6
               case 6 -> {
                   int[] manualArray = readArrayFromInput(scanner);
                   if (manualArray != null) {
                       array = manualArray;
                       System.out.println("Исходный массив:");
                       System.out.println(Arrays.toString(array));

                       int[] finalArray = array;
                       measureTime(() -> shellSort(finalArray));

                       System.out.println("Отсортированный массив:");
                       System.out.println(Arrays.toString(array));
                   }
               }

               default -> System.out.println("Неверный символ. Повторите попытку.");
           }
       }
   }
}
