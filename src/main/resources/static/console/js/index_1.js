var _menuDta = {};
var basePath = 'http://' + window.location.host + '/web/';
var index_1 = (function() {
	var bindingA = function(a) {
		a.click(function() {
			var thiz = $(this);
			var t = thiz.data('value');
			if (null != t && '' != t) {
				if (t.indexOf('http') > -1) {
					$(".content-wrapper").empty().html('<iframe frameborder=0 width=100% height=900 marginheight=0 marginwidth=0 scrolling=no src=https://www.baidu.com></iframe>');
				} else {
					$(".content-wrapper").empty().load(t, function(response, status, xhr) {
						// 加载公共页，需要加载不同 的js
						var ss = t.split('/');
						if (ss[0] == 'tableList') {
							$.getScript(basePath + 'mtadmin/js/tableList/' + ss[1] + '.js',
									function() {
									});
						}
					});
				}
			}
		});
	};

	var buildMenu = function(data) {
		$(data)
				.each(
						function(i) {
							if (this.parent_num == '0-0') {
								var note = '<li class="treeview" data-value="'
										+ this.number
										+ '"><a style="cursor: pointer;" data-value="'
										+ this.short_url
										+ '"> <i class="'
										+ this.left_icon
										+ '"></i> <span>'
										+ this.name
										+ '</span> <span class="pull-right-container"> <i class="'
										+ this.right_icon
										+ '"></i></span></a></li>';
								$('.header_main_labels').before(note);
							}
						});
		bindingA($('.treeview').find('a'));
	};

	var buildCMenu = function(thiz) {
		var value = thiz.parent().data('value');
		if (value != '') {
			var ul = $('<ul class="treeview-menu"></ul>');
			var index = 0;
			$(_menuDta)
					.each(
							function(i) {
								if (this.parent_num == value) {
									var note = '<li data-value="'
											+ this.number
											+ '"><a style="cursor: pointer;" data-value="'
											+ this.short_url
											+ '"> <i class="'
											+ this.left_icon
											+ '"></i> <span>'
											+ this.name
											+ '</span> <span class="pull-right-container"> <i class="'
											+ this.right_icon
											+ '"></i></span></a></li>';
									ul.append(note);
									index = i;
								}
							});
			if (index != 0)
				thiz.after(ul);
			/* 需要加载完才能绑定，对新增的节点绑定避免绑定多次 */
			bindingA($(ul).find('a'));
			$(ul).find('a').hover(function() {
				buildCMenu($(this));
			}, function() {
				$(this).parent().data('value', '');
			});
		}
	};
	var membership_init = function(am, aminfo) {
		if(aminfo.image!= null){
			$('.membership-pic').attr("src", aminfo.image); 
		}
		$('.membership-name').html(am.name); 
		$('.membership-time').html(aminfo.time);
	};
	var thiz = $(this);
	var init = function() {
		$.ajax({
			type : "POST",
			url : "/console/init",
			async : false,
			data : {},
			dataType : "json",
			beforeSend : function() {
				console.log('--构造左侧菜单--');
			},
			success : function(data) {
				membership_init(data.am, data.aminfo);
				_menuDta = data.menu;
				buildMenu(data.menu);
			},
			error : function(e) {
				console.log(e);
			}
		});
		// 防止刷新，刷新停留当前页
		var currentPage = $('.currentPage').data('value');
		if ('' != currentPage) {
			$(".content-wrapper").empty().load(
					"/"+currentPage,
					function(response, status, xhr) {
						// 加载公共页，需要加载不同 的js
						var ss = currentPage.split('/');
						if (ss[0] == 'tableList') {
							$.getScript(basePath + 'mtadmin/js/tableList/'
											+ ss[1] + '.js', function() {
									});
						}

					});
		}
	};

	binding = function() {
		//非菜单加载
		$('.loadA').click(function(){
			var thiz = $(this);
			var zz = thiz.data('value');
			console.log(zz);
			$(".content-wrapper").empty().load('console/'+zz,
					function(response, status, xhr) {
					});
		});
		$('.treeview').find('a').hover(function() {
			buildCMenu($(this));
		}, function() {
			$(this).parent().data('value', '');
		});
		$('.cd-nav-trigger').on(
				'click',
				function() {
					$(this).toggleClass('menu-is-open');
					if ($(this).hasClass('menu-is-open')) {
						$(".cd-main-nav").show().empty().load('im/chat',
								function(response, status, xhr) {
								});
					} else {
						$('.cd-main-nav').hide();
					}
				});
		//聊天加载
		$('.chat-trigger').on('click', function() {
			var thiz = $(this);
			$(".chat-main").empty().load('im/chat2',
					function(response, status, xhr) {
						// 加载完成后执行
						thiz.hide();
						$('#chat-hide').on('click', function() {
							$('.chat-main').hide();
							$('.chat-trigger').show();
						});
					}).show();
		});
	};

	return {
		init : init,
		binding : binding
	};

})();

(function() {
	console.log('index_1 init...');
	index_1.init();
	index_1.binding();
})();