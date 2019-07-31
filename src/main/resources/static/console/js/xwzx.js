var xwzx = (function() {
	var thiz = $(this);
	var init = function() {
		// 列表标题
		$('.xwzx-title').html('‘夏娃之秀’助力参加者');
		// 列表头
		$('#tsHead')
				.empty()
				.html(
						'<tr><th>序号</th><th>姓名</th><th>手机号码</th><th>参加时间</th><th>参加的IP</th><th>助力数</th></tr>');
		// 列表初始化
		new commonPage(xwzx, 'xwzx', $("#tsList"), 'page_select_0', 'pNum_0',
				'', $(".pagination_0")).init(10);
	};

	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList).each(
				function(i) {
					var trList = '<tr><td style="font-weight:bold;">'
							+ (++index) + '</td><td>' + this.name + '</td><td>'
							+ this.phonenum + '</td><td>' + this.adddate + ' '
							+ this.addtime + '</td><td>' + this.addip
							+ '</td><td>' + this.agreecount + '</td></tr>';

					str += trList;
				});
		return str;
	};

	return {
		init : init,
		dataList : dataList
	};
})();

var xwzx_agree = (function() {
	var thiz = $(this);

	var init = function() {
		$('.xwzx-agree-title').html('助力记录');
		$('#tsHead_1')
				.empty()
				.html(
						'<tr><th nowrap="nowrap">序号</th><th>参赛者OPENID</th><th>助力者OPENID</th><th nowrap="nowrap">参加时间</th><th>参加的IP</th></tr>');
		new commonPage(xwzx_agree, 'xwzx_agree', $("#tsList_1"),
				'page_select_1', 'pNum_1', '', $(".pagination_1")).init(10);
	};

	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList).each(
				function(i) {
					var trList = '<tr><td style="font-weight:bold;">'
							+ (++index) + '</td><td nowrap="nowrap">'
							+ this.openid + '</td><td nowrap="nowrap">'
							+ this.aopenid + '</td><td nowrap="nowrap">'
							+ this.adddate + ' ' + this.addtime + '</td><td>'
							+ this.addip + '</td></tr>';

					str += trList;
				});
		return str;
	};
	return {
		init : init,
		dataList : dataList
	};
})();

(function() {
	xwzx.init();
	xwzx_agree.init();
	$.ajaxProxy("xwzx/count", "POST", true, "json", {}, function(data) {
		$('#xwzx_a').html(data.data.a);
		$('#xwzx_b').html(data.data.b);
		$('#xwzx_c').html(data.data.c);
	}, function() {
	});
})();