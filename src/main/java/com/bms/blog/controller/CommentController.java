package com.bms.blog.controller;

import com.bms.blog.dto.CommentDto;
import com.bms.blog.entity.Comment;
import com.bms.blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/board/{board_uuid}")
    public ResponseEntity<List<CommentDto>> getComment(@PathVariable(value = "board_uuid") String uuid){
        return ResponseEntity.ok(commentService.getComment(uuid));
    }

    @PostMapping
    public ResponseEntity<CommentDto> setComment(@RequestParam(value = "uuid", required = false) String uuid,
                                                 @RequestParam("board_uuid") String boardId,
                                                 @RequestParam("user_uuid") String userId,
                                                 @RequestParam("comment") String comment,
                                                 @RequestParam(value = "topComment", required = false) String topComment){
        return ResponseEntity.ok(commentService.setComment(uuid, boardId, userId, comment, topComment));
    }

    @GetMapping("/recent/{count}")
    public ResponseEntity<List<CommentDto>> getRecentBoard(@PathVariable(value = "count", required = false) int count){
        if(count==0){ count=10; }   // 초기값
        List<Comment> list = commentService.getRecentBoard(count);
        List<CommentDto> dto = new ArrayList<>();
        list.forEach(i -> dto.add(modelMapper.map(i, CommentDto.class)));
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/delete/{uuid}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable(value = "uuid") String uuid){
        return ResponseEntity.ok(modelMapper.map(commentService.deleteComment(uuid), CommentDto.class));
    }
}
