(function() {
	$('#chat').click(
			function() {
				$(".content-wrapper").empty().load('im/chat',
						function(response, status, xhr) {
							console.log('im chat load success');
						});
			});

	// 防止刷新，刷新停留当前页
	var currentPage = $('.currentPage').data('value');
	if ('' != currentPage) {
		$(".content-wrapper").empty().load(currentPage,
				function(response, status, xhr) {

				});
	}
})();