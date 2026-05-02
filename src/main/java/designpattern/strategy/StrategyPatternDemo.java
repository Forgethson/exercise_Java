package designpattern.strategy;

import java.util.HashMap;
import java.util.Map;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        int originalAmount = 100;

        Map<String, DiscountStrategy> strategyMap = new HashMap<>();
        // 注册策略：把优惠类型和具体策略对象绑定起来。
        strategyMap.put("none", new NoDiscountStrategy());
        strategyMap.put("percent", new PercentDiscountStrategy(80));
        strategyMap.put("full_reduce", new FullReduceStrategy(100, 30));

        calculateAndPrint("无优惠", strategyMap, "none", originalAmount);
        calculateAndPrint("八折优惠", strategyMap, "percent", originalAmount);
        calculateAndPrint("满 100 减 30", strategyMap, "full_reduce", originalAmount);
    }

    private static void calculateAndPrint(String title, Map<String, DiscountStrategy> strategyMap, String type, int originalAmount) {
        // 选择策略：if else 没有完全消失，而是集中到策略查找/注册逻辑中。
        DiscountStrategy strategy = strategyMap.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("不支持的优惠类型：" + type);
        }

        // 执行策略：业务计算只依赖统一接口，不关心具体算法实现。
        DiscountContext context = new DiscountContext(strategy);
        System.out.println(title + "：" + context.calculate(originalAmount));
    }
}
