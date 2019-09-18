package com.mmoney.util;

import java.util.HashMap;
import java.util.List;

public class LayuiTable extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;
    public static LayuiTable tableData(Integer count,List<?> data){
        LayuiTable tableData = new LayuiTable();
        tableData.put("code", 0);
        tableData.put("msg", "");
        tableData.put("count", count);
        tableData.put("data", data);
        return tableData;
    }
}

