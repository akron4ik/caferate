package repository.datajpa;

import model.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaCafeRepository {

   @Autowired
   private CrudCafeRepository crudRepository;


    public Cafe save(Cafe cafe) {
        return crudRepository.save(cafe);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public Cafe get(int id) {
        return crudRepository.get(id);
    }

    public List<Cafe> getAll() {
        return crudRepository.getAll();
    }
}
