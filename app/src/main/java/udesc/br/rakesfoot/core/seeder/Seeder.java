package udesc.br.rakesfoot.core.seeder;

/**
 * Created by felic on 31/10/2016.
 */
public interface Seeder<ParentModel> {

    public void seed(ParentModel parent);

    public void crop(ParentModel parent);


}
