package ru.roman.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import ru.roman.dto.SearchResponseDto;
import ru.roman.services.StackExchangeService;

@RestController
@RequestMapping("/api")
public class Api extends ApiBase {

    @Autowired
    StackExchangeService stackExchangeService;

    @RequestMapping(value = "/stackExchange/search",
            params = {"text"}, method = RequestMethod.GET)
    public DeferredResult<SearchResponseDto> search(@RequestParam(value = "text", defaultValue = "java") String searchString) {
        final DeferredResult<SearchResponseDto> result = new DeferredResult<>();
        result.setResult(stackExchangeService.search(searchString));
        return result;
    }


}

