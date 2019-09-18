package com.mmoney.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * @program: mmoney
 * @description: 借款利率计算工具库
 * @author: Li.QiXuan
 * @create: 2019-08-31 13:16
 **/

public class InterestUtil {
    //月利率=日利率X30
    //prin 本金 /  intes 利息 / tages 期数 /usrRate 利率 / 还款

    //每月等额
    public static Map monthlyEquivalence(BigDecimal prin,BigDecimal usrRate,int tages){
        /*
        每月月供额＝(贷款本金÷还款月数)＋(贷款本金－已归还本金累计额)×月利率
        每月应归还本金＝贷款本金÷还款月数
        月利率＝日利率×30
        */
        DecimalFormat df2 =new DecimalFormat("#.00");
        BigDecimal monthPrin = prin.divide(BigDecimal.valueOf(tages),2,ROUND_HALF_DOWN);//月本金
        BigDecimal monthIntes = usrRate.multiply(BigDecimal.valueOf(30));//月利率
        //总利息
        BigDecimal intes = BigDecimal.ZERO;
        //
        BigDecimal amouts = BigDecimal.ZERO;
        Map map = new HashMap();
        Map map1 = new HashMap();
        for (int i = 1 ; i <= tages;i++){
            //当期利息 = (贷款本金－已归还本金累计额)×月利率
            BigDecimal inte = monthPrin.multiply(BigDecimal.valueOf(tages-i+1)).multiply(monthIntes);
            intes = intes.add(inte);
            //当期还款
            BigDecimal amout = monthPrin.add(inte);
            amouts = amouts.add(amout);
            System.out.println("第"+i+"期，本金:"+monthPrin+" 利息:"+inte+" 需要还款："+amout);
            map1.put(i,df2.format(inte));
        }
        System.out.println("总利息:"+intes+" 总还款："+amouts);
        map.put("amouts",amouts);
        map.put("intes",intes);
        map.put("inte",map1);
        System.out.println(map);
        return map;
    }

    public static void main(String[] args) {
        monthlyEquivalence(BigDecimal.valueOf(1000),BigDecimal.valueOf(0.0005),12);
    }

    //先息后本
    public Map successComesFirst(){
        return null;
    }
}
