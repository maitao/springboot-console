package com.mt.console.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mt.console.constants.AdminSessionConstants;
import com.mt.console.util.DateUtils;
import com.mt.console.util.PageList;
import com.mt.console.web.mapper.IAdminMembershipMapper;
import com.mt.console.web.mapper.ICommonMapper;
import com.mt.console.web.mapper.IGirlDateMapper;
import com.mt.console.web.mapper.ILogMapper;
import com.mt.console.web.mapper.IMenuMapper;
import com.mt.console.web.mapper.IOperationMapper;
import com.mt.console.web.mapper.IPortalCommonMapper;
import com.mt.console.web.mapper.IRoleMapper;
import com.mt.console.web.mapper.ISysConfMapper;
import com.mt.console.web.mapper.IXWZXMapper;
import com.mt.console.web.mapper.abs.ACommonDao;
import com.mt.console.web.po.Setting;
import com.mt.console.web.service.ICommonService;

@Service("CommonServiceImpl")
public class CommonServiceImpl extends ACommonDao implements ICommonService {

	@Autowired
	private IRoleMapper irm;

	@Autowired
	private IOperationMapper iop;

	@Autowired
	private ISysConfMapper iscm;

	@Autowired
	private IAdminMembershipMapper iamm;

	@Autowired
	private ILogMapper ilm;

	@Autowired
	private IMenuMapper imm;

	@Autowired
	private IGirlDateMapper iacm;

	@Autowired
	private IXWZXMapper ixwzxm;

	@Autowired
	private ICommonMapper icm;

	@Autowired
	private IPortalCommonMapper ipcm;

	public void status(JdbcTemplate jdbcTemplate, String id, String status, String key) {
		String sql = "";
		switch (key) {
		case "account":
			sql = "update t_szyx_account set status = ? where id = ?";
			break;
		case "account_permission":
			sql = "update t_szyx_permission set status = ? where id = ?";
			break;
		default:
			break;
		}
		update(jdbcTemplate, sql, new Object[] { Integer.valueOf(status), Long.valueOf(id) });
	}

	public List<String> setSession(JdbcTemplate jdbcTemplate, String key, Object accountId) {
		List<String> list = new ArrayList<String>();
		String sql = "";
		switch (key) {
		case "permission_class":
			sql = "select number from t_szyx_permission group by class_num";
			List<Map<String, Object>> list_pc = selectList(jdbcTemplate, sql, null);
			for (Map<String, Object> map : list_pc) {
				list.add(map.get("class_num").toString());
			}
			break;
		case "permission_method":
			sql = "select method_num from t_szyx_permission";
			List<Map<String, Object>> list_pm = selectList(jdbcTemplate, sql, null);
			for (Map<String, Object> map : list_pm) {
				list.add(map.get("method_num").toString());
			}
			break;
		case "login_account_permission":
			sql = "SELECT a.permission FROM t_szyx_role a WHERE number = (SELECT b.role_num FROM t_szyx_account b WHERE b.id = ?)";
			List<Map<String, Object>> list_rp = selectList(jdbcTemplate, sql, new Object[] { (long) accountId });
			for (Map<String, Object> map : list_rp) {
				String temp = map.get("permission").toString();
				String[] rps = temp.split(",");
				for (String string : rps) {
					list.add(string);
				}
			}
			break;
		default:
			break;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public String addFormUuid(HttpSession session) {
		String uuid = UUID.randomUUID().toString();
		Object obj = session.getAttribute(AdminSessionConstants.FORM_UUID_LIST_SESSION);
		List<String> list = null;
		list = (null != obj) ? (List<String>) obj : new ArrayList<String>();
		list.add(uuid);
		session.setAttribute(AdminSessionConstants.FORM_UUID_LIST_SESSION, list);
		return uuid;
	}

	public boolean removeFormUuid(HttpSession session, String uuid) {
		Object obj = session.getAttribute(AdminSessionConstants.FORM_UUID_LIST_SESSION);
		if (null != obj) {
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) obj;
			if (list.remove(uuid)) {
				session.setAttribute(AdminSessionConstants.FORM_UUID_LIST_SESSION, list);
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取分页记录
	 */
	@Override
	public <S> PageList getPageList(String key, int pageNo, int pageCount, String condition) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageList pl = new PageList();
		pl.setCurrentPageNo(pageNo);
		pl.setCurrentPageSize(pageCount);
		int index = (pageNo - 1) * pageCount;
		map.put("index", index);
		map.put("offset", pageCount);
		map.put("key", key);

		switch (key) {
		/*
		 * case "space": pl.setTotalSize(irdm.getTotalCount(map));
		 * pl.setDataList(irdm.getPageList(map)); break;
		 */
		case "membership":
			// 会员
			pl.setTotalSize(iamm.getTotalCount(map));
			pl.setDataList(iamm.getPageList(map));
			break;
		case "role":
			// 角色
			pl.setTotalSize(irm.getTotalCount(map));
			pl.setDataList(irm.getPageList(map));
			break;
		case "log":
			// 后台操作日志记录
			pl.setTotalSize(ilm.getTotalCount(map));
			pl.setDataList(ilm.getPageList(map));
			break;
		case "gdate":
			// 一起约女神
			pl.setTotalSize(iacm.getTotalCount(map));
			pl.setDataList(iacm.getPageList(map));
			break;
		case "sysConf":
			// 系统配置
			pl.setTotalSize(iscm.getTotalCount(map));
			pl.setDataList(iscm.getPageList(map));
			break;
		case "operation":
			// 操作
			pl.setTotalSize(iop.getTotalCount(map));
			pl.setDataList(iop.getPageList(map));
			break;
		case "xwzx":
			// 夏娃之秀
			pl.setTotalSize(ixwzxm.getTotalCount(map));
			pl.setDataList(ixwzxm.getPageList(map));
			break;
		case "xwzx_agree":
			// 夏娃之秀
			pl.setTotalSize(ixwzxm.getXTotalCount(map));
			pl.setDataList(ixwzxm.getXPageList(map));
			break;
		case "menu":
			// 菜单
			map.put("condition", condition);
			pl.setTotalSize(imm.getTotalCount(map));
			pl.setDataList(imm.getPageList(map));
			break;
		case "image":
			pl.setTotalSize(icm.getTotalCount(map));
			pl.setDataList(icm.getPageList(map));
			break;
		case "article":
			map.put("condition", StringUtils.isNotBlank(condition) ? Long.valueOf(condition) : "");
			pl.setTotalSize(ipcm.getTotalCount(map));
			pl.setDataList(ipcm.getPageList(map));
			break;

		default:
			break;
		}

		return pl;
	}

	@Override
	public Map<String, Object> sysConf() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map<String, Object> m : icm.getSysConf()) {
			map.put((String) m.get("number"), m.get("value"));
		}
		return map;
	}

	@Override
	public Setting getSetting(Long accountId) {
		return icm.getSetting(accountId);
	}

	@Override
	public Map<String, Object> getXWZXCount() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adddate", DateUtils.getCurrentDate("yyyy-MM-dd"));
		return ixwzxm.getXWZXCount(map);
	}

}
