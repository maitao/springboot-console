package com.mt.console.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mt.console.web.po.QaArticle;
import com.mt.console.web.po.QaCategory;

@Mapper
public interface IQaArticleMapper extends IBaseMapper<QaArticle, Long> {

	int getTotalQaArticleCount();

	List<Map<String, Object>> getQaArticlesList(Map<String, Object> map);

	Map<String, Object> getQaArticle(@Param(value = "number") String number);

	QaCategory getQaCategory(@Param(value = "key") String key);

	void saveQaArticle(QaArticle article);

	void saveQaContent(@Param(value = "articleId") Long articleId, @Param(value = "content") String content);

}
