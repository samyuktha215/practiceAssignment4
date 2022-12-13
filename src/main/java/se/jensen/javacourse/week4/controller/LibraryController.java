package se.jensen.javacourse.week4.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.javacourse.week4.model.Artist;
import se.jensen.javacourse.week4.service.LibraryService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")

public class LibraryController
{
    @Autowired
    LibraryService libraryService;
    @GetMapping("/artists")
    public Object getArtists(HttpServletRequest req){
        boolean namesonly="true".equals(req.getParameter("namesOnly"));
    if(namesonly)
     return libraryService.getArtistsNamesOnly();

        return libraryService.getArtists() ;
    }
    @PostMapping("/artists")
    public ResponseEntity postartists(@RequestBody Artist artist){
        int res= libraryService.createArtist(artist);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    @PutMapping("/artists/{id}")
    public ResponseEntity putartists(@PathVariable("id")Integer id,@RequestBody Artist artist){
        int res= libraryService.updateArtist(id,artist);
        return new ResponseEntity(HttpStatus.OK);
    }

}
