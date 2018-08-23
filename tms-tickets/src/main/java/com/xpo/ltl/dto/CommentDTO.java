package com.xpo.ltl.dto;

import java.io.Serializable;

public class CommentDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long commentId;
	private String crteById;
	private String crteByName;
	private long crteTmst;
	private String text;
	private long updtTmst;

	public long getCommentId()
	{
		return commentId;
	}

	public void setCommentId(final long commentId)
	{
		this.commentId = commentId;
	}

	public String getCrteById()
	{
		return crteById;
	}

	public void setCrteById(final String crteById)
	{
		this.crteById = crteById;
	}

	public String getCrteByName()
	{
		return crteByName;
	}

	public void setCrteByName(final String crteByName)
	{
		this.crteByName = crteByName;
	}

	public long getCrteTmst()
	{
		return crteTmst;
	}

	public void setCrteTmst(final long crteTmst)
	{
		this.crteTmst = crteTmst;
	}

	public String getText()
	{
		return text;
	}

	public void setText(final String text)
	{
		this.text = text;
	}

	public long getUpdtTmst()
	{
		return updtTmst;
	}

	public void setUpdtTmst(final long updtTmst)
	{
		this.updtTmst = updtTmst;
	}
}
