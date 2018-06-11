package com.haibin.rule;

import com.haibin.exception.BizException;
import com.haibin.vo.CardVo;
import com.haibin.vo.RuleEnum;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 抽象牌型匹配器模板方法类
 *
 * @author haibin.tang
 * @create 2018-06-11 下午3:41
 **/
public abstract class AbstractRuleTemplate {
    /**
     * 父级匹配器
     */
    private AbstractRuleTemplate parent;
    /**
     * 玩家的牌 最大5张，由底牌+公共牌 拼凑出最大的五张牌
     */
    private List<CardVo> cardVos;

    private static final int MAX_CARD_SIZE = 5;

    /**
     * 模板方法，首先子匹配器去匹配牌型，匹配不到再委托给父级去匹配，与类加载器双亲委派模型类似
     *
     * @param ruleMatch
     * @return
     */
    public final RuleEnum match(AbstractRuleTemplate ruleMatch) {
        if (CollectionUtils.isEmpty(cardVos) || cardVos.size() > MAX_CARD_SIZE) {
            throw new BizException("参数错误");
        }
        RuleEnum rule = ruleMatch.match();
        if (rule == null && ruleMatch.parent != null) {
            ruleMatch.parent.setCardVos(cardVos);
            return ruleMatch.match(ruleMatch.parent);
        }
        return rule;
    }

    /**
     * 子类自己的匹配器
     * @return
     */
    abstract RuleEnum match();

    List<CardVo> getCardVos() {
        return cardVos;
    }

    void setParent(AbstractRuleTemplate parent) {
        this.parent = parent;
    }

    public void setCardVos(List<CardVo> cardVos) {
        this.cardVos = cardVos;
    }
}
