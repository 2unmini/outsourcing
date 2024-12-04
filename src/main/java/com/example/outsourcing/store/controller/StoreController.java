package com.example.outsourcing.store.controller;

import com.example.outsourcing.store.dto.OpenedStoreRequestDto;
import com.example.outsourcing.store.dto.OpenedStoreResponseDto;
import com.example.outsourcing.store.dto.StoreDetailInfoResponseDto;
import com.example.outsourcing.store.dto.StoreInfoResponseDto;
import com.example.outsourcing.store.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 가게 창업
    @PostMapping
    public ResponseEntity<OpenedStoreResponseDto> openStore(@Validated @RequestBody OpenedStoreRequestDto openedStoreRequestDto, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("id");
        OpenedStoreResponseDto openedStoreResponseDto = storeService.open(userId, openedStoreRequestDto);
        return new ResponseEntity<>(openedStoreResponseDto, HttpStatus.CREATED);

    }
    // 가게 전체 조회
    @GetMapping
    public ResponseEntity<List<StoreInfoResponseDto>> showStore() {
        List<StoreInfoResponseDto> storeInfoResponseDtoList = storeService.showStoreList();
        return new ResponseEntity<>(storeInfoResponseDtoList,HttpStatus.OK);
    }
    // 가게 검색 조회
    @GetMapping("/search")
    public ResponseEntity<List<StoreInfoResponseDto>> researchStore(@RequestParam String text) {
        List<StoreInfoResponseDto> storeInfoResponseDtoList = storeService.searchStoreList(text);
        return new ResponseEntity<>(storeInfoResponseDtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDetailInfoResponseDto> StoreDetailInfoResponseDto(@PathVariable Long id) {
        StoreDetailInfoResponseDto storeDetailInfoResponseDto = storeService.showDetailStore(id);
        return new ResponseEntity<>(storeDetailInfoResponseDto,HttpStatus.OK);

    }
}
