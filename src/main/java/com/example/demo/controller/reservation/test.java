package com.example.demo.controller.reservation;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class test {

	@GetMapping("/test")
	public String View() {
		return "reservation/test";
	}

    @PostMapping("/test")
    public ResponseEntity<String> updateReceptionManagement(@RequestBody Map<String, Object> changedData) {
        // データを保存する処理
        return ResponseEntity.ok("データが更新されました");
    }
}