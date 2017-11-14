package DAO;

import Entities.Tournament;
import Connection.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public Tournament getTournamentById(Long tournamentId) {
        Tournament tournament = null;
        try (Connection connection = ConnectionManager.newConnection()){
            String sql = "SELECT * FROM tournaments  WHERE tournament_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, tournamentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tournament = new Tournament(resultSet.getLong("tournament_id"),
                        resultSet.getString("tournament_name"),
                        resultSet.getLong("team_organizer_id"),
                        resultSet.getDate("tournament_start_date"),
                        resultSet.getLong("tournament_state_id") );
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return tournament;
    }

    public boolean updateTournament(Tournament tournament) {
        try (Connection connection = ConnectionManager.newConnection()){
            String sql = "UPDATE tournaments SET tournament_name =?, team_organizer_id =? , tournament_start_date =?, tournament_state_id =? WHERE tournament_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tournament.getName());
            statement.setLong(2, tournament.getOrganizerId());
            statement.setDate(3, tournament.getStartDate());
            statement.setLong(4,tournament.getStateId());
            statement.setLong(5,tournament.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Tournament> getListOfTournaments() {
        List<Tournament> tournaments = new ArrayList<>();
        try (Connection connection = ConnectionManager.newConnection()){
            String sql = "SELECT * FROM tournaments";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tournaments.add(new Tournament(resultSet.getLong("tournament_id"),
                        resultSet.getString("tournament_name"),
                        resultSet.getLong("team_organizer_id"),
                        resultSet.getDate("tournament_start_date"),
                        resultSet.getLong("tournament_state_id")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return tournaments;
    }

    //регистрирует пользователя на турнир,
    public boolean registerOnTournament() {
        return true;
    }
}
