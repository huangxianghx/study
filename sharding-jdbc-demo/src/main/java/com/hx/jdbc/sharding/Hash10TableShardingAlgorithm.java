package com.hx.jdbc.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author huangxiang
 * @Note 分表策略（10张表）
 * @date 2017年11月22日 14:35
 */
public class Hash10TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String> {
    private static final Logger log = LoggerFactory.getLogger(Hash10TableShardingAlgorithm.class);


    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<String> shardingValue) {

        String value = shardingValue.getValue();
        long targetValue = UidHashUtils.hashResult(value, UidHashUtils.TABLE_COUNT);

        for (String each : collection) {
            if (each.endsWith("_" + targetValue + "")) {
                return each;
            }
        }
        log.error("未找到key:{}对 应的表: index: _{}", value, targetValue);
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<String> shardingValue) {
        log.error("不支持 in 操作");
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<String> shardingValue) {
        log.error("不支持 Between 操作");
        throw new UnsupportedOperationException();
    }
}
