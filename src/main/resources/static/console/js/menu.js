var menu = (function() {
	var thiz = $(this);
	var init = function() {
		new commonPage(menu, 'membership', $("#tsList"), 'page_select',
				'pNum', '0-0', $(".pagination")).init(10);
		$.ajax({
			type : "POST",
			url : "menu/json",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				// console.log('menu json string');
				// console.log(data);
				initTree(data.zNodes);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};

	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList)
				.each(
						function(i) {
							if (this.update_time == null)
								this.update_time = '';
							if (this.status == 1)
								this.status = '<a href="#"><i class="fa fa-star text-yellow"></i></a>';
							else
								this.status = '<a href="#"><i class="fa fa-star-o text-yellow"></i></a>';

							var trList = '<tr><td>'
									+ (++index)
									+ '</td><td>'
									+ this.status
									+ '</td><td>'
									+ this.number
									+ '</td><td>'
									+ this.parent_num
									+ '</td><td>'
									+ this.name
									+ '</td><td>'
									+ this.short_url
									+ '</td><td>'
									+ this.left_icon
									+ '</td><td>'
									+ this.right_icon
									+ '</td><td>'
									+ this.create_time
									+ '</td><td><span class="createtime">'
									+ this.update_time
									+ '</span></td><td><a href="#" title="删除"><i class="fa fa-trash text-aqua"></i></a>&nbsp;&nbsp;&nbsp;<a href="#" title="编辑"><i class="fa fa-pencil-square-o text-yellow"></i></a>&nbsp;&nbsp;&nbsp;<a href="#" title="详细"><i class="fa fa-mail-forward"></i></a></td></tr>';
							str += trList;
						});
		return str;
	};

	var initTree = function(zNodes) {

		var setting = {};
		/*
		 * var zNodes = [ { name : "父节点1 - 展开", open : true, children : [ { name :
		 * "父节点11 - 折叠", children : [ { name : "叶子节点111" }, { name : "叶子节点112" }, {
		 * name : "叶子节点113" }, { name : "叶子节点114" } ] }, { name : "父节点12 - 折叠",
		 * children : [ { name : "叶子节点121" }, { name : "叶子节点122" }, { name :
		 * "叶子节点123" }, { name : "叶子节点124" } ] }, { name : "父节点13 - 没有子节点",
		 * isParent : true } ] }, { name : "父节点2 - 折叠", children : [ { name :
		 * "父节点21 - 展开", open : true, children : [ { name : "叶子节点211" }, { name :
		 * "叶子节点212" }, { name : "叶子节点213" }, { name : "叶子节点214" } ] }, { name :
		 * "父节点22 - 折叠", children : [ { name : "叶子节点221" }, { name : "叶子节点222" }, {
		 * name : "叶子节点223" }, { name : "叶子节点224" } ] }, { name : "父节点23 - 折叠",
		 * children : [ { name : "叶子节点231" }, { name : "叶子节点232" }, { name :
		 * "叶子节点233" }, { name : "叶子节点234" } ] } ] }, { name : "父节点3 - 没有子节点",
		 * isParent : true } ];
		 */
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	};

	return {
		init : init,
		dataList : dataList
	};
})();

(function() {
	menu.init();
})();
