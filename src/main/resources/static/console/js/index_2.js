/** 首页 maitao 2015 */
function isPhone(phone) {
	var length = phone.length;
	return length == 11
			&& /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(phone);
}

function isEMail(email) {
	var length = email.length;
	return length > 5
			&& /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/
					.test(email);
}

function isAccount(account) {
	return isPhone(account) || isEMail(account);
}

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
Date.prototype.format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

$.ajaxProxy = function(url, type, async, dataType, data, successfn, errorfn,
		showLoading) {
	type = (type == null || type == "" || typeof (type) == "undefined") ? "POST"
			: type;
	async = (async == null || async == "" || typeof (async) == "undefined") ? "true"
			: async;
	data = (data == null || data == "" || typeof (data) == "undefined") ? {
		"date" : new Date().getTime()
	} : data;
	dataType = (dataType == null || dataType == "" || typeof (dataType) == "undefined") ? "json"
			: dataType;
	$.ajax({
		type : type,
		url : url,
		async : async,
		data : data,
		dataType : dataType,
		beforeSend : function() {
			if (null == showLoading ? true : showLoading)
				$("#iloading").show();
		},
		success : function(d) {
			successfn(d);
		},
		complete : function() {
			$("#iloading").hide();
		},
		error : function(e) {
			errorfn(e);
		}
	});
};
$(function() {

});