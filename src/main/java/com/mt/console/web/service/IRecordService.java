package com.mt.console.web.service;

import com.mt.console.web.po.QaArticle;

public interface IRecordService {

	public Long add(QaArticle rd);

	public void update(QaArticle rd);

	public QaArticle view(long id, boolean addViewCount);

	public void addStar(long id, int level);

	public void delete(long id);

}
