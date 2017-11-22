package com.hx.jdbc.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author huangxiang
 * @Note uid分库策略
 * @date 2017年11月22日 14:35
 */
public class UidDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<String> {
    private static final Logger log = LoggerFactory.getLogger(UidDatabaseShardingAlgorithm.class);

    private static final int DB_COUNTS = 2;

    private static final String PREFIX = "ds_";
    private static final String MAIN_DS="m_";


    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<String> shardingValue) {

        long targetValue =  UidHashUtils.hashNum(shardingValue.getValue());

        String targetDs = PREFIX+getKeyDs()+(targetValue%DB_COUNTS);

        if(collection.contains(targetDs)){
            return targetDs;
        }

        log.error("数据源中无对应的源：{}",targetDs);

        throw new UnsupportedOperationException();
    }

    private String getKeyDs(){
        return MAIN_DS;
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
