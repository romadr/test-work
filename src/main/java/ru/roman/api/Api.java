package ru.roman.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import ru.roman.dto.StackExchangeResponseDto;
import ru.roman.services.StackExchangeService;

@RestController
@RequestMapping("/api")
public class Api extends ApiBase {

    @Autowired
    StackExchangeService stackExchangeService;

    @RequestMapping(value = "/helloWord", method = RequestMethod.GET)
    public DeferredResult<String> get() {
        final DeferredResult<String> result = new DeferredResult<>();
        result.setResult("Hello word!(API)");
        return result;
    }

    @RequestMapping(value = "/request", method = RequestMethod.GET, produces = "application/json")
    public DeferredResult<StackExchangeResponseDto> request() throws Exception{
        final DeferredResult<StackExchangeResponseDto> result = new DeferredResult<>();
        result.setResult(stackExchangeService.search());
        return result;
    }

}

