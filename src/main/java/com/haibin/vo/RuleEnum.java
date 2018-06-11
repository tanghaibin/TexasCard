package com.haibin.vo;

/**
 * 规则
 *
 * @author haibin.tang
 * @create 2018-06-11 下午3:26
 **/
public enum  RuleEnum {

    ROYAL_FLUSH(1, "同花大顺", 1),

    STRAIGHT_FLUSH(2, "同花顺", 2);

    /**
     * 编号
     */
    private int code;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 排序
     */
    private int sort;

    RuleEnum(int code, String ruleName, int sort) {
        this.code = code;
        this.ruleName = ruleName;
        this.sort = sort;
    }

    public int getCode() {
        return code;
    }

    public String getRuleName() {
        return ruleName;
    }

    public int getSort() {
        return sort;
    }

    public static RuleEnum transFromCode(int code) {
        for (RuleEnum ruleEnum : RuleEnum.values()) {
            if(ruleEnum.getCode() == code) {
                return ruleEnum;
            }
        }
        return null;
    }
}
