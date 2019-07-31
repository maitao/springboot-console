var account = (function() {
	var thiz = $(this);
	var init = function() {
		console.log('account init');
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
							if (null == this.last_login_time)
								this.last_login_time = '-';
							if (null == this.phone)
								this.phone = '-';
							if (null == this.email)
								this.email = '-';
							if (null == this.role_name)
								this.role_name = '-';
							var trList = '<tr class='
									+ tr_c
									+ '><td><input type="checkbox"></td><td style="font-weight:bold;">'
									+ (++index)
									+ '</td><td>'
									+ this.account
									+ '</td><td>'
									+ this.account
									+ '</td><td>'
									+ this.phone
									+ '</td><td>'
									+ this.email
									+ '</td><td>'
									+ this.login_count
									+ '</td><td>'
									+ this.last_login_time
									+ '</td><td>'
									+ 1
									+ '</td><td>'
									+ this.create_time
									+ '</td><td>'
									+ this.role_name
									+ '</td><td class="status_open status_change" data-value="'
									+ this.id
									+ '"></td><td><span data-value="'
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
	account.init();
})();