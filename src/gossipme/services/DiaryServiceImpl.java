package gossipme.services;

import gossipme.data.models.Diary;
import gossipme.data.models.Entry;
import gossipme.data.repositories.DiaryRepository;
import gossipme.exceptions.DiaryLockedException;
import gossipme.exceptions.InvalidDetailsExceptions;
import gossipme.exceptions.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DiaryServiceImpl implements  DiaryService{
    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    EntryService entryService ;

    @Override
    public void register(String username, String password) {
        if(userExist(username))throw new UserExistException(username + "already exist");
        Diary diary = new Diary();
        diary.setUsername(username);
        diary.setPassword(password);
        diaryRepository.save(diary);
    }

    private boolean userExist(String username) {
        Diary foundDiary = diaryRepository.findByUsername(username);
        return foundDiary != null;
    }

    @Override
    public void login(String username, String password) {
        Diary foundDiary = findDiaryBelongingTo(username);
        if(!(userExist(username)))throw new InvalidDetailsExceptions();
        if(!(foundDiary.getPassword().equals( password)))throw new InvalidDetailsExceptions();
        foundDiary.setLocked(false);
        diaryRepository.save(foundDiary);

    }

    @Override
    public Diary findDiaryBelongingTo(String username) {
        return  diaryRepository.findByUsername(username);
    }

    @Override
    public void writeOn(String username, String title, String body) {
        Diary foundDiary = diaryRepository.findByUsername(username);
        if(foundDiary == null)throw new InvalidDetailsExceptions();
        if(foundDiary.isLocked()) throw new DiaryLockedException();
        entryService.creatEntry(title,body,foundDiary.getId());
    }

    @Override
    public List<Entry> findEntriesBelongingTo(String username){
        Diary foundDiary = diaryRepository.findByUsername(username);
        List <Entry> entries = new ArrayList<>();
        for(Entry entry : entryService.findAll()){
            if(entry.getDiaryId().equals(foundDiary.getId())) entries.add(entry);
        }
        return entries;
    }
}
