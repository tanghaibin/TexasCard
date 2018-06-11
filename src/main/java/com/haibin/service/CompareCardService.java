package com.haibin.service;

import com.haibin.vo.CompareCardReqVo;

import java.util.List;
import java.util.Set;

/**
 * 抽象对比牌型
 *
 * @author haibin.tang
 * @create 2018-06-11 下午4:27
 **/
public interface CompareCardService {

    /**
     * 如果牌比不出大小，则返回0，否则返回最大牌 请求中的编号
     *
     * @param reqVo
     * @return
     */
    Set<Integer> compare(List<CompareCardReqVo> reqVo);
}
