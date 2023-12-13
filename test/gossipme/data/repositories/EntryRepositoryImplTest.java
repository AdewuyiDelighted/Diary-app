package gossipme.data.repositories;

import gossipme.data.models.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EntryRepositoryImplTest {
   @Autowired
    private EntryRepository entryRepository;
    @BeforeEach
    public void startBeforeAllTest(){
        entryRepository.deleteAll();
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
        assertNull(entry.getId());
        entryRepository.save(entry);
        assertNotNull(entry.getId());
        Entry entry1 = new Entry();
        entryRepository.save(entry1);
        assertNotNull(entry1.getId());
    }
    @Test
    public void whenICreatTwoDifferentEntry_EntryCanBeFoundByItId(){
        Entry entryOne = new Entry();
        entryRepository.save(entryOne);
        Entry entryTwo = new Entry();
        entryRepository.save(entryTwo);
        assertEquals(2,entryRepository.count());
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
        entryRepository.delete(entryThree);
        assertEquals(2,entryRepository.count());
    }
    @Test public void testThatEntryCanBeDeletedByEntry(){
        Entry entryOne = new Entry();
        entryRepository.save(entryOne);
        Entry entryTwo = new Entry();
        entryRepository.save(entryTwo);
        Entry entryThree = new Entry();
        entryRepository.save(entryThree);
        assertEquals(3,entryRepository.count());
        entryRepository.deleteById(entryOne.getId());
        assertEquals(2,entryRepository.count());
    }
    @Test public void testThatWhenICreateThreeEntriesAndClearAllEntriesWouldIsRemove(){
        Entry entryOne = new Entry();
        entryRepository.save(entryOne);
        Entry entryTwo = new Entry();
        entryRepository.save(entryTwo);
        Entry entryThree = new Entry();
        entryRepository.save(entryThree);
        assertEquals(3,entryRepository.count());
        entryRepository.deleteAll();
        assertEquals(0,entryRepository.count());
    }


}