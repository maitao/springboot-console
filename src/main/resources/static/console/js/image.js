var m_image = (function() {
	var thiz = $(this);
	var init = function() {
		new commonPage(m_image, 'image', $("#tsList"), '', '', '',
				$(".pagination")).init(30);
		f_imgBinding();
	};

	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList)
				.each(
						function(i) {
							if (i % 6 == 0) {
								str += '<div class="row" style="margin-bottom:20px;">';
							}
							str += '<div class="col-sm-4 col-md-2"><div class="color-palette-set"><a href="'
									+ this.img_path
									+ '" target="_blank"><img src="'
									+ this.img_path
									+ '" alt="'
									+ this.name
									+ '" class="img-thumbnail"></a></div></div>';
							if (i % 6 == 5) {
								str += '</div>';
							}
						});
		return str;
	};

	var f_imgBinding = function() {
		$(".img-thumbnail").click(function() {
			
		});
	};

	return {
		init : init,
		dataList : dataList
	};
})();

(function() {
	m_image.init();
	console.log('image init...');
})();