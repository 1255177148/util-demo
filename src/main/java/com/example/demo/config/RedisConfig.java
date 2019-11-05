package com.example.demo.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

/**
 * @Author hezhan
 * @Date 2019/11/5 10:08
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis1.host}")
    private String dbHost;

    @Value("${spring.redis1.port}")
    private int dbPort;

    @Value("${spring.redisTimeout}")
    private int timeout;

    @Value("${spring.redis2.host}")
    private String tokenHost;

    @Value("${spring.redis2.port}")
    private int tokenPort;

    @Value("${spring.redis2.password}")
    private String tokenPassword;

    @Value("${spring.lettuce.pool.max-active}")
    private int maxActive;

    @Value("${spring.lettuce.pool.min-idle}")
    private int minIdle;

    @Value("${spring.lettuce.pool.max-wait}")
    private int maxWait;

    @Value("${spring.lettuce.shutdown-timeout}")
    private int shutTimeout;

    /**
     * 配置连接池
     * @return
     */
    @Bean
    public GenericObjectPoolConfig redisPool() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxWaitMillis(maxWait);
        return genericObjectPoolConfig;
    }

    /**
     * 配置第一个redis的环境配置
     * @return
     */
    @Bean(name = "dbRedisConfig")
    public RedisStandaloneConfiguration dbRedisConfig() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(dbHost);
        config.setPort(dbPort);
        return config;
    }

    /**
     * 配置第二个redis的环境配置
     * @return
     */
    @Bean(name = "tokenRedisConfig")
    public RedisStandaloneConfiguration tokenRedisConfig() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(tokenHost);
        config.setPort(tokenPort);
        config.setPassword(tokenPassword);
        return config;
    }

    /**
     * 配置第一个redis的lettuce连接工厂
     * 将lettuce连接池和redis环境配置以参数形式注入
     * @param config
     * @param dbRedisConfig
     * @return
     */
    @Bean(name = "factory")
    @Primary
    public LettuceConnectionFactory factory(GenericObjectPoolConfig config, @Qualifier("dbRedisConfig") RedisStandaloneConfiguration dbRedisConfig) {
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(timeout))
                .poolConfig(config).shutdownTimeout(Duration.ofMillis(shutTimeout)).build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(dbRedisConfig, clientConfig);
        return factory;
    }

    /**
     * 配置第二个redis的lettuce连接工厂
     * 将lettuce连接池和redis环境配置以参数形式注入
     * @param config
     * @param tokenRedisConfig
     * @return
     */
    @Bean(name = "factory2")
    public LettuceConnectionFactory factory2(GenericObjectPoolConfig config, @Qualifier("tokenRedisConfig") RedisStandaloneConfiguration tokenRedisConfig){
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder().commandTimeout(Duration.ofMillis(timeout))
                .poolConfig(config).shutdownTimeout(Duration.ofMillis(shutTimeout)).build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(tokenRedisConfig, clientConfig);
        return factory;
    }

    /**
     * 将lettuce连接工厂注入，获取第一个redis的操作类
     * @param factory
     * @return
     */
    @Bean(name = "stringRedisTemplate")
    @Primary
    public StringRedisTemplate redisTemplate(@Qualifier("factory") LettuceConnectionFactory factory){
        return new StringRedisTemplate(factory);
    }

    /**
     * 将lettuce连接工厂注入，获取第二个redis的操作类
     * @param factory
     * @return
     */
    @Bean(name = "stringRedisTemplate2")
    public StringRedisTemplate redisTemplate2(@Qualifier("factory2") LettuceConnectionFactory factory){
        return new StringRedisTemplate(factory);
    }
}
