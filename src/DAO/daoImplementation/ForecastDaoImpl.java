package DAO.daoImplementation;

public class ForecastDaoImpl {
    private static ForecastDaoImpl INSTANCE;

    private ForecastDaoImpl() {}

    public static ForecastDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (ForecastDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ForecastDaoImpl();
                }
            }
        }
        return INSTANCE;
    }
}
