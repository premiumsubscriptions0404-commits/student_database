package com.example.student_database.Config;

import java.time.Duration;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.cache.annotation.EnableCaching;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        Hibernate6Module hibernate6Module = new Hibernate6Module();
        // Cached entities carry lazy Hibernate collection proxies (e.g. PersistentBag) that
        // cannot be reconstructed from JSON without an open Session. Forcing lazy loading here
        // resolves those proxies to plain collections while the request's Session is still open,
        // so the cached JSON is plain data instead of a Hibernate-specific type.
        hibernate6Module.enable(Hibernate6Module.Feature.FORCE_LAZY_LOADING);
        // Without this, PersistentBag/PersistentSet get their own runtime type embedded in the
        // JSON (e.g. "org.hibernate.collection.spi.PersistentBag") instead of a plain List, and
        // that type can't be reconstructed on read without a live Hibernate Session.
        hibernate6Module.enable(Hibernate6Module.Feature.REPLACE_PERSISTENT_COLLECTIONS);

        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        serializer.configure(mapper -> mapper.registerModule(hibernate6Module));

        RedisCacheConfiguration jsonCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));

        return builder -> builder.cacheDefaults(jsonCacheConfig);
    }
}
