package org.crealytics.test.unit;

import org.crealytics.repository.AdRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoTest{

    @Autowired
    AdRepository repo;


    @Test
    public void repoAutoWired() {
        Assert.assertNotNull(repo);
    }

}
