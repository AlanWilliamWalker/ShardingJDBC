
package com.example.readwrite.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * RedisTemplate 配置
 *
 * @author admin
 */
@EnableCaching
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class RedisTemplateConfiguration {

	//日期 时间
	private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	//日期
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	//时间
	private static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	/**
	 * value 值 序列化
	 *
	 * @return RedisSerializer
	 */
//	@Bean
//	@ConditionalOnMissingBean(RedisSerializer.class)
//	public RedisSerializer<Object> redisSerializer() {
//		return new JdkSerializationRedisSerializer();
//	}

	@Bean
	@ConditionalOnMissingBean(RedisSerializer.class)
	public RedisSerializer<Object> redisSerializer() {
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		//日期
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(
				DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)
		));
		javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(
				DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)
		));
		javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(
				DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)
		));
		javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
				DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)
		));
		javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(
				DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)
		));
		javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(
				DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT)
		));
		objectMapper.registerModule(javaTimeModule);
		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		return jackson2JsonRedisSerializer;
	}

	@Bean(name = "redisTemplate")
	@ConditionalOnMissingBean(RedisTemplate.class)
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
													   RedisSerializer<Object> redisSerializer) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		RedisKeySerializer redisKeySerializer = new RedisKeySerializer();
		// key 序列化
		redisTemplate.setKeySerializer(redisKeySerializer);
		redisTemplate.setHashKeySerializer(redisKeySerializer);
		// value 序列化
		redisTemplate.setValueSerializer(redisSerializer);
		redisTemplate.setHashValueSerializer(redisSerializer);
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}



	@Bean(name = "redisUtil")
	@ConditionalOnBean(RedisTemplate.class)
	public RedisUtil redisUtils(RedisTemplate<String, Object> redisTemplate) {
		return new RedisUtil(redisTemplate);
	}

}
