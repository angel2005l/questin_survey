 package com.xinhai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xinhai.entity.Question;
import com.xinhai.entity.User;
import com.xinhai.servie.IQuestionService;
import com.xinhai.servie.IUserService;
import com.xinhai.servie.impl.QuestionServiceImpl;
import com.xinhai.servie.impl.UserServiceImpl;
import com.xinhai.util.StrUtil;

import net.sf.json.JSONObject;

public class IndexController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IQuestionService service = new QuestionServiceImpl();
	private IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;UTF-8");
		resp.setCharacterEncoding("UTF-8");
		switch (req.getParameter("method")) {
		case "login":
			login(req, resp);
			break;
		case "index":
			getPage(req, resp);
			break;
		case "selQue":
			getPage(req, resp);
			break;
		case "addQue":
			addQue(req, resp);
			break;
		case "editQueForInfo":
			editQueForInfo(req, resp);
			break;
		case "editQueForSave":
			editQueForSave(req, resp);
			break;
		case "quit":
			quit(req, resp);
			break;
		default:
			quit(req, resp);
			break;
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		System.err.println("初始化");
		super.init();
	}

	// 登录
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		User user = userService.login(request.getParameter("userAccount"), request.getParameter("userPass"));
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject json = null;
		if (null != user) {
			map.put("status", "success");
			json = JSONObject.fromObject(map);
		} else {
			map.put("status", "fault");
			json = JSONObject.fromObject(map);
		}
		out.println(json.toString());
	}

	// 获得分页数据
	@SuppressWarnings("unchecked")
	private void getPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得是否显示的复选框
		String isShow = "index".equals(request.getParameter("method")) ? "Y"
				: "Y".equals(request.getParameter("isShow")) ? "Y" : "N";
		String headIndex = StrUtil.notBlank(request.getParameter("page")) ? request.getParameter("page").trim() : "1";
		request.setAttribute("queryQueShow", isShow);
		Map<String, Object> resultData = service.selQuectionWithCount(isShow, headIndex);
		request.setAttribute("data", (List<Map<String, Object>>) resultData.get("data"));
		request.setAttribute("count", resultData.get("count"));
		request.setAttribute("page", headIndex);
		request.getRequestDispatcher("view/index.jsp").forward(request, response);
	}

	// 添加
	private void addQue(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String queTitle = request.getParameter("queTitle");
		String queLevel = request.getParameter("queLevel");
		String queSeq = request.getParameter("queSeq");
		String queType = request.getParameter("queType");
		String queIsAns = request.getParameter("queIsAns");
		String queShow = request.getParameter("queShow");
		String queInputType = "radios".equals(queType) ? "radio" : queType;
		Question que = new Question(queTitle, queType, queIsAns, queShow, Integer.parseInt(queSeq),
				Integer.parseInt(queLevel), queInputType);

		// 详细数组
		String[] detailArr = StrUtil.cutStringForLeft(request.getParameter("details"), 1).split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		if (service.addQue(que, detailArr)) {
			map.put("status", "success");
			map.put("msg", "添加成功");
		} else {
			map.put("status", "fault");
			map.put("msg", "添加失败");

		}
		JSONObject json = JSONObject.fromObject(map);
		out.print(json.toString());

	}

	// 编辑 查询旧数据
	private void editQueForInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Question question = service.selQuestionById(request.getParameter("queId"));
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != question) {
			map.put("status", "success");
			map.put("data", question);
		} else {
			map.put("status", "fault");
		}
		JSONObject json = JSONObject.fromObject(map);
		out.write(json.toString());

	}

	// 编辑保存
	private void editQueForSave(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Map<String, Object> map = new HashMap<String, Object>();
		if (service.editQueForSeqAndIsShow(request.getParameter("queSeq"), request.getParameter("queShow"),
				request.getParameter("queId"))) {
			map.put("status", "success");
			map.put("msg", "修改成功");
		} else {
			map.put("status", "fault");
			map.put("msg", "修改失败");
		}
		JSONObject json = JSONObject.fromObject(map);
		out.print(json.toString());

	}

	// 注销
	private void quit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 销毁session
		request.getSession().invalidate();
		// 返回登录界面
		response.sendRedirect("login.jsp");
	}
}
