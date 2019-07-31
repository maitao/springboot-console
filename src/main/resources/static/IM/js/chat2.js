var chat2 = (function() {

	var thiz = $(this);
	//发送者
	var fromAccountName = null;
	var fromAccountImage = null;
	// 接收者
	var toAccount = null;
	var toAccountName = null;
	var toAccountImage = null;

	var viewHistoryTxt = '<div class="direct-chat-msg right"><div class="direct-chat-info clearfix" style="text-align: center;"><span class="direct-chat-timestamp">查看更多消息</span></div></div>';
	
	var htmlSend = function(date, content) {
		var datetime = new Date(date);
		if (datetime.toDateString() === new Date().toDateString()) {
			datetime = datetime.format("HH:mm:ss");
		} else {
			datetime = datetime.format("yyyy-MM-dd HH:mm:ss");
		}
		return '<div class="direct-chat-msg right"><div class="direct-chat-info clearfix"><span class="direct-chat-name pull-right">'
				+ fromAccountName
				+ '</span> <span class="direct-chat-timestamp pull-left">'
				+ datetime
				+ '</span></div><img class="direct-chat-img" src="'
				+ fromAccountImage
				+ '" alt="message user image"><div class="direct-chat-text">'
				+ content + '</div></div>';
	};

	var htmlReceived = function(date, content) {
		var datetime = new Date(date);
		if (datetime.toDateString() === new Date().toDateString()) {
			datetime = datetime.format("HH:mm:ss");
		} else {
			datetime = datetime.format("yyyy-MM-dd HH:mm:ss");
		}
		return '<div class="direct-chat-msg"><div class="direct-chat-info clearfix"><span class="direct-chat-name pull-left">'
				+ toAccountName
				+ '</span><span class="direct-chat-timestamp pull-right">'
				+ datetime
				+ '</span></div><img class="direct-chat-img" src="'
				+ toAccountImage
				+ '" alt="message user image"><div class="direct-chat-text">'
				+ content + '</div></div>';
	}

	var handlerMsg = function(obj){
		if (obj.fromAccount == toAccount) {
			console.log(obj.datetime);
			receivedMsg(obj.datetime, obj.content);
			//已收到信息应答
			ws.send(JSON.stringify({
				ctype: 2,//收到消息，反馈
				datetime : obj.datetime
			}));
		} else {
			// 显示消息数
			var thiz = $('.contacts-list li[data-value="' + obj.fromAccount + '"]');
			var s = thiz.find(".unreach-num-span");
			if (s.length > 0) {
				s.html(parseInt(s.html()) + 1);
			} else {
				thiz.find('small').before('<span class="label label-primary unreach-num-span">1</span>');
			}
			var m = $('.msg-num-span');
			m.html(parseInt(m.html()) + 1);
		}
	};
	
	/*var connect = function() {
		$('.direct-chat-messages').empty().append(viewHistoryTxt);
		ws.onmessage = function(event) {
			for ( var i in event.originalTarget) {
				console.log("event[" + i + "]:" + event.originalTarget[i]);
			}
			var obj = JSON.parse(event.data);
			if (obj.fromAccount == toAccount) {
				console.log(obj.datetime);
				receivedMsg(obj.datetime, obj.content);
				//已收到信息应答
				ws.send(JSON.stringify({
					ctype: 2,//收到消息，反馈
					datetime : obj.datetime
				}));
			} else {
				// 显示消息数
				var thiz = $('.contacts-list li[data-value="' + obj.fromAccount + '"]');
				var s = thiz.find(".unreach-num-span");
				if (s.length > 0) {
					s.html(parseInt(s.html()) + 1);
				} else {
					thiz.find('small').before('<span class="label label-primary unreach-num-span">1</span>');
				}
				var m = $('.msg-num-span');
				m.html(parseInt(m.html()) + 1);
			}
			heartCheck.reset();
		};

		$('#sendMsgButton').click(function() {
			sendMessage();
		});

		$("input[name='message']").bind('keypress', function(event) {
			if (event.keyCode == "13") {
				sendMessage();
				return false;
			}
		});
	}*/

	var receivedMsg = function(createTime, content) {
		$('.direct-chat-messages').append(htmlReceived(createTime, content)).scrollTop($(".direct-chat-messages")[0].scrollHeight);
	}

	/* 发送消息 */
	function sendMessage() {
		if (ws != null) {
			var content = $("input[name='message']").val();
			if ($.trim(content) == '')
				return false;
			ws.send(JSON.stringify({
				ctype: 1,//发送消息
				msgType: 1,//单聊
				account : toAccount.toString(),
				content : content
			}));
			$('.direct-chat-messages').append(htmlSend(new Date(), content)).scrollTop($(".direct-chat-messages")[0].scrollHeight);
			$("input[name='message']").val('');
		} else {
			alert('connection not established, please connect.');
		}
	}

	var init = function() {
		// 连接
		/*connect();*/
		fromAccountName = $('.user-panel').find('p').html();
		fromAccountImage = $('.user-panel').find('img').attr("src");
		$('.direct-chat-messages').empty().append(viewHistoryTxt);
		$('#sendMsgButton').click(function() {
			sendMessage();
		});

		$("input[name='message']").bind('keypress', function(event) {
			if (event.keyCode == "13") {
				sendMessage();
				return false;
			}
		});
		// 显示列表
		contactList();

		binding();
	};

	var contactList = function() {
		$.ajaxProxy("im/contactList",
						"POST",
						true,
						"json",
						{},
						function(data) {
							$('.msg-num-span').html(data.unReachNum);
							var str = '';
							$(data.contactList)
									.each(
											function(i) {
												var numStr = '';
												if (this.unReachNum != null && this.unReachNum != 0)
													numStr = '<span class="label label-primary unreach-num-span">'
															+ this.unReachNum
															+ '</span>';
												str += '<li data-value="'
														+ this.toAccount
														+ '"><img class="contacts-list-img" src="'
														+ this.image
														+ '" alt="'
														+ this.name
														+ '"><div class="contacts-list-info"><span class="contacts-list-name"><font>'
														+ this.name
														+ '</font>&nbsp;'
														+ numStr
														+ '<small class="contacts-list-date pull-right">'
														+ this.createTime
														+ '</small></span><span class="contacts-list-msg">'
														+ this.remark
														+ '</span></div></li>';
											});
							$('.contacts-list').empty().append(str);
							bindingLi();
						}, function() {
						});
	};

	var bindingLi = function() {
		$('.contacts-list li')
				.click(
						function() {
							var thiz = $(this);
							toAccount = $(this).data('value');
							toAccountName = $(this).find('font').html();
							toAccountImage = $(this).find('img').attr("src");
							$('.direct-chat .box-title').html(toAccountName);
							$.ajax({type : "POST",
										url : "im/imChatReady",
										async : false,
										data : {
											account : toAccount
										},
										dataType : "json",
										success : function(data) {
											var str = viewHistoryTxt;
											if (data.history != null) {
												$(data.history).each(
														function(i) {
															if(this.fromAccount==toAccount)
																str += htmlReceived(this.createTime, this.content);
															else
																str += htmlSend(this.createTime, this.content);
														});
											}
											$('.direct-chat-messages').empty().append(str);
											$('.direct-chat').removeClass('direct-chat-contacts-open');
											var zz = thiz.find('.unreach-num-span');
											if (zz.length > 0){
												var m = $('.msg-num-span');
												m.html((parseInt(m.html()) - parseInt(zz.html())).toString());
												zz.remove();
											}
										},
										error : function(e) {
											console.log(e);
										}
									});
						});
	};

	var binding = function() {
		$('#history_btn').on('click', function() {
			$('.direct-chat').removeClass('direct-chat-contacts-open');
			$('.direct-chat').removeClass('direct-chat-search-contacts-open');
			$.ajax({
				type : "POST",
				url : "im/history",
				async : false,
				data : {
					toAccount : toAccount
				},
				dataType : "json",
				beforeSend : function() {
					$('.direct-chat-messages').empty();
				},
				success : function(data) {
				},
				error : function(e) {
					console.log(e);
				}
			});
		});

		$('#search_btn')
				.on('click', function() {
							$('.direct-chat').toggleClass('direct-chat-search-contacts-open');
							$('.direct-chat').removeClass('direct-chat-contacts-open');
							$.ajaxProxy("im/search",
											"POST",
											true,
											"json",
											{},
											function(data) {
												$(data.mList)
														.each(function(i) {
																	$('.select2').append(
																					'<option value="'
																							+ this.account
																							+ '">'
																							+ this.account
																							+ '('
																							+ this.name
																							+ ')</option>');
																});
												$(".select2").select2();
												$('#add_contact').on('click',
																function() {
																	var thiz = $(this);
																	var account = $(".select2").val();
																	$.ajaxProxy("im/addContact",
																					"POST",
																					true,
																					"json",
																					{
																						account : account
																					},
																					function(data) {
																						$('.msg_contact_text').html(data.msg);
																						thiz.removeClass('text-white').addClass('text-yellow');
																						contactList();
																					},
																					function() {
																					});
																});
											}, function() {
											});
						});
	};

	return {
		init : init,
		handlerMsg : handlerMsg
	};
})();

(function() {
	console.log('chat...');
	chat2.init();
})();