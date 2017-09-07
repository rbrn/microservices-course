package ro.microservices.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ro.microservices.store.entities.Category;
import ro.microservices.store.entities.Product;

import java.util.List;


@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
