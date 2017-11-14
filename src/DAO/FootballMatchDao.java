package DAO;

public class FootballMatchDao {
    private static FootballMatchDao INSTANCE;

    private FootballMatchDao() {}

    public static FootballMatchDao getInstance() {
        if (INSTANCE == null) {
            synchronized (FootballMatchDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FootballMatchDao();
                }
            }
        }
        return INSTANCE;
    }
}
