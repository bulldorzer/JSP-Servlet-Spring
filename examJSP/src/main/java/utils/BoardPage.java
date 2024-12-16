package utils;

public class BoardPage {
	public static String pagingStr(
	         int totalCount, // 전체 갯수 
	         int pageSize,  // 한 페이지당 갯수
	         int blockPage,
	            int pageNum,  // 페이지 번호
	            String reqUrl // url
	       ) {
	        String pagingStr = ""; 

	        // 단계 3 : 전체 페이지 수 계산
	        int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));
	        
	        /*
	        // int blockCount = 1;
	        int pageTemp = 1; // 현재 페이지, 기본값 = 1
	        
	        // 페이지네이션 - 태그 생성
	        while (pageTemp <= totalPages) {
	           // 현재 페이지와 출력할 페이지 번호가 같으면...
	            if (pageTemp == pageNum) { // 현재 페이지는 링크를 걸지 않음
	                // 링크x 태그 만들어서 추가
	               pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
	                
	            } else {
	               // 링크0 태그 만들어서 추가
	                pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
	                             + "'>" + pageTemp + "</a>&nbsp;";
	            }
	            pageTemp++;
	            // blockCount++;
	        }
	        */
	        
	        // 단계 4 : '이전 페이지 블록 바로가기' 출력
	        // (((현재페이지-1) /5)*5)+1; - 5개씩 숫자 생성해주는
	        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
	        if (pageTemp != 1) {
	            pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
	            pagingStr += "&nbsp;";
	            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1)
	                         + "'>[이전 블록]</a>";
	        }
	         
	   
	        // 단계 5 : 각 페이지 번호 출력
	        int blockCount = 1; // 5개씩 카운트 하는 역할
	        
	        // blockPage = 5, totalPages = 11
	        // blockCount = 페이지 번호가 5개씩 출력되는지 카운트 하는 역할 (5개짜지만 출력하도록 제한)
	        // pageTemp <= totalPages : 마지막 페이지보다 작을때 까지 반복 / 마지막 페이지 넘어가지 않도록
	        while (blockCount <= blockPage && pageTemp <= totalPages) {
	            if (pageTemp == pageNum) {
	                // 현재 페이지는 링크를 걸지 않음
	                pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
	            } else {
	                pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
	                             + "'>" + pageTemp + "</a>&nbsp;";
	            }
	            pageTemp++;
	            blockCount++;
	        }
	   
	        // 단계 6 : '다음 페이지 블록 바로가기' 출력
	        System.out.println("pageTemp, pageTemp: "+pageTemp+","+totalPages);
	        if (pageTemp <= totalPages) {
	            pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp
	                         + "'>[다음 블록]</a>";
	            pagingStr += "&nbsp;";
	            pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages
	                         + "'>[마지막 페이지]</a>";
	        }
	        return pagingStr;
	    }
}
