package com.mt.console.web.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.console.web.mapper.IQaArticleMapper;
import com.mt.console.web.po.QaArticle;
import com.mt.console.web.service.IRecordService;

@Service("RecordServiceImpl")
public class RecordServiceImpl implements IRecordService {

	@Autowired
	private IQaArticleMapper irm;

	public Long add(QaArticle rd) {
		rd.setCreateTime(new Timestamp(System.currentTimeMillis()));
		irm.insert(rd); 
		return rd.getId();
		
	}

	@Override
	public void update(QaArticle rd) {
		rd.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		irm.update(rd);
	}

	@Override
	public QaArticle view(long id, boolean addViewCount) {
		if (addViewCount) {
			QaArticle rd = new QaArticle();
			rd.setId(id);
			irm.updateCount(rd);
		}
		return irm.selectById(id);
	}

	@Override
	public void addStar(long id, int level) {
		//irm.addStar(id, level);
	}

	@Override
	public void delete(long id) {
		irm.deleteById(id);
	}

}
