package com.delightedDiary.data.repositories;

import com.delightedDiary.data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryRepositoryImplTest {
    private EntryRepository entryRepository;

    @BeforeEach
    private void setUp(){
        entryRepository = new EntryRepositoryImpl();
    }
    @Test
    public void whenICreateEntryItsSavedTest(){
        Entry entry = new Entry();
        entryRepository.save(entry);
        assertEquals(1,entryRepository.count());
    }
    @Test
    public void whenICreatedTwoEntriesISShouldBeSaved(){
        Entry entry = new Entry();
        entryRepository.save(entry);
        Entry entry1 = new Entry();
        entryRepository.save(entry1);
        assertEquals(2,entryRepository.count());
    }
    @Test
    public void testThatTheEntryIdWhenICreatTwoEntries(){
        Entry entry = new Entry();
        entryRepository.save(entry);
        assertEquals(1,entry.getId());
        Entry entry1 = new Entry();
        entryRepository.save(entry1);
        assertEquals(2,entry1.getId());
    }
    @Test
    public void whenICreatTwoDifferentEntry_EntryCanBeFoundByItId(){
        Entry entryOne = new Entry();
        entryRepository.save(entryOne);
        Entry entryTwo = new Entry();
        entryRepository.save(entryTwo);
        assertEquals(2,entryRepository.count());
        assertEquals(entryOne,entryRepository.findById(1));
        assertEquals(entryTwo,entryRepository.findById(2));

    }
    @Test
    public void whenEntriesAreCreatedAllEntriesCanBeFoundAtOnce(){
        Entry entryOne = new Entry();
        entryRepository.save(entryOne);
        Entry entryTwo = new Entry();
        entryRepository.save(entryTwo);
        Entry entryThree = new Entry();
        entryRepository.save(entryThree);
        assertEquals(3,entryRepository.findAll().size());

    }
    @Test
    public void testThatEntryCanBeDeletedById(){
        Entry entryOne = new Entry();
        entryRepository.save(entryOne);
        Entry entryTwo = new Entry();
        entryRepository.save(entryTwo);
        Entry entryThree = new Entry();
        entryRepository.save(entryThree);
        assertEquals(3,entryRepository.count());
        entryRepository.delete(2);
        assertEquals(2,entryRepository.count());
        assertNull(entryRepository.findById(2));

    }
    @Test public void testThatEntryCanBeDeletedByEntry(){
        Entry entryOne = new Entry();
        entryRepository.save(entryOne);
        Entry entryTwo = new Entry();
        entryRepository.save(entryTwo);
        Entry entryThree = new Entry();
        entryRepository.save(entryThree);
        assertEquals(3,entryRepository.count());
        entryRepository.delete(entryOne);
        assertEquals(2,entryRepository.count());
        assertNull(entryRepository.findById(1));
    }
    @Test public void testThatWhenICreateThreeEntriesAndClearAllEntriesWouldIsRemove(){
        Entry entryOne = new Entry();
        entryRepository.save(entryOne);
        Entry entryTwo = new Entry();
        entryRepository.save(entryTwo);
        Entry entryThree = new Entry();
        entryRepository.save(entryThree);
        assertEquals(3,entryRepository.count());
        entryRepository.clear();
        assertEquals(0,entryRepository.count());
        assertNull(entryRepository.findById(1));
        assertNull(entryRepository.findById(2));
        assertNull(entryRepository.findById(3));



    }


}