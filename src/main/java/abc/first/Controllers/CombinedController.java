package abc.first.Controllers;

import abc.first.Domain.BankDomain;
import abc.first.Domain.CombinedObject;
import abc.first.Services.CombinedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/combined")
public class CombinedController {

    @Autowired
    CombinedService combinedService;

    @PostMapping()
    public String save(@RequestBody CombinedObject combinedObject)
    {
        return combinedService.save(combinedObject);
    }

    @GetMapping("/{phone_num}")
    public CombinedObject getAccount(@PathVariable Long phone_num)
    {
        return combinedService.getCombinedDomain(phone_num);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id)
    {
       return combinedService.delete(id);
    }

    @PutMapping()
    public String updateCombined(@RequestBody CombinedObject combinedObject){
        return combinedService.update(combinedObject);

    }
}
