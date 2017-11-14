package DAO;

public class ForecastDao {
    private static ForecastDao INSTANCE;

    private ForecastDao() {}

    public static ForecastDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ForecastDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ForecastDao();
                }
            }
        }
        return INSTANCE;
    }
}
