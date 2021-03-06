import domain.UserServiceArray;
import domain.UserServiceH2;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Created by Paul on 15.10.2015.
 */
public class Main {

    @Inject @Default
    private UserServiceArray serviceArray;

    @Inject @Default
    private UserServiceH2 serviceH2;

    public void callService() {
        serviceArray.execute();
        serviceH2.execute();
    }

    public static void main(String[] args) {

        WeldContainer container = new Weld().initialize();
        Instance<Main> main = container.instance().select(Main.class);
        main.get().callService();
        container.destroy(main);
        container.close();
    }
}
