import java.util.Arrays;
import java.util.Scanner;

public class TuringMachine {

    private char[] tape;
    private int head;

    public TuringMachine(String input) {
        this.tape = input.toCharArray();
        this.head = 0;
    }

    public void replaceAll(char replacement) {
        Arrays.fill(tape, replacement);
    }

    public void shiftLeft() {
        for (int i = 1; i < tape.length; i++) {
            tape[i - 1] = tape[i];
        }
        tape[tape.length - 1] = '0';
    }

    public void invertBits() {
        for (int i = 0; i < tape.length; i++) {
            if (tape[i] == '0') tape[i] = '1';
            else if (tape[i] == '1') tape[i] = '0';
        }
    }

    public void doubleValue() {
        try {
            String binary = new String(tape);
            int value = Integer.parseInt(binary, 2);
            value *= 2;
            String doubled = Integer.toBinaryString(value);
            Arrays.fill(tape, '0');
            int start = tape.length - doubled.length();
            for (int i = 0; i < doubled.length(); i++) {
                if (start + i < tape.length) {
                    tape[start + i] = doubled.charAt(i);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: лента содержит недопустимые символы.");
        }
    }

    public void printTape() {
        System.out.println("Текущая лента: " + new String(tape));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите начальное состояние ленты (например, 10110): ");
        String input = scanner.nextLine();
        TuringMachine machine = new TuringMachine(input);

        while (true) {
            System.out.println("\n===== МЕНЮ =====");
            System.out.println("1. Заменить все на 0");
            System.out.println("2. Заменить все на 1");
            System.out.println("3. Сдвинуть все влево");
            System.out.println("4. Инвертировать 0 и 1");
            System.out.println("5. Удвоить значение");
            System.out.println("6. Показать ленту");
            System.out.println("0. Выход");
            System.out.print("Выберите опцию: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    machine.replaceAll('0');
                    break;
                case "2":
                    machine.replaceAll('1');
                    break;
                case "3":
                    machine.shiftLeft();
                    break;
                case "4":
                    machine.invertBits();
                    break;
                case "5":
                    machine.doubleValue();
                    break;
                case "6":
                    machine.printTape();
                    break;
                case "0":
                    System.out.println("Завершение работы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }

            machine.printTape();
        }
    }
}
