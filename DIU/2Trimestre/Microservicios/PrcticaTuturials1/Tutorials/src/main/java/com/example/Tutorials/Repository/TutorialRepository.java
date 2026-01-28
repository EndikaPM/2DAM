package com.example.Tutorials.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import com.example.Tutorials.Model.TutoarialsVO;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends MongoRepository<TutoarialsVO, String>{
    List<TutoarialsVO> findByTitleContaining(String title);
    List<TutoarialsVO> findByPublished(boolean published);
}
