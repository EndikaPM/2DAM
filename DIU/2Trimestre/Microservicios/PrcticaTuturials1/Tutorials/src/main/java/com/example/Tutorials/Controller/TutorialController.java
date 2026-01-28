package com.example.Tutorials.Controller;

import com.example.Tutorials.Model.TutoarialsVO;
import com.example.Tutorials.Repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class TutorialController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public List<TutoarialsVO> getAllTutorials(@RequestParam(required = false) String title) {
        if (title == null) {
            return tutorialRepository.findAll();
        } else {
            return tutorialRepository.findByTitleContaining(title);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<TutoarialsVO> createTutorial(@RequestBody TutoarialsVO tutorial) {
        try {
            TutoarialsVO _tutorial = tutorialRepository.save(new TutoarialsVO(null, tutorial.getTitle(), tutorial.getDescription(), false));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<TutoarialsVO> updateTutorial(@PathVariable("id") String id, @RequestBody TutoarialsVO tutorial) {
        Optional<TutoarialsVO> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            TutoarialsVO _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("tutorials/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable("id") String id) {
        tutorialRepository.deleteById(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}