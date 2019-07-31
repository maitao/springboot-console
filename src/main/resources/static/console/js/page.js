/**
 * 分页 maitao 2015
 * 
 * 支持多个表格 20161112
 */
/*
 * 参数依次为： 1、引用对象 2、类型 3、显示表单的id：tsList 4、显示页数 6、过滤条件 7、分页id tPage
 */
function commonPage(obj, key, tList, pNumSelect, pNumInput, condition, tPage) {
	console.log('common page');
	var pageCount = 10;
	var h = window.screen.height;
	if (1200 > h && h > 800)
		pageCount = 25;
	var _thiz = this;
	var _count = 0;

	var init = function(count) {
		pageCount = count;
		f_dataList(1, pageCount);
	};

	/*
	 * 参数依次为：引用对象、类型、显示表单的id：tsList、当前页、显示条数、过滤条件 --改造：使用回调函数callback
	 */
	var f_dataList = function(pageNo, pageCount) {
		// 绑定一页显示多少数据下拉列表
		f_pageBinding($("select[name='" + pNumSelect + "']"));
		$.ajax({
			type : "POST",
			url : "/dataList/" + key,
			async : false,
			data : {
				key : key,
				pageNo : pageNo,
				pageCount : pageCount,
				condition : condition
			},
			dataType : "json",
			beforeSend : function() {
				// 由于是jquery load加载的没加载完全不能使用
				// $(".overlay").show();
			},
			success : function(data) {
				tList.empty().html(
						obj.dataList(data, (data.data.currentPageNo - 1)
								* pageCount));
				// 分页栏
				tPage.empty().html(
						f_pnav(data.data.currentPageNo, Math
								.ceil(data.data.totalSize / pageCount),
								data.data.totalSize));

				f_navBinding(obj, key, tList, condition, tPage);
				$(".overlay").hide();
			},
			error : function(e) {
				console.log(e);
			}
		});
	};

	var f_pageBinding = function(pNumSelect) {
		pNumSelect.change(function() {
			pageCount = $(this).val();
			f_dataList(1, pageCount);
		});
	};

	var f_pnav = function(cp, totalPage, totalSize) {
		// 显示分页条
		var length = cp.toString().length;
		var start, end;// 主要的参数
		if (length == 1) {
			start = 1;
			if (totalPage < 10)
				end = totalPage;
			else
				end = 10;
		} else if (length == 2) {
			var unit = cp.toString().substring(0, 1);
			var decade = cp.toString().substring(1, 2);
			if ("0" == decade) {
				end = cp;
				start = cp - 9;
			} else {
				start = parseInt(unit) * 10 + 1;
				end = (parseInt(unit) + 1) * 10;
				if (totalPage < end)
					end = totalPage;
			}
		} else if (length == 3) {
			var unit_decade = cp.toString().substring(0, 2);
			var hundreds_place = cp.toString().substring(2, 3);
			if ("0" == hundreds_place) {
				end = cp;
				start = cp - 9;
			} else {
				start = parseInt(unit_decade) * 10 + 1;
				end = (parseInt(unit_decade) + 1) * 10;
				if (totalPage < end)
					end = totalPage;
			}
		}
		if (cp <= 10)
			var strb = '';
		else {
			var strb = '<li><span aria-hidden="true" data-value="1" class="fa fa-step-backward"></span></li><li><span aria-label="Previous" style="cursor:pointer;" data-value="'
					+ (start - 1)
					+ '"><span aria-hidden="true" class="fa fa-caret-left"></span></li>';
		}
		for (var int = start; int <= end; int++) {
			if (int == cp) {
				strb += '<li><span style="background: #CBCBC6;" data-value="'
						+ int + '">' + int + '</span></li>';
			} else {
				strb += '<li><span data-value="' + int
						+ '" style="cursor:pointer;">' + int + '</span></li>';
			}
		}
		if (end + 1 <= totalPage)
			strb += '<li><span aria-label="Next" style="cursor:pointer;" data-value="'
					+ (++end)
					+ '"><span aria-hidden="true" class="fa fa-caret-right"></sapn></li><li><span data-value="'
					+ totalPage
					+ '" style="cursor:pointer;" class="fa fa-step-forward"></sapn></li>';
		strb += '<li><input id="'
				+ pNumInput
				+ '" value="'
				+ cp
				+ '"class="form-control input-sm" placeholder="页" maxlength="5" style="float: left;padding: 1px;width:33px;height:30px;border-right-width: 2px;"></li><li><span aria-hidden="true" data-value="gotoPage" class="fa fa-undo"></span></sapn></li><li><span>'
				+ totalSize + '条记录 ' + totalPage + '页</span></li>';
		return strb;
	}

	var f_navBinding = function(obj, key, tList, condition, tPage) {
		// 分页
		tPage.find('span').click(function() {
			if ('gotoPage' == $(this).data("value")) {
				var p = $('#' + pNumInput).val();
				if (null == p || '' == p)
					return false;
				f_dataList(p, pageCount);
				return false;
			}
			f_dataList($(this).data("value"), pageCount);
		});
	};

	return {
		pageCount : pageCount,
		init : init,
		f_dataList : f_dataList
	};
}
