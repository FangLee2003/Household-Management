CREATE DATABASE HouseholdManagement
USE HouseholdManagement

--1. Create table
CREATE TABLE People
(
  PID BIGINT NOT NULL IDENTITY(1, 1),
  PName NTEXT NOT NULL,
  PIdentity BIGINT NOT NULL,
  CONSTRAINT PK_People_PIdentity PRIMARY KEY (PIdentity),
  HouseholderIdentity BIGINT,
  --CONSTRAINT FK_People_Household__HouseholderIdentity FOREIGN KEY (HouseholderIdentity) REFERENCES dbo.Household(HouseholderIdentity)
  PRelationshipwithHouseholder NTEXT NOT NULL,
  PGender NTEXT NOT NULL,
  PBirthday DATE NOT NULL,
  PHometown NTEXT NOT NULL,
  PJob NTEXT NOT NULL,
  PEdu NTEXT,
  PReligion NTEXT,
)
CREATE TABLE Household
(
  HouseholdID BIGINT NOT NULL IDENTITY(1, 1),
  HouseholderName NTEXT NOT NULL,
  HouseholderIdentity BIGINT NOT NULL,
  CONSTRAINT PK_Household_HouseholderIdentity PRIMARY KEY (HouseholderIdentity),
  --CONSTRAINT FK_Household_People_HouseholderIdentity FOREIGN KEY (HouseholderIdentity) REFERENCES dbo.view.People(PIdentity),
  HouseholdAddress NTEXT
)
CREATE TABLE TempResidence_Absence
(
  TID BIGINT NOT NULL IDENTITY(1, 1),
  TName NTEXT NOT NULL,
  TIdentity BIGINT NOT NULL,
  CONSTRAINT PK_TempResidence_TIdentity PRIMARY KEY (TIdentity),
  --CONSTRAINT FK_TempResidence_People_TIdentity FOREIGN KEY (TIdentity) REFERENCES dbo.view.People(PIdentity),
  TDate DATE NOT NULL,
  TempResidenceLocation NTEXT NOT NULL,
  AbsenceLocation NTEXT NOT NULL,
  TReason NTEXT
)
CREATE TABLE Manager
(
  MID BIGINT NOT NULL IDENTITY(1, 1),
  MName NTEXT NOT NULL,
  MIdentity BIGINT NOT NULL,
  CONSTRAINT PK_Manager_MIdentity PRIMARY KEY (MIdentity),
  --CONSTRAINT FK_Manager_People_MIdentity FOREIGN KEY (MIdentity) REFERENCES dbo.view.People (PIdentity),
  MPosition NTEXT,
  MAccount NTEXT NOT NULL,
  MPass NTEXT NULL
)
--2. Insert data
INSERT INTO dbo.People
VALUES
  (
    'Admin',
    123456789,
	123456789,
	'Myself',
    'Male',
    '0001-01-01',
    'Singapore',
    'Admin',
    '0/0',
    'None'
		),
(
    'Fang Lee',
    2121212121,
	1212121212,
	'Son',
    'Male',
    '2000-05-12',
    'Da Nang',
    'Coder',
    '12/12',
    'None'
  ),
(
  'Kiki',
  1212121212,
  1212121212,
  'Friend',
  'Female',
  '1997-05-12',
  'Kon Tum',
  'Dog',
  '0/12',
  'Buddha'
  )

INSERT INTO dbo.Household
VALUES
('Kiki',
1212121212,
'Ha Noi')

INSERT INTO dbo.TempResidence_Absence
VALUES
('Fang Lee',
21212121,
'2022-01-01',
'Da Nang',
'Hue',
'Travel'
)
INSERT INTO dbo.Manager
VALUES
  (
    'Admin',
    123456789,
    'Admin',
    'admin',
    'admin'
)

