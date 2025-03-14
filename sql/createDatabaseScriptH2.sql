-- H2: CREATE DATABASE footballersH2DB;

CREATE TABLE Teams (
   TeamId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   TeamName VARCHAR(30),
   DateFound DATE,
   Earnings DECIMAL(15,2)
);


CREATE TABLE Matches (
     MatchId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     HomeTeamId INT,
     AwayTeamId INT,
     HomeTeamGoals INT,
     AwayTeamGoals INT,
     CONSTRAINT FK_HomeTeam FOREIGN KEY (HomeTeamId) REFERENCES Teams(TeamId),
     CONSTRAINT FK_AwayTeam FOREIGN KEY (AwayTeamId) REFERENCES Teams(TeamId)
);

CREATE TABLE Athletes (
      AthleteId INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      FirstName VARCHAR(30),
      LastName VARCHAR(30),
      Nationality VARCHAR(50),
      Position VARCHAR(15),
      Price DECIMAL(15, 2),
      DateBorn DATE,
      TeamId INT,
      CONSTRAINT FK_Team FOREIGN KEY (TeamId) REFERENCES Teams(TeamId)
);