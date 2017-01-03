package hello.session;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
    Iterable<Person> findByLastName(@Param("name") String name);
}
