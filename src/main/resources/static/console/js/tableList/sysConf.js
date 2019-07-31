var sysConf = (function() {
	var thiz = $(this);
	var init = function() {
		$('.box-title').html('系统配置');
		$('#tsHead')
				.empty()
				.html(
						'<tr><th>序号</th><th>启用</th><th>编号</th><th>上级编号</th><th>键</th><th>值</th><th>备注</th><th>创建时间</th><th>修改时间</th></tr>');
		new commonPage(sysConf, 'sysConf', $("#tsList"), 'page_select', 'pNum',
				'', $(".pagination")).init(10);
	};

	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList)
				.each(
						function(i) {
							if (this.update_time == null)
								this.update_time = '-';

							if (this.status == 1)
								this.status = '<a href="#"><i class="fa fa-check-circle-o text-yellow"></i></a>';
							else
								this.status = '<a href="#"><i class="fa fa-circle-o text-yellow"></i></a>';

							var trList = '<tr><td style="font-weight:bold;">'
									+ (++index) + '</td><td>' + this.status
									+ '</td><td>' + this.number + '</td><td>'
									+ this.parent_num + '</td><td>'
									+ this.conf_key + '</td><td>' + this.value
									+ '</td><td>' + this.remark + '</td><td>'
									+ this.create_time + '</td><td>'
									+ this.update_time + '</td></tr>';

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
	sysConf.init();
})();