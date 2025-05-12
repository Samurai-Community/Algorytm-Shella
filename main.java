// Импортируем необходимые классы
import java.util.Arrays;    // для удобной печати массивов
import java.util.Random;    // для генерации случайных чисел
import java.util.Scanner;   // для ввода данных с клавиатуры

public class Main { // Главный класс программы

    // Метод сортировки Шелла (Shell Sort)
    public static void shellSort(int[] array) {
        int n = array.length; // Получаем длину массива

        // Начинаем с шага (gap), равного половине длины массива, и делим его пополам на каждой итерации
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Перебираем элементы массива начиная с gap
            for (int i = gap; i < n; i++) {
                int temp = array[i]; // Временное хранилище текущего элемента
                int j;

                // Перемещаем элементы, которые больше temp, на gap позиций вперёд
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }

                // Вставляем temp на правильное место
                array[j] = temp;
            }
        }
    }

    // Генерация случайного массива
    public static int[] generateRandomArray(int size, int maxValue) {
        Random rand = new Random(); // Создаём генератор случайных чисел
        int[] array = new int[size]; // Создаём массив нужного размера

        // Заполняем массив случайными числами от 0 до maxValue
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(maxValue + 1);
        }

        return array; // Возвращаем массив
    }

    // Чтение массива от пользователя
    public static int[] readArrayFromInput(Scanner scanner) {
        System.out.println("Введите элементы массива через пробел:");
        String[] parts = scanner.nextLine().trim().split("\\s+"); // Разделяем введённую строку по пробелам
        int[] array = new int[parts.length]; // Создаём массив нужной длины

        try {
            // Преобразуем каждый элемент строки в число
            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i]);
            }
        } catch (NumberFormatException e) {
            // Если пользователь ввёл не число — выводим ошибку
            System.out.println("Ошибка: убедитесь, что вы ввели только целые числа.");
            return null; // Возвращаем null, если ошибка
        }

        return array; // Возвращаем корректный массив
    }

    // Измерение времени и использования памяти
    public static void measureTime(Runnable task) {
        System.gc(); // Запрашиваем сборку мусора для точности измерения памяти
        long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(); // Память до выполнения
        long start = System.nanoTime(); // Время до выполнения

        task.run(); // Выполняем задачу

        long end = System.nanoTime(); // Время после выполнения
        long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(); // Память после

        // Выводим результаты
        System.out.println("Время выполнения: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Использовано памяти: " + (memAfter - memBefore) + " байт");
    }

    // Оценка времени выполнения (теоретическая)
    public static void estimateTime(int n) {
        double estimatedTime = Math.pow(n, 1.5) * 0.00001; // Оценка O(n^1.5)
        System.out.printf("Оценка времени для n = %d: ~%.4f мс%n", n, estimatedTime);
    }

    // Оценка использования памяти
    public static void estimateMemory(int n) {
        int memoryBytes = n * 4 + 64; // 4 байта на int + немного на накладные данные
        System.out.printf("Оценка памяти для n = %d: %d байт%n", n, memoryBytes);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Создаём сканер для ввода
        int[] array = null; // Изначально массив пуст

        // Бесконечный цикл меню
        while (true) {
            // Выводим меню
            System.out.println("\nCopyright: Naniak Pavlo 123903\n");
            System.out.println("\nМеню управления:");
            System.out.println("1. Сгенерировать массив и отсортировать методом Шелла");
            System.out.println("2. Показать текущий массив");
            System.out.println("3. Оценить время сортировки");
            System.out.println("4. Оценить использование памяти");
            System.out.println("5. Выход");
            System.out.println("6. Ввести массив вручную");
            System.out.print("Выберите действие: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine()); // Преобразуем ввод в число
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число!");
                continue; // Возвращаемся к началу меню
            }

            switch (choice) {
                // Генерация случайного массива и сортировка
                case 1 -> {
                    System.out.print("Введите размер массива: ");
                    int n = Integer.parseInt(scanner.nextLine()); // Ввод размера
                    array = generateRandomArray(n, 1000); // Генерация массива
                    System.out.println("Исходный массив:");
                    System.out.println(Arrays.toString(array)); // Печатаем до сортировки

                    int[] finalArray = array;
                    measureTime(() -> shellSort(finalArray)); // Сортировка с замером времени

                    System.out.println("Отсортированный массив:");
                    System.out.println(Arrays.toString(array)); // Печатаем после сортировки
                }

                // Показ текущего массива
                case 2 -> {
                    if (array == null) {
                        System.out.println("Сначала создайте или введите массив!");
                    } else {
                        System.out.println("Текущий массив:");
                        System.out.println(Arrays.toString(array));
                    }
                }

                // Оценка времени
                case 3 -> {
                    System.out.print("Введите размер массива для оценки: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    estimateTime(n);
                }

                // Оценка памяти
                case 4 -> {
                    System.out.print("Введите размер массива для оценки: ");
                    int n = Integer.parseInt(scanner.nextLine());
                    estimateMemory(n);
                }

                // Выход из программы
                case 5 -> {
                    System.out.println("Выход из программы...");
                    return; // Завершаем программу
                }

                // Ввод массива вручную
                case 6 -> {
                    int[] manualArray = readArrayFromInput(scanner); // Читаем массив от пользователя
                    if (manualArray != null) {
                        array = manualArray; // Сохраняем массив
                        System.out.println("Исходный массив:");
                        System.out.println(Arrays.toString(array));

                        int[] finalArray = array;
                        measureTime(() -> shellSort(finalArray)); // Сортируем с замером

                        System.out.println("Отсортированный массив:");
                        System.out.println(Arrays.toString(array));
                    }
                }

                // Неверный выбор
                default -> System.out.println("Неверный символ. Повторите попытку.");
            }
        }
    }
}
