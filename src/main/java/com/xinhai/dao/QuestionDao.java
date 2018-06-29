package com.xinhai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xinhai.entity.Answer;
import com.xinhai.entity.Question;
import com.xinhai.entity.QuestionDetail;
import com.xinhai.entity.Visit;
import com.xinhai.enums.QuestionEnum;
import com.xinhai.util.SqlUtil;
import com.xinhai.util.StrUtil;

public class QuestionDao {
	private Connection conn = null;

	// 添加题目
	public int addQuestion(Question que, String[] contexts) {
		conn = SqlUtil.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"insert into question(que_title,que_type,que_is_ans,que_show,que_seq,que_level,que_input_type) values(?,?,?,?,?,?,?)");
			ps.setString(1, que.getQueTitle());
			ps.setString(2, que.getQueType());
			ps.setString(3, que.getQueIsAns());
			ps.setString(4, que.getQueShow());
			ps.setInt(5, que.getQueSeq());
			ps.setInt(6, que.getQueLevel());
			ps.setString(7, que.getQueInputType());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			int queId = 0;
			while (rs.next()) {
				queId = rs.getInt(1);
			}
			if (null != contexts && contexts.length > 1) {
				conn.setAutoCommit(false);
				PreparedStatement ptstDetail = conn
						.prepareStatement("insert into question_detail(det_context,que_id) values(?,?)");
				for (String contextStr : contexts) {
					ptstDetail.setString(1, contextStr);
					ptstDetail.setInt(2, queId);
					ptstDetail.executeUpdate();
				}
				conn.commit();
			}
			ps.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 1;
		}
		return 0;
	}

	/**
	 * 获得问卷
	 * 
	 * @return
	 */
	public List<Question> selQueByIsShow() {
		conn = SqlUtil.getConn();
		List<Question> data = new ArrayList<Question>();
		try {
			PreparedStatement ps = conn
					.prepareStatement("select * from question where que_show = 'Y' order by que_seq,que_level");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question qt = new Question(rs.getInt("id"), rs.getString("que_title"),
						QuestionEnum.getName(rs.getString("que_type").trim()), rs.getString("que_is_ans"),
						rs.getString("que_show"), rs.getInt("que_seq"), rs.getInt("que_level"),
						rs.getString("que_input_type"));
				data.add(qt);
			}
			SqlUtil.close(rs);
			SqlUtil.close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn);
		}
		return data;
	}

	/**
	 * 获得问卷明细
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public List<QuestionDetail> selQueDetaByIsShow() {
		conn = SqlUtil.getConn();
		List<QuestionDetail> data = new ArrayList<QuestionDetail>();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select a.id as id ,a.det_context as detContext,a.que_id as queId from question_detail a inner join question b on a.que_id = b.id where b.que_show = 'Y' order by b.que_level,b.que_seq ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				QuestionDetail qd = new QuestionDetail(rs.getInt("id"), rs.getString("detContext"), rs.getInt("queId"));
				data.add(qd);
			}
			SqlUtil.close(rs);
			SqlUtil.close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn);
			return data;
		}
	}

	/**
	 * 查询问卷信息（分页）
	 * 
	 * @param queIsShow
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	@SuppressWarnings("finally")
	public List<Map<String, Object>> selQueForPage(String queIsShow, int startIndex, int endIndex) {
		conn = SqlUtil.getConn();
		StringBuffer sql = new StringBuffer("select * from question where 1=1 ");
		if ("Y".equals(queIsShow)) {
			sql.append("and que_show = ? ");
		}
		sql.append("order by que_seq limit ? ,? ");
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			int index = 1;
			if ("Y".equals(queIsShow)) {
				ps.setString(index++, queIsShow);
			}
			ps.setInt(index++, startIndex);
			ps.setInt(index++, endIndex);
			ResultSet rs = ps.executeQuery();
			List<Question> queList = new ArrayList<Question>();
			while (rs.next()) {
				Question question = new Question(rs.getInt("id"), rs.getString("que_title"), rs.getString("que_type"),
						rs.getString("que_is_ans"), rs.getString("que_show"), rs.getInt("que_seq"),
						rs.getInt("que_level"), rs.getString("que_input_type"));
				queList.add(question);
			}
			for (Question que : queList) {
				Map<String, Object> map = new HashMap<String, Object>();
				StringBuffer sb = new StringBuffer();
				List<QuestionDetail> qdList = selQueDetatilById(que.getId());
				for (QuestionDetail qdObj : qdList) {
					sb.append("[").append(qdObj.getDetContext()).append("] ");
				}
				map.put("queDetail", sb.toString());
				map.put("que", que);
				result.add(map);
			}
			SqlUtil.close(rs);
			SqlUtil.close(ps);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn);
			return result;
		}
	}

	/**
	 * 获得相关的明细数据
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public List<QuestionDetail> selQueDetatilById(int queId) {
		conn = SqlUtil.getConn();
		if (null == conn) {
		}
		List<QuestionDetail> qdList = new ArrayList<QuestionDetail>();
		try {

			PreparedStatement ps = conn
					.prepareStatement("select det_context,que_id from question_detail where que_id = ?");
			ps.setInt(1, queId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				QuestionDetail qd = new QuestionDetail(rs.getString(1), rs.getInt(2));
				qdList.add(qd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn);
			return qdList;
		}
	}

	/**
	 * 当前条件 数据的总行数
	 * 
	 * @param queIsShow
	 * @return
	 */
	@SuppressWarnings("finally")
	public int selQueWithCount(String queIsShow) {
		int count = 0;
		conn = SqlUtil.getConn();
		StringBuffer sql = new StringBuffer("select count(id) from question where 1=1 ");
		if ("Y".equals(queIsShow)) {
			sql.append("and que_show = ? ");
		}
		try {
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			if ("Y".equals(queIsShow)) {
				ps.setString(1, queIsShow);
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn);
			return count;
		}
	}

	/**
	 * 
	 * @Title: selQuestionById
	 * @Description: 查询更具id
	 * @param queId
	 * @return
	 * @author: MR.H
	 * @return: Question
	 *
	 */
	@SuppressWarnings("finally")
	public Question selQuestionById(int queId) {
		conn = SqlUtil.getConn();
		Question que = null;
		try {
			PreparedStatement ps = conn.prepareStatement("select * from question where id = ?");
			ps.setInt(1, queId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				que = new Question(rs.getInt("id"), rs.getString("que_title"), rs.getString("que_type"),
						rs.getString("que_is_ans"), rs.getString("que_show"), rs.getInt("que_seq"),
						rs.getInt("que_level"), rs.getString("que_input_type"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return que;
		}

	}

	/**
	 * 
	 * @Title: uptQuestion
	 * @Description: 编辑
	 * @param isShow
	 * @param queSeq
	 * @param queId
	 * @return
	 * @author: MR.H
	 * @return: int
	 *
	 */
	@SuppressWarnings("finally")
	public int uptQuestion(String isShow, int queSeq, int queId) {
		conn = SqlUtil.getConn();
		int status = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("update question set que_show = ? ,que_seq = ? where id = ?");
			ps.setString(1, isShow);
			ps.setInt(2, queSeq);
			ps.setInt(3, queId);
			status = ps.executeUpdate();
			SqlUtil.close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
			status = 0;
		} finally {
			SqlUtil.close(conn);
			return status;
		}
	}

	// 获得问卷的题目（不包括矢量表）
	public List<Question> selQueForAns() {
		conn = SqlUtil.getConn();
		List<Question> list = new ArrayList<Question>();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select id,que_title,que_type from question where que_type <> 'radios' and que_show='Y' ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Question(rs.getInt("id"), rs.getString("que_title"), rs.getString("que_type")));
			}
			SqlUtil.close(rs);
			SqlUtil.close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn);
		}
		return list;
	}

	/**
	 * 录入答案是 匹配查询问卷信息
	 * 
	 * @return
	 */
	public List<QuestionDetail> selQueDetForAns() {
		conn = SqlUtil.getConn();
		List<QuestionDetail> list = new ArrayList<QuestionDetail>();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"select a.id as id,a.det_context as det_context,a.que_id as que_id from question_detail a inner join question b on a.que_id = b.id where b.que_show='Y' and b.que_type = 'radios' order by b.que_seq");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new QuestionDetail(rs.getInt("id"), rs.getString("det_context"), rs.getInt("que_id")));
			}
			SqlUtil.close(rs);
			SqlUtil.close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SqlUtil.close(conn);
		}
		return list;
	}

	/**
	 * 添加答案 并更新访问表 记录参与问卷信息 0:true,1:false
	 * 
	 * @param list
	 * @param visit
	 * @return
	 */
	public int insAnswer(List<Answer> list, Visit visit) {
		conn = SqlUtil.getConn();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(
					"insert into answer(que_id,que_title,ans_result,naire_code,ans_open_id) values(?,?,?,?,?)");
			for (Answer ans : list) {
				if (null == ans)
					continue;
				ps.setInt(1, ans.getQueId());
				ps.setString(2, ans.getQueTitle());
				ps.setString(3, ans.getAnsResult());
				ps.setString(4, ans.getNaireCode());
				ps.setString(5, ans.getAnsOpenId());
				ps.executeUpdate();
			}
			ps = conn.prepareStatement(
					"insert into visit(naire_code,naire_title,open_id,submit_date) values(?,(select naire_title from questionnaire where naire_code =?),?,datetime('now'))");
			ps.setString(1, visit.getNaireCode());
			ps.setString(2, visit.getNaireCode());
			ps.setString(3, visit.getOpenId());
			ps.executeUpdate();
			conn.commit();
			SqlUtil.close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 1;
		} finally {
			SqlUtil.close(conn);
		}
		return 0;
	}

	/**
	 * 检查 该用户是否参加过问卷调查 0:未参加,1:已参加
	 * 
	 * @param naireCode
	 * @param openId
	 * @return
	 */
	public int selVistByNaireCodeAndOpenId(String naireCode, String openId) {
		conn = SqlUtil.getConn();
		int isPart = 0;
		try {
			PreparedStatement ps = conn
					.prepareStatement("select count(id) from visit where naire_code = ? and open_id = ?");
			ps.setString(1, naireCode);
			ps.setString(2, openId);
			ResultSet rs = ps.executeQuery();
			rs.next();// 获得第一行数据
			isPart = rs.getInt(1);
			SqlUtil.close(rs);
			SqlUtil.close(ps);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			SqlUtil.close(conn);
		}
		return isPart;
	}

}