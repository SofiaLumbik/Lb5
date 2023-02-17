import java.util.HashMap;
import java.util.HashSet;

public class Fraction { //создаём класс
    private int Num; //
    private int Denom;

    public Fraction(int num, int denom) {
        if (denom == 0) throw new ArithmeticException("Zero cannot be used as a denom"); //throw используется для возбуждения исключения
        Num = num;
        Denom = denom;
        ReductionOfNumbers(); //сокращаем дробь
    }
    public Fraction() { //создаём дефолтный конструктор, если пользователь не ввёл значения
        Num = 1;
        Denom = 1;
    }

    public int GetNum() { return Num; } //метод get позволяет получить значение и записать туда значение
    public int GetDenom() { return Denom; }

    public Fraction Summation(Fraction other) { //метод сложения
        return Fraction.Summation(this, other);
    }

    public static Fraction Summation(Fraction first, Fraction second) {
        var newDenom = LCM(first.Denom, second.Denom) ;//наименьшее общее кратное
        var firstNumerator = first.Num * (newDenom / first.Denom);
        var secondNumerator = second.Num * (newDenom / second.Denom);
        return new Fraction(firstNumerator + secondNumerator, newDenom);
    }

    public Fraction Subtraction(Fraction other) {
        return Fraction.Subtraction(this, other);
    }

    public static Fraction Subtraction(Fraction first, Fraction second) {
        var newDenom = LCM(first.Denom, second.Denom);
        var firstNumerator = first.Num * (newDenom / first.Denom);
        var secondNumerator = second.Num * (newDenom / second.Denom);
        return new Fraction(firstNumerator - secondNumerator, newDenom);
    }

    public Fraction Multiplication(Fraction other) {
        return Fraction.Multiplication(this, other);
    }
    public static Fraction Multiplication(Fraction first, Fraction second) {
        return new Fraction(first.Num * second.Num, first.Denom * second.Denom);
    }

    public Fraction Division(Fraction other) {
        return Fraction.Division(this, other);
    }

    public static Fraction Division(Fraction first, Fraction second) {
        if (second.Num == 0) throw new ArithmeticException("Division by zero is not allowed");
        return new Fraction(first.Num * second.Denom, second.Num * first.Denom);
    }

    private void ReductionOfNumbers() {
        var numTerms = PrimeFactors(Num);//раскладываем числа на простые множители
        var denomTerms = PrimeFactors(Denom);
        var allKeys = new HashSet<>(numTerms.keySet());//создаём множество всех ключей числителя
        allKeys.retainAll(denomTerms.keySet());//находим пересечение ключей числителя и знаменателя
        for (var key: allKeys) {
            int minimum = Math.min(numTerms.get(key), denomTerms.get(key));
            for (int i = 0; i < minimum; i++) {
                Num /= key;
                Denom /= key;
            }
        }
    }

    private HashMap<Integer, Integer> PrimeFactors(int number) {
        var result = new HashMap<Integer, Integer>();
        if (number < 0) {
            result.put(-1, 1);
            number *= -1;
        }
        int i = 2;
        while (i <= number) {
            while (number % i == 0) {
                result.put(i, result.getOrDefault(i, 0) + 1);
                number /= i;
            }
            i++;
        }
        return result;
    }

    private static int GCD(int a, int b) { //наибольший общий делитель
        while (a > 0 && b > 0)
            if (a > b) a %= b;
            else b %= a;
        return a + b;
    }

    private static int LCM(int a, int b) {
        return (a * b) / GCD(a, b);
    } //наименьшее общее кратное

    @Override //переопределение метода
    public String toString() {
        return Num + "/" + Denom;
    }
}
