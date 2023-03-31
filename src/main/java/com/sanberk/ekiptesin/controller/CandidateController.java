package com.sanberk.ekiptesin.controller;

import com.sanberk.ekiptesin.model.Candidate;
import com.sanberk.ekiptesin.repositories.CandidateRepository;
import exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static exception.message.Error.CANDIDATE_NOT_FOUND_EXCEPTION;

@RestController
@RequestMapping("/candidate")
@AllArgsConstructor
public class CandidateController {

    CandidateRepository candidateRepository;

    @GetMapping()
    public List<Candidate> getAllCandidates(){
        return candidateRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getACandidateById(@PathVariable Long id){
        return ResponseEntity.ok(candidateRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(CANDIDATE_NOT_FOUND_EXCEPTION,id))));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidateById(@PathVariable Long id){
        if (candidateRepository.findById(id).isPresent()) {
            candidateRepository.deleteById(id);
            return ResponseEntity.ok("Candidate Deleted Successfully");
        }else{
            return ResponseEntity.status(404).body("There is no candidate with this " + id);
        }

    }

    @PostMapping("/new")
    public ResponseEntity<?> addACandidate(@RequestBody Candidate candidate){
        if (candidateRepository.findByEmail(candidate.getEmail()).isEmpty()) {
            candidateRepository.save(candidate);
            return ResponseEntity.ok(candidate);
        }else{
            return ResponseEntity.status(404).body("THERE EXIST A CANDIDATE WITH THIS " + candidate.getEmail());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateACandidate(@PathVariable Long id , @RequestBody Candidate candidate){

        if(candidateRepository.findByEmail(candidate.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("This e-mail is already registered with another user "+ candidate.getEmail());
        }
        Candidate cDB = candidateRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(CANDIDATE_NOT_FOUND_EXCEPTION,id)));

        cDB.setFullName(candidate.getFullName());
        cDB.setGender(candidate.getGender());
        cDB.setEmail(candidate.getEmail());
        cDB.setStatus(candidate.getStatus());
        cDB.setPhone(candidate.getPhone());

        candidateRepository.save(cDB);

        return ResponseEntity.ok("New Candidate Registered Successfully");
    }


}
