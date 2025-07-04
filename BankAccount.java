import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    private final String ownerName;  // final
    private int balance;
    private final LocalDateTime openDate;  // final
    private boolean isBlocked;
    private final String accountNumber;  // final

    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
        this.openDate = LocalDateTime.now();
        this.isBlocked = false;
        this.accountNumber = generateAccountNumber();
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public boolean deposit(int amount) {
        if (isBlocked || amount <= 0) return false;
        balance += amount;
        return true;
    }

    public boolean withdraw(int amount) {
        if (isBlocked || amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount otherAccount, int amount) {
        if (isBlocked || otherAccount.isBlocked || amount <= 0 || amount > balance) return false;
        return this.withdraw(amount) && otherAccount.deposit(amount);
    }

    // Геттеры
    public String getOwnerName() { return ownerName; }
    public int getBalance() { return balance; }
    public LocalDateTime getOpenDate() { return openDate; }
    public boolean isBlocked() { return isBlocked; }
    public void setBlocked(boolean blocked) { isBlocked = blocked; }
    public String getAccountNumber() { return accountNumber; }

    @Override
    public String toString() {
        return String.format("Счет %s (владелец: %s, баланс: %d, открыт: %s, статус: %s)",
                accountNumber,
                ownerName,
                balance,
                openDate.toString(),
                isBlocked ? "заблокирован" : "активен");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return accountNumber.equals(that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}