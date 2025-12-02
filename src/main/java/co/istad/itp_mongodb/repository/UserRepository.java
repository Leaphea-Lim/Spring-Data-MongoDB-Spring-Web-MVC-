package co.istad.itp_mongodb.repository;

import co.istad.itp_mongodb.domain.Users;
import co.istad.itp_mongodb.filter.FilterableRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<Users, String>, FilterableRepository<Users> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);



    //this one custom MongoDB query inside a Spring Data MongoDB repository
    @Query("{ name: {$eq: ?0}}")
    List<Users> filterByName(String name);

    @Query("{ $and: [{ city: ?0 }, {age: { $lt:  ?1 } } ]}")
    List<Users> filter(Integer age, String city);

}
