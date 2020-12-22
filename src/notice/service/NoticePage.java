package notice.service;

import java.util.List;

import notice.model.Notice;

public class NoticePage {


		private int total; // 전체 게시물 수
		private int currentPage;  // 현재 페이지
		private List<Notice> content;  // article 데이터
		private int totalPages; // 총 페이지 수
		private int startPage; // 시작 페이지
		private int endPage;  // 끝 페이지
		
		public NoticePage(int total, int currentPage, int size, List<Notice> content) {
			this.total = total;
			this.currentPage = currentPage;
			this.content = content;
			
			if(total != 0) {
				this.totalPages = total / size;
				if(total % size > 0) {
					this.totalPages++;
			    }
				this.startPage = (currentPage-1) / 5 * 5 + 1;
				this.endPage = Math.min(startPage + 4, totalPages);
			}
		}
		
		public NoticePage(List<Notice> content) {
			this.content = content;
		}
		
			public int getTotal() {
				return total;
			}
			
			public boolean hasNoArticles() {
				return total == 0;
			}
			public boolean hasArticles() {
				return total > 0;
			}
			
			public int getCurrentPage() {
				return currentPage;
			}
			public int getTotalPages() {
				return totalPages;
			}
			public List<Notice> getContent() {
				return content;
			}
			public int getStartPage() {
				return startPage;
			}

			public int getEndPage() {
				return endPage;
			}

		}
		
		
