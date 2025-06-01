package back.Loader;
import java.util.List;
import back.Toy.Toy;

public interface Loading {
    Toy getToyById(int id);
    List<Toy> loadAll();
    List<Toy> load(int toyId);
}
