package ru.roman.mappers;

import org.springframework.stereotype.Service;
import ru.roman.dto.QuestionDto;
import ru.roman.dto.SearchResponseDto;
import ru.roman.dto.stack.exchange.ItemDto;
import ru.roman.dto.stack.exchange.StackExchangeResponseDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class SearchMapper {
    public SearchResponseDto fromStackExchangeResponse(final StackExchangeResponseDto data) {
        final SearchResponseDto res = new SearchResponseDto();
        res.quotaMax = data.quotaMax;
        res.quotaRemaining = data.quotaRemaining;
        res.hasMore = data.hasMore;
        res.data = new ArrayList<>();
        if (data.items != null) {
            res.data.addAll(data.items
                    .stream()
                    .filter(it -> it.owner != null)
                    .map(this::fromItemDto)
                    .collect(Collectors.toList()));
        }
        return res;
    }

    public QuestionDto fromItemDto(final ItemDto data) {
        final QuestionDto questionDto = new QuestionDto();
        questionDto.questionId = data.questionId;
        questionDto.date = data.creationDate != null ? new Timestamp(data.creationDate) : null;
        questionDto.title = data.title;
        questionDto.ownerName = data.owner.displayName;
        questionDto.url = data.link;
        questionDto.isAnswered = data.isAnswered;
        return questionDto;
    }
}
