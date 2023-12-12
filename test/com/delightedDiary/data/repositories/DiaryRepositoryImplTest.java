package com.delightedDiary.data.repositories;

import com.delightedDiary.data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRepositoryImplTest {
    private DiaryRepository diaryRepository;

    @BeforeEach
    public void setUp() {
        diaryRepository = new DiaryRepositoryImpl();
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
        assertEquals(0, diary.getId());
        diaryRepository.save(diary);
        assertEquals(1, diary.getId());

    }
    @Test public void saveUpdatedDiaryDoesNotInfluenceCountTest(){
        Diary diary = new Diary();
        diaryRepository.save(diary);

        Diary updateDiary = new Diary();
        updateDiary.setId(1);
        diaryRepository.save(updateDiary);

        assertEquals(1,diaryRepository.count());
        assertEquals(updateDiary,diaryRepository.findById(1));
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
        diaryRepository.delete(1);
        assertEquals(2,diaryRepository.count());
    }





}