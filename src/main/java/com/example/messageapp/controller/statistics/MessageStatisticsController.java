package com.example.messageapp.controller.statistics;

import com.example.messageapp.service.statistics.MessageStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor
public class MessageStatisticsController {

    private final MessageStatisticsService messageStatisticsService;

    @Operation(summary = "Get all users message statistics", description = "Retrieve statistics for all users' messages.")
    @GetMapping
    public List<UserMessageStatistics> getAllUserMessageStatistics() {
        return messageStatisticsService.getAllUserMessageStatistics();
    }
}
