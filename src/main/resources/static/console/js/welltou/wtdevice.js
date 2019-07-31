var wtdevice = (function() {
	var thiz = $(this);
	var init = function() {
		// 列表标题
		$('.wtuser-title').html('微舵管理员列表');
		// 列表头
		$('#tsHead')
				.empty()
				.html(
						'<tr><th style="width: 15px;"><input type="checkbox"></th><th>序号</th><th>编号</th><th>设备ID(电话号码)</th><th>设备名称</th><th>所属用户</th><th>添加时间</th><th>更新时间</th><th>定位时间</th><th>操作</th></tr>');
		// 列表初始化
		new commonPage(wtdevice, 'wtdevice', $("#tsList"), 'page_select', 'pNum',
				'', $(".pagination")).init(10);
	};

	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList)
				.each(
						function(i) {
							if (index % 2 == 0)
								tr_c = 'tr_even';
							else
								tr_c = 'tr_odd';
							if (null != this.UPDATE_TIME) {
								this.UPDATE_TIME = new Date(this.UPDATE_TIME)
										.format("yyyy-MM-dd HH:mm:ss")
							} else {
								this.UPDATE_TIME = '-';
							}
							var trList = '<tr class='
									+ tr_c
									+ '><td><input type="checkbox"></td><td style="font-weight:bold;">'
									+ (++index)
									+ '</td><td>'
									+ this.ID
									+ '</td><td>'
									+ this.DID
									+ '</td><td>'
									+ this.NAME
									+ '</td><td>'
									+ this.USER_ID
									+ '</td><td>'
									+ this.CREATE_TIME
									+ '</td><td>'
									+ this.UPDATE_TIME
									+ '</td><td>'
									+ this.TIME_POINT
									+ '</td><td><span data-value="'
									+ this.id
									+ '" class="record_edit" style="cursor:pointer;">编辑</span> <span data-value="'
									+ this.id
									+ '" class="record_delete" style="cursor:pointer;">删除</span></td></tr>';
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
	wtdevice.init();
})();