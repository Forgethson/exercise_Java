package smallExercise;

import java.util.ArrayList;
import java.util.List;

public class IITCalculator {
    public double monthlyBaseSalary;
    public double annualBonus;
    public List<Double> tax;
    public double annualTax;
    public List<Double> socialSecurityAndHousingFund;
    public List<Double> receivedSalary;
    public Double annualReceivedSalary;
    public double monthlySpecialDeduction;
    public static final double monthlyDefaultDeduction = 5000.;

    public IITCalculator(double monthlyBaseSalary, double annualBonus, double monthlySpecialDeduction) {
        this.annualBonus = annualBonus;
        this.monthlyBaseSalary = monthlyBaseSalary;
        this.monthlySpecialDeduction = monthlySpecialDeduction;
        tax = new ArrayList<>(12);
        socialSecurityAndHousingFund = new ArrayList<>(12);
        receivedSalary = new ArrayList<>(12);
    }

    // 计算个税：奖金一起算
    public void calculate1() {
//        double a = monthlyBaseSalary * 0.225;
        double a = 5646.3;
        for (int i = 0; i < 12; i++) {
            socialSecurityAndHousingFund.add(a);
        }
        double beforeTexIncome = monthlyBaseSalary * 12 + annualBonus;
        double accumulatedTaxableIncome = beforeTexIncome - (monthlyDefaultDeduction + monthlySpecialDeduction) * 12 - getSum(socialSecurityAndHousingFund);
        System.out.println("累计应纳所得额 = " + accumulatedTaxableIncome);
        annualTax = getTex(accumulatedTaxableIncome);
        System.out.println("年个人所得税 = " + annualTax);
        annualReceivedSalary = beforeTexIncome - annualTax - getSum(socialSecurityAndHousingFund);
        System.out.println("年税后工资 = " + annualReceivedSalary);
    }

    // 计算个税：奖金不一起算
    public void calculate2() {
//        double a = monthlyBaseSalary * 0.225;
        double a = 5646.3;
        for (int i = 0; i < 12; i++) {
            socialSecurityAndHousingFund.add(a);
        }
        double beforeTexIncome = monthlyBaseSalary * 12;
        double accumulatedTaxableIncome = beforeTexIncome - (monthlyDefaultDeduction + monthlySpecialDeduction) * 12 - getSum(socialSecurityAndHousingFund);
        System.out.println("累计应纳所得额 = " + accumulatedTaxableIncome);
        annualTax = getTex(accumulatedTaxableIncome);
        System.out.println("年个人所得税 = " + (annualTax + 6990.));
        annualReceivedSalary = beforeTexIncome - annualTax - getSum(socialSecurityAndHousingFund);
        System.out.println("年税后工资 = " + (annualReceivedSalary + annualBonus - 6990.));
    }

    private double getTex(double t) {
        double res;
        if (t < 36000) {
            res = t * 0.03;
        } else if (t < 144000) {
            res = t * 0.1 - 2520;
        } else if (t < 300000) {
            res = t * 0.2 - 16920;
        } else if (t < 420000) {
            res = t * 0.25 - 31920;
        } else if (t < 660000) {
            res = t * 0.3 - 52920;
        } else if (t < 960000) {
            res = t * 0.35 - 85920;
        } else {
            res = t * 0.45 - 181920;
        }
        return res;
    }

    public double getSum(List<Double> values) {
        double res = 0.;
        for (Double v : values) {
            res += v;
        }
        return res;
    }

    public static void main(String[] args) {
        IITCalculator iitCalculator = new IITCalculator(26800, 26000 * 3, 1500);
        System.out.println("奖金在一起算");
        iitCalculator.calculate1();
        System.out.println("---------------");
        IITCalculator iitCalculator2 = new IITCalculator(26800, 26000 * 3, 1500);
        System.out.println("奖金不一起算");
        iitCalculator2.calculate2();
        System.out.println("公积金 = 3120 * 2 * 12 = 74880");

    }
}
