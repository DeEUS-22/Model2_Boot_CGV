package com.springboot.mycgv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mycgv.dto.MovieDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.mapper.MovieMapper;

@Service
public class MovieService {
	@Autowired
	private MovieMapper movieDao;
	
	public List<MovieDto> getMovieList(PageDto pageDto){
		return movieDao.getMovieList(pageDto);
	}
	
	public MovieDto getMovieContent(String mid) {
		return movieDao.getMovieContent(mid);
	}
	
	public int getMovieInsert(MovieDto movieDto) {
		return movieDao.getMovieInsert(movieDto);
	}
	
	public int getMovieInsertFile(MovieDto movieDto) {
		return movieDao.getMovieInsertFile(movieDto);
	}

}
