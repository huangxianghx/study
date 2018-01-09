package com.hx.jdbc.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author huangxiang
 * @Note 分表策略（10张表）
 * @date 2017年11月22日 14:35
 */
public class HashTableShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    private static final Logger log = LoggerFactory.getLogger(HashTableShardingAlgorithm.class);

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String value = preciseShardingValue.getValue();
        long targetValue = UidHashUtils.hashResult(value, UidHashUtils.TABLE_COUNT);
        for (String each : collection) {
            if (each.endsWith("_" + targetValue + "")) {
                return each;
            }
        }
        log.error("未找到key:{}对 应的表: index: _{}", value, targetValue);
        throw new UnsupportedOperationException();
    }
}
