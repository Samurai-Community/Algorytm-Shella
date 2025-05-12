import java.util.Scanner;

public class TuringMachineByStates {

    // Пример: инвертирует все 0 на 1 и 1 на 0
    public static void invertBinary(String input) {
        char[] tape = (input + "_").toCharArray();
        int head = 0;
        String state = "q0";

        System.out.println("\n--- Инверсия двоичной строки ---");
        System.out.println("Стартовая лента: " + new String(tape));

        while (!state.equals("qf")) {
            char current = tape[head];

            System.out.printf("[Состояние: %s] Символ под головкой: '%c', Позиция: %d\n", state, current, head);

            switch (state) {
                case "q0" -> {
                    if (current == '1') {
                        tape[head] = '0';
                        System.out.println("Заменили 1 на 0");
                        head++;
                    } else if (current == '0') {
                        tape[head] = '1';
                        System.out.println("Заменили 0 на 1");
                        head++;
                    } else if (current == '_') {
                        System.out.println("Конец строки. Переход в конечное состояние.");
                        state = "qf";
                    }
                }
            }

            System.out.println("Лента: " + new String(tape));
        }

        System.out.println("Результат: " + new String(tape).replaceAll("_+$", ""));
    }

    // Пример: унарное сложение — добавляет один символ '1' в конец
    public static void unaryAddition(String input) {
        if (!input.matches("1+")) {
            System.out.println("Ошибка: вход должен содержать только символы '1' (унарное число).");
            return;
        }

        char[] tape = (input + "_").toCharArray();
        int head = 0;
        String state = "q0";

        System.out.println("\n--- Унарное сложение ---");
        System.out.println("Стартовая лента: " + new String(tape));

        while (!state.equals("qf")) {
            char current = tape[head];

            System.out.printf("[Состояние: %s] Символ: '%c', Позиция: %d\n", state, current, head);

            switch (state) {
                case "q0" -> {
                    if (current == '1') {
                        head++;
                    } else if (current == '_') {
                        tape[head] = '1';
                        state = "qf";
                        System.out.println("Добавили 1 в конец.");
                    }
                }
            }

            System.out.println("Лента: " + new String(tape));
        }

        System.out.println("Результат: " + new String(tape).replaceAll("_+$", ""));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Машина Тьюринга ---");
            System.out.println("1. Инверсия двоичной строки (0 → 1, 1 → 0)");
            System.out.println("2. Унарное сложение (добавить 1)");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine();

            if (choice.equals("3")) {
                System.out.println("Выход...");
                break;
            }

            System.out.print("Введите строку на ленте: ");
            String input = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> invertBinary(input);
                case "2" -> unaryAddition(input);
                default -> System.out.println("Неверный выбор.");
            }
        }
    }
}
