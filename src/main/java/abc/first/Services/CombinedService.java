package abc.first.Services;

import abc.first.Domain.CombinedObject;
public interface CombinedService {

    String save(CombinedObject combinedObject);
    CombinedObject getCombinedDomain(Long phoneNumber);
    String delete(Long id);
    String update(CombinedObject combinedObject);
}
