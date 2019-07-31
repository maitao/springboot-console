<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Content Header (Page header) -->

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-3">

			<!-- Profile Image -->
			<div class="box box-primary">
				<div class="box-body box-profile">
					<img class="profile-user-img img-responsive img-circle"
						src="${aminfo.image}" alt="User profile picture">

					<h3 class="profile-username text-center">${am.name}</h3>

					<p class="text-muted text-center">${aminfo.occupation}</p>

					<ul class="list-group list-group-unbordered">
						<li class="list-group-item"><b>原创文章</b> <a
							class="pull-right">1,322</a></li>
						<li class="list-group-item"><b>摘录收藏</b> <a
							class="pull-right">543</a></li>
						<li class="list-group-item"><b>图片</b> <a
							class="pull-right">13,287</a></li>
					</ul>

					<a href="#" class="btn btn-primary btn-block"><b>Follow</b></a>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->

			<!-- About Me Box -->
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">关于我的</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<strong><i class="fa fa-book margin-r-5"></i> 教育经历</strong>

					<p class="text-muted">${aminfo.education}</p>

					<hr>

					<strong><i class="fa fa-map-marker margin-r-5"></i> 位置</strong>

					<p class="text-muted">${aminfo.location}</p>

					<hr>

					<strong><i class="fa fa-pencil margin-r-5"></i> 擅长</strong>

					<p>
						<c:forEach items="${skills}" var="skill" varStatus="gp">
							<c:if test="${gp.index%5==0}">
								<span class="label label-danger" style="margin-bottom: 5px;">${skill}</span>
							</c:if>
							<c:if test="${gp.index%5==1}">
								<span class="label label-success">${skill}</span>
							</c:if>
							<c:if test="${gp.index%5==2}">
								<span class="label label-info">${skill}</span>
							</c:if>
							<c:if test="${gp.index%5==3}">
								<span class="label label-warning">${skill}</span>
							</c:if>
							<c:if test="${gp.index%5==4}">
								<span class="label label-primary">${skill}</span>
							</c:if>
						</c:forEach>
						<%-- <span class="label label-danger">UI Design${skills[0]}</span>
						<span class="label label-success">Coding</span> <span
							class="label label-info">Javascript</span> <span
							class="label label-warning">PHP</span> <span
							class="label label-primary">Node.js</span> --%>
					</p>

					<hr>

					<strong><i class="fa fa-file-text-o margin-r-5"></i> 记下</strong>
					<p>${aminfo.remark}</p>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
		<div class="col-md-9">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title"></h3>
					<div class="box-tools">
						<!-- Check all button -->
						<button type="button" class="btn btn-default btn-sm">
							<i class="fa fa-trash-o"></i>
						</button>
						<div class="btn-group">
							<button type="button" class="btn btn-default btn-sm">
								<i class="fa fa-reply"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm"
								title="生成Excel">
								<i class="fa fa-file-excel-o text-green"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm"
								title="生成PDF">
								<i class="fa fa-file-pdf-o text-red"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm" title="打印">
								<i class="fa fa-print text-navy"></i>
							</button>
							<button type="button" class="btn btn-default btn-sm">
								<i class="fa fa-download"></i>
							</button>
						</div>
						<!-- /.btn-group -->
						<button type="button" class="btn btn-default btn-sm" title="刷新列表">
							<i class="fa fa-refresh text-yellow"></i>
						</button>

					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body table-responsive">
					<div class="overlay">
						<i class="fa fa-refresh fa-spin"></i>
					</div>
					<div>
						<div style="padding-bottom: 10px;">
							<label
								style="font-weight: normal; text-align: left; white-space: nowrap;">显示
								<select name="page_select" aria-controls="example1"
								class="form-control input-sm"
								style="display: inline-block; width: auto; vertical-align: middle;"><option
										value="10">10</option>
									<option value="25">25</option>
									<option value="50">50</option>
									<option value="100">100</option></select>
							</label>
							<div class="pull-right">
								<div class="input-group input-group-sm" style="width: 150px;">
									<input type="text" name="table_search"
										class="form-control pull-right" placeholder="搜索日志">

									<div class="input-group-btn">
										<button type="submit" class="btn btn-default">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>

					<table class="table table-bordered table-hover">
						<thead id="tsHead">

						</thead>
						<tbody id="tsList">

						</tbody>
					</table>
				</div>
				<!-- /.box-body -->
				<!-- /.box-body -->
				<div class="box-footer clearfix">
					<div class="row">
						<div class="col-sm-5"></div>
						<div class="col-sm-7">
							<ul class="pagination pagination-sm no-margin pull-right">
								<li><a href="#">&laquo;</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">&raquo;</a></li>
							</ul>
						</div>
					</div>

				</div>
				<!-- /.box -->
			</div>
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->

</section>
<!-- /.content -->

<script src="mtadmin/js/space.js"></script>
