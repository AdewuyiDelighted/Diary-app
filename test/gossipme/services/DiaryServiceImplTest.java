package gossipme.services;

import gossipme.data.repositories.DiaryRepository;
import gossipme.data.repositories.EntryRepository;
import gossipme.exceptions.InvalidDetailsExceptions;
import gossipme.exceptions.UserExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiaryServiceImplTest {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private EntryRepository entryRepository;

    @BeforeEach public void startAllWith(){
        diaryRepository.deleteAll();
        entryRepository.deleteAll();
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