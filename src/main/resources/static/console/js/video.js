var video = (function() {
	var thiz = $(this);
	var init = function() {
		console.log('video init');
	};

	return {
		init : init
	};
})();

(function() {
	video.init();
})();