-- MSSQL: CREATE DATABASE fbDB;

CREATE TABLE Teams
(
    TeamId INT IDENTITY PRIMARY KEY,
    [Name] VARCHAR(30),
    DateFound DATE,
    Earnings DECIMAL(15, 2)
)

CREATE TABLE Matches
(
    MatchId INT IDENTITY PRIMARY KEY,
    HomeTeamId INT FOREIGN KEY REFERENCES Teams(TeamId),
    AwayTeamId INT FOREIGN KEY REFERENCES Teams(TeamId),
    HomeTeamGoals INT,
    AwayTeamGoals INT,
)

CREATE TABLE Athletes
(
    AthleteId INT IDENTITY PRIMARY KEY,
    FirstName VARCHAR(30),
    LastName VARCHAR(30),
    Nationality VARCHAR(50),
    Position VARCHAR(15),
    Price DECIMAL (15, 2),
    DateBorn DATE,
    TeamId INT FOREIGN KEY REFERENCES Teams(TeamId),
)