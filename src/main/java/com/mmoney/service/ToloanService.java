package com.mmoney.service;


import com.mmoney.pojo.PageChoice;
import com.mmoney.pojo.Toloan;

import java.util.List;

public interface ToloanService {

    List<Toloan> selectToloans(Integer usrId);

    List<Toloan> queryToloan(PageChoice pc);

    Toloan selectToloansById(String tolId);

    Toloan queryThisToloan(Integer tolId);

    int insertToloan(Toloan toloan);

    int updatetoloan(Toloan toloan);
}
