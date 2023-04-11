package com.project.recommandBook.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.recommandBook.dto.KakaoBookDto;
import com.project.recommandBook.dto.KakaoResultBookDto;
import com.project.recommandBook.dto.NaverBookDto;
import com.project.recommandBook.dto.NaverResultBookDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RecommandBookService {
    public List<NaverBookDto> SearchRecommandNaverBookList(String keyWord) {
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
        NaverResultBookDto resultBookDto = null;

        try {
            resultBookDto = objectMapper.readValue(resp.getBody(), NaverResultBookDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<NaverBookDto> bookDtoList = resultBookDto.getItems();

        return bookDtoList;
    }


    public List<KakaoBookDto> SearchRecommandKakaoBookList(String keyWord) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://dapi.kakao.com")
                .path("/v3/search/book.json")
                .queryParam("query", keyWord)
                .queryParam("total_count", 10)
                .queryParam("pageable_count", 1)
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity.get(uri)
                .header("Authorization", "KakaoAK d1adebcb92458ad4ca9afc820b3b533d")
                .build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        KakaoResultBookDto resultBookDto = null;

        try {
            resultBookDto = objectMapper.readValue(resp.getBody(), KakaoResultBookDto.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<KakaoBookDto> bookDtoList = resultBookDto.getDocuments();

        return bookDtoList;
    }
}
