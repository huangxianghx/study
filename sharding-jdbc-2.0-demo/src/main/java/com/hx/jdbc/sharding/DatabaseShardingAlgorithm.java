package com.hx.jdbc.sharding;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * @author huangxiang
 * @Note uid分库策略
 * @date 2017年11月22日 14:35
 */
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    private static final Logger log = LoggerFactory.getLogger(DatabaseShardingAlgorithm.class);

    private static final int DB_COUNTS = 2;

    private static final String PREFIX = "study_";
//    private static final String MAIN_DS="m_";

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        long targetValue =  UidHashUtils.hashNum(preciseShardingValue.getValue());

        String targetDs = PREFIX+(targetValue%DB_COUNTS);

        if(collection.contains(targetDs)){
            return targetDs;
        }

        log.error("数据源中无对应的源：{}",targetDs);

        throw new UnsupportedOperationException();
    }

//    private String getKeyDs(){
//        return MAIN_DS;
//    }
}
