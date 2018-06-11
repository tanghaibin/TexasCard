import com.haibin.service.CompareCardService;
import com.haibin.service.impl.CompareCardServiceImpl;
import com.haibin.vo.CardVo;
import com.haibin.vo.CompareCardReqVo;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author haibin.tang
 * @create 2018-06-11 下午4:45
 **/
public class CompareCardServiceTest {

    public static void main(String[] args) {
        CompareCardService compareCardService = new CompareCardServiceImpl();

        List<CompareCardReqVo> reqVo = new ArrayList<>();
        CompareCardReqVo hobson = new CompareCardReqVo();
        hobson.setCode(1);
        List<CardVo> hobsonCardVos = new ArrayList<>();
        hobsonCardVos.add(new CardVo("红桃", "red", "A"));
        hobsonCardVos.add(new CardVo("红桃", "red", "8"));
        hobsonCardVos.add(new CardVo("红桃", "red", "Q"));
        hobsonCardVos.add(new CardVo("红桃", "red", "J"));
        hobsonCardVos.add(new CardVo("红桃", "red", "10"));
        hobson.setCardVos(hobsonCardVos);
        reqVo.add(hobson);

        CompareCardReqVo dom = new CompareCardReqVo();
        dom.setCode(3);
        List<CardVo> domCardVos = new ArrayList<>();
        domCardVos.add(new CardVo("黑桃", "red", "A"));
        domCardVos.add(new CardVo("黑桃", "red", "K"));
        domCardVos.add(new CardVo("黑桃", "red", "Q"));
        domCardVos.add(new CardVo("黑桃", "red", "J"));
        domCardVos.add(new CardVo("黑桃", "red", "10"));
        dom.setCardVos(domCardVos);
        reqVo.add(dom);


        CompareCardReqVo killer = new CompareCardReqVo();
        killer.setCode(2);
        List<CardVo> killerCardVos = new ArrayList<>();
        killerCardVos.add(new CardVo("黑桃", "red", "K"));
        killerCardVos.add(new CardVo("黑桃", "red", "Q"));
        killerCardVos.add(new CardVo("黑桃", "red", "J"));
        killerCardVos.add(new CardVo("黑桃", "red", "10"));
        killerCardVos.add(new CardVo("黑桃", "red", "9"));
        killer.setCardVos(killerCardVos);
        reqVo.add(killer);

        CompareCardReqVo sliber = new CompareCardReqVo();
        sliber.setCode(4);
        List<CardVo> sliberCardVos = new ArrayList<>();
        sliberCardVos.add(new CardVo("黑桃", "red", "1"));
        sliberCardVos.add(new CardVo("黑桃", "red", "Q"));
        sliberCardVos.add(new CardVo("黑桃", "red", "7"));
        sliberCardVos.add(new CardVo("黑桃", "red", "10"));
        sliberCardVos.add(new CardVo("黑桃", "red", "9"));
        sliber.setCardVos(sliberCardVos);
        reqVo.add(sliber);


        System.out.println("======================================");
        Set<Integer> result = compareCardService.compare(reqVo);
        System.out.println("MAX CARD " + result);
        System.out.println("======================================");

        Assert.assertTrue("对比错误,最大牌编号应该为3", CollectionUtils.isNotEmpty(result) && result.size() == 1 && result.iterator().next() == 3);

        hobson.getCardVos().get(1).setNumber("K");
        result = compareCardService.compare(reqVo);
        System.out.println("MAX CARD " + result);
        Iterator<Integer> iterator = result.iterator();
        Assert.assertTrue("对比错误,最大牌编号应该为1、3", CollectionUtils.isNotEmpty(result) && result.size() == 2 && iterator.next() == 1 && iterator.next() == 3);
        System.out.println("======================================");
    }

}
