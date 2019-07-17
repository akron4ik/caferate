package web.cafe;

import model.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import repository.datajpa.CrudCafeRepository;
import web.SecurityUtil;

import java.util.List;


public class CafeRestController {
    /*private final CrudCafeRepository repository;

    @Autowired
    public CafeRestController(CrudCafeRepository repository){
        this.repository = repository;
    }

    public List<Cafe> getAll(){
        int userId = SecurityUtil.authUserId();
        return repository.getAll();
    }
*/

}
