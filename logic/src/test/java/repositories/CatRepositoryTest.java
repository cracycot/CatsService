package repositories;

import models.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

public class CatRepositoryTest {
    @Autowired
    CatRepository catRepository;
    @BeforeEach
    public void test() {

    }

    public void setCatRepository(CatRepository catRepository) {
        this.catRepository = catRepository;
    }


    @Test
    public void getAllCats() {

    }

    @Test
    public void getCatsFilter() {
        ArrayList<Cat> catArrayList = (ArrayList<Cat>) catRepository.sortByAge();
        boolean flag = true;
        if (catArrayList.isEmpty()) {
            flag = false;
        }
        for (int i  = 0; i < catArrayList.size() - 1; i ++) {
            if (catArrayList.get(i).getDateBirth().isAfter(catArrayList.get(i + 1).getDateBirth())) {
                flag = false;
            }
        }
        Assertions.assertTrue(flag);
    }
}
