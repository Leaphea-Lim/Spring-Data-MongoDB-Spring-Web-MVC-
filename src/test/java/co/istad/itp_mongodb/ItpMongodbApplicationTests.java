package co.istad.itp_mongodb;

import co.istad.itp_mongodb.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItpMongodbApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
	void testFindUserByName_Query() {
        System.out.println(userRepository.filterByName("Leaphea Lim"));
	}

    @Test
    void testFilter_Query() {
        System.out.println(userRepository.filter(27,"Phnom Penh"));
    }

}
