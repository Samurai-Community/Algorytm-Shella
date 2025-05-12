import java.util.Arrays; // импортируем класс Arrays для вывода массива
import java.util.Random; // импортируем Random для генерации случайных чисел
import java.util.Scanner; // импортируем Scanner для ввода с клавиатуры

public class Main {

    // Метод сортировки Шелла
    public static void shellSort(int[] array) {
        int n = array.length; // длина массива

        // Начинаем с промежутка (gap), равного половине длины массива
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Проходим по элементам массива от gap до конца
            for (int i = gap; i < n; i++) {
                int temp = array[i]; // сохраняем текущий элемент
                int j;

                // Сдвигаем элементы массива, пока они больше temp
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap]; // сдвигаем элемент
                }

                array[j] = temp; // вставляем сохранённое значение
            }
        }
    }

    // Метод генерации случайного массива
    public static int[] generateRandomArray(int size, int maxValue) {
        Random rand = new Random(); // создаём генератор случайных чисел
        int[] array = new int[size]; // создаём массив заданного размера

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(maxValue + 1); // заполняем случайными числами от 0 до maxValue
        }

        return array; // возвращаем сгенерированный массив
    }

    // Метод считывания массива, введённого пользователем
    public static int[] readArrayFromInput(Scanner scanner) {
        System.out.println("Введите элементы массива через пробел:");
        String[] parts = scanner.nextLine().trim().split("\\s+"); // разбиваем строку по пробелам
        int[] array = new int[parts.length]; // создаём массив соответствующей длины

        try {
            for (int i = 0; i < parts.length; i++) {
                array[i] = Integer.parseInt(parts[i]); // преобразуем строку в число
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: убедитесь, что вы ввели только целые числа.");
            return null; // если ошибка — возвращаем null
        }

        return array; // возвращаем массив
    }

    // Метод для измерения времени и памяти выполнения задачи
    public static void measureTime(Runnable task) {
        System.gc(); // сборка мусора перед измерением
        long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(); // использование памяти до
        long start = System.nanoTime(); // время начала

        task.run(); // выполнение задачи

        long end = System.nanoTime(); // время окончания
        long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(); // использование памяти после

        // Вывод результатов
        System.out.println("Время выполнения: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Использовано памяти: " + (memAfter - memBefore) + " байт");
    }

    // Метод оценки времени выполнения (более точная формула)
    public static void estimateTime(int n) {
        double logN = Math.log(n); // логарифм по основанию e
        double estimatedTime = n * logN * logN * 0.000005; // формула оценки
        System.out.printf("Оценка времени для n = %d: ~%.4f мс%n", n, estimatedTime);
    }

    // Метод оценки использования памяти
    public static void estimateMemory(int n) {
        int memoryBytes = 4 * n + 64; // 4 байта на int + накладные расходы
        System.out.printf("Оценка использования памяти для n = %d: %d байт%n", n, memoryBytes);
    }

    // Главный метод
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // создаём объект Scanner для ввода
        int[] array = null; // переменная для хранения текущего массива

        while (true) {
            // Меню
            System.out.println("\nCopyright: Naniak Pavlo 123903\n");
            System.out.println("Меню управления:");
            System.out.println("1. Сгенерировать массив и отсортировать методом Шелла");
            System.out.println("2. Показать текущий массив");
            System.out.println("3. Оценить время сортировки");
            System.out.println("4. Оценить использование памяти");
            System.out.println("5. Выход");
            System.out.println("6. Ввести массив вручную");
            System.out.print("Выберите действие: ");

            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine()); // читаем выбор пользователя
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число!");
                continue; // переход к следующей итерации
            }

            // Обработка выбора
            switch (choice) {
                case 1 -> {
                    System.out.print("Введите размер массива: ");
                    int n = Integer.parseInt(scanner.nextLine()); // считываем размер массива
                    array = generateRandomArray(n, 1000); // создаём массив

                    System.out.println("Исходный массив:");
                    System.out.println(Arrays.toString(array)); // выводим массив до сортировки

                    int[] finalArray = array; // копия ссылки для использования в лямбде
                    measureTime(() -> shellSort(finalArray)); // сортируем и измеряем

                    System.out.println("Отсортированный массив:");
                    System.out.println(Arrays.toString(array)); // выводим результат
                }

                case 2 -> {
                    if (array == null) {
                        System.out.println("Сначала создайте или введите массив!"); // если массив не задан
                    } else {
                        System.out.println("Текущий массив:");
                        System.out.println(Arrays.toString(array)); // вывод текущего массива
                    }
                }

                case 3 -> {
                    System.out.print("Введите размер массива для оценки времени: ");
                    int n = Integer.parseInt(scanner.nextLine()); // считываем размер
                    estimateTime(n); // оцениваем время
                }

                case 4 -> {
                    System.out.print("Введите размер массива для оценки памяти: ");
                    int n = Integer.parseInt(scanner.nextLine()); // считываем размер
                    estimateMemory(n); // оцениваем память
                }

                case 5 -> {
                    System.out.println("Выход из программы..."); // завершаем выполнение
                    return;
                }

                case 6 -> {
                    int[] manualArray = readArrayFromInput(scanner); // считываем вручную
                    if (manualArray != null) {
                        array = manualArray; // сохраняем в текущий массив
                        System.out.println("Исходный массив:");
                        System.out.println(Arrays.toString(array));

                        int[] finalArray = array;
                        measureTime(() -> shellSort(finalArray)); // сортировка и замер

                        System.out.println("Отсортированный массив:");
                        System.out.println(Arrays.toString(array));
                    }
                }

                default -> System.out.println("Неверный символ. Повторите попытку."); // обработка неизвестной команды
            }
        }
    }
}
