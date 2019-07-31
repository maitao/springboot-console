var log = (function() {
	var thiz = $(this);
	var init = function() {
		new commonPage(log, 'log', $("#tsList"), 'page_select', 'pNum', '',
				$(".pagination")).init(10);
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
							if (null == this.class_path)
								this.class_path = '-';
							if (null == this.method_name)
								this.method_name = '-';
							if (null == this.url_name)
								this.url_name = '-';
							if (null == this.error_msg)
								this.error_msg = '-';
							if (1 == this.level)
								color = 'background:red';
							if (this.use_time > 800)
								utc = 'color:red';
							else
								utc = '';

							var t_level = '';
							if (this.level == 0) {
								t_level = '<span class="badge bg-yellow">info</span>';
							} else {
								t_level = '<span class="badge bg-red">error</span>';
							}

							var trList = '<tr class='
									+ tr_c
									+ '><td><input type="checkbox"></td><td>'
									+ (++index)
									+ '</td><td>'
									+ t_level
									+ '</td><td>'
									+ this.number
									+ '</td><td><span class="createtime">'
									+ this.method_remark
									+ '</span></td><td><span>'
									+ this.class_path
									+ '.<font style="color:red;">'
									+ this.method_name
									+ '</font></span></td><td style="font-size:14px;font-weight:bold;'
									+ utc
									+ '">'
									+ this.use_time
									+ '</td><td><span>'
									+ this.account
									+ '</span></td><td><span class="createtime">'
									+ this.create_time
									+ '</span></td><td><a href="#" title="删除"><i class="fa fa-trash text-aqua"></i></a>&nbsp;&nbsp;&nbsp;<a href="#" title="编辑"><i class="fa fa-pencil-square-o text-yellow"></i></a>&nbsp;&nbsp;&nbsp;<a href="#" title="详细"><i class="fa fa-mail-forward"></i></a></td></tr>';
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
	log.init();
})();