package DAO.daoImplementation;

import DAO.FootballMatchDao;
import entities.FootballMatch;
import connection.ConnectionManager;
import entities.Tournament;

import java.sql.*;
import java.util.List;

public class FootballMatchDaoImpl implements FootballMatchDao {
    private static FootballMatchDaoImpl INSTANCE;

    private FootballMatchDaoImpl() {}

    public static FootballMatchDaoImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (FootballMatchDaoImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FootballMatchDaoImpl();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public boolean addMatch(FootballMatch footballMatch) {
        try (Connection connection = ConnectionManager.newConnection()){
            String sql = "INSERT INTO matches (match_datetime, match_state_id, match_type_id, first_team_id, second_team_id, tournament_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, footballMatch.getMatchDateTime());
            statement.setInt(2, footballMatch.getMatchState());
            statement.setInt(3, footballMatch.getMatchType());
            statement.setInt(4,footballMatch.getFirstTeamId());
            statement.setInt(5,footballMatch.getSecondTeamId());
            statement.setLong(6,footballMatch.getTournament().getId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                footballMatch.setId(generatedKeys.getLong(1));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateMatch(FootballMatch footballMatch) {
        try (Connection connection = ConnectionManager.newConnection()){
            String sql = "UPDATE matches SET first_team_result = ?, second_team_result = ?, match_datetime = ?, match_state_id = ? , match_type_id = ?," +
                    " first_team_id = ?, second_team_id= ?, tournament_id = ?  WHERE tournament_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, footballMatch.getMatchState());
            statement.setInt(2, footballMatch.getMatchState());
            statement.setDate(3, footballMatch.getMatchDateTime());
            statement.setInt(4, footballMatch.getMatchState());
            statement.setInt(5, footballMatch.getMatchType());
            statement.setInt(6,footballMatch.getFirstTeamId());
            statement.setInt(7,footballMatch.getSecondTeamId());
            statement.setLong(8,footballMatch.getTournament().getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public FootballMatch getMatchById(Long matchId) {
        FootballMatch  footballMatch = null;
        try (Connection connection = ConnectionManager.newConnection()){
            String sql = "SELECT * FROM matches a " +
                    "LEFT JOIN tournaments b ON a.tournament_id = b.tournament_id " +
                    "LEFT JOIN forecasts c ON a.match_id = c.match_id " +
                    "WHERE a.match_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, matchId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Tournament tournament = new Tournament(resultSet.getLong("b.tournament_id"), resultSet.getString("b.tournament_name"),
                        resultSet.getInt("b.team_organizer_id"), resultSet.getDate("b.tournament_start_date"),
                        resultSet.getInt("b.tournament_state_id"));
                footballMatch = new FootballMatch(resultSet.getLong("a.match_id"), resultSet.getInt("a.first_team_result"),
                        resultSet.getInt("a.second_team_result"), resultSet.getDate("a.match_datetime"),
                        resultSet.getInt("a.match_state_id"), resultSet.getInt("a.match_type_id"),
                        resultSet.getInt("a.first_team_id"), resultSet.getInt("a.second_team_id"), tournament);
            }
            while (resultSet.next()) {
//                footballMatch = new FootballMatch(resultSet.getLong("match_id"),
//                        resultSet.getString("tournament_name"),
//                        resultSet.getLong("team_organizer_id"),
//                        resultSet.getDate("tournament_start_date"),
//                        resultSet.getLong("tournament_state_id") );
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return footballMatch;
    }

    @Override
    public List<FootballMatch> getListOfMatchs() {
        return null;
    }
}
