package board.spring.mybatis;

import java.util.List;

import org.springframework.stereotype.Service;


public interface BoardService {
	//게시물 리스트 서비스
	int getTotalBoard();
	List<BoardDTO> getBoardList(int limit);
	BoardDTO getOneBoard(int seq);
	void insertOneBoard(BoardDTO dto);
	//void updateViewCount(int viewcount);
}

