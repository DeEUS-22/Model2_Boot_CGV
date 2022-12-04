package com.springboot.mycgv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.mycgv.dto.MovieDto;
import com.springboot.mycgv.dto.PageDto;

@Mapper
public interface MovieMapper {

	List<MovieDto> getMovieList(PageDto pageDto);
	MovieDto getMovieContent(String mid);
	int getMovieInsert(MovieDto movieDto);
	int getMovieInsertFile(MovieDto movieDto);

	
}
