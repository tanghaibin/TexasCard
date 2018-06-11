package com.haibin.rule;

import com.haibin.vo.CardVo;
import com.haibin.vo.RuleEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 同花大顺匹配器
 * 规则：花型、颜色一样，标号为 A、K、Q、J、10
 *
 * @author haibin.tang
 * @create 2018-06-11 下午3:45
 **/
public class RoyalFlushRuleTemplate extends AbstractRuleTemplate {

    private static final List<String> NUMBERS = new ArrayList<>(5);

    static {
        NUMBERS.add("A");
        NUMBERS.add("K");
        NUMBERS.add("Q");
        NUMBERS.add("J");
        NUMBERS.add("10");
    }

    @Override
    public RuleEnum match() {
        List<CardVo> cardVos = getCardVos();
        final int maxSize = 5;
        final int minSize = 1;
        Set<String> flowers = new HashSet<>(maxSize);
        Set<String> colors = new HashSet<>(maxSize);
        Set<String> numbers = new HashSet<>(maxSize);

        for (CardVo cardVo : cardVos) {
            flowers.add(cardVo.getFlower());
            colors.add(cardVo.getColor());
            numbers.add(cardVo.getNumber());
        }
        if (flowers.size() > minSize || colors.size() > minSize || numbers.size() < maxSize) {
            return null;
        }
        if (!numbers.containsAll(NUMBERS)) {
            return null;
        }
        return RuleEnum.ROYAL_FLUSH;
    }
}
