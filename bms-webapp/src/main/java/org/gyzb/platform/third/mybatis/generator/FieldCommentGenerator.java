package org.gyzb.platform.third.mybatis.generator;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.internal.util.JavaBeansUtil;

/**
 * 字段注释产生器
 */
public class FieldCommentGenerator {

	/**
	 * 针对java文件进行处理,增加注释
	 * 
	 * @param file java文件
	 * @param tableName 表名
	 * @param className 类名
	 * @param jdbcConfig 数据库连接配置
	 */
	public static void generate_oracle(File file, String tableName, String className, JDBCConnectionConfiguration jdbcConfig) throws Exception {

		Class.forName(jdbcConfig.getDriverClass());
		Connection connection = DriverManager.getConnection(jdbcConfig.getConnectionURL(), jdbcConfig.getUserId(), jdbcConfig.getPassword());

		// 收集字段注释
		PreparedStatement prepareStatement = connection.prepareStatement("SELECT T.COLUMN_NAME, T.COMMENTS FROM USER_COL_COMMENTS T WHERE T.table_name = ? ");
		prepareStatement.setString(1, tableName);

		HashMap<String, String> mapper = new HashMap<String, String>();

		ResultSet executeQuery = prepareStatement.executeQuery();
		while (executeQuery.next()) {
			String columnName = executeQuery.getString(1);
			String comment = executeQuery.getString(2);
			mapper.put(columnName, comment);
		}

		connection.close();

		// 生成文件
		ByteArrayInputStream bais = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bais));

		// 清空文件
		file.delete();
		FileUtils.touch(file);

		FileWriter writer = new FileWriter(file);

		while (true) {
			String line = bufferedReader.readLine();
			if (line == null) {
				break;
			}
			if (isFieldLine(line)) {
				String fieldName = parseField(line);
				for (String columnName : mapper.keySet()) {
					if (isSameField(fieldName, columnName)) {
						String columnComment = mapper.get(columnName);
						IOUtils.write("	/**" + IOUtils.LINE_SEPARATOR, writer);
						IOUtils.write("	 * " + columnComment + IOUtils.LINE_SEPARATOR, writer);
						IOUtils.write("	 */" + IOUtils.LINE_SEPARATOR, writer);
						break;
					}
				}
			}

			IOUtils.write(line, writer);
			IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
		}

		writer.flush();
		IOUtils.closeQuietly(writer);
	}
	
	/**
	 * 针对java文件进行处理,增加注释
	 * 
	 * @param file java文件
	 * @param tableName 表名
	 * @param className 类名
	 * @param jdbcConfig 数据库连接配置
	 */
	public static void generate_mysql(File file, String tableName, String className, JDBCConnectionConfiguration jdbcConfig) throws Exception {

		Class.forName(jdbcConfig.getDriverClass());
		Connection connection = DriverManager.getConnection(jdbcConfig.getConnectionURL(), jdbcConfig.getUserId(), jdbcConfig.getPassword());

		// 收集字段注释
		PreparedStatement prepareStatement = connection.prepareStatement("SELECT T.COLUMN_NAME, T.COLUMN_COMMENT COMMENTS FROM INFORMATION_SCHEMA.COLUMNS T WHERE T.table_name = ? ");
		prepareStatement.setString(1, tableName);

		HashMap<String, String> mapper = new HashMap<String, String>();

		ResultSet executeQuery = prepareStatement.executeQuery();
		while (executeQuery.next()) {
			String columnName = executeQuery.getString(1);
			String comment = executeQuery.getString(2);
			mapper.put(columnName, comment);
		}

		connection.close();

		// 生成文件
		ByteArrayInputStream bais = new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bais));

		// 清空文件
		file.delete();
		FileUtils.touch(file);

		FileWriter writer = new FileWriter(file);

		while (true) {
			String line = bufferedReader.readLine();
			if (line == null) {
				break;
			}
			if (isFieldLine(line)) {
				String fieldName = parseField(line);
				for (String columnName : mapper.keySet()) {
					if (isSameField(fieldName, columnName)) {
						String columnComment = mapper.get(columnName);
						IOUtils.write("	/**" + IOUtils.LINE_SEPARATOR, writer);
						IOUtils.write("	 * " + columnComment + IOUtils.LINE_SEPARATOR, writer);
						IOUtils.write("	 */" + IOUtils.LINE_SEPARATOR, writer);
						break;
					}
				}
			}

			IOUtils.write(line, writer);
			IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
		}

		writer.flush();
		IOUtils.closeQuietly(writer);
	}

	/**
	 * 判断是否是字段
	 */
	private static boolean isFieldLine(String line) {

		if (line != null) {
			line = line.trim();
			if (line.startsWith("private") && line.endsWith(";")) {
				String[] strings = line.split("\\s");
				if (strings.length == 3) {
					return true;
				}
			}
		}

		return false;
	};

	/**
	 * 抽取字段名称
	 */
	private static String parseField(String line) {
		line = line.trim();
		line = line.replaceAll(";$", "");
		String[] strings = line.split("\\s");
		return strings[2];
	}

	private static boolean isSameField(String fieldName, String columnName) {

		if (fieldName.equals(columnName)) {
			return true;
		}

		if (fieldName.equals(JavaBeansUtil.getCamelCaseString(columnName, false))) {
			return true;
		}

		return false;
	}

}
