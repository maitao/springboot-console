<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Content Header (Page header) -->
<!-- <section class="content-header">
	<h1>
		菜单管理 <small>Control panel</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
		<li class="active">菜单管理</li>
	</ol>
</section> -->

<!-- Main content -->
<section class="content">

	<div class="row">
		<div class="col-md-3">
			<a class="btn btn-primary btn-block margin-bottom"
				data-toggle="modal" data-target="#myModal">新增菜单</a>

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">菜单结构</h3>
				</div>
				<!-- /.box-header -->
				<!-- form start -->
				<div class="box-body">

					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>


				</div>
				<!-- /.box-body -->

				<!-- <div class="box-footer">
					<button type="submit" class="btn btn-primary">提交</button>
				</div> -->
			</div>
			<!-- /.box -->

		</div>
		<!-- /.col -->
		<div class="col-md-9">


			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">菜单列表</h3>
					<div class="box-tools">
						<!-- Check all button -->
						<button type="button" class="btn btn-default btn-sm">
							<i class="fa fa-trash-o"></i>
						</button>
						<div class="btn-group">
							<button type="button" class="btn btn-default btn-sm">
								<i class="glyphicon glyphicon-plus"></i>
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

					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>序号</th>
								<th>状态</th>
								<th>编码</th>
								<th>上级编码</th>
								<th>名称</th>
								<th>请求url</th>
								<th>左图</th>
								<th>右图</th>
								<th>创建时间</th>
								<th>更新时间</th>
								<th>操作</th>
							</tr>
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

							</ul>
						</div>
					</div>

				</div>
				<!-- /.box -->
			</div>
			<!-- /. box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">新增菜单</h4>
			</div>
			<form class="form-horizontal">
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<!-- <div class="box-header with-border">
								<h3 class="box-title">新增菜单</h3>
							</div> -->
							<!-- /.box-header -->
							<!-- form start -->

							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">上级编码</label>

								<div class="col-sm-10">
									<input type="email" class="form-control" id="inputEmail3"
										placeholder="Email">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">编码</label>

								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword3"
										placeholder="Password">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">名称</label>

								<div class="col-sm-10">
									<input type="email" class="form-control" id="inputEmail3"
										placeholder="Email">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">请求url</label>

								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword3"
										placeholder="Password">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">左图</label>

								<div class="col-sm-10">
									<input type="email" class="form-control" id="inputEmail3"
										placeholder="Email">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-2 control-label">右图</label>

								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword3"
										placeholder="Password">
								</div>
							</div>
						</div>
						<!-- /.box-body -->
						<!-- <div class="box-footer">
									<button type="button" id="menu_cancel" class="btn btn-default">取消</button>
									<button type="submit" class="btn btn-info pull-right">提交</button>
								</div> -->
						<!-- /.box-footer -->

					</div>
				</div>
			</form>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary">提交</button>
			</div>
		</div>
	</div>
</div>

<link rel="stylesheet" href="plugin/ztree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="plugin/ztree/js/jquery.ztree.core.js"></script>
<script src="mtadmin/js/page.js"></script>
<script src="mtadmin/js/menu.js"></script>
<script>
	
</script>
