package com.casestudy.forecast.weather.Cache;

import com.casestudy.forecast.weather.Model.Response.Stats;

import java.util.concurrent.ConcurrentHashMap;

/**
 * A simple concurrenthashmap to cache the weather forecast results in an in-memory layout.
 */

//TODO : Add TTL (time to live) for the items in cache
public class Cache {

    private static final ConcurrentHashMap<String, Stats> cache = new ConcurrentHashMap<>();

    public Stats get (String city) {
        return cache.get(city);
    }

    public void put(String city, Stats stats) {
        cache.put(city, stats);
    }
}
