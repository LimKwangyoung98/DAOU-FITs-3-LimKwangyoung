package org.example.boardproject.service;

import org.example.boardproject.vo.BoardVO;
import org.example.boardproject.vo.MemberVO;

import java.util.List;
import java.util.Map;

public interface BoardService {
    public List<BoardVO> getAll();
    BoardVO getById(String boardId);
    void createBoard(Map<String, Object> map);
}
