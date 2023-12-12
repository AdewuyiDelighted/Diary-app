package com.delightedDiary.services;

import com.delightedDiary.exceptions.InvalidDetailsExceptions;
import com.delightedDiary.exceptions.UserExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImplTest {
    private DiaryService diaryService;

    @BeforeEach
    public void startAllWith() {
        diaryService = new DiaryServiceImpl();
    }

    @Test
    public void testThatWhenIRegisterAUserOnceIfITryToRegisterAgainAnExceptionWouldBeThrowed() {
        diaryService.register("Delighted", "password");
        assertThrows(UserExistException.class, () -> diaryService.register("Delighted", "password"));

    }

    @Test
    public void testThatRegisterANewUser_NewUserTriedToLogInWithWrongPassword() {
        diaryService.register("Delighted", "password");
        assertThrows(InvalidDetailsExceptions.class, () -> diaryService.login("Delighted", "wrongPassword"));
    }

    @Test
    public void testThatRegisterANewUser_NewUserTriedToLogInWithWrongUsername() {
        diaryService.register("Delighted", "password");
        assertThrows(InvalidDetailsExceptions.class, () -> diaryService.login("Delight", "password"));
    }

    @Test
    public void loginWithRightDetails_and_diaryIsUnlockedTest() {
        diaryService.register("Delighted", "password");
        boolean isLocked = diaryService.findDiaryBelongingTo("Delighted").isLocked();
        assertTrue(isLocked);
        diaryService.login("Delighted", "password");
        isLocked = diaryService.findDiaryBelongingTo("Delighted").isLocked();
        assertFalse(isLocked);
    }

    @Test
    public void whenUserRegister_enterTheRightDetailToLogin_andCreateEntryTest() {
        diaryService.register("Delighted", "password");
        diaryService.login("Delighted", "password");
        diaryService.writeOn("Delighted", "myTitle", "Maven");
        assertEquals(1, diaryService.findEntriesBelongingTo("Delighted").size());

    }
    @Test
    public void whenUserRegister_enterTheRightDetailToLogin_andCreateTwoEntriesTest() {
        diaryService.register("Delighted", "password");
        diaryService.login("Delighted", "password");
        diaryService.writeOn("Delighted", "myTitle", "Maven");
        diaryService.writeOn("Delighted", "mySecondEntry", "Dependency");
        assertEquals(2, diaryService.findEntriesBelongingTo("Delighted").size());
    }
    @Test
    public void whenUserRegister_enterTheRightDetailToLogin_andCreateThreeEntryTest() {
        diaryService.register("Delighted", "password");
        diaryService.login("Delighted", "password");
        diaryService.writeOn("Delighted", "myTitle", "Maven");
        diaryService.writeOn("Delighted", "mySecondEntry", "Dependency");
        diaryService.writeOn("Delighted","myThirdEntry","pom.xml");
        assertEquals(3, diaryService.findEntriesBelongingTo("Delighted").size());

    }



}