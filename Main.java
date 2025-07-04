import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        // Создаем счета
        BankAccount account1 = new BankAccount("Иван Иванов");
        BankAccount account2 = new BankAccount("Петр Петров");

        // Используем методы класса
        account1.deposit(5000);
        account1.transfer(account2, 2000);
        account2.withdraw(500);

        // Блокируем счет
        account1.setBlocked(true);

        // Выводим информацию
        System.out.println("=== Состояние счетов ===");
        System.out.println(account1);
        System.out.println(account2);

        System.out.println("\n=== Детали счета 1 ===");
        System.out.println("Владелец: " + account1.getOwnerName());
        System.out.println("Баланс: " + account1.getBalance());
        System.out.println("Номер счета: " + account1.getAccountNumber());
        System.out.println("Дата открытия: " +
                account1.getOpenDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("Статус: " + (account1.isBlocked() ? "заблокирован" : "активен"));

        // Проверка равенства счетов
        System.out.println("\nСчета одинаковые? " + account1.equals(account2));
    }
}