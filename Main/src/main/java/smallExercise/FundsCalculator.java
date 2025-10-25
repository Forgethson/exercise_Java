package smallExercise;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FundsCalculator {
    // 起始资金
    public double baseFund;
    // 年薪
    public double yearlyBonus;
    // 年投资回报率
    public double balanceRatio;

    public double calculatedBalance(int yearsAfter) {
        double sumFund = baseFund;
        for (int i = 0; i < yearsAfter; i++) {
            sumFund = sumFund * (1 + balanceRatio);
            sumFund += yearlyBonus;
        }
        return sumFund;
    }

    public static void main(String[] args) {
        FundsCalculator calculator = FundsCalculator.builder()
                .baseFund(700000.)
                .yearlyBonus(338105.51999999996 + 41040.0)
                .balanceRatio(0.1)
                .build();
        for (int i = 1; i <= 30; i++) {
            double total = calculator.calculatedBalance(i);
            double bonus = calculator.getYearlyBonus() * i;
            System.out.printf("%d 年后的, 资产为 %.2f ,总工资所得为 %.2f, 总投资所得为 %.2f %n",
                    i, total, bonus, total - bonus - calculator.getBaseFund());
        }

    }
}
