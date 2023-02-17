public class Main {
    public static void main(String[] args) {
        var firstFraction = new Fraction(11,4);
        var secondFraction = new Fraction(12,3);
        var result = firstFraction.Summation(secondFraction);
        var result2 = firstFraction.Subtraction(secondFraction);
        var result3 = firstFraction.Multiplication(secondFraction);
        var result4 = firstFraction.Division(secondFraction);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}