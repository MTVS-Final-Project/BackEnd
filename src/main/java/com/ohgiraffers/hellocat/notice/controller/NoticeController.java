package com.ohgiraffers.hellocat.notice.controller;

import com.ohgiraffers.hellocat.notice.dto.NoticeResponseDto;
import com.ohgiraffers.hellocat.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "공지/이벤트 API", description = "공지 및 이벤트 관련 API")
@Slf4j
@RestController
@RequestMapping("/api/v1/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    @Operation(summary = "공지 조회", description = "전체 공지를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
    })
    public ResponseEntity<List<NoticeResponseDto>> findAllNotice() {

        List<NoticeResponseDto> noticeList = noticeService.findAllNotice();

        return ResponseEntity.status(OK).body(noticeList);
    }

    @GetMapping("/{noticeId}")
    @Operation(summary = "공지 단일 조회", description = "특정 공지사항을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "공지 조회 성공, 공지가 없는 경우 빈 리스트 반환"),
            @ApiResponse(responseCode = "404", description = "공지를 찾을 수 없습니다."),
    })
    public ResponseEntity<NoticeResponseDto> findNoticeById(@PathVariable("noticeId") Long noticeId) {

        NoticeResponseDto foundNotice = noticeService.findNoticeById(noticeId);

        return ResponseEntity.status(OK).body(foundNotice);
    }
}
