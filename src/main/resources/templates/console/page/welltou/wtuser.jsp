<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
#tsHead th {
	text-align: center;
}
</style>
<!-- Content Header (Page header) -->
<!-- <section class="content-header">
	<h1>
		用户数据 <small>Control panel</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 用户管理</a></li>
		<li class="active">用户列表</li>
	</ol>
</section> -->

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header with-border">
					<h3 class="box-title wtuser-title"></h3>
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
										class="form-control pull-right" placeholder="搜索用户">

									<div class="input-group-btn">
										<button type="submit" class="btn btn-default">
											<i class="fa fa-search"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
					</div>

					<table class="table table-bordered table-hover"
						style="text-align: center;">
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
						<div class="col-sm-12">
							<ul
								class="pagination pagination-sm no-margin pull-right pagination">
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
<script src="mtadmin/js/welltou/wtuser.js"></script>
