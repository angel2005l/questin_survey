package com.xinhai.servie.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xinhai.dao.QuestionDao;
import com.xinhai.entity.Answer;
import com.xinhai.entity.Question;
import com.xinhai.entity.QuestionDetail;
import com.xinhai.entity.Visit;
import com.xinhai.servie.IQuestionService;
import com.xinhai.util.Result;
import com.xinhai.util.StrUtil;

public class QuestionServiceImpl implements IQuestionService {

	private QuestionDao dao = new QuestionDao();
	private final int defaultCount = 10;

	// 分页 查询
	@Override
	public Map<String, Object> selQuectionWithCount(String isShow, String headIndex) {
		if (StrUtil.isBlank(headIndex) || StrUtil.isBlank(isShow)) {
			return null;
		}
		int index = Integer.parseInt(headIndex);
		Map<String, Object> map = new HashMap<String, Object>();

		List<Map<String, Object>> queAndqdData = dao.selQueForPage(isShow, (index - 1) * defaultCount,
				index * defaultCount);
		int count = dao.selQueWithCount(isShow);
		map.put("data", queAndqdData);
		map.put("count", count);
		return map;
	}

	// 全显示
	@Override
	public Map<String, Object> selQuectionForCreate() {
		Map<String, Object> map = new HashMap<String, Object>();

		List<Question> queList = dao.selQueByIsShow();
		List<QuestionDetail> qdList = dao.selQueDetaByIsShow();
		map.put("queData", queList);
		map.put("qdList", qdList);

		return map;
	}

	// 新增
	@Override
	public boolean addQue(Question que, String[] contexts) {
		return dao.addQuestion(que, contexts) == 0 ? true : false;
	}

	// 编辑
	@Override
	public boolean editQueForSeqAndIsShow(String queSeq, String isShow, String queId) {
		return dao.uptQuestion(isShow, Integer.parseInt(queSeq), Integer.parseInt(queId)) > 0 ? true : false;
	}

	// 查询久数据
	@Override
	public Question selQuestionById(String queId) {
		return dao.selQuestionById(Integer.parseInt(queId));

	}

	@Override
	public Map<String, List> questionsInfoForSaveAns() {
		List<Question> queList = dao.selQueForAns();
		List<QuestionDetail> qdList = dao.selQueDetForAns();
		Map<String, List> map = new HashMap<String, List>();
		map.put("queData", queList);
		map.put("qdData", qdList);
		return map;
	}

	@Override
	public Result<Object> addAnswer(List<Answer> list, Visit visit) {
		return dao.insAnswer(list, visit) == 0 ? new Result<Object>(Result.SUCCESS, "提交成功，感谢您的配合。")
				: new Result<>(Result.FAIL, "提交失败，为避免重新填写请勿刷新界面，并再次尝试提交");
	}

	@Override
	public boolean checkIsPart(String naireCode, String openId) {
		return dao.selVistByNaireCodeAndOpenId(naireCode, openId) > 0 ? true : false;
	}

}
