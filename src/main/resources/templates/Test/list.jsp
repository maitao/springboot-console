<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="记事">
<meta name="author" content="tm">
<title>写记事-后台管理</title>
</head>
<body>
	<jsp:include page="../Common/header.jsp" />
	<div class="container"
		style="width: 100%; padding-left: 195px; margin-top: 55px; margin-bottom: 42px;">
		<div class="row">
			<div class="col-md-12">
				<div style="padding: 8px; background: #fff; border: 1px solid #ddd;">
					<font style="font-size: 16px; line-height: 16px;">记事列表</font><font
						style="color: red; float: right;"> <span class="add-btn"
						style="margin-right: 10px;" id="record_add" title="写记事"></span>
					</font>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div
					style="padding-bottom: 8px; border-right: 1px solid #ddd; border-left: 1px solid #ddd; background: #fff;">
					<div class="col-sm-3">
						<div style="position: relative;">
							<span class="search-btn" style="right: 5px;" title="搜索"></span> <input
								type="text" id="search_number" class="form-control search-input" placeholder="编号"
								style="box-shadow: none;" maxlength="13">
						</div>
					</div>
					<div class="col-sm-3">
						<div style="position: relative;">
							<span class="search-btn" style="right: 5px;" title="搜索"></span> <input
								type="text" id="search_title" class="form-control search-input" placeholder="标题"
								style="box-shadow: none;" maxlength="20">
						</div>
					</div>
					<div class="col-sm-3">
						<div style="position: relative;">
							<span class="search-btn" style="right: 5px;" title="搜索"></span> <input
								type="text" id="search_createAccount" type="text" class="form-control search-input"
								placeholder="作者" style="box-shadow: none;" maxlength="10">
						</div>
					</div>
					<div class="col-sm-3">
						<div style="position: relative;">
							<span class="search-btn" style="right: 5px;" title="搜索"></span> <input
								type="text" id="search_createTime" class="form-control search-input"
								placeholder="新增时间" style="box-shadow: none;background: #fff;" maxlength="50" readonly="readonly">
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-12">
						<table class="table table-bordered"
							style="margin-bottom: 0px; text-align: center;">
							<thead>
								<tr>
									<th class="order_img"></th>
									<th>星标</th>
									<th>编号</th>
									<th>标题</th>
									<th>作者</th>
									<th>最后修改</th>
									<th class="look_img"></th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="tsList">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div
					style="background: #fff; border: 1px solid #ddd; border-top: 0px; text-align: right; padding-top: 10px; padding-right: 10px;">
					<nav>
						<ul class="pagination" style="margin: 0px;">
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增弹出层 -->
	<div class="container" id="record_add_div"
		style="padding-top: 25px; width: 50%; padding-left: 15px; margin-top: 26px; position: fixed; z-index: 900; background: #f5f5f5; border: 1px solid #dcdcdc; height: 100%; top: 0; right: -860px; display: none;">
		<div class="row">
			<div class="col-sm-12">
				<font style="font-weight: bold; font-size: 14px; line-height: 14px;">写记事
				</font> <font
					style="color: red; float: right; font-size: 13px; line-height: 13px;">
					<span id="record_close" style="cursor: pointer;">关闭</span>
				</font><font style="color: red; float: right;">&nbsp;&nbsp;</font><font
					style="color: red; float: right; font-size: 13px; line-height: 13px;">
					<span id="record_save" style="cursor: pointer;">保存</span>
				</font>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<form role="form" id="recordForm" method="post"
					action="record/save.mt">
					<div class="row">
						<div class="col-sm-8 col-md-8">
							<input type="hidden" name="id"> <input type="hidden"
								name="uuid">
						</div>
					</div>
					<div class="row" style="margin-top: 5px;">
						<div class="col-sm-12 col-md-12">
							<div class="input-group">
								<div class="input-group-addon" style="background-color: #fff;">
									标题<font style="color: red;">*</font>
								</div>
								<input type="text" class="form-control" name="title"
									maxlength="50" placeholder="标题">
							</div>
						</div>
					</div>
					<div class="row" style="margin-top: 5px;">
						<div class="col-sm-12 col-md-12">
							<script id="editor" name="content" type="text/plain"
								style="height: 330px;"></script>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="view_modal" class="modal fade bs-example-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="gridSystemModalLabel"
						style="text-align: center;"></h4>
				</div>
				<div class="modal-body" style="height: 450px; overflow-y: auto;">
					<div class="modal_content" style="background-color: #fff;"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						style="float: left;">Close</button>
					<button type="button" class="btn btn-primary max_modal">最大化</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../Common/footer.jsp" />
</body>
<link href="Record/css/record.css" rel="stylesheet">
<script src="Common/js/jquery.validate.min.js"></script>
<script src="Common/js/messages_zh.js"></script>
<script type="text/javascript" charset="utf-8"
	src="Common/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="Common/ueditor1_4_3_3-utf8-jsp/utf8-jsp/ueditor.all.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script
	src="Common/ueditor1_4_3_3-utf8-jsp/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<script src="Common/laydate/laydate.js"></script>
<script type="text/javascript" src="Record/js/record.js"></script>
<style>
.laydate_body .laydate_y {
	margin-right: 4px;
}

.laydate_body .laydate_table {
	width: 228px;
}
</style>
</html>

