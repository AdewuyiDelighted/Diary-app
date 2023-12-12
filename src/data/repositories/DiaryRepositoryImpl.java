package data.repositories;

import data.models.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository {
    private int count;
    private final List<Diary> diaries = new ArrayList<>();

    @Override
    public Diary save(Diary dairy) {
        if (isNew(dairy)) {
            createNew(dairy);
        } else {
            update(dairy);
        }
        return null;
    }

    public void update(Diary dairy) {
        Diary oldDiary = findById(dairy.getId());
        diaries.remove(oldDiary);
        diaries.add(dairy);
    }

    private void createNew(Diary dairy) {
        count++;
        dairy.setId(generateId());
        diaries.add(dairy);
    }

    private int generateId() {
        return count;
    }

    private boolean isNew(Diary dairy) {
        return dairy.getId() == 0;
    }

    @Override
    public List<Diary> findAll() {
        return diaries;
    }

    @Override
    public Diary findById(int id) {
        for (Diary diary : diaries) {
            if (diary.getId() == id) return diary;
        }
        return null;
    }

    @Override
    public Diary findByEntryIdAndDiaryId(int entryId, int diaryId) {
        return null;
    }

    @Override
    public void delete(int id) {
        Diary diary = findById(id);
        diaries.remove(diary);
        count--;
    }


    @Override
    public void delete(Diary diary) {
        diaries.remove(diary);
        count--;

    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void clear() {
        diaries.clear();
        count = 0;

    }

    @Override
    public Diary findByUsername(String username) {
        for(Diary diary : diaries){
            if(diary.getUsername().equals(username))return diary;
        }
        return null;
    }
}
