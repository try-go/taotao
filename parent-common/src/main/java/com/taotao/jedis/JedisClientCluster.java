package com.taotao.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class JedisClientCluster implements JedisClient {
	private JedisCluster jedisCluster = jedisCreate();

	public static JedisCluster jedisCreate(){
		Set<HostAndPort> set = new HashSet<>();
		HostAndPort hp1 = new HostAndPort("192.168.108.202",7001);
		HostAndPort hp2 = new HostAndPort("192.168.108.202",7002);
		HostAndPort hp3 = new HostAndPort("192.168.108.202",7003);
		HostAndPort hp4 = new HostAndPort("192.168.108.202",7004);
		HostAndPort hp5 = new HostAndPort("192.168.108.202",7005);
		HostAndPort hp6 = new HostAndPort("192.168.108.202",7006);
		set.add(hp1);
		set.add(hp2);
		set.add(hp3);
		set.add(hp4);
		set.add(hp5);
		set.add(hp6);
		return new JedisCluster(set);
	}

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}

	@Override
	public Long hdel(String key, String... field) {
		return jedisCluster.hdel(key, field);
	}

}
