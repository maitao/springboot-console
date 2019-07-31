<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!-- Content Header (Page header) -->

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-3">

			<div class="box box-default">
				<div class="box-header with-border">
					<h3 class="box-title">搜索用户</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-widget="remove">
							<i class="fa fa-remove"></i>
						</button>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<!-- <label>搜索用户</label> -->
								<select class="form-control select2" style="width: 100%;">
									<!-- <option selected="selected">Alabama</option>
									<option>Alaska</option>
									<option>California</option>
									<option>Delaware</option>
									<option>Tennessee</option>
									<option>Texas</option>
									<option>Washington</option> -->
									<option selected="selected"></option>
									<c:forEach items="${mList}" var="list" varStatus="m">
										<option data-value="${list.account}">${list.name}</option>
									</c:forEach>
								</select>
							</div>
							<!-- /.form-group -->
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.box-body -->
				<!-- <div class="box-footer">
					Visit <a href="https://select2.github.io/">Select2
						documentation</a> for more examples and information about the plugin.
				</div> -->
			</div>
			<!-- /.box -->

			<div class="box box-solid">
				<div class="box-header with-border">
					<h3 class="box-title">未读信息</h3>
					<div class="box-tools">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
					</div>
				</div>
				<div class="box-body no-padding">
					<ul class="nav nav-pills nav-stacked contact-ul un-reach-num">
						<c:forEach items="${rList}" var="list" varStatus="m">
							<li data-value="${list.from_account}"><a
								href="javascript:void(0);"><i class="fa fa-user"></i> <span>${list.from_account}</span><span
									class="label label-primary pull-right">${list.unReachNum}</span></a></li>
						</c:forEach>
					</ul>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /. box -->

			<div class="box box-solid">
				<div class="box-header with-border">
					<h3 class="box-title">联系人</h3>

					<div class="box-tools">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
					</div>
				</div>
				<div class="box-body no-padding">
					<ul class="nav nav-pills nav-stacked contact-ul">
						<c:forEach items="${mList}" var="list" varStatus="m">
							<li data-value="${list.account}"><a
								href="javascript:void(0);"><i class="fa fa-user"></i> <span>${list.name}</span><span
									class="label label-primary pull-right">12</span></a></li>
						</c:forEach>
					</ul>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /. box -->
		</div>
		<div class="col-md-6">
			<!-- DIRECT CHAT -->
			<div class="box box-warning direct-chat direct-chat-warning">
				<div class="box-header with-border">
					<h3 class="box-title">聊天</h3>

					<div class="box-tools pull-right">
						<span data-toggle="tooltip" title="3 New Messages"
							class="badge bg-yellow">3</span>
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-toggle="tooltip" title="Contacts"
							data-widget="chat-pane-toggle">
							<i class="fa fa-comments"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-widget="remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- Conversations are loaded here -->
					<div class="direct-chat-messages" style="height: 330px;"></div>
					<!--/.direct-chat-messages-->

					<!-- Contacts are loaded here -->
					<div class="direct-chat-contacts" style="height: 330px;">
						<ul class="contacts-list">
							<li><a href="#"> <img class="contacts-list-img"
									src="mtcommon/lte/dist/img/user1-128x128.jpg" alt="User Image">

									<div class="contacts-list-info">
										<span class="contacts-list-name"> Count Dracula <small
											class="contacts-list-date pull-right">2/28/2015</small>
										</span> <span class="contacts-list-msg">How have you been? I
											was...</span>
									</div> <!-- /.contacts-list-info -->
							</a></li>
							<!-- End Contact Item -->
							<li><a href="#"> <img class="contacts-list-img"
									src="mtcommon/lte/dist/img/user7-128x128.jpg" alt="User Image">

									<div class="contacts-list-info">
										<span class="contacts-list-name"> Sarah Doe <small
											class="contacts-list-date pull-right">2/23/2015</small>
										</span> <span class="contacts-list-msg">I will be waiting
											for...</span>
									</div> <!-- /.contacts-list-info -->
							</a></li>
							<!-- End Contact Item -->
							<li><a href="#"> <img class="contacts-list-img"
									src="mtcommon/lte/dist/img/user3-128x128.jpg" alt="User Image">

									<div class="contacts-list-info">
										<span class="contacts-list-name"> Nadia Jolie <small
											class="contacts-list-date pull-right">2/20/2015</small>
										</span> <span class="contacts-list-msg">I'll call you back
											at...</span>
									</div> <!-- /.contacts-list-info -->
							</a></li>
							<!-- End Contact Item -->
							<li><a href="#"> <img class="contacts-list-img"
									src="mtcommon/lte/dist/img/user5-128x128.jpg" alt="User Image">

									<div class="contacts-list-info">
										<span class="contacts-list-name"> Nora S. Vans <small
											class="contacts-list-date pull-right">2/10/2015</small>
										</span> <span class="contacts-list-msg">Where is your new...</span>
									</div> <!-- /.contacts-list-info -->
							</a></li>
							<!-- End Contact Item -->
							<li><a href="#"> <img class="contacts-list-img"
									src="mtcommon/lte/dist/img/user6-128x128.jpg" alt="User Image">

									<div class="contacts-list-info">
										<span class="contacts-list-name"> John K. <small
											class="contacts-list-date pull-right">1/27/2015</small>
										</span> <span class="contacts-list-msg">Can I take a look
											at...</span>
									</div> <!-- /.contacts-list-info -->
							</a></li>
							<!-- End Contact Item -->
							<li><a href="#"> <img class="contacts-list-img"
									src="mtcommon/lte/dist/img/user8-128x128.jpg" alt="User Image">

									<div class="contacts-list-info">
										<span class="contacts-list-name"> Kenneth M. <small
											class="contacts-list-date pull-right">1/4/2015</small>
										</span> <span class="contacts-list-msg">Never mind I found...</span>
									</div> <!-- /.contacts-list-info -->
							</a></li>
							<!-- End Contact Item -->
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
						<div id="senderInfo" style="display: none">
							<span data-value="${aminfo.name}"></span><span
								data-value="${aminfo.image}"></span>
						</div>
					</div>
				</div>
				<!-- /.box-footer-->
			</div>
			<!--/.direct-chat -->
		</div>

		<div class="col-md-3">
			<div id="console"></div>
		</div>
		<!-- /.col -->

		<!-- <div class="col-md-6">
			USERS LIST
			/.box
		</div> -->
		<!-- /.col -->
	</div>
	<!-- /.row -->

</section>
<!-- /.content -->

<!-- Select2 -->
<script src="mtcommon/lte/plugins/select2/select2.full.min.js"></script>

<script src="mtim/js/chat.js"></script>
<script>
	$(function() {
		$(".select2").select2();
	});
</script>