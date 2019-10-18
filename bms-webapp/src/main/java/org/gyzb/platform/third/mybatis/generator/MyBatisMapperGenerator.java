package org.gyzb.platform.third.mybatis.generator;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gyzb.platform.common.bean.InvokeInfo;
import org.gyzb.platform.common.utils.InvokeUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * MyBatis映射层自动生成器<br>
 */
public class MyBatisMapperGenerator {

	private static final Logger logger = LoggerFactory.getLogger(MyBatisMapperGenerator.class);

	/**
	 * XML配置文件
	 */
	public static final String XML_CONFIG_FILE = "GeneratorConfig.xml";

	/**
	 * 配置文件
	 */
	private String configFileName;

	/**
	 * 抽象配置
	 */
	private Configuration config;

	/**
	 * 抽象配置
	 */
	private JDBCConnectionConfiguration jdbcConfig;

	/**
	 * 调用类
	 */
	private Class<?> invokeClazz;

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	/**
	 * 代码生成方法主体
	 */
	public void generate() throws Exception {

		// 调用信息
		InvokeInfo invokeInfo = InvokeUtils.findInvokeInfo();

		// 调用类
		String invokeClassName = invokeInfo.getClassName();
		invokeClazz = Class.forName(invokeClassName);

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		ConfigurationParser cp = new ConfigurationParser(warnings);
		String configFilePath = "/" + invokeClazz.getPackage().getName().replaceAll("\\.", "/") + "/" + configFileName;
		logger.info("使用配置文件路径:{}", configFilePath);

		InputStream inputStream = invokeClazz.getResourceAsStream(configFilePath);
		this.config = cp.parseConfiguration(inputStream);

		Context context = this.config.getContexts().get(0);
		jdbcConfig = context.getJdbcConnectionConfiguration();

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);

		// 产生表注释
		generateClassComment();

		// 产生注释信息
		generateFieldComment();

		// 产生注解
		generateMapperAnnotation();

	}

	/**
	 * 增加JavaBean字段注释
	 */
	private void generateClassComment() {

		logger.info("增加JavaBean注释");

		Map<String, String> tableMapper = parseTableMapper(config);

		for (String key : tableMapper.keySet()) {
			try {

				String tableName = key;
				String className = tableMapper.get(key);

				File clazzFile = new File("src/main/java/" + invokeClazz.getPackage().getName().replaceAll("\\.", "/") + "/bean/" + className + ".java");
				File file = new File(clazzFile.getAbsolutePath());

				ClassCommentGenerator.generate_mysql(file, tableName, className, jdbcConfig);

			} catch (Exception e) {
				logger.error("表映射{}注释产生失败", key);
				logger.error("表映射注释产生失败异常打印", e);
			}
		}

	}

	/**
	 * 增加JavaBean字段注释
	 */
	private void generateFieldComment() {

		logger.info("增加JavaBean字段注释");

		Map<String, String> tableMapper = parseTableMapper(config);

		for (String key : tableMapper.keySet()) {
			try {

				String tableName = key;
				String className = tableMapper.get(key);

				File clazzFile = new File("src/main/java/" + invokeClazz.getPackage().getName().replaceAll("\\.", "/") + "/bean/" + className + ".java");
				File file = new File(clazzFile.getAbsolutePath());

				FieldCommentGenerator.generate_mysql(file, tableName, className, jdbcConfig);

			} catch (Exception e) {
				logger.error("表映射{}注释产生失败", key);
				logger.error("表映射注释产生失败异常打印", e);
			}
		}
	}

	/**
	 * 产生映射注解
	 */
	private void generateMapperAnnotation() {
		MapperAnnotationGenerator.generate(invokeClazz, MapperScan.class);
	}

	/**
	 * 从配置文件中解析Table与Entity映射关系
	 */
	private Map<String, String> parseTableMapper(Configuration config) {

		Map<String, String> tableMapper = new HashMap<String, String>();

		for (Context context : config.getContexts()) {
			List<TableConfiguration> configurations = context.getTableConfigurations();
			for (TableConfiguration tableConfiguration : configurations) {
				tableMapper.put(tableConfiguration.getTableName(), tableConfiguration.getDomainObjectName());
			}
		}

		return tableMapper;
	}

}
