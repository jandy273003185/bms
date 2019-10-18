package org.gyzb.platform.web.admin.function.dao;

import java.util.List;

import org.gyzb.platform.web.admin.function.bean.Function;
import org.gyzb.platform.web.admin.function.mapper.FunctionMapper;
import org.gyzb.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName FunctionDAO.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@Repository
public class FunctionDAO {
	
	@Autowired
	private FunctionMapper functionMapper;
	
	@Page
	public List<Function> selectFunctionByIdAndName(Function function){
		return functionMapper.selectFunctionsByIdAndName(function);
	}
}
