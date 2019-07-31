package com.mt.console.web.service;

import java.util.List;
import java.util.Map;

public interface IMenuService {

	public List<Map<String, Object>> getMenu(int roleNum);
}
