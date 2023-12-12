package data.repositories;

import data.models.Diary;
import data.models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImpl implements EntryRepository {
    private int count;
    private List<Entry> entries = new ArrayList<>();

    @Override
    public Entry save(Entry entry) {
        if (isNew(entry)) createNew(entry);
        else update(entry);
        return entry;
    }

    private void update(Entry entry) {
        Entry oldEntry = findById(entry.getId());
        entries.remove(oldEntry);
        entries.add(entry);
    }

    private void createNew(Entry entry) {
        count++;
        entry.setId(generateId());
        entries.add(entry);
    }

    private int generateId() {
        return count;
    }

    private boolean isNew(Entry entry) {
        if (entry.getId() == 0) return true;
        return false;
    }

    @Override
    public List<Entry> findAll() {
        return entries;
    }

    @Override
    public Entry findById(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) return entry;
        }
        return null;
    }

    @Override
    public Entry findByEntryIdAndDiaryId(int entryId, int diaryId) {
        //Diary newDiary = findById(diaryId);

        return null;
    }

    @Override
    public void delete(int id) {
        for (Entry entry : entries) {
            if (entry.getId() == id) {
                entries.remove(entry);
                count--;
            }
        }

    }

    @Override
    public void delete(Entry entry) {
        entries.remove(entry);
        count--;
    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public void clear() {
       entries.clear();
       count = 0;


    }
}