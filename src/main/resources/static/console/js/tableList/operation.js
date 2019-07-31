var operation = (function() {
	var thiz = $(this);
	var init = function() {
		console.log('operation init');
		$('.box-title').html('操作权限列表');
		$('#tsHead')
				.empty()
				.html(
						'<tr><th style="width: 15px;"><input type="checkbox"></th><th>序号</th><th>编号</th><th>访问URL</th><th>类路径</th><th>方法名</th><th>备注</th><th>启用</th><th>创建时间</th><th>操作</th></tr>');
		new commonPage(operation, 'operation', $("#tsList"), 'page_select',
				'pNum', '', $(".pagination")).init(10);
	};

	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList)
				.each(
						function(i) {
							if (1 == this.status)
								this.status = '是';
							else
								this.status = '否';
							if (null == this.method_remark)
								this.method_remark = '';
							var trList = '<tr><td><input type="checkbox"></td><td style="font-weight:bold;">'
									+ (++index)
									+ '</td><td style="background:#e6c6c6;">'
									+ this.number
									+ '</td><td>'
									+ this.name
									+ '</td><td>'
									+ this.class_path
									+ '</td><td>'
									+ this.method_name
									+ '</td><td>'
									+ this.method_remark
									+ '</td><td>'
									+ this.status
									+ '</td><td>'
									+ this.create_time
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
	operation.init();
})();