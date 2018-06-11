package com.haibin.service.impl;

import com.haibin.exception.BizException;
import com.haibin.rule.AbstractRuleTemplate;
import com.haibin.rule.StraightFlushRuleTemplate;
import com.haibin.service.CompareCardService;
import com.haibin.vo.CompareCardReqVo;
import com.haibin.vo.RuleEnum;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * @author haibin.tang
 * @create 2018-06-11 下午4:30
 **/
public class CompareCardServiceImpl implements CompareCardService {

    /**
     * 最小玩家数
     */
    private static final int MIN_PERSON = 2;

    @Override
    public Set<Integer> compare(List<CompareCardReqVo> reqVo) {
        if (CollectionUtils.isEmpty(reqVo)) {
            throw new BizException("参数错误");
        }
        if (reqVo.size() < MIN_PERSON) {
            throw new BizException("最小两人才能对比");
        }
        //由匹配器匹配出牌型
        for (CompareCardReqVo compareCardReqVo : reqVo) {
            AbstractRuleTemplate ruleMatch = new StraightFlushRuleTemplate();
            ruleMatch.setCardVos(compareCardReqVo.getCardVos());
            compareCardReqVo.setRuleEnum(ruleMatch.match(ruleMatch));
            if (compareCardReqVo.getRuleEnum() != null) {
                System.out.println(String.format("编号[%s]的牌型为[%s]", compareCardReqVo.getCode(), compareCardReqVo.getRuleEnum().getRuleName()));
            } else {
                System.out.println(String.format("编号[%s]的未匹配到牌型", compareCardReqVo.getCode()));
            }
        }
        sortByRule(reqVo);
        //第一个牌型为空 说明 一个都没匹配上牌型
        if (reqVo.get(0).getRuleEnum() == null) {
            Set<Integer> result = new HashSet<>(reqVo.size());
            result.add(0);
            return result;
        }
        return buildResp(reqVo);
    }

    /**
     * 构建结果 如果后续元素与第一个牌型相等 则返回请求的code
     *
     * @param reqVo
     * @return
     */
    private Set<Integer> buildResp(List<CompareCardReqVo> reqVo) {
        Set<Integer> result = new HashSet<>(reqVo.size());
        RuleEnum maxCardRuleEnum = null;
        for (CompareCardReqVo compareCardReqVo : reqVo) {
            if (result.isEmpty()) {
                maxCardRuleEnum = compareCardReqVo.getRuleEnum();
                result.add(compareCardReqVo.getCode());
            } else if (compareCardReqVo.getRuleEnum() == maxCardRuleEnum) {
                result.add(compareCardReqVo.getCode());
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * 根据牌型排序，排序code越小越靠前
     *
     * @param reqVo
     */
    private void sortByRule(List<CompareCardReqVo> reqVo) {
        Collections.sort(reqVo, new Comparator<CompareCardReqVo>() {
            @Override
            public int compare(CompareCardReqVo source, CompareCardReqVo target) {
                if (source.getRuleEnum() == null && target.getRuleEnum() == null) {
                    return 0;
                }
                if (source.getRuleEnum() == null) {
                    return 1;
                }
                if (target.getRuleEnum() == null) {
                    return -1;
                }
                int sourceCode = source.getRuleEnum().getSort();
                int targetCode = target.getRuleEnum().getSort();
                if (sourceCode > targetCode) {
                    return 1;
                }
                if (sourceCode < targetCode) {
                    return -1;
                }
                return 0;
            }
        });
    }
}
