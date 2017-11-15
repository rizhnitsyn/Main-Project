package DAO.daoImplementation;

import DAO.FootballMatchDao;
import entities.FootballMatch;
import connection.ConnectionManager;
import entities.Forecast;
import entities.Tournament;

import java.sql.*;
import java.util.ArrayList;
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
                    " first_team_id = ?, second_team_id= ?  WHERE tournament_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, footballMatch.getFirstTeamResult());
            statement.setInt(2, footballMatch.getSecondTeamResult());
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
                Forecast forecast = new Forecast(resultSet.getLong("c.forecast_id"), resultSet.getInt("c.first_team_forecast"),
                        resultSet.getInt("c.second_team_forecast"), footballMatch);
                if (forecast.getId() != 0) {
                    footballMatch.addForecast(forecast);
                }
                tournament.addFootballMatch(footballMatch);
            }
            while (resultSet.next()) {
                Forecast forecast = new Forecast(resultSet.getLong("c.forecast_id"), resultSet.getInt("c.first_team_forecast"),
                        resultSet.getInt("c.second_team_forecast"), footballMatch);
                if (footballMatch != null && forecast.getId() != 0) {
                    footballMatch.addForecast(forecast);
                }
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
    public List<FootballMatch> getListOfMatches() {
        List<FootballMatch> footballMatches = new ArrayList<>();
        try (Connection connection = ConnectionManager.newConnection()){
            String sql = "SELECT * FROM matches b ";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                footballMatches.add(new FootballMatch(resultSet.getLong("b.match_id"),
                        resultSet.getInt("b.first_team_result"), resultSet.getInt("b.second_team_result"),
                        resultSet.getDate("b.match_datetime"), resultSet.getInt("b.match_state_id"),
                        resultSet.getInt("b.match_type_id"), resultSet.getInt("b.first_team_id"),
                        resultSet.getInt("b.second_team_id")));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return footballMatches;
    }
}
