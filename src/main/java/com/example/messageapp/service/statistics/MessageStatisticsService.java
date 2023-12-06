package com.example.messageapp.service.statistics;

import com.example.messageapp.controller.statistics.UserMessageStatistics;

import java.util.List;

public interface MessageStatisticsService {
    List<UserMessageStatistics> getAllUserMessageStatistics();
}
