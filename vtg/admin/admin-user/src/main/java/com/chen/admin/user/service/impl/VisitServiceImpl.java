package com.chen.admin.user.service.impl;

import com.chen.admin.user.service.VisitService;
import com.chen.vtg.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @Author : Chen
 * @Date : Create in 2019/8/11 10:46
 * @Description :
 * @Modify by :
 */
@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String buildHotKey(String app) {
        return "hot_cnt_" + app;
    }

    @Override
    public Long addHot(String key, String uri) {
        return redisTemplate.execute((RedisCallback<Long>) connection ->
                connection.hIncrBy(key.getBytes(), uri.getBytes(), 1));
    }

    @Override
    public String buildPvKey(String app) {
        return "site_cnt_" + app;
    }

    @Override
    public Long getPv(String key, String uri) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] ans = connection.hGet(key.getBytes(), uri.getBytes());
            if (ans == null || ans.length == 0) {
                return null;
            }

            return Long.parseLong(new String(ans));
        });
    }

    @Override
    public void addPv(String key, String uri) {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.hIncrBy(key.getBytes(), uri.getBytes(), 1);
            return null;
        });
    }

    @Override
    public String buildUvKey(String app, String uri) {
        return "uri_rank_" + app + "_" + uri;
    }

    @Override
    public ImmutablePair<Long, Long> getUv(String key, String ip) {
        // 获取总uv数，也就是最大的score
        Long uv = redisTemplate.execute((RedisCallback<Long>) connection -> {
            Set<RedisZSetCommands.Tuple> set = connection.zRangeWithScores(key.getBytes(), -1, -1);
            if (CollectionUtils.isEmpty(set)) {
                return 0L;
            }

            Double score = set.stream().findFirst().get().getScore();
            return score.longValue();
        });

        if (uv == null || uv == 0L) {
            // 表示还没有人访问过
            return ImmutablePair.of(0L, 0L);
        }

        // 获取ip对应的访问排名
        Long rank = redisTemplate.execute((RedisCallback<Long>) connection -> {
            Double score = connection.zScore(key.getBytes(), ip.getBytes());
            return score == null ? 0L : score.longValue();
        });

        return ImmutablePair.of(uv, rank);
    }

    @Override
    public void addUv(String key, String ip, Long rank) {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.zAdd(key.getBytes(), rank, ip.getBytes());
            return null;
        });
    }

    /**
     * 每日访问统计
     *
     * @param app
     * @param uri
     * @return
     */
    @Override
    public String buildUriTagKey(String app, String uri) {
        return "uri_tag_" + DateUtil.getToday() + "_" + app + "_" + uri;
    }

    /**
     * 标记ip访问过这个key
     *
     * @param key
     * @param ip
     */
    @Override
    public void tagVisit(String key, String ip) {
        String[] segments = StringUtils.split(ip, ".");
        for (int i = 0; i < segments.length; i++) {
            int finalI = i;
            redisTemplate.execute((RedisCallback<Void>) connection -> {
                connection.setBit((key + "_" + finalI).getBytes(), Integer.valueOf(segments[finalI]), true);
                return null;
            });

        }
    }

    @Override
    public boolean visitToday(String key, String ip) {
        // ip地址进行分段 127.0.0.1
        String[] segments = StringUtils.split(ip, ".");
        for (int i = 0; i < segments.length; i++) {
            if (!contain(key + "_" + i, Integer.valueOf(segments[i]))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contain(String key, Integer val) {
        Boolean execute = redisTemplate.execute((RedisCallback<Boolean>) connection ->
                                                    connection.getBit(key.getBytes(), val));
        return execute;
    }
}
