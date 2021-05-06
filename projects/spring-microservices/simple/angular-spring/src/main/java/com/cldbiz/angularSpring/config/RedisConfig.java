package com.cldbiz.angularSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.cldbiz.angularSpring.spring.component.AppEnvironment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

	  @Autowired
	  protected AppEnvironment appEnv;

	  @Bean
	  public ObjectMapper objectMapper() {
	      return Jackson2ObjectMapperBuilder.json()
    		  .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
              .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
              .build();
	  }

	  @Bean
	  public RedisConnectionFactory connectionFactory() {
	    return new LettuceConnectionFactory(appEnv.getRedisHost(), appEnv.getRedisPort());
	  }

	  @Bean
	  public RedisTemplate<Long, Object> redisTemplate() {
	    RedisTemplate<Long, Object> template = new RedisTemplate<Long, Object>();
	    
	    template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(objectMapper()));
	    template.setConnectionFactory(connectionFactory());
	    template.setEnableTransactionSupport(false);
	    return template;
	  }
	  
	  @Bean
	  public RedisCacheManager redisCacheManager() {
		  RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				  .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper())))
				  .disableCachingNullValues();
		  
		  return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory())
				  .cacheDefaults(redisCacheConfiguration).build();
	  }  
}
