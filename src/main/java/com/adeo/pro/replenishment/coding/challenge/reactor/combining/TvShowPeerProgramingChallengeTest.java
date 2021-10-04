package com.adeo.pro.replenishment.coding.challenge.reactor.combining;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class TvShowPeerProgramingChallengeTest {

	static Long PAGE_NUMBER = 0L;
	static Long PAGE_SIZE = 10L;
	
	static WebClient webClient = WebClient.getInstance();
	
	/**
	 * @see {@link WebClient#countTvShows()}
	 * @see {@link WebClient#getManyTvShows(Long, Long)}
	 * @see {@link PagedTvShow}
	 * @see {@link PageMetadata}
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("TV SHOWS :");
		
		/*
		 * I wish a page of TvShows with :
		 * - The content = 5 items
		 * - Page number = 0
		 * - Page size = 5
		 * You can implement the `getPageOfTvShows` method in the WebClient class.
		 */
		
		//FIXME : Your code...
		
		
		
		/*
		 * I wish a page of TvShows enriched with the ratings data.
		 */
		
		//FIXME : Your code...
		
		
	}
		
	/*
	 * Your different methods.
	 */
	
	
	/*
	 * The embedded library.
	 */
	
	static class WebClient {
		
		/**
		 * @param id
		 * @return {@link TvShow flow of TV show}
		 */
		public Mono<TvShow> getTvShowById(Integer id) {
			// @formatter:off
			return DataInMemory.fluxOfTvShow.
								filter(tvShow -> tvShow.getId().equals(id)).
								singleOrEmpty();
			// @formatter:on
		}
		
		/**
		 * @param ids
		 * @return {@link TvShow flow of TV shows}
		 */
		public Flux<TvShow> getTvShowsByIds(List<Integer> ids) {
			// @formatter:off
			return DataInMemory.fluxOfTvShow.
								filter(tvShow -> ids.contains(tvShow.getId()));
			// @formatter:on
		}
		
		/**
		 * @return {@link TvShow flow of TV shows}
		 */
		public Flux<TvShow> getManyTvShows(Long pageNumber, Long pageSize) {
			// @formatter:off
			return DataInMemory.fluxOfTvShow.
					skip(pageNumber * pageSize).
					take(pageSize);
			// @formatter:on
		}
		
		/**
		 * @return {@link TvShow flow of TV shows}
		 */
		public Flux<TvShow> getManyTvShows() {
			return DataInMemory.fluxOfTvShow;
		}
		
		/**
		 * @param limit
		 * @return {@link TvShow flow of TV shows}
		 */
		public Flux<TvShow> getManyTvShows(Long limit) {
			// @formatter:off
			return DataInMemory.fluxOfTvShow.
								take(limit);
			// @formatter:on
		}
		
		//FIXME : getPageOfTvShows(Long, Long) implementation
		
		/**
		 * @return {@link Long number of TV Shows}
		 */
		public Mono<Long> countTvShows() {
			// @formatter:off
			return DataInMemory.fluxOfTvShow.
								count();
			// @formatter:on
		}
		
		/**
		 * @return {@link Rating flow of ratings}
		 */
		public Flux<Rating> getManyRatings() {
			return DataInMemory.fluxOfRating;
		}
		
		/**
		 * @param limit
		 * @return {@link Rating flow of ratings}
		 */
		public Flux<Rating> getManyRatings(Long limit) {
			// @formatter:off
			return DataInMemory.fluxOfRating.
								take(limit);
			// @formatter:on
		}
		
		/**
		 * @param tvShowId
		 * @return @return {@link Rating flow of ratings}
		 */
		public Flux<Rating> getManyRatingsByTvShowId(Integer tvShowId) {
			
			log.info(" > Get many ratings by tv show id {}.", tvShowId);
			
			// @formatter:off
			return DataInMemory.fluxOfRating.
								filter(rating -> rating.getTvShowId().equals(tvShowId));
			// @formatter:on
		}
		
		/**
		 * @param tvShowIds
		 * @return @return {@link Rating flow of ratings}
		 */
		public Flux<Rating> getManyRatingsByTvShowIds(List<Integer> tvShowIds) {
			
			log.info(" > Get many ratings by tv show ids {}.", tvShowIds.toString());
			
			// @formatter:off
			return DataInMemory.fluxOfRating.
								filter(rating -> tvShowIds.contains(rating.getTvShowId()));
			// @formatter:on
		}
		
		public static WebClient getInstance() {
			return new WebClient();
		}
		
	}
	
	public static class DataInMemory {
		
		static Random random = new Random();
		static Integer maxNumberRating = 200;
		
		// @formatter:off
		static Flux<TvShow> fluxOfTvShow = Flux.range(1, TitleEnum.values().length).
										map(id -> TvShow.builder().id(id).title(TitleEnum.values()[id-1].name()).build());
		
		static Flux<Rating> fluxOfRating = Flux.range(1, TitleEnum.values().length).
										map(id -> Stream.iterate(1, i -> i <= maxNumberRating, i -> ++i).
													map(i -> Rating.builder().tvShowId(id).grade(random.nextInt(6)).build())
										).
										flatMap(stream -> Flux.fromStream(stream));
		// @formatter:on
	}
	
	@Data @AllArgsConstructor @Builder 
	static class TvShow {
		
		private Integer id;
		private @Exclude String title;
		private @Exclude Double average;
		private @Exclude Integer min;
		private @Exclude Integer max;
		private @Exclude Long count;
	}
	
	@Data @AllArgsConstructor @Builder
	static class Rating {
		
		private Integer tvShowId;
		private Integer grade;
	}
	
	@Data @AllArgsConstructor @Builder
	static class PagedTvShow {
		
		private List<TvShow> content;
		private PageMetadata pagination;
	}
	
	@Data @AllArgsConstructor @Builder
	static class PageMetadata {
		private Long number;
		private Long size;
		private Long totalElements;
		private Long totalPages;
		
		/**
		 * @param page
		 * @param size
		 * @param count
		 * @return {@link PageMetadata}
		 */
		public static PageMetadata pageMetadataBuild(Long page, Long size, Long count) {
			// @formatter:off
			return PageMetadata.builder().
					number(page).
					size(size).
					totalElements(count).
					totalPages(count < size ? 1L : count % size == 0L ? count / size : (count / size) + 1L).
					build();
			// @formatter:on
		}
	}
	
	static enum TitleEnum {
		
		GOOD_WILL_HUNTING,
		PULP_FICTION,
		FINDING_YOU,
		CRUELLA,
		THE_CONJURING,
		WRATH_OF_MAN,
		SPIRAL,
		BLACK_WIDOW,
		NOBODY,
		MY_NAME_IS_NOBODY,
		DIE_HARD,
		LETHAL_WEAPON,
		THE_SILENCE_OF_THE_LAMBS,
		GROUNDHOG_DAY,
		STAR_WARS,
		ALIEN,
		BACK_TO_THE_FUTURE,
		TITANIC,
		MATRIX,
		MONSTER_HUNTER,
		WONDER_WOMAN,
		BIOMAN,
		THE_CROODS,
		BAD_BOYS,
		BOYS_STATE,
		BLOODSHOT,
		MULAN,
		GRETEL_AND_HANSEL,
		THE_HUNT,
		LOST_GIRLS,
		THE_MEG,
		SWEET_GIRL,
		BECKETT,
		THE_MIST,
		CODE_MERCURY,
		JURASSIC_WORLD,
		BLACK_SUMMER,
		AWAKE,
		EQUALIZER,
		BLOOD_DIAMOND;
	}
}
