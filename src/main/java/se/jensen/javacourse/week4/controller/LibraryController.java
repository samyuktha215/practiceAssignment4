package se.jensen.javacourse.week4.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.javacourse.week4.model.Artist;
import se.jensen.javacourse.week4.model.Track;
import se.jensen.javacourse.week4.service.LibraryService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")

public class LibraryController {
    @Autowired
    LibraryService libraryService;

    @GetMapping("/artists")
    public Object getArtists(HttpServletRequest req) {
        boolean namesonly = "true".equals(req.getParameter("namesOnly"));
        if (namesonly)
            return libraryService.getArtistsNamesOnly();

        return libraryService.getArtists();
    }

    @PostMapping("/artists")
    public ResponseEntity postartists(@RequestBody Artist artist) {
        int res = libraryService.createArtist(artist);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @PutMapping("/artists/{id}")
    public ResponseEntity putartists(@PathVariable("id") Integer id, @RequestBody Artist artist) {
        int res = libraryService.updateArtist(id, artist);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/artists/{id}")
    public void deleteartists(@PathVariable("id") Integer id) {
        libraryService.deleteArtist(id);
    }
    @GetMapping("/tracks")
    public List<Track> getTracks(){
        return libraryService.getTracks();
    }
    @PostMapping("/artist/{artistid}/tracks")
    public ResponseEntity postTrack(@PathVariable("artistid") Integer artistid,@RequestBody Track track){
        int res= libraryService.addTrack(artistid,track);
        return new ResponseEntity(HttpStatus.CREATED);

    }
}