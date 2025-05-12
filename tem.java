import java.util.Scanner;

public class TuringMachineExample {

    // Метод для преобразования двоичной строки в символ ASCII
    public static char binaryToChar(String binary) {
        // Преобразуем двоичную строку в десятичное число
        int asciiValue = Integer.parseInt(binary, 2);
        // Преобразуем десятичное число в символ
        return (char) asciiValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод двоичной строки
        System.out.print("Введите двоичную строку (например, 1100001 для буквы 'a'): ");
        String binaryInput = scanner.nextLine();

        // Проверяем, чтобы введенная строка была длиной 7 (для символа 'a' в ASCII)
        if (binaryInput.length() != 7) {
            System.out.println("Ошибка: строка должна быть длиной 7 символов.");
            return;
        }

        // Преобразуем двоичную строку в символ
        char result = binaryToChar(binaryInput);

        // Выводим результат
        System.out.println("Полученный символ: " + result);
    }
}
