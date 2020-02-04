package FinanceRelated;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinanceDataTypes {

    public static void main(String[] args) {
        double interestRate = 8.10;
        double periodInYears = 20;
        String LAKHS = "00000";
        String amount = "20"+ LAKHS;
        BigDecimal valueFromDoubleCalculation = new BigDecimal(calculatePayments(Double.valueOf(amount),interestRate,periodInYears));
        BigDecimal valueFromBigDecimalCalculation = calculatePayments(new BigDecimal(amount),interestRate,periodInYears);
        System.out.println("Difference from the two is "+valueFromBigDecimalCalculation.subtract(valueFromDoubleCalculation).setScale(2,RoundingMode.HALF_EVEN));
    }

    private static double calculatePayments(double principal, double interestRate, double periodInYears){
        interestRate = (interestRate/(100*12));
        double periodInMonths = periodInYears*12;
        System.out.println("interestRate "+ interestRate);
        System.out.println("principal = "+BigDecimal.valueOf(principal).toString());
        double rate = Math.pow((1+interestRate),periodInMonths);
        double emi  = Math.round((principal*interestRate * rate)/(rate-1));
        double totalAmountPaid  =  Math.round(emi*periodInMonths);
        System.out.println("emi = "+emi);
        System.out.println("totalAmountPaid = "+ BigDecimal.valueOf(totalAmountPaid).toString());
        double totalInterestPaid = totalAmountPaid-principal;
        System.out.println("totalInterestPaid = "+BigDecimal.valueOf(totalInterestPaid).toString());
        return totalAmountPaid;
    }

    private static BigDecimal calculatePayments(BigDecimal principal, double interestRate, double periodInYears){
        System.out.println("calculatePayments using Double = "+principal.toString());
        double periodInMonths = periodInYears*12;
        interestRate = (interestRate / (100*12));
        System.out.println("interestRate "+ interestRate);
        System.out.println("principal = "+principal.toString());
        double rate = Math.pow((1+interestRate),periodInMonths);
        BigDecimal emi  = principal.multiply(new BigDecimal(interestRate * rate)).divide(new BigDecimal(rate-1), RoundingMode.HALF_EVEN).setScale(2,BigDecimal.ROUND_HALF_EVEN);
        BigDecimal totalAmountPaid  =  emi.multiply(new BigDecimal(periodInMonths).setScale(2,BigDecimal.ROUND_HALF_EVEN));
        System.out.println("emi = "+emi);
        System.out.println("totalAmountPaid = "+ totalAmountPaid.toString());
        BigDecimal totalInterestPaid = totalAmountPaid.subtract(principal);
        System.out.println("totalInterestPaid = "+totalInterestPaid.toString());
        return totalAmountPaid;
    }
}

