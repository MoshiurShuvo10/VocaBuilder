package com.example.vocabuilder.controllers;

import com.example.vocabuilder.entities.Word;
import com.example.vocabuilder.services.WordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/words")
public class WordController {

    private final WordService wordService ;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/")
    private ResponseEntity<List<Word>> getAllWords(){
        List<Word> wordList = wordService.getWords() ;
        if(wordList.size()==0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        return ResponseEntity.of(Optional.of(wordList)) ;
    }

    @GetMapping("/{wordId}")
    @ApiOperation(
            value = "Finds a word by id",
            notes = "Provide an Id to look up specific word from database",
            response = Word.class)

    private ResponseEntity<Word> getWordById(
            @ApiParam(value="ID value for the word you need to retrieve", required=true)
            @PathVariable("wordId") long wId){
        Word word = wordService.getWordById(wId) ;
        if(word==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build() ;
        return ResponseEntity.of(Optional.of(word)) ;
    }

    @PostMapping("/")
    @ApiOperation(
            value = "Inserts a new word",
            notes = "Put the payload to insert the word",
            response = Word.class)
    private ResponseEntity<Word> insertNewWord(@RequestBody Word word){
         Word _word ;
         try{
             _word = wordService.addWord(word);
             return ResponseEntity.of(Optional.of(_word)) ;
         }
         catch (Exception ex){
             ex.printStackTrace();
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
         }
    }

    @PutMapping("/{wordId}")
    @ApiOperation(
            value = "Updates a word",
            notes = "Provide an Id and body to update a word",
            response = Word.class)
    private ResponseEntity<Word> updateWord(
            @ApiParam(value="ID value for the word you need to update", required=true)
            @PathVariable("wordId") long wId,

            @ApiParam(value="Payload with the updated information of that word", required=true)
            @RequestBody Word word){
        Word _word ;
        try{
            _word = wordService.updateWord(wId,word);
            return ResponseEntity.of(Optional.of(_word)) ;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
        }
    }

    @DeleteMapping("/{wordId}")
    @ApiOperation(
            value = "Deletes a word",
            notes = "Provide an Id and body to delete a word")
    private ResponseEntity<Void> deleteWord(
            @ApiParam(value="ID value for the word you need to delete", required=true)
            @PathVariable("wordId") long wId){
        try{
            wordService.deleteWord(wId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
        }
    }
}
