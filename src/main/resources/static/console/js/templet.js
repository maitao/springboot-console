var templet = (function() {
	var thiz = $(this);
	var init = function() {
		console.log('templet init');
	};

	return {
		init : init
	};
})();

(function() {
	templet.init();
})();