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
<title>支付宝-returnUrl回调</title>
</head>
<body>
	<jsp:include page="../Common/header.jsp" />
	<div class="container"
		style="width: 100%; padding-left: 195px; margin-top: 55px; margin-bottom: 42px;">
		return-success
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

