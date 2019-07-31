var basePath = 'http://' + window.location.host + '/console/';
var register = (function() {
	var thiz = $(this);

	var init = function() {
		console.log('register init...');
		$("#console_reg").click(function() {
			reg();
			return false;
		});
		$("input").focus(function() {
			$(".label-warning").hide();
		});
	};

	var reg = function() {
		$('form').ajaxSubmit({
			dataType : 'json',
			beforeSubmit : function() {
				if (check())
					$("#console_reg").html('注册中。。');
				else
					return false;
			},
			success : function(xhr) {
				if ($.isEmptyObject(xhr))
					window.location.href = basePath;
				else
					$("#account_warnning").html(xhr.msg).fadeIn(1000);
				m3();
				$("#console_reg").html('注 册');
			}
		});
	};

	var check = function() {
		var name = $.trim($("input[name='name']").val());
		var account = $.trim($("input[name='account']").val());
		var password = $.trim($("input[name='password']").val());
		var repassword = $.trim($("input[name='repassword']").val());
		 if(!$("input[name='agreement']").is(':checked')){
			$("#name_warnning").html("请阅读并同意协议！").fadeIn(1000);
			return false;
		} else if ('' == name || name.length < 2) {
			$("#name_warnning").html("全名填写有误（至少2个字符）").fadeIn(1000);
			return false;
		} else if ('' == account || account.length < 6) {
			$("#account_warnning").html("帐号填写有误（至少6个字符）").fadeIn(1000);
			return false;
		} else if ('' == password || password.length < 6) {
			$("#password_warnning").html("密码填写有误（至少6个字符）").fadeIn(1000);
			return false;
		} else if (password != repassword) {
			$("#repassword_warnning").html("两次输入密码不一致！").fadeIn(1000);
			return false;
		}
		return true;
	};
	var m3 = function() {
		$("input[name='account']").focus(function() {
			$("#account_warnning").fadeOut(2000);
		});
	};
	return {
		init : init
	};
})();

$(function() {
	register.init();
});
