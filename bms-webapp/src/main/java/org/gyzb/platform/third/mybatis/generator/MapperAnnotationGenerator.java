package org.gyzb.platform.third.mybatis.generator;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 产生Mapper注解
 */
public class MapperAnnotationGenerator {

	private static Logger logger = LoggerFactory.getLogger(MapperAnnotationGenerator.class);

	public static void generate(Class<?> generatorClass, Class<?> annotationClass) {

		// 循环查询Mapper文件
		File mapperFolder = new File("src/main/java/" + generatorClass.getPackage().getName().replaceAll("\\.", "/") + "/dao/");
		File[] mapperFiles = mapperFolder.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith("Mapper.java")) {
					return true;
				}
				return false;
			}

		});

		// 针对每个Mapper进行处理
		for (File mapperFile : mapperFiles) {
			try {

				// 生成文件
				ByteArrayInputStream bais = new ByteArrayInputStream(FileUtils.readFileToByteArray(mapperFile));
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bais));

				// 清空文件
				mapperFile.delete();
				FileUtils.touch(mapperFile);

				FileWriter writer = new FileWriter(mapperFile);

				while (true) {
					String line = bufferedReader.readLine();
					if (line == null) {
						break;
					}

					if (line.startsWith("public interface")) {
						IOUtils.write("import ", writer);
						IOUtils.write(annotationClass.getCanonicalName(), writer);
						IOUtils.write(";", writer);

						IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
						IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
						IOUtils.write(IOUtils.LINE_SEPARATOR, writer);

						IOUtils.write("@", writer);
						IOUtils.write(annotationClass.getSimpleName(), writer);
						IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
					}

					IOUtils.write(line, writer);
					IOUtils.write(IOUtils.LINE_SEPARATOR, writer);
				}

				writer.flush();
				IOUtils.closeQuietly(writer);
			} catch (Exception e) {
				logger.error("注解产生异常", mapperFile.getAbsolutePath());
				logger.error("注解产生异常", e);
			}
		}

	}

}
