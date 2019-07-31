var basePath = 'http://' + window.location.host + '/admin/';
var wsPath = basePath.replace("http://", "ws://");
var ws = null;

var webs = (function() {
	
	//websocket心跳重连
	var heartCheck = {
		timeout : 60000,//60ms
		timeoutObj : null,
		reset : function() {
			clearTimeout(this.timeoutObj);
			this.start();
		},
		start : function() {
			if(ws != null){
				this.timeoutObj = setTimeout(function() {
					ws.send("HeartBeat");
				}, this.timeout)
			}
		}
	};
	
	var init = function(){
		if ('WebSocket' in window) {
			ws = new WebSocket(wsPath + 'ws');
		} else if ('MozWebSocket' in window) {
			ws = new MozWebSocket(wsPath + 'ws');
		} else {
			ws = new SockJS(wsPath + 'ws/sockjs');
		}
		
		ws.onopen = function() {
			heartCheck.start();
			console.log('ws open!');
		};

		ws.onmessage = function(event) {
			/*for ( var i in event.originalTarget) {
				console.log("event[" + i + "]:" + event.originalTarget[i]);
			}*/
			var obj = JSON.parse(event.data);
			
			var type = obj.stype;
			//发送信息到单个用户
			if(type == 'm-o'){
				chat2.handlerMsg(obj);
			}
			
			heartCheck.reset();
		};

		ws.onclose = function(event) {
			alert('ws关闭');
			console.log('ws close!');
		};
	};
	
	colse = function(){
		if (ws != null) {
			alert('客户端主动关闭ws');
			ws.close();
			ws = null;
		}
	};
	
	return {
		init : init,
		colse : colse
	};

})();

$(function() {
	webs.init();
});