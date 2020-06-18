package edu.miu.cs425.eCarRental;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractECarRentalComponentTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
