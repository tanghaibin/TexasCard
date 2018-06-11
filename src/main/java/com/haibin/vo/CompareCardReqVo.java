package com.haibin.vo;

import java.util.List;

/**
 * 对比牌型请求数据
 *
 * @author haibin.tang
 * @create 2018-06-11 下午4:28
 **/
public class CompareCardReqVo {

    private int code;

    private List<CardVo> cardVos;

    private RuleEnum ruleEnum;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<CardVo> getCardVos() {
        return cardVos;
    }

    public void setCardVos(List<CardVo> cardVos) {
        this.cardVos = cardVos;
    }

    public RuleEnum getRuleEnum() {
        return ruleEnum;
    }

    public void setRuleEnum(RuleEnum ruleEnum) {
        this.ruleEnum = ruleEnum;
    }

    @Override
    public String toString() {
        return "CompareCardReqVo{" +
                "code=" + code +
                ", cardVos=" + cardVos +
                ", ruleEnum=" + ruleEnum +
                '}';
    }
}
