package com.mt.console.web.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mt.console.util.Tools;
import com.mt.console.util.algorithm.PasswordCrypt;
import com.mt.console.web.mapper.IAdminMembershipMapper;
import com.mt.console.web.po.AdminMembership;
import com.mt.console.web.po.AdminMembershipInfo;
import com.mt.console.web.po.IMContact;
import com.mt.console.web.service.IAdminMembershipService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service("AdminMembershipServiceImpl")
public class AdminMembershipServiceImpl implements IAdminMembershipService {


	@Autowired
	private IAdminMembershipMapper imm;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public String registerAdminMembership(AdminMembership m) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		m.setCreateTime(time);
		String pw = m.getPassword();
		m.setPassword(PasswordCrypt.crypt(m.getAccount(), m.getPassword()));
		m.setPlatform("welltou_user");
		m.setRoleNum(6);
		int count = imm.checkAccountExit(m.getAccount());
		if (count == 0) {
			// id为返回成功的行数
			imm.insertAdminMembership(m);
			AdminMembershipInfo ami = new AdminMembershipInfo();
			ami.setAccount(m.getAccount());
			if(Tools.isPhone(m.getAccount())){
				ami.setPhone(m.getAccount());
			} else if(Tools.isEmail(m.getAccount())){
				ami.setEmail(m.getAccount());
			}
			ami.setCreateTime(time);
			ami.setRemark(pw);
			imm.insertAdminMembershipInfo(ami);
			return "";
		} else {
			log.info("帐号重复");
			return "帐号重复";
		}
	}

	@Override
	public AdminMembership getAdminMembership(AdminMembership m) {
		m.setPassword(PasswordCrypt.crypt(m.getAccount(), m.getPassword()));
		AdminMembership am = imm.selectAdminMembership(m);
		if (null != am) {
			am.setLastLoginIp(m.getLastLoginIp());
			am.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
			am.setOnline(1);
			imm.updateAdminMembership(am);
		}
		return am;
	}

	@Override
	public List<Map<String, Object>> getAllAdminMembership() {
		return imm.getAllAdminMembership();
	}

	@Override
	public List<Map<String, Object>> getIMContact(String account) {
		return imm.getIMContact(account);
	}

	@Override
	public AdminMembershipInfo getAdminMembershipInfo(String account) {
		return imm.selectAdminMembershipInfo(account);

	}

	@Override
	public void addContactAdminMembership(String account, String toAccount) {
		IMContact ic = new IMContact();
		ic.setAccount(account);
		ic.setToAccount(toAccount);
		ic.setCreateTime(new Timestamp(System.currentTimeMillis()));
		imm.insertContactAdminMembership(ic);
	}

}
