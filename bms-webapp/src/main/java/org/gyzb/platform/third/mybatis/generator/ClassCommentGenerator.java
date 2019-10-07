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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.mybatis.generator.config.JDBCConnectionConfiguration;

/**
 * 类注释产生器
 */
public class ClassCommentGenerator {

	/**
	 * 针对java文件进行处理,增加注释
	 * 
	 * @param file
	 *            java文件
	 * @param tableName
	 *            表名
	 * @param className
	 *            类名
	 * @param jdbcConfig
	 *            数据库连接配置
	 */
	public static void generate_oracle(File file, String tableName, String className, JDBCConnectionConfiguration jdbcConfig) throws Exception {

		Class.forName(jdbcConfig.getDriverClass());
		Connection connection = DriverManager.getConnection(jdbcConfig.getConnectionURL(), jdbcConfig.getUserId(), jdbcConfig.getPassword());

		// 查询表的注释
		PreparedStatement prepareStatement = connection.prepareStatement("SELECT T.comments FROM USER_TAB_COMMENTS T WHERE T.table_name = ?");
		prepareStatement.setString(1, tableName);

		String comment = "";

		ResultSet executeQuery = prepareStatement.executeQuery();
		if (executeQuery.next()) {
			comment = executeQuery.getString(1);
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
			if (isClassLine(line)) {

				IOUtils.write("/**" + IOUtils.LINE_SEPARATOR, writer);
				IOUtils.write("* " + comment + IOUtils.LINE_SEPARATOR, writer);
				IOUtils.write("*/" + IOUtils.LINE_SEPARATOR, writer);
				
				IOUtils.write(line, writer);
				IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
				IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
				
				continue;
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
	 * @param file
	 *            java文件
	 * @param tableName
	 *            表名
	 * @param className
	 *            类名
	 * @param jdbcConfig
	 *            数据库连接配置
	 */
	public static void generate_mysql(File file, String tableName, String className, JDBCConnectionConfiguration jdbcConfig) throws Exception {

		Class.forName(jdbcConfig.getDriverClass());
		Connection connection = DriverManager.getConnection(jdbcConfig.getConnectionURL(), jdbcConfig.getUserId(), jdbcConfig.getPassword());

		// 查询表的注释
		PreparedStatement prepareStatement = connection.prepareStatement("SELECT T.TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES T WHERE T.table_name = ?");
		prepareStatement.setString(1, tableName);

		String comment = "";

		ResultSet executeQuery = prepareStatement.executeQuery();
		if (executeQuery.next()) {
			comment = executeQuery.getString(1);
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
			if (isClassLine(line)) {

				IOUtils.write("/**" + IOUtils.LINE_SEPARATOR, writer);
				IOUtils.write("* " + comment + IOUtils.LINE_SEPARATOR, writer);
				IOUtils.write("*/" + IOUtils.LINE_SEPARATOR, writer);
				
				IOUtils.write(line, writer);
				IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
				IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
				
				continue;
			}

			IOUtils.write(line, writer);
			IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
		}

		writer.flush();
		IOUtils.closeQuietly(writer);
	}

	/**
	 * 判断是否是类申明的字段
	 */
	private static boolean isClassLine(String line) {

		if (line != null) {
			line = line.trim();
			if (line.startsWith("public class ") && line.endsWith("{")) {
				return true;
			}
		}

		return false;
	};

}
