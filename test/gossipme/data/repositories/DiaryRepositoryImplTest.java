package gossipme.data.repositories;

import gossipme.data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DiaryRepositoryImplTest {
    @Autowired
    private DiaryRepository diaryRepository;
    @BeforeEach public void startAllWith(){
        diaryRepository.deleteAll();
    }

    @Test
    public void testThatWhenANewDiaryIsCreatedItIsSaved() {
        Diary diary = new Diary();
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void testThatDiaryWhenICreateTwoDiaryCountWouldIncrease() {
        Diary diaryOne = new Diary();
        diaryRepository.save(diaryOne);
        Diary diaryTwo = new Diary();
        diaryRepository.save(diaryTwo);
        assertEquals(2, diaryRepository.count());
    }

    @Test
    public void whenICreateThreeEntriesICanFindAll() {
        Diary diaryOne = new Diary();
        diaryRepository.save(diaryOne);
        Diary diaryTwo = new Diary();
        diaryRepository.save(diaryTwo);
        Diary diaryThree = new Diary();
        diaryRepository.save(diaryThree);
        assertEquals(3, diaryRepository.findAll().size());

    }

    @Test
    public void testThatWhenISaveNewDiary_DiaryIdIsOne() {
        Diary diary = new Diary();
        assertNull( diary.getId());
        diaryRepository.save(diary);
        assertNotNull(diary.getId());
    }
    @Test public void saveUpdatedDiaryDoesNotInfluenceCountTest(){
        Diary diary = new Diary();
        diaryRepository.save(diary);
        Diary updateDiary = new Diary();
        updateDiary.setId(diary.getId());
        diaryRepository.save(updateDiary);
        assertEquals(1,diaryRepository.count());

    }
    @Test public void whenICreateDiaryAndDeleteDiaryByDiary(){
        Diary diaryOne = new Diary();
        diaryRepository.save(diaryOne);
        Diary diaryTwo = new Diary();
        diaryRepository.save(diaryTwo);
        Diary diaryThree = new Diary();
        diaryRepository.save(diaryThree);
        assertEquals(3,diaryRepository.count());
        diaryRepository.delete(diaryOne);
        assertEquals(2,diaryRepository.count());
    }
    @Test public void whenICreateDiaryAndDeleteDiaryById(){
        Diary diaryOne = new Diary();
        diaryRepository.save(diaryOne);
        Diary diaryTwo = new Diary();
        diaryRepository.save(diaryTwo);
        Diary diaryThree = new Diary();
        diaryRepository.save(diaryThree);
        assertEquals(3,diaryRepository.count());
        diaryRepository.deleteById(diaryOne.getId());
        assertEquals(2,diaryRepository.count());
    }
}