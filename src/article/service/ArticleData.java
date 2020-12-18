package article.service;

import article.model.Article;
import article.model.ArticleContent;

public class ArticleData {

	private Article article;
	private ArticleContent content;
	
	private int prevNumber;
	private int nextNumber;
	
	public ArticleData(Article article, ArticleContent content) {
		this.article = article;
		this.content = content;
	}
	
	public ArticleData(Article article, ArticleContent content, int prevNumber, int nextNumber) {
		this.article = article;
		this.content = content;
		this.prevNumber = prevNumber;
		this.nextNumber = nextNumber;
	}

	public Article getArticle() {
		return article;
	}

	public String getContent() {
		return content.getContent();
	}
	
	public int getNextNumber() {
		return nextNumber;
	}
	
	public int getPrevNumber() {
		return prevNumber;
	}
	

	
}
