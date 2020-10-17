package com.example.vocabuilder.services;

import com.example.vocabuilder.entities.Word;
import com.example.vocabuilder.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository ;

    // get all words
    public List<Word> getWords(){
        List<Word> allWords = new ArrayList<>() ;
        wordRepository.findAll().forEach(word->allWords.add(word));
        return allWords ;
    }

    // get a specific word by id
    public Word getWordById(long wordId){
        Word word = null ;
        try{
            word = wordRepository.findById(wordId).get() ;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return word ;
    }

    // add a new word
    public Word addWord(Word word){
       Word _word = null ;
       try{
           _word = wordRepository.save(word) ;
       }
       catch (Exception ex){
           ex.printStackTrace();
       }
       return _word ;
    }

    //update a word
    public Word updateWord(long wordId, Word word){
        return wordRepository.findById(wordId).map(updatedWord ->{
            updatedWord.setWordId(wordId);
            updatedWord.setWord(word.getWord());
            updatedWord.setMeaning(word.getMeaning());
            updatedWord.setSentence(word.getSentence());

            return wordRepository.save(updatedWord) ;
        }).orElseGet(() ->{
            word.setWordId(wordId);
            return wordRepository.save(word) ;
        }) ;
    }

    //delete a word
    public void deleteWord(long wordId){
        wordRepository.deleteById(wordId);
    }
}
