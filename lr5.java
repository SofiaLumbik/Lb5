import java.util.HashMap;
import java.util.HashSet;

public class Fraction {
    private int Numerator;
    private int Denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) throw new ArithmeticException("Denominator can't be zero");
        Numerator = numerator;
        Denominator = denominator;
        ToLowestTerms();
    }
    public Fraction() {
        Numerator = 1;
        Denominator = 1;
    }

    public int GetNumerator() { return Numerator; }
    public int GetDenominator() { return Denominator; }

    public Fraction Add(Fraction other) {
        return Fraction.Add(this, other);
    }

    public static Fraction Add(Fraction first, Fraction second) {
        var newDenominator = LeastCommonMultiple(first.Denominator, second.Denominator);
        var firstNumerator = first.Numerator * (newDenominator / first.Denominator);
        var secondNumerator = second.Numerator * (newDenominator / second.Denominator);
        return new Fraction(firstNumerator + secondNumerator, newDenominator);
    }

    public Fraction Subtract(Fraction other) {
        return Fraction.Subtract(this, other);
    }

    public static Fraction Subtract(Fraction first, Fraction second) {
        var newDenominator = LeastCommonMultiple(first.Denominator, second.Denominator);
        var firstNumerator = first.Numerator * (newDenominator / first.Denominator);
        var secondNumerator = second.Numerator * (newDenominator / second.Denominator);
        return new Fraction(firstNumerator - secondNumerator, newDenominator);
    }

    public Fraction Multiply(Fraction other) {
        return Fraction.Multiply(this, other);
    }
    public static Fraction Multiply(Fraction first, Fraction second) {
        return new Fraction(first.Numerator * second.Numerator, first.Denominator * second.Denominator);
    }

    public Fraction Divide(Fraction other) {
        return Fraction.Divide(this, other);
    }

    public static Fraction Divide(Fraction first, Fraction second) {
        if (second.Numerator == 0) throw new ArithmeticException("Can't divide by zero");
        return new Fraction(first.Numerator * second.Denominator, second.Numerator * first.Denominator);
    }

    private void ToLowestTerms() {
        var numeratorTerms = GetPrimeFactors(Numerator);
        var denominatorTerms = GetPrimeFactors(Denominator);
        var commonKeys = new HashSet<>(numeratorTerms.keySet());
        commonKeys.retainAll(denominatorTerms.keySet());
        for (var key: commonKeys) {
            int minCount = Math.min(numeratorTerms.get(key), denominatorTerms.get(key));
            for (int i = 0; i < minCount; i++) {
                Numerator /= key;
                Denominator /= key;
            }
        }
    }

    private HashMap<Integer, Integer> GetPrimeFactors(int num) {
        var res2 = new HashMap<Integer, Integer>();
        if (num < 0) {
            res2.put(-1, 1);
            num *= -1;
        }
        int i = 2;
        while (i <= num) {
            while (num % i == 0) {
                res2.put(i, res2.getOrDefault(i, 0) + 1);
                num /= i;
            }
            i++;
        }
        return res2;
    }

    private static int GreatestCommonDivisor(int a, int b) {
        while (a > 0 && b > 0)
            if (a > b) a %= b;
            else b %= a;
        return a + b;
    }

    private static int LeastCommonMultiple(int a, int b) {
        return (a * b) / GreatestCommonDivisor(a, b);
    }

    @Override
    public String toString() {
        return Numerator + "/" + Denominator;
    }
}
