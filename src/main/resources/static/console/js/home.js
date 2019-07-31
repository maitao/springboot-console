var home = (function() {
	var thiz = $(this);
	var init = function() {
		console.log('home init');
	};

	return {
		init : init
	};
})();

(function() {
	home.init();
})();