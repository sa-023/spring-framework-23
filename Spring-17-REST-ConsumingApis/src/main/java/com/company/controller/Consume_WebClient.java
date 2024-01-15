package com.company.controller;
import com.company.entity.Genre;
import com.company.entity.MovieCinema;
import com.company.repository.GenreRepository;
import com.company.repository.MovieCinemaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/*
 * 🖍️...
 *  🔍 Sync vs Async
 * · Synchronous (single-thread): is a blocking architecture, so the execution of each operation depends on completing the one before it.
 *   Each task requires an answer before moving on to the next iteration.
 * · Asynchronous (multi-thread): is a non-blocking architecture, so the execution of one task isn’t dependent on another. Tasks can run simultaneously.
 * 🔍 Spring WebFlux
 * · Spring WebFlux is a counterpart module for Spring MVC. Where Spring MVC implements synchronous, blocking I/O,
 *   Spring WebFlux implements reactive programming via Reactive Streams.
 * · Reactive programming is a programming paradigm where the focus is on developing asynchronous and non-blocking components.
 * 🔍 Mono and Flux
 * · In Spring WebFlux, the data returned from any operation is packed into a reactive stream. There are two types that
 *   embody this approach and are building blocks in WebFlux applications - Mono and Flux.
 * · Mono is a stream which returns zero items or a single item (0…1), whereas Flux is a stream which returns zero or more items (0…N).
 * · Mono is therefore used when you’re expecting a single (or none) result, such as retrieving a unique user from database,
 *   whereas Flux is used when you’re expecting multiple results or a collection of some sort.
 *
 *
 * 🔺 Spring WebClient
 * · WebClient is a non-blocking, reactive client to perform HTTP requests.
 * · It was created as part of the Spring Web Reactive module, and will be replacing the classic RestTemplate.
 * · Even though, it is a non-blocking client, and it belongs to the Spring Webflux library, it offers support for both synchronous and asynchronous operations.
 * · To implement WebClient in our project, we first need to add the spring-boot-starter-webflux dependency to pom.xml.
 * · To work properly with the web client, we need to know how to: · Create an instance · Make a request · Handle the response
 * · There are different options to create an instance:
 * 1. Create with the default setting: WebClient client = WebClient.create();
 * 2. Create instance with a given base url: WebClient client = WebClient.create(“http://localhost:8080");
 * 3. Create customized instance : WebClient client = WebClient.builder().baseUrl(“http://localhost:8080”).defaultCookie("cookieKey",
 *                                 “cookieValue”).defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
 *                                 .defaultUriVariables(Collections.singletonMap("url", “http://localhost:8080”)).build();
 *
 */
@RestController
public class Consume_WebClient {
    private final MovieCinemaRepository movieCinemaRepository;
    private final GenreRepository genreRepository;
    public Consume_WebClient(MovieCinemaRepository movieCinemaRepository, GenreRepository genreRepository) {
        this.movieCinemaRepository = movieCinemaRepository;
        this.genreRepository = genreRepository;
    }
    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:9090").build();



    @GetMapping("/flux-movie-cinemas")
    public Flux<MovieCinema> readAllCinemaFlux(){
        return Flux.fromIterable(movieCinemaRepository.findAll());
    }

//    @GetMapping("/mono-movie-cinema/{id}")
//    public Mono<MovieCinema> readById(@PathVariable("id") Long id){
//        return Mono.just(movieCinemaRepository.findById(id).get()); // findById() method return type is Optional, because of that we use get() method with it.
//    }

    @GetMapping("/mono-movie-cinema/{id}")
    public ResponseEntity<Mono<MovieCinema>> readById(@PathVariable("id") Long id){
        return ResponseEntity.ok(Mono.just(movieCinemaRepository.findById(id).get()));
    }

    @PostMapping("/create-genre")
    public Mono<Genre> createGenre(@RequestBody Genre genre){
        Genre createdGenre = genreRepository.save(genre);
        return Mono.just(createdGenre);
//        return Mono.just(genreRepository.save(genre));
    }

    @DeleteMapping("/delete/genre/{id}")
    public Mono<Void> deleteGenre(@PathVariable("id") Long id){
        genreRepository.deleteById(id);
        return Mono.empty(); // When there is no return type, we just return Mono.empty().
    }



/*----------------------------------------------- WEBCLIENT-------------------------------------------------------------*/

    @GetMapping("/flux")
    public Flux<MovieCinema> readWithWebClient(){
        return webClient
                .get()
                .uri("/flux-movie-cinemas")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(MovieCinema.class);
    }

    @GetMapping("/mono/{id}")
    public Mono<MovieCinema> readMonoWithWebClient(@PathVariable("id") Long id){
        return webClient
                .get() // We're making an HTTP.GET request. We can change it to post(), put(), delete(), etc.
                .uri("/mono-movie-cinema/{id}",id) // The uri() method specifies the URI that we want to consume.
                .retrieve() // The retrieve() method performs the HTTP request and retrieves the response body.
                .bodyToMono(MovieCinema.class); // The bodyToMono(Pojo.class) method maps the response of the API to the POJO class.
    }








}
