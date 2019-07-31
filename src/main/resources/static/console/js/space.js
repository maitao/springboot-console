var space = (function() {
	var thiz = $(this);
	var articleChange = false;
	var interv;
	var init = function() {
		$('#tsHead').empty().html('<tr><th style="width:15px;"><i class="fa fa-star text-yellow"></i></th><th>主题</th><th class="hidden-xs hidden-sm">摘要</th><th>查看数</th><th>创建时间</th><th>修改时间</th><th>分类</th></tr>');
		new commonPage(space, 'article', $("#tsList"), 'page_select', 'pNum', '', $(".pagination")).init(10);
		binding();
		bindingTable();
		$.ajax({
			type : "POST",
			url : "space/category",
			async : false,
			data : {},
			dataType : "json",
			success : function(data) {
				console.log(data.zNodes);
				initTree(data.zNodes);
			},
			error : function(e) {
				console.log(e);
			}
		});
	};
	
	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList).each(function(i) {
							var createtime = new Date(this.create_time);
							if (createtime.toDateString() === new Date()
									.toDateString()) {
								createtime = createtime.format("HH:mm:ss");
							} else {
								createtime = createtime.format("yyyy-MM-dd");
							}
							var updatetime = '';
							if(null != this.update_time){
								var updatetime = new Date(this.update_time);
								if (updatetime.toDateString() === new Date().toDateString()) {
									updatetime = updatetime.format("HH:mm:ss");
								} else {
									updatetime = updatetime.format("yyyy-MM-dd");
								}
							}
							var level = 'fa-star-o';
							if(this.level == 1){
								level = 'fa-star';
							}
							if(null == this.name){
								this.name = '';
							}
							if(null == this.summary){
								this.summary = '';
							}
							str += '<tr><td style="font-weight:bold;"><i class="star_change fa '
									+ level
									+ ' text-yellow" style="cursor:pointer;" data-value='
									+ this.id
									+ '></i></td><td><a style="cursor:pointer;" class="article_view" data-value='
									+ this.id
									+ '>'
									+ this.title
									+ '</a></td><td class="hidden-xs hidden-sm">'+this.summary+'</td><td>'
									+ this.view_count
									+ '</td><td>'
									+ createtime
									+ '</td><td>'
									+ updatetime
									+ '</td><td>' + this.name + '</td></tr>';
						});
		return str;
	};

	var binding = function() {
		$(".article_add").click(function() {
			var obj = zTree.getSelectedNodes()[0];
			if(!obj || obj.pId == 0 || obj.pId == 1){
				alert('请选择记事分类');
				return false;
			}
			if($('.newArticle-tr').length > 0)
				return false;
			$.ajaxProxy("common/formUuid", "POST", true, "json", {
			}, function(data) {
				$('#tsList').prepend('<tr class="newArticle-tr"><td style="font-weight:bold;"><i class="star_change fa fa-star-o text-yellow" style="cursor:pointer;" data-value=""></i></td><td><a style="cursor:pointer;" class="article_view" data-value="">(无主题)</a></td><td class="hidden-xs hidden-sm"></td><td></td><td></td><td></td><td></td></tr>');
				$("input[name='id']").val('');
				$("input[name='categoryId']").val(obj.id);
				$("input[name='uuid']").val(data.uuid);
				$("input[name='title']").val('');
				var editor = UE.getEditor('editor');
				editor.setContent('');
				$("input[name='title']").change(function(){
					articleChange = true;
					$('.newArticle-tr').find('.article_view').html($(this).val());
				});
				editor.addListener("contentChange", function () {
					articleChange = true;
					var contentTxt = this.getContentTxt();
					if(contentTxt.length < 30){
						$('.newArticle-tr').find('.hidden-sm').html(contentTxt);
					}else{
						$('.newArticle-tr').find('.hidden-sm').html(contentTxt.substring(0,30)+'...');;
					}
					$("input[name='summary']").val($('.newArticle-tr').find('.hidden-sm').html());
	            });
				console.log('开启定时保存任务...');
				//定时保存任务
				interv = setInterval(function(){ // 设置定时器
					return function() {
						if($('.newArticle-tr').length <= 0){
							clearInterval(interv); // 清除定时器
							console.log('结束定时保存任务...');
							return;
						}
						if(articleChange){
							$('#recordForm').ajaxSubmit({
								dataType : 'json',
								beforeSubmit : function() {
								},
								success : function(xhr) {
									articleChange = false;
									var id = xhr.id;
									$("input[name='id']").val(id);
									$('.newArticle-tr').find('.article_view').data('value', id);
								}
							});
						}
					}
				}(),3000); // 3s保存
			}, function() {
			});
		});
	};

	var bindingTable = function() {
		$('.article_view').click(
				function() {
					clearInterval(interv); // 清除定时器
					console.log('结束定时保存任务...');
					var id = $(this).data('value');
					$.ajaxProxy("record/articleView", "POST", true, "json", {
						'id' : id,
						'type' : 1
					}, function(data) {
						$("input[name='id']").val(data.data.id);
						$("input[name='title']").val(data.data.title);
						UE.getEditor('editor').setContent(
								null == data.data.content ? ''
										: data.data.content);
					}, function() {
					});
				});
		// 加星
		$(".star_change").click(function() {
			var thiz = $(this);
			var id = $(this).data("value");
			var level = 1;
			if ($(this).hasClass('fa-star'))
				level = 0;

			$.ajaxProxy("record/addStar", "POST", true, "json", {
				id : id,
				level : level
			}, function(data) {
				if (0 == data.level) {
					thiz.removeClass('fa-star');
					thiz.addClass('fa-star-o');
				} else {
					thiz.removeClass('fa-star-o');
					thiz.addClass('fa-star');
				}
			}, function() {
			});
		});
	}
	
	var zTree, rMenu;
	var initTree = function(zNodes) {
		var setting = {
				data: {
					simpleData: {
						enable: true
					}
				},
			view : {
				selectedMulti : false,
				dblClickExpand : true
			},
			edit : {
				enable : true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			check : {
				enable : false
			},
			callback : {
				beforeDrag: beforeDrag,
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				beforeClick : beforeClick,
				onRemove: onRemove,
				onRename: onRename,
				onClick : onClick,
				onRightClick : OnRightClick
			}
		};
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		rMenu = $("#rMenu");
		$('#m_add').click(function() {
			addTreeNode();
		});
		$('#m_del').click(function() {
			removeTreeNode();
		});
		$('#m_rename').click(function() {
			renameTreeNode();
		});
	};

	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeRemove(treeId, treeNode) {
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}
	function beforeRename(treeId, treeNode, newName) {
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	function beforeClick(treeId, treeNode, clickFlag) {
		return (treeNode.click != false);
	}
	function onRemove(e, treeId, treeNode) {
	}
	function onRename(e, treeId, treeNode) {
		$.ajaxProxy("space/renameNode", "POST", true, "json", {
			'id' : treeNode.id,
			'name' : treeNode.name
		}, function(data) {
		}, function() {
		});
	}
	function onClick(event, treeId, treeNode, clickFlag) {
		new commonPage(space, 'article', $("#tsList"), 'page_select', 'pNum',
				treeNode.id, $(".pagination")).init(10);
		bindingTable();
	}
	// =================================================================right
	// click
	function OnRightClick(event, treeId, treeNode) {
		var id = treeNode.id;
		var pId = treeNode.pId;
		if(pId == 0 || pId == 1){
			if(id != 5 && id != 6 && id != 7)
				return false;
		}
		if (!treeNode && event.target.tagName.toLowerCase() != "button"
				&& $(event.target).parents("a").length == 0) {
			zTree.cancelSelectedNode();
			console.log('X:' + event.clientX);
			console.log('Y:' + event.clientY);
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			zTree.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY);
		}
	}

	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type == "root") {
			$("#m_del").hide();
			$("#m_check").hide();
			$("#m_unCheck").hide();
		} else {
			$("#m_del").show();
			$("#m_check").show();
			$("#m_unCheck").show();
		}
		rMenu.css({
			"top" : y + "px",
			"left" : x + "px",
			"visibility" : "visible"
		});
		$("body").bind("mousedown", onBodyMouseDown);
	}
	function hideRMenu() {
		if (rMenu)
			rMenu.css({
				"visibility" : "hidden"
			});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	function onBodyMouseDown(event) {
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
			rMenu.css({
				"visibility" : "hidden"
			});
		}
	}
	function addTreeNode() {
		hideRMenu();
		var obj = zTree.getSelectedNodes()[0];
		if (obj) {
			var newNodeName = '新建分类';
			$.ajaxProxy("space/addNode", "POST", true, "json", {
				'pId' : obj.id,
				'name' : newNodeName
			}, function(data) {
				console.log('新增的节点id：' + data.id);
				zTree.addNodes(obj, {
					id : data.id,
					pId : obj.id,
					name : newNodeName
				});
			}, function() {
			});
		} else {
			zTree.addNodes(null, newNode);
		}
	}
	function removeTreeNode() {
		hideRMenu();
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length > 0) {
			if (nodes[0].children && nodes[0].children.length > 0) {
				var msg = "此分类的记事将全部移动至‘已删除’，是否继续？";
				if (confirm(msg) == true) {
					$.ajaxProxy("space/removeNode", "POST", true, "json", {
						'pId' : nodes[0].pId,
						'id' : nodes[0].id
					}, function(data) {
						zTree.removeNode(nodes[0]);
					}, function() {
					});
				}
			} else {
				$.ajaxProxy("space/removeNode", "POST", true, "json", {
					'pId' : nodes[0].pId,
					'id' : nodes[0].id
				}, function(data) {
					zTree.removeNode(nodes[0]);
				}, function() {
				});
			}
		}
	}
	function renameTreeNode() {
		hideRMenu();
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alert("请先选择一个节点");
			return;
		}
		zTree.editName(treeNode);
	};
	function checkTreeNode(checked) {
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length > 0) {
			zTree.checkNode(nodes[0], checked, true);
		}
		hideRMenu();
	}
	function resetTree() {
		hideRMenu();
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	}
	return {
		init : init,
		dataList : dataList
	};
})();

(function() {
	console.log('space init...');
	space.init();
})();