package web.cafe;

import model.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import repository.CafeRepository;
import web.SecurityUtil;

import java.util.List;

@Controller
public class CafeRestController {
    private final CafeRepository repository;

    @Autowired
    public CafeRestController(CafeRepository repository){
        this.repository = repository;
    }

    public List<Cafe> getAll(){
        int userId = SecurityUtil.authUserId();
        return repository.getAll(userId);
    }


}
