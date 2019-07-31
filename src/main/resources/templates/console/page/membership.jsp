<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		用户数据 <small>Control panel</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
		<li class="active">用户管理</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-3"></div>
		<!-- /.col -->
		<div class="col-md-12">

			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title">用户列表</h3>
					<div class="box-tools">
						<div class="input-group input-group-sm" style="width: 150px;">
							<input type="text" name="table_search"
								class="form-control pull-right" placeholder="搜索用户">

							<div class="input-group-btn">
								<button type="submit" class="btn btn-default">
									<i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body table-responsive">
					<div>
						<div style="padding-bottom: 10px;">
							<!-- Check all button -->
							<button type="button"
								class="btn btn-default btn-sm checkbox-toggle">
								<i class="fa fa-square-o"></i>
							</button>
							<div class="btn-group">
								<button type="button" class="btn btn-default btn-sm">
									<i class="fa fa-trash-o"></i>
								</button>
								<button type="button" class="btn btn-default btn-sm">
									<i class="fa fa-reply"></i>
								</button>
								<button type="button" class="btn btn-default btn-sm">
									<i class="fa fa-share"></i>
								</button>
							</div>
							<!-- /.btn-group -->
							<button type="button" class="btn btn-default btn-sm">
								<i class="fa fa-refresh"></i>
							</button>
							<div class="pull-right">
								<label
									style="font-weight: normal; text-align: left; white-space: nowrap;">显示
									<select name="example1_length" aria-controls="example1"
									class="form-control input-sm"
									style="display: inline-block; width: auto; vertical-align: middle;"><option
											value="10">10</option>
										<option value="25">25</option>
										<option value="50">50</option>
										<option value="100">100</option></select>
								</label>
							</div>
						</div>
					</div>

					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th style="width: 15px;"><input type="checkbox"></th>
								<th>序号</th>
								<th>帐号</th>
								<th>用户名</th>
								<th>角色</th>
								<th>是否在线</th>
								<th>注册IP</th>
								<th>最后登录IP</th>
								<th>最后登录时间</th>
								<th>登录数</th>
								<th>锁定时间</th>
								<th>是否启用</th>
								<th>创建时间</th>
								<th>修改时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tsList">
							<tr>
								<td>183</td>
								<td>John Doe</td>
								<td>11-7-2014</td>
								<td><span class="label label-success">Approved</span></td>
								<td>Bacon ipsum dolor sit amet salami venison chicken flank
									fatback doner.</td>
							</tr>
							<tr>
								<td>219</td>
								<td>Alexander Pierce</td>
								<td>11-7-2014</td>
								<td><span class="label label-warning">Pending</span></td>
								<td>Bacon ipsum dolor sit amet salami venison chicken flank
									fatback doner.</td>
							</tr>
							<tr>
								<td>657</td>
								<td>Bob Doe</td>
								<td>11-7-2014</td>
								<td><span class="label label-primary">Approved</span></td>
								<td>Bacon ipsum dolor sit amet salami venison chicken flank
									fatback doner.</td>
							</tr>
							<tr>
								<td>175</td>
								<td>Mike Doe</td>
								<td>11-7-2014</td>
								<td><span class="label label-danger">Denied</span></td>
								<td>Bacon ipsum dolor sit amet salami venison chicken flank
									fatback doner.</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.box-body -->
				<!-- /.box-body -->
				<div class="box-footer clearfix">
					<div class="row">
						<div class="col-sm-5">共7355条/共736页 显示20-30记录</div>
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
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
</section>
<!-- /.content -->


<script src="mtadmin/js/page.js"></script>
<script src="mtadmin/js/membership.js"></script>
