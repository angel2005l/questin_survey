package com.xinhai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.xinhai.entity.Questionnaire;
import com.xinhai.util.SqlUtil;

public class QuestionnaireDao {
	Connection conn = null;
	PreparedStatement ps = null;

	// 新增
	public int insQueNaire(Questionnaire naire, String[] levelContext) {
		conn = SqlUtil.getConn();
		try {
			ps = conn.prepareStatement(
					"insert into questionnaire(naire_code,naire_title,naire_level_num,naire_remark) values(?,?,?,?)");

			ps.setString(1, naire.getNaireCode());
			ps.setString(2, naire.getNaireTitle());
			ps.setInt(3, naire.getNaireLevleNum());
			ps.setString(4, naire.getNaireRemark());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			int naireId = 0;
			if (rs.next()) {
				naireId = rs.getInt(1);
			} else {
				return 1;
			}
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("insert into level_naire_real values(?,?,?)");
			for (String context : levelContext) {
				ps.setInt(1, naireId);
				ps.setInt(2, 1);
				ps.setString(3, context);
				ps.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		} finally {
			SqlUtil.close(ps);
			SqlUtil.close(conn);
		}
		return 0;
	}

	// 改序列
	public int uptQueNaireWith(Map<String, Object> data) {
		conn = SqlUtil.getConn();
		try {
			ps = conn.prepareStatement("update level_naire_real set level_seq=?,level_context=? where naire_id = ? ");
			ps.setInt(1, Integer.parseInt(data.get("levelSeq").toString()));
			ps.setString(2, data.get("levelContext").toString());
			ps.setInt(3, Integer.parseInt(data.get("naireId").toString()));
			return ps.executeUpdate() > 0 ? 0 : 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		} finally {
			SqlUtil.close(ps);
			SqlUtil.close(conn);
		}
	}

	// 删除
	public int delQueNaireByNaireAndLevelSeq() {
		conn = SqlUtil.getConn();
		try {
			ps = conn.prepareStatement("delete from ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// 查
}
