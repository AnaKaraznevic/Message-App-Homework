package com.example.messageapp.service.statistics;

import com.example.messageapp.controller.statistics.StatisticsMapper;
import com.example.messageapp.controller.statistics.UserMessageStatistics;
import com.example.messageapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageStatisticsServiceImpl implements MessageStatisticsService {

    private final UserRepository userRepository;

    @Override
    public List<UserMessageStatistics> getAllUserMessageStatistics() {
        List<Object[]> rawStatistics = userRepository.getAllUserMessageStatisticsRaw();
        return StatisticsMapper.mapToUserMessageStatistics(rawStatistics);
    }
}
