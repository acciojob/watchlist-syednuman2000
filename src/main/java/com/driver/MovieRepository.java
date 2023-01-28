package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String, Movie> m = new HashMap<>();
    Map<String, Director> d = new HashMap<>();
    Map<String, ArrayList<String>> map = new HashMap<>();

    public void addMovie(Movie movie) {
        m.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        d.put(director.getName(), director);
        map.put(director.getName(), new ArrayList<String>());
    }

    public void addMovieDirectorPair(String movie, String director) {
        if(m.containsKey(movie) && d.containsKey(director)){
            map.get(director).add(movie);
        }
    }

    public Movie getMovieByName(String name) {
        return m.get(name);
    }

    public Director getDirectorByName(String name) {
        return d.get(name);
    }

    public List<String> getMoviesByDirectorName(String name) {
        List<String> moviesList = new ArrayList<>();
        if(map.containsKey(name)) moviesList = map.get(name);
        return moviesList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(m.keySet());
    }

    public void deleteDirector(String director){
        List<String> movies = new ArrayList<String>();
        if(map.containsKey(director)){
            movies = map.get(director);
            for(String movie: movies){
                if(m.containsKey(movie)){
                    m.remove(movie);
                }
            }

            map.remove(director);
        }

        if(d.containsKey(director)){
            d.remove(director);
        }
    }

    public void deleteAllDirector(){
        HashSet<String> moviesSet = new HashSet<String>();

        //directorMap = new HashMap<>();

        for(String director: map.keySet()){
            for(String movie: map.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(m.containsKey(movie)){
                m.remove(movie);
            }
        }
    }
}
