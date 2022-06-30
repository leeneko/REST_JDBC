package com.leeneko.user.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leeneko.user.model.User;
import com.leeneko.user.model.UserIn;
import com.leeneko.util.DbUtils;

public class GetUserList {
	
	public List<User> getList(UserIn input) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("factoryCode", "1400");
		paramMap.put("userId", input.getUserId());
		paramMap.put("fromDate", input.getFromDate());
		paramMap.put("toDate", input.getToDate());
		
		String sql = DbUtils.getSqlByPath(this.getClass(), "select_member_list.sql");
		
		List<User> list = DbUtils.selectList(sql, paramMap, User.class);
		
		return list;
	}
	
}
