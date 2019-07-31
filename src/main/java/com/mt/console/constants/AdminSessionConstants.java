package com.mt.console.constants;

/**
 * 一次回话信息保存20161105
 * 
 * @author maitao
 *
 */
public class AdminSessionConstants {
	// 会员信息
	public static String LOGIN_ACCOUNT_ENCRYPTION = "szyx_login_account_digest";
	public static String LOGIN_MEMBERSHIP = "login_membership_session";
	public static String LOGIN_MEMBERSHIP_INFO = "MEMBERSHIP_INFO";
	public static String LOGIN_MEMBERSHIP_ACCOUNT = "login_membership_account_session";
	public static String LOGIN_MEMBERSHIP_LOGON_COUNT = "login_membership_logon_count_session";


	public static String MT_SYS_CONF = "MT_SYS_CONF";
	public static String MT_USER_SETTING = "MT_USER_SETTING";

	public static String MEMBERSHIP_VERIFYCODE = "membership_verifycode_session";

	// 权限
	public static String PERMISSION_CLASS = "permission_class_session";
	public static String PERMISSION_METHOD = "permission_method_session";

	// 记录
	public static String RECORD_NUMBER = "record_number_session";

	// 表单uuid，防重提交
	public static String FORM_UUID_LIST_SESSION = "form_uuid_list_session";

	// SVN
	public static String MT_EFIIOSLKAFJOIAEJFIOIWEFU = "mt_efiioslkafjoiaejfioiwefu";
	public static String COOKIE_SVN_PATH = "cookie_svn_path";// svn项目路径
	public static String COOKIE_SVN_USERNAME = "cookie_svn_username";// svn用户
	public static String COOKIE_SVN_PASSWORD = "cookie_svn_password";// svn密码
	public static String SESSION_SVN_LIST = "session_svn_list";

	public static String SESSION_CURRENT_PAGE = "session_current_page";

}
