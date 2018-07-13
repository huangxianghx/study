package com.hx.service;

import io.shardingjdbc.orchestration.reg.zookeeper.ZookeeperConfiguration;
import io.shardingjdbc.orchestration.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangxiang
 * @Note
 * @date 2017年11月22日 15:08
 */
@Service
public class ZookeeperService {
    @Autowired
    ZookeeperConfiguration zookeeperConfiguration;

    /**
     * 根据key获取zNode
     * @param key
     * @return
     */
    public String getZNodeByKey(String key){
        ZookeeperRegistryCenter zookeeperRegistryCenter=new ZookeeperRegistryCenter(zookeeperConfiguration);
        String zNode=zookeeperRegistryCenter.get(key);
        return zNode;
    }
}
