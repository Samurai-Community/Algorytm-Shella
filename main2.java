import java.util.Scanner;

public class TuringMachineSimulator {

    public static void turingAddOneUnary(String input) {
        char[] tape = (input + "_").toCharArray();
        int head = 0;
        String state = "q0";

        while (!state.equals("qf")) {
            switch (state) {
                case "q0" -> {
                    if (tape[head] == '1') head++;
                    else if (tape[head] == '_') {
                        tape[head] = '1';
                        state = "qf";
                    }
                }
            }
        }
        System.out.println("Результат (унарное сложение): " + new String(tape).replaceAll("_+$", ""));
    }

    public static void turingInvertBinary(String input) {
        char[] tape = (input + "_").toCharArray();
        int head = 0;
        String state = "q0";

        while (!state.equals("qf")) {
            switch (state) {
                case "q0" -> {
                    if (tape[head] == '1') {
                        tape[head] = '0';
                        head++;
                    } else if (tape[head] == '0') {
                        tape[head] = '1';
                        head++;
                    } else if (tape[head] == '_') {
                        state = "qf";
                    }
                }
            }
        }
        System.out.println("Результат (инверсия): " + new String(tape).replaceAll("_+$", ""));
    }

    public static void turingCheckAllOnes(String input) {
        char[] tape = (input + "_").toCharArray();
        int head = 0;
        String state = "q0";

        while (!state.equals("q_yes") && !state.equals("q_no")) {
            switch (state) {
                case "q0" -> {
                    if (tape[head] == '1') head++;
                    else if (tape[head] == '0') state = "q_no";
                    else if (tape[head] == '_') state = "q_yes";
                }
            }
        }
        System.out.println("Результат: " + (state.equals("q_yes") ? "Все символы — 1" : "Найдены нули!"));
    }

    public static void turingRemoveZeros(String input) {
        char[] tape = (input + "_").toCharArray();
        int head = 0;

        while (tape[head] != '_') {
            if (tape[head] == '0') tape[head] = '_';
            head++;
        }

        StringBuilder result = new StringBuilder();
        for (char c : tape) {
            if (c == '1') result.append(c);
        }

        System.out.println("Результат (удалены нули): " + result);
    }

    public static void turingMultiplyUnaryByTwo(String input) {
        char[] tape = (input + "____").toCharArray();
        int head = 0;
        String state = "q0";

        while (!state.equals("qf")) {
            switch (state) {
                case "q0" -> {
                    if (tape[head] == '1') {
                        tape[head] = 'x';
                        head++;
                        state = "q1";
                    } else if (tape[head] == '_') {
                        state = "q2";
                        head--;
                    }
                }
                case "q1" -> {
                    if (tape[head] == '1' || tape[head] == 'x') {
                        head++;
                    } else if (tape[head] == '_') {
                        tape[head] = '1';
                        head++;
                        tape[head] = '1';
                        head--;
                        state = "q0";
                    }
                }
                case "q2" -> {
                    if (tape[head] == 'x') {
                        tape[head] = '1';
                        head--;
                    } else if (tape[head] == '_') {
                        state = "qf";
                    }
                }
            }
        }

        System.out.println("Результат (умножение унарного числа на 2): " + new String(tape).replaceAll("_+$", ""));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nМеню Машины Тьюринга:");
            System.out.println("1. Унарное сложение (добавить 1)");
            System.out.println("2. Инверсия двоичной строки");
            System.out.println("3. Проверка: все ли символы — 1");
            System.out.println("4. Удаление нулей");
            System.out.println("5. Унарное умножение на 2");
            System.out.println("6. Выход");
            System.out.print("Выберите опцию: ");
            String choice = scanner.nextLine();

            if (choice.equals("6")) {
                System.out.println("Выход из программы...");
                break;
            }

            System.out.print("Введите входную строку: ");
            String input = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> turingAddOneUnary(input);
                case "2" -> turingInvertBinary(input);
                case "3" -> turingCheckAllOnes(input);
                case "4" -> turingRemoveZeros(input);
                case "5" -> turingMultiplyUnaryByTwo(input);
                default -> System.out.println("Неверный выбор!");
            }
        }
    }
}
