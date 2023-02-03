package board.spring.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardservice")
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO dao;

	@Override
	public int getTotalBoard() {
		return dao.getTotalBoard();
	}

	@Override
	public List<BoardDTO> getBoardList(int limit) {

		return dao.getBoardList(limit);
	}

	@Override
	public BoardDTO getOneBoard(int seq) {
		dao.updateViewCount(seq);
		return dao.getOneBoard(seq);
	}

	@Override
	public void insertOneBoard(BoardDTO dto) {
		dao.insertOneBoard(dto);
	}

//	@Override
//	public void updateViewCount(int viewcount) {
//
//		dao.updateViewCount(viewcount);
//	}

}
