package com.project.recommandBook.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.recommandBook.dto.BookDto;
import com.project.recommandBook.dto.ResultBookDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RecommandBookService {
    public List<BookDto> SearchRecommandBookList(String keyWord) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query", keyWord)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity.get(uri)
                .header("X-Naver-Client-Id", "ErSeRhKUqIUJFn_3Ldxp")
                .header("X-Naver-Client-Secret", "9QFjFoUWZn")
                .build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        ResultBookDto resultBookDto = null;

        try {
            resultBookDto = objectMapper.readValue(resp.getBody(), ResultBookDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<BookDto> bookDtoList = resultBookDto.getItems();

        return bookDtoList;
    }

}
