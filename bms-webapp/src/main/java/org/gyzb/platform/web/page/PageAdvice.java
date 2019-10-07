package org.gyzb.platform.web.page;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.gyzb.platform.web.Constants;
import org.gyzb.platform.common.utils.ThreadUtils;
import org.springframework.stereotype.Component;

/**
 * 分页功能的探测性增强
 * 
 * @project sevenpay-platform-web
 * @fileName PageInfoAdvice.java
 * @author huiquan.ma
 * @date 2015年5月8日
 * @memo
 */
@Aspect
@Component
public class PageAdvice {
	
	/**
	 * 只拦截注解了Page的方法
	 */
	@Around("@annotation(org.gyzb.platform.web.page.Page)")
	public Object detactPageAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		ThreadUtils.put(Constants.Platform.$_BY_PAGE, Boolean.TRUE);
		try {
			return proceedingJoinPoint.proceed();
		} finally {
			ThreadUtils.remove(Constants.Platform.$_BY_PAGE);
		}
	}
}
