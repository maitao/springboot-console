var basePath = 'http://' + window.location.host + '/console/';

var login = (function() {
	var thiz = $(this);
	var init = function() {
		$.ajax({
			type : "POST",
			url : "/console/cookie",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				if (null != data.account) {
					$("input[name='account']").val(data.account);
					$(".icheckbox_square-blue").click();
					$("input[name='remember']").attr("checked", true);
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
		$("input[name='password']").bind('keypress', function(event) {
			if (event.keyCode == "13") {
				logon();
				return false;
			}
		});
		$("#account_logon").click(function() {
			logon();
			return false;
		});
		$('#yzmImg').bind(
				'click',
				function(e) {
					$('#yzmImg').attr('src',
							'/console/verifycode?r=' + Math.random());
					return false;
				});
	};

	var logon = function() {
		$('form').ajaxSubmit({
			dataType : 'json',
			beforeSubmit : function() {
				if (check()) {
					$("#account_logon").html('登录中。。');
				} else {
					return false;
				}
			},
			success : function(xhr) {
				if ($.isEmptyObject(xhr)){
					window.location.href = basePath;
				}
				else {
					$("#account_warnning").html(xhr.msg).fadeIn(1000);
					$("input[name='account']").focus(function() {
						$("#account_warnning").fadeOut(2000);
					});
					$("#account_logon").html('登 录');
					if (xhr.logonCount >= 3) {
						// 显示验证码登录
						$('.verify_div').show();
					}
				}
			}
		});
	};
	var check = function() {
		var account = $.trim($("input[name='account']").val());
		var password = $.trim($("input[name='password']").val());
		if ('' == account || account.length < 6) {
			$("#account_warnning").html("帐号填写有误（至少6个字符）").fadeIn(1000);
			return false;
		}
		if ('' == password || password.length < 6) {
			$("#password_warnning").html("密码填写有误（至少6个字符）").fadeIn(1000);
			return false;
		}
		return true;
	};
	return {
		init : init
	};
})();

$(function() {
	console.log('login init...');
	login.init();
});
