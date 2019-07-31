<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.direct-chat-search-contacts {
	-webkit-transform: translate(101%, 0);
	-ms-transform: translate(101%, 0);
	-o-transform: translate(101%, 0);
	transform: translate(101%, 0);
	position: absolute;
	top: 0;
	bottom: 0;
	height: 250px;
	width: 100%;
	background: gray;
	color: #fff;
	overflow: auto;
	padding: 10px;
	z-index: 2001;
}

.direct-chat-search-contacts-open .direct-chat-search-contacts {
	-webkit-transform: translate(0, 0);
	-ms-transform: translate(0, 0);
	-o-transform: translate(0, 0);
	transform: translate(0, 0);
}

.select2-dropdown {
	z-index: 2000;
}
</style>

<!-- Content Header (Page header) -->

<!-- Main content -->
<div>
	<div class="row">

		<div class="col-md-12 col-sm-12">
			<!-- DIRECT CHAT -->
			<div
				class="box box-warning direct-chat direct-chat-warning direct-chat-contacts-open"
				style="margin-bottom: 0px;">
				<div class="box-header with-border">
					<h3 class="box-title">聊天</h3>

					<div class="box-tools pull-right">
						<span data-toggle="tooltip" title="新的消息"
							class="badge bg-yellow msg-num-span">0</span>
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" id="history_btn" class="btn btn-box-tool"
							data-toggle="tooltip" title="历史记录" data-widget="">
							<i class="fa fa-history"></i>
						</button>
						<button type="button" id="contact_btn" class="btn btn-box-tool"
							data-toggle="tooltip" title="联系人" data-widget="chat-pane-toggle">
							<i class="fa fa-comments"></i>
						</button>
						<button type="button" id="search_btn" class="btn btn-box-tool"
							data-toggle="tooltip" title="查找联系人" data-widget="">
							<i class="fa fa-search"></i>
						</button>
						<button type="button" class="btn btn-box-tool" data-widget=""
							id="chat-hide">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- Conversations are loaded here -->
					<div class="direct-chat-messages" style="height: 330px;"></div>
					<!--/.direct-chat-messages-->

					<div class="direct-chat-search-contacts" style="height: 330px;">
						<div class="form-group">
							<!-- <label>搜索用户</label> -->
							<select class="form-control select2" style="width: 100%;">
								<option selected="selected">请选择</option>
							</select>
						</div>

						<p>
							<span class="msg_contact_text">添加到联系人</span><span
								id="add_contact" class="fa fa-check-circle-o text-white"
								style="float: right;"></span>
						</p>
						<!-- /.form-group -->
					</div>

					<!-- Contacts are loaded here -->
					<div class="direct-chat-contacts" style="height: 330px;">
						<ul class="contacts-list">
						</ul>
						<!-- /.contatcts-list -->
					</div>
					<!-- /.direct-chat-pane -->
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<div class="input-group">
						<input type="text" name="message" placeholder="输入 ..."
							class="form-control"> <span class="input-group-btn">
							<button id="sendMsgButton" type="button"
								class="btn btn-warning btn-flat">发送</button>
						</span>
					</div>
				</div>
				<!-- /.box-footer-->
			</div>
			<!--/.direct-chat -->
		</div>

	</div>
	<!-- /.row -->
</div>
<!-- /.content -->



<!-- Select2 -->
<script src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.min.js"></script>
<script src="mtcommon/lte/plugins/select2/select2.full.min.js"></script>
<script src="mtim/js/chat2.js"></script>