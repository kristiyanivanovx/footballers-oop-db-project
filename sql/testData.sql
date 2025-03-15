
-- Teams inserts
INSERT INTO Teams(Name, DateFound, Earnings) VALUES ('Porto', '2000-10-10', 9999999)
INSERT INTO Teams(Name, DateFound, Earnings) VALUES ('Levski', '1914-02-02', 9000100)
INSERT INTO Teams(Name, DateFound, Earnings) VALUES ('CSKA Sofia', '1948-02-02', 9000100)
INSERT INTO Teams(Name, DateFound, Earnings) VALUES ('Slavia', '1913-04-10', 8000100)
INSERT INTO Teams(Name, DateFound, Earnings) VALUES ('Lokomotiv PD', '1926-07-25', 10000100)

-- Athletes inserts
INSERT INTO Athletes (FirstName, LastName, Nationality, Position, Price, DateBorn, TeamId) VALUES
('Ivan', 'Ivanov', 'BG', 'Goalkeeper', 919191, '1994-01-01', 1);
INSERT INTO Athletes (FirstName, LastName, Nationality, Position, Price, DateBorn, TeamId) VALUES
('Fedor', 'Kovalenko', 'UKR', 'Forward', 929292, '1995-01-01', 2);
INSERT INTO Athletes (FirstName, LastName, Nationality, Position, Price, DateBorn, TeamId) VALUES
('Otto', 'Toepfer', 'GER', 'Midfielder', 939393, '1996-01-01', 3);
INSERT INTO Athletes (FirstName, LastName, Nationality, Position, Price, DateBorn, TeamId) VALUES
('Aleksei', 'Popov', 'RUS', 'RightWing', 949494, '1997-01-01', 4);
INSERT INTO Athletes (FirstName, LastName, Nationality, Position, Price, DateBorn, TeamId) VALUES
('John', 'Smith', 'USA', 'LeftWing', 909095, '1998-01-01', 5);

INSERT INTO Athletes (FirstName, LastName, Nationality, Position, Price, DateBorn, TeamId) VALUES
('Petar', 'Petrov', 'BG', 'Defender', 1111, '2000-01-01', 1);

SELECT * FROM [fbDB].[dbo].[Athletes];
SELECT * FROM [fbDB].[dbo].[Matches];
SELECT * FROM [fbDB].[dbo].[Teams];
