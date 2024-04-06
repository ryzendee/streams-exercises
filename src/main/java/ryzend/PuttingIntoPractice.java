package ryzend;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        List<Transaction> transactions = getTransactions();

        System.out.println("1) " + exOne(transactions));
        System.out.println("2) " + exTwo(transactions));
        System.out.println("3) " + exThree(transactions));
        System.out.println("4) " + exFour(transactions));
        System.out.println("5) " + exFive(transactions));
        System.out.println("6) " + exSix(transactions));
        System.out.println("7) " + exSeven(transactions));
        System.out.println("8) " + exEight(transactions));
    }

    private static List<Transaction> getTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        return Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //1) Транзакции за 2011 + сортировка по сумме min -> max
    private static List<Transaction> exOne(List<Transaction> list) {
        return list.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .toList();
    }

    //2) Список неповторяющихся городов
    private static List<String> exTwo(List<Transaction> list) {
        return list.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .toList();
    }


    //3) Трейдеры из Кембриджа + сортировка по именам
    private static List<Trader> exThree(List<Transaction> list) {
        return list.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
    }

    //4) Строка со всеми именами трейдеров + сортировка по именам
    private static String exFour(List<Transaction> list) {
        return list.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .collect(Collectors.joining(" "));
    }

    //5) Выяснить есть ли трейдеры из Милана
    private static boolean exFive(List<Transaction> list) {
        return list.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    }

    //6) Сумма транзакций трейдеров из Кембриджа
    private static int exSix(List<Transaction> list) {
        return list.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
    }

    //7) Макс сумма среди всех транзакций
    private static int exSeven(List<Transaction> list) {
        return list.stream()
                .mapToInt(Transaction::getValue)
                .max()
                .getAsInt();
    }

    //8) Мин сумма среди всех транзакций
    private static int exEight(List<Transaction> list) {
        return list.stream()
                .mapToInt(Transaction::getValue)
                .min()
                .getAsInt();
    }
}