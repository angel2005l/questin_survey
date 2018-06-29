package com.xinhai.entity;

public class Question {
	private int id;
	private String queTitle;
	private String queType;
	private String queIsAns;
	private String queShow;
	private int queSeq;
	private int queLevel;
	private String queInputType;

	public Question() {
		super();
	}

	public Question(int id, String queTitle, String queType) {
		this.id = id;
		this.queTitle = queTitle;
		this.queType = queType;
	}

	public Question(String queTitle, String queType, String queIsAns, String queShow, int queSeq, int queLevel,
			String queInputType) {
		super();
		this.queTitle = queTitle;
		this.queType = queType;
		this.queIsAns = queIsAns;
		this.queShow = queShow;
		this.queSeq = queSeq;
		this.queLevel = queLevel;
		this.queInputType = queInputType;
	}

	public Question(int id, String queTitle, String queType, String queIsAns, String queShow, int queSeq, int queLevel,
			String queInputType) {
		super();
		this.id = id;
		this.queTitle = queTitle;
		this.queType = queType;
		this.queIsAns = queIsAns;
		this.queShow = queShow;
		this.queSeq = queSeq;
		this.queLevel = queLevel;
		this.queInputType = queInputType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQueTitle() {
		return queTitle;
	}

	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}

	public String getQueType() {
		return queType;
	}

	public void setQueType(String queType) {
		this.queType = queType;
	}

	public String getQueIsAns() {
		return queIsAns;
	}

	public void setQueIsAns(String queIsAns) {
		this.queIsAns = queIsAns;
	}

	public String getQueShow() {
		return queShow;
	}

	public void setQueShow(String queShow) {
		this.queShow = queShow;
	}

	public int getQueSeq() {
		return queSeq;
	}

	public void setQueSeq(int queSeq) {
		this.queSeq = queSeq;
	}

	public int getQueLevel() {
		return queLevel;
	}

	public void setQueLevel(int queLevel) {
		this.queLevel = queLevel;
	}

	public String getQueInputType() {
		return queInputType;
	}

	public void setQueInputType(String queInputType) {
		this.queInputType = queInputType;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", queTitle=" + queTitle + ", queType=" + queType + ", queIsAns=" + queIsAns
				+ ", queShow=" + queShow + ", queSeq=" + queSeq + ", queLevel=" + queLevel + ", queInputType="
				+ queInputType + "]";
	}

}
