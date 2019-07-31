var membership = (function() {
	var thiz = $(this);
	var init = function() {
		new commonPage(membership, 'membership', $("#tsList"), 'page_select',
				'pNum', '', $(".pagination")).init(10);
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
							if(this.online==1)
								this.online = '是';
							else
								this.online = '否';
							if(this.status==1)
								this.status = '是';
							else
								this.status = '否';
							var trList = '<tr class='
									+ tr_c
									+ '><td><input type="checkbox"></td><td style="font-weight:bold;">'
									+ (++index)
									+ '</td><td>'
									+ this.account
									+ '</td><td>'
									+ this.name
									+ '</td><td>'
									+ this.roleNum
									+ '</td><td>'
									+ this.online
									+ '</td><td>'
									+ this.regIp
									+ '</td><td>'
									+ this.lastLoginIp
									+ '</td><td>'
									+ this.lastLoginTime
									+ '</td><td>'
									+ this.loginCount
									+ '</td><td>'
									+ this.lockTime
									+ '</td><td>'
									+ this.status
									+ '</td><td>'
									+ this.createTime
									+ '</td><td class="status_open status_change" data-value="'
									+ this.updateTime
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
	membership.init();
})();