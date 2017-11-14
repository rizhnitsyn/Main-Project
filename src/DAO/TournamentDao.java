package DAO;

import Entities.Tournament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TournamentDao {
    private static TournamentDao INSTANCE;

    private TournamentDao() {}

    public static TournamentDao getInstance() {
        if (INSTANCE == null) {
            synchronized (TournamentDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TournamentDao();
                }
            }
        }
        return INSTANCE;
    }

    public boolean addTournament(Tournament tournament) {
        try (Connection connection = ConnectionManager.newConnection()){
            String sql = "INSERT INTO tournaments (tournament_name, team_organizer_id, tournament_start_date, tournament_state_id) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tournament.getName());
            statement.setLong(2, tournament.getOrganizerId());
            statement.setDate(3, tournament.getStartDate());
            statement.setLong(4,tournament.getStateId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //возвращаем пользователю список турниров доступных для регистрации, не давать для регистрации те турниры где он уже зареген
    public List<Tournament> getListOfActiveTournaments() {

        return null;
    }

    //регистрирует пользователя на турнир,
    public boolean registerOnTournament() {

    }
}
