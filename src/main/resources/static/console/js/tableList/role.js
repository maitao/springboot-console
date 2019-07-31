var role = (function() {
	var thiz = $(this);
	var init = function() {
		console.log('role init');
		$('.box-title').html('角色列表');
		$('#tsHead')
				.empty()
				.html(
						'<tr><th style="width: 15px;"><input type="checkbox"></th><th>序号</th><th>编号</th><th>角色名</th><th>操作集</th><th>菜单集</th><th>状态</th><th>备注</th><th>创建时间</th><th>修改时间</th><th>操作</th></tr>');
		new commonPage(role, 'role', $("#tsList"), 'page_select', 'pNum', '',
				$(".pagination")).init(10);
	};

	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList)
				.each(
						function(i) {
							if (this.status == 1)
								this.status = '是';
							else
								this.status = '否';
							var trList = '<tr><td><input type="checkbox"></td><td style="font-weight:bold;">'
									+ (++index)
									+ '</td><td>'
									+ this.number
									+ '</td><td>'
									+ this.name
									+ '</td><td>'
									+ this.operation
									+ '</td><td>'
									+ this.menu
									+ '</td><td>'
									+ this.status
									+ '</td><td>'
									+ this.remark
									+ '</td><td>'
									+ this.createTime
									+ '</td><td class="status_open status_change" data-value="'
									+ this.updateTime
									+ '"></td><td><div class="tools"><i class="fa fa-edit"></i><i class="fa fa-trash-o"></i></div></td></tr>';

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
	role.init();
})();