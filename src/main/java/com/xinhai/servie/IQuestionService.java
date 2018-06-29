package com.xinhai.servie;

import java.util.List;
import java.util.Map;

import com.xinhai.entity.Answer;
import com.xinhai.entity.Question;
import com.xinhai.entity.Visit;
import com.xinhai.util.Result;

public interface IQuestionService {

	/**
	 * 查询（分页）
	 * 
	 * @param isShow
	 * @param headIndex
	 * @return
	 */
	public Map<String, Object> selQuectionWithCount(String isShow, String headIndex);

	/**
	 * 查询 获得问卷
	 * 
	 * @return
	 */
	public Map<String, Object> selQuectionForCreate();

	/**
	 * 新增 问卷
	 * 
	 * @param que
	 * @param contexts
	 * @return
	 */
	public boolean addQue(Question que, String[] contexts);

	/**
	 * 修改问卷
	 * 
	 * @param queSeq
	 * @param isShow
	 * @param queId
	 * @return
	 */
	public boolean editQueForSeqAndIsShow(String queSeq, String isShow, String queId);

	/**
	 * 根据id 查询问题信息
	 * 
	 * @param queId
	 * @return
	 */
	public Question selQuestionById(String queId);

	/**
	 * 获得参照数据
	 * 
	 * @return
	 */
	public Map<String, List> questionsInfoForSaveAns();

	/**
	 * 保存问卷答案
	 * 
	 * @param list
	 * @return
	 */
	public Result<Object> addAnswer(List<Answer> list, Visit visit);

	//生成問卷前判斷用戶是否參加過
	public boolean checkIsPart(String naireCode ,String openId);	
}
