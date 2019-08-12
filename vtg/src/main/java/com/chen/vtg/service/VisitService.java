package com.chen.vtg.service;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

@Service
public interface VisitService {

    /**
     * 应用的热度统计计数
     *
     * @param app
     * @return
     */
    String buildHotKey(String app);

    /**
     * 热度，每访问一次，计数都+1
     *
     * @param key
     * @param uri
     * @return
     */
    Long addHot(String key, String uri);


    /**
     * 应用的pv统计计数
     *
     * @param app
     * @return
     */
    String buildPvKey(String app);

    /**
     * 获取pv
     *
     * pv存储结果为hash，一个应用一个key; field 为uri； value为pv
     *
     * @return null表示首次有人访问；这个时候需要+1
     */
    Long getPv(String key, String uri);


    /**
     * pv 次数+1
     *
     * @param key
     * @param uri
     */
    void addPv(String key, String uri);


    /**
     * app+uri 对应的uv
     *
     * @param app
     * @param uri
     * @return
     */
    String buildUvKey(String app, String uri);


    /**
     * 获取uri对应的uv，以及当前访问ip的历史访问排名
     * 使用zset来存储，key为uri唯一标识；value为ip；score为访问的排名
     *
     * @param key : 由app与URI来生成，即一个uri维护一个uv集
     * @param ip: 访问者ip
     * @return 返回uv/rank, 如果对应的值为0，表示没有访问过
     */
    ImmutablePair</** uv */Long, /** rank */Long> getUv(String key, String ip);

    /**
     * uv +1
     *
     * @param key
     * @param ip
     * @param rank
     */
    void addUv(String key, String ip, Long rank);

    /**
     *
     * @param app
     * @param uri
     * @return
     */
    String buildUriTagKey(String app, String uri);

    /**
     *
     * @param key
     * @param ip
     */
    void tagVisit(String key, String ip);

    /**
     *
     * @param key
     * @param ip
     * @return
     */
    boolean visitToday(String key, String ip);

    /**
     *
     * @param key
     * @param val
     * @return
     */
    boolean contain(String key, Integer val);
}
