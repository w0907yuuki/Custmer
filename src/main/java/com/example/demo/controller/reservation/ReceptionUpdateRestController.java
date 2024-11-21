package com.example.demo.controller.reservation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.reception.ReceptionUpdateInfo;


@RestController
@RequestMapping("/Reception")
public class ReceptionUpdateRestController {
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, String>> updateReceptionManagement(@RequestBody List<ReceptionUpdateInfo> changedData) {
        // 受け取ったデータをコンソールに表示
        System.out.println("Received data: " + changedData);

        // レスポンスをJSON形式で返す
        Map<String, String> response = new HashMap<>();
        response.put("message", "データが更新されました");

        return ResponseEntity.ok(response);  // JSONレスポンスを返す
    }


}
