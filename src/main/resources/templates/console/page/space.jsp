<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.article-table>thead>tr>th, .article-table>tbody>tr>td {
	padding: 2px 8px;
}

.box-body {
	padding-top: 0px;
}

.btn-default {
	background-color: #fff;
}

.tool-div .btn-default {
	border-color: #fff;
}

/*right click*/
div#rMenu {
	position: absolute;
	visibility: hidden;
	top: 0;
	background-color: #555;
	text-align: left;
	padding: 2px;
	z-index: 2000;
}

div#rMenu ul {
	margin: 0;
	padding: 0;
}

div#rMenu ul li {
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
</style>
<!-- Content Header (Page header) -->
<!-- Main content -->
<section class="content">
	<div class="row">

		<div class="col-md-12">

			<div class="row">
				<div class="col-md-12">
					<div class="box" style="margin-bottom: 5px;">
						<div class="box-header with-border" style="padding: 2px 10px;">
							<div class="row">
								<div class="hidden-xs hidden-sm col-md-6 tool-div">
									<button type="button"
										class="article_add btn btn-default btn-sm" title="写记事">
										<i class="fa fa-plus text-green"></i>
									</button>
									<div class="btn-group">
										<button type="button" class="btn btn-default btn-sm"
											title="生成Excel">
											<i class="fa fa-file-excel-o text-green"></i>
										</button>
										<button type="button" class="btn btn-default btn-sm"
											title="生成PDF">
											<i class="fa fa-file-pdf-o text-red"></i>
										</button>
										<button type="button" class="btn btn-default btn-sm"
											title="打印">
											<i class="fa fa-print text-navy"></i>
										</button>
										<button type="button" class="btn btn-default btn-sm"
											title="同步">
											<i class="fa fa-refresh text-yellow"></i>
										</button>
									</div>
									<button type="button" class="btn btn-default btn-sm" title="删除">
										<i class="fa fa-trash-o text-red"></i>
									</button>
								</div>
								<div class="col-md-6">
									<div class="input-group input-group-sm pull-right"
										style="width: 100%; max-width: 300px;">
										<input type="text" name="table_search"
											class="form-control pull-right" placeholder="搜索记事">

										<div class="input-group-btn">
											<button type="submit" class="btn btn-default">
												<i class="fa fa-search"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="hidden-xs hidden-sm col-md-2"
									style="max-height: 500px; overflow: auto;">
									<div class="box-body">
										<div class="zTreeDemoBackground left">
											<ul id="treeDemo" class="ztree"></ul>
										</div>
									</div>
								</div>
								<div class="col-md-10">
									<div
										style="height: 200px; overflow-y: auto; overflow-x: hidden; padding: 0px;">
										<table class="table table-bordered table-hover article-table">
											<thead id="tsHead">
											</thead>
											<tbody id="tsList">
											</tbody>
										</table>
									</div>
									<div class="row">
										<div class="col-md-12">
											<form role="form" id="recordForm" method="post"
												action="record/save">
												<input type="hidden" name="id"> <input type="hidden"
													name="uuid"> <input type="hidden" name="categoryId">
												<input type="hidden" name="summary">
												<div class="row">
													<div class="col-sm-12 col-md-12">
														<div class="input-group">
															<div class="input-group-addon"
																style="background-color: #fff;">
																主题<font style="color: red;">*</font>
															</div>
															<input type="text" class="form-control" name="title"
																maxlength="50" placeholder="无主题...">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-12 col-md-12">
														<script id="editor" name="content" type="text/plain"
															style="min-height: 250px; width: 100%;"></script>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<div id="rMenu">
	<ul>
		<li id="m_addA">新建记事</li>
		<li id="m_rename">重命名</li>
		<li id="m_del">删除</li>
		<li id="m_add">新建分类</li>
	</ul>
</div>
<link rel="stylesheet" href="plugin/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="plugin/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript"
	src="plugin/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript"
	src="plugin/ztree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="mtadmin/js/space.js"></script>
<script type="text/javascript" charset="utf-8"
	src="plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="plugin/ueditor/ueditor.all.js">
	
</script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	var basePath = 'http://' + window.location.host + '/web/';

	//实例化编辑器
	// 建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE
			.getEditor(
					'editor',
					{
						UEDITOR_HOME_URL : basePath+ 'plugin/ueditor/',
						toolbars : [ [ 'fullscreen', 'source', '|', 'undo',
								'redo', '|', 'bold', 'italic', 'underline',
								'fontborder', 'strikethrough', 'superscript',
								'subscript', 'removeformat', 'formatmatch',
								'autotypeset', 'blockquote', 'pasteplain', '|',
								'forecolor', 'backcolor', 'insertorderedlist',
								'insertunorderedlist' ] ],
						autoHeightEnabled : false
					});
</script>
<style>
#edui1_elementpath {
	display: none;
}
</style>
