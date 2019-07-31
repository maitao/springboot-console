var chat = (function() {

	var thiz = $(this);

	var ws = null;
	var url = null;
	var transports = [];
	var basePath = "http://localhost:8080/web/";
	var wsPath = basePath.replace("http://", "ws://");

	// 发送者
	var fromAccountName = null;
	var fromAccountImage = null;
	// 接收者
	var toAccountName = null;
	var toAccountImage = null;

	var init = function() {
		// 打开页面即自动链接
		connect();
	};

	var connect = function() {
		// if (!url) {
		// alert('Select whether to use W3C WebSocket or SockJS');
		// return;
		// }
		console.log(wsPath);
		ws = new WebSocket(wsPath + 'ws'); /*
											 * (url.indexOf('sockjs') != -1) ?
											 * new SockJS(url, undefined,
											 * {protocols_whitelist:
											 * transports}) :
											 */
		ws.onopen = function() {
			setConnected(true);
			log('Info: connection opened.');
		};
		ws.onmessage = function(event) {
			for ( var i in event.originalTarget) {
				console.log("event[" + i + "]:" + event.originalTarget[i]);
			}
			var obj = JSON.parse(event.data);
			var account = $('.direct-chat .box-title').data('value');
			if (obj.fromAccount == account) {
				var datetime = new Date(obj.datetime);
				if (datetime.toDateString() === new Date().toDateString()) {
					datetime = datetime.format("HH:mm:ss");
				} else {
					datetime = datetime.format("yyyy-MM-dd HH:mm:ss");
				}
				receivedMsg(toAccountName, toAccountImage, obj.content,
						datetime);
			} else {
				// 显示消息数
				var s = $(
						'.un-reach-num li[data-value=' + obj.fromAccount + ']')
						.find(".pull-right");
				if (s.length > 0) {
					s.html(parseInt(s.html()) + 1);
				} else {
					$('.un-reach-num')
							.append(
									'<li data-value="'
											+ obj.fromAccount
											+ '"><a href="javascript:void(0);"><i class="fa fa-user"></i> <span>'
											+ obj.fromAccount
											+ '</span><span class="label label-primary pull-right">1</span></a></li>');
					// 重新绑定
					bindingLi();
				}
			}
		};

		ws.onclose = function(event) {
			setConnected(false);
			log('Info: connection closed.');
			log(event);
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
	}

	var setConnected = function(connected) {
		fromAccountName = $('#senderInfo').find('span:eq(0)').data('value');
		fromAccountImage = $('#senderInfo').find('span:eq(1)').data('value');
		console.log($('#senderInfo'));
		console.log($('#senderInfo').find('span:eq(0)'));
		console.log(fromAccountImage);
		// 绑定发送人
		bindingLi();
	}

	var bindingLi = function() {
		$('.contact-ul li')
				.click(
						function() {
							var thiz = $(this);
							// 绘聊天框
							var account = $(this).data('value');
							var name = $(this).find('span').html();
							$('.direct-chat .box-title').html(name);
							$('.direct-chat .box-title').data('value', account);
							if(thiz.find(".pull-right").html() == 0) 
								return false;
							// 获取用户信息图片，名称
							$
									.ajax({
										type : "POST",
										url : "im/imChatReady",
										async : false,
										data : {
											account : account
										},
										dataType : "json",
										success : function(data) {

											// 接收者
											toAccountName = data.data.imAInfo.name;
											toAccountImage = data.data.imAInfo.image;
											// 绘未读信息
											/*
											 * $(data.data.unReachMsg) .each(
											 * function(i) { console
											 * .log(this.content); var datetime =
											 * new Date( this.createTime);
											 * 
											 * if (datetime .toDateString() ===
											 * new Date() .toDateString()) {
											 * datetime = datetime
											 * .format("HH:mm:ss"); } else {
											 * datetime = datetime
											 * .format("yyyy-MM-dd HH:mm:ss"); }
											 * receivedMsg( toAccountName,
											 * toAccountImage, this.content,
											 * datetime);
											 * 
											 * });
											 */
											// 标识信息已读
											$('.direct-chat-messages')
													.empty()
													.append(
															'<div class="direct-chat-msg right"><div class="direct-chat-info clearfix" style="text-align: center;"><span class="direct-chat-timestamp">查看更多消息</span></div></div>');
											thiz.find(".pull-right").html(0);
										},
										error : function(e) {
											console.log(e);
										}
									});
						});
	};

	var updateUrl = function(urlPath) {
		if (urlPath.indexOf('sockjs') != -1) {
			url = urlPath;
			document.getElementById('sockJsTransportSelect').style.visibility = 'visible';
		} else {
			if (window.location.protocol == 'http:') {
				url = 'ws://' + window.location.host + urlPath;
			} else {
				url = 'wss://' + window.location.host + urlPath;
			}
			document.getElementById('sockJsTransportSelect').style.visibility = 'hidden';
		}
	}

	var receivedMsg = function(toAccountName, toAccountImage, content,
			createTime) {
		var htmlReceived = '<div class="direct-chat-msg"><div class="direct-chat-info clearfix"><span class="direct-chat-name pull-left">'
				+ toAccountName
				+ '</span><span class="direct-chat-timestamp pull-right">'
				+ createTime
				+ '</span></div><img class="direct-chat-img" src="'
				+ toAccountImage
				+ '" alt="message user image"><div class="direct-chat-text">'
				+ content + '</div></div>';
		$('.direct-chat-messages').append(htmlReceived);
		$(".direct-chat-messages").scrollTop(
				$(".direct-chat-messages")[0].scrollHeight);
	}

	function sendMessage() {
		if (ws != null) {
			var message = $("input[name='message']").val();
			if ($.trim(message) == '')
				return false;
			log('Sent: ' + message);
			ws.send(JSON.stringify({
				accounts : $('.direct-chat .box-title').data('value')
						.toString(),
				content : message
			}));
			var htmlSend = '<div class="direct-chat-msg right"><div class="direct-chat-info clearfix"><span class="direct-chat-name pull-right">'
					+ fromAccountName
					+ '</span> <span class="direct-chat-timestamp pull-left">23 Jan 2:05 pm</span></div><img class="direct-chat-img" src="'
					+ fromAccountImage
					+ '" alt="message user image"><div class="direct-chat-text">'
					+ message + '</div></div>';
			$('.direct-chat-messages').append(htmlSend);
			$(".direct-chat-messages").scrollTop(
					$(".direct-chat-messages")[0].scrollHeight);
			$("input[name='message']").val('');
		} else {
			alert('connection not established, please connect.');
		}
	}

	var disconnect = function() {
		if (ws != null) {
			ws.close();
			ws = null;
		}
	}

	var log = function(message) {
		var console = document.getElementById('console');
		var p = document.createElement('p');
		p.style.wordWrap = 'break-word';
		p.appendChild(document.createTextNode(message));
		console.appendChild(p);
		while (console.childNodes.length > 25) {
			console.removeChild(console.firstChild);
		}
		console.scrollTop = console.scrollHeight;
	}

	return {
		init : init
	};
})();

(function() {
	chat.init();
})();