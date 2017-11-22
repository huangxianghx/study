package com.hx.jdbc.sharding;

import org.apache.commons.lang3.StringUtils;

/**
 *
 */
public class UidHashUtils {
     public static final int TABLE_COUNT = 10;
    /**
     *  获取 value 的 hash 数，如果 uid 小于 Long.MAX 则直接转成相应的数据，否则统一用当作字符串
     * @param value
     * @return
     */
    public static long hashNum(String value){
        if(StringUtils.isNumeric(value)){
            try{
                return Long.parseLong(value);
            }catch(Exception e){
            }
        }
        return Math.abs(value.hashCode());
    }

    /**
     * 计算 hash 后的结果
     * @param value
     * @param hashCount
     * @return
     */
    public static long hashResult(String value,long hashCount){
        return hashNum(value) % hashCount;
    }
}
