//var basePath = 'http://' + window.location.host;

var gdate = (function() {
	var thiz = $(this);
	var init = function() {
		new commonPage(gdate, 'gdate', $("#tsList"), 'page_select', 'pNum', '',
				$(".pagination")).init(50);
		f_imgBinding();
	};

	var dataList = function(data, index) {
		var str = '';
		$(data.data.dataList)
				.each(
						function(i) {
							if (this.status == 1)
								this.status = '参加';
							else
								this.status = '不参加';
							if (i % 6 == 0) {
								str += '<div class="row">';
							}
							str += '<div class="col-sm-4 col-md-2"><h4 class="text-center">'
									+ this.name
									+ '<span class="badge badge-info">'
									+ this.status
									+ '<span></h4><div class="color-palette-set"><img src="/upload'
									+ this.imgpath
									+ '" alt="'
									+ this.name
									+ '" class="img-thumbnail" data-value="'
									+ this.num
									+ '"><div class="bg-navy disabled color-palette"><span>'
									+ this.city
									+ ' '
									+ this.occupation
									+ ' '
									+ this.birthdate
									+ '</span></div><div class="bg-navy color-palette"><span>'
									+ this.height
									+ 'cm '
									+ this.weight
									+ 'kg '
									+ this.bust
									+ 'cm '
									+ this.waist
									+ 'cm '
									+ this.hip
									+ 'cm</span></div><div class="bg-navy-active color-palette"><span>点赞'
									+ this.praisecount
									+ ' 评论'
									+ this.commentcount
									+ '</span></div></div></div>';
							if (i % 6 == 5) {
								str += '</div>';
							}
						});
		return str;
	};

	var f_imgBinding = function() {
		// 分页
		$(".img-thumbnail").click(function() {
			var num = $(this).data('value');
			$(".content-wrapper").empty().load('/gdate/infoPage', {
				num : num
			}, function(response, status, xhr) {
				// console.log('==============' + num);
				// console.log('=============='
				// + $('.thumbnail'));
				// $('.thumbnail').data('value', num);
				console.log('num=========' + num);
			});
		});
	};

	return {
		init : init,
		dataList : dataList
	};
})();

(function() {
	gdate.init();
})();