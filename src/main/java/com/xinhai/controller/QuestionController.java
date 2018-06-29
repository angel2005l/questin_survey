package com.xinhai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.xinhai.entity.Answer;
import com.xinhai.entity.Question;
import com.xinhai.entity.QuestionDetail;
import com.xinhai.entity.Visit;
import com.xinhai.enums.AnswerEnum;
import com.xinhai.servie.IQuestionService;
import com.xinhai.servie.impl.QuestionServiceImpl;
import com.xinhai.util.Result;
import com.xinhai.util.StrUtil;

import net.sf.json.JSONObject;

public class QuestionController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IQuestionService service = new QuestionServiceImpl();
	private String path = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		resp.setContentType("text/html;UTF-8");
		resp.setCharacterEncoding("UTF-8");
		switch (req.getParameter("method")) {
		case "question":
			showQuestion(req, req.getSession(), resp);
			break;
		case "answer":
			saveAnswer(req, req.getSession(), resp);
			break;
		default:
			break;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		path = config.getServletContext().getContextPath();
		super.init();
	}

	/**
	 * 获得生成
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void showQuestion(HttpServletRequest req, HttpSession session, HttpServletResponse resp)
			throws IOException, ServletException {
		String naireCode = "naire_bgld";// 先将死值写入
		try {
			String openId = StrUtil.isBlank(req.getParameter("openId")) ? session.getAttribute("openId").toString()
					: req.getParameter("openId");
			if (StrUtil.isBlank(openId)) {
				resp.sendRedirect(path + "/view/errForNull.jsp");
				return;
			} else {
				if (service.checkIsPart(naireCode, openId)) {
					resp.sendRedirect(path + "/view/errForPart.jsp");
					return;
				}
				session.setAttribute("openId", openId);
			}

			// 获得数据
			Map<String, Object> data = service.selQuectionForCreate();
			req.setAttribute("naireCode", naireCode);
			req.setAttribute("queData", data.get("queData"));
			req.setAttribute("qdData", data.get("qdList"));
			req.getRequestDispatcher("/view/question.jsp").forward(req, resp);
		} catch (NullPointerException e) {
			resp.sendRedirect(path + "/view/errForNull.jsp");
		}
	}

	/**
	 * 保存返回的信息
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void saveAnswer(HttpServletRequest req, HttpSession session, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		Map<String, List> resultData = service.questionsInfoForSaveAns();
		List<Question> ques = resultData.get("queData");
		List<QuestionDetail> qds = resultData.get("qdData");
		String naireCode = req.getParameter("naireCode");
		String ansOpenId = session.getAttribute("openId").toString();

		List<Answer> list = new ArrayList<Answer>();
		// text/radio
		// checkbox
		for (Question que : ques) {
			Answer ans = null;
			String ansValue = "";
			switch (que.getQueType()) {
			case "text":
			case "radio":
				ansValue = req.getParameter(que.getId() + "");
				ans = new Answer(que.getId(), que.getQueTitle(), ansValue, naireCode, ansOpenId);
				break;
			case "checkbox":
				// String[] ansValues = req.getParameterValues(que.getId()+"");
				ansValue = StringUtils.join(req.getParameterValues(que.getId() + ""), ",");
				ans = new Answer(que.getId(), que.getQueTitle(), ansValue, naireCode, ansOpenId);
				break;
			default:
				break;
			}
			list.add(ans);
		}
		// radios(矢量)
		for (QuestionDetail qd : qds) {
			String ansValue = AnswerEnum.getName(req.getParameter(qd.getQueId() + "-" + qd.getId()));
			list.add(new Answer(qd.getQueId(), qd.getDetContext() + "," + qd.getId(), ansValue, naireCode, ansOpenId));
		}
		Visit visit = new Visit(naireCode, ansOpenId);
		Result<Object> result = service.addAnswer(list, visit);
		JSONObject json = JSONObject.fromObject(result);
		System.err.println(json);
		out.print(json.toString());
	}

}
