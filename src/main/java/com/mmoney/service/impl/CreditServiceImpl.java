package com.mmoney.service.impl;

import com.mmoney.dao.CreditMapper;
import com.mmoney.pojo.Credit;
import com.mmoney.service.CreditService;
import com.mmoney.util.IdCardInfo;
import com.mmoney.util.IdcardTest;
import com.mmoney.util.NativePlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

/**
 * @program: mmoney
 * @description: 信用Service实现层
 * @author: Chen.gm
 * @create: 2019-08-26 21:20
 **/
@Service("creditService")
public class CreditServiceImpl implements CreditService {


    @Autowired(required = false)
    private CreditMapper creditMapper;

    public Credit selectCreditById(int cdtId){
        return  creditMapper.selectCreditById(cdtId);
    }

    @Override
    public int usrCreditinit(Map info) {
        Integer creditCode = 0;
        String idCard = (String) info.get("idCode");//身份证
        Integer occ = Integer.parseInt((String) info.get("occ"));//职业
        Integer income = Integer.parseInt((String)info.get("income"));//收入
        IdcardTest idcardTest = new IdcardTest();
        if (idcardTest.isValidatedAllIdcard(idCard)){
            int age = IdCardInfo.getAgeByIdCard(idCard);
            String sex = IdCardInfo.getGenderByIdCard(idCard);
            String birth = IdCardInfo.getBirthByIdCard(idCard);
            int na = Integer.parseInt(idCard.substring(0,6));
            String nativePlace = NativePlace.getNativePlace(na);
            String[] ratios = {"北京","上海","广州","深圳","杭州"};

            //地域授分
            try {
                for (String ratio:ratios){
                    if (nativePlace.contains(ratio)){
                        creditCode+=200;
                        throw new RuntimeException();
                    }
                }
            }catch (Exception e){

            }finally {
                if (creditCode==0)
                    creditCode += 180;
            }
            //年龄授分
            if ((age>=16&&age<=20)||(age>35&&age<=50)){
                creditCode += 200;
            }else if (age>20&&age<=35){
                creditCode += 350;
            }

            //职业授分
            if (occ==1){
                creditCode += 370;
            }else if(occ==2){
                creditCode += 370;
            }else if (occ>2&&occ<7){
                creditCode += 370;
            }else {
                creditCode += 370;
            }
            //收入授分
            switch (income){
                case 2:
                    creditCode += 50;
                    break;
                case 3:
                    creditCode += 150;
                    break;
                case 4:
                    creditCode += 200;
                    break;
                case 5:
                    creditCode += 250;
                    break;
            }
            return creditCode;
        }
        return 0;
    }

    @Override
    public int addcredit(Credit credit) {
        return 0;
    }
}
