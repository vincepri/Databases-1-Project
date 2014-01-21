CREATE TABLE StateMachine(
    SID integer NOT NULL,
    Name varchar2(30) NOT NULL,
    Description varchar2(100),
    ABSPath varchar2(1000) NOT NULL,
    Author varchar2(50),
    CONSTRAINT sm_pk PRIMARY KEY (SID),
    CONSTRAINT sm_ck1 UNIQUE (Name, ABSPath)
);

CREATE TABLE StateVertex(
    VID integer NOT NULL UNIQUE,
    SID integer NOT NULL,
    Name varchar2(30),
    CONSTRAINT ver_pk PRIMARY KEY (VID, SID),
    CONSTRAINT ver_ck UNIQUE (SID, Name),
    CONSTRAINT ver_fk1 FOREIGN KEY (SID)
        REFERENCES StateMachine (SID) ON DELETE CASCADE
);

CREATE TABLE State(
    StateID integer PRIMARY KEY,
    VID integer NOT NULL,
    Visibility varchar2(15),
    Description varchar2(100),
    isFinal integer CHECK (isFinal IN (0, 1)),
    isTop integer CHECK (isTop IN (0, 1)),
    CONSTRAINT state_vis CHECK
        (Visibility IN ('Protected', 'Private', 'Public', 'Package')),
    CONSTRAINT state_fk FOREIGN KEY (VID)
        REFERENCES StateVertex(VID) ON DELETE CASCADE
);

CREATE TABLE PseudoState(
    VID integer NOT NULL UNIQUE,
    StateType varchar2(15) NOT NULL,
    CONSTRAINT ps_fk FOREIGN KEY (VID)
        REFERENCES StateVertex(VID) ON DELETE CASCADE
);

CREATE TABLE CompositeState(
    StateID integer NOT NULL,
    isConcurrent integer CHECK (isConcurrent IN(0, 1)),
    childSID integer NOT NULL,
     CONSTRAINT cs_fk1 FOREIGN KEY (StateID)
         REFERENCES State (StateID) ON DELETE CASCADE,
     CONSTRAINT cs_fk3 FOREIGN KEY (childSID)
         REFERENCES StateMachine (SID) ON DELETE CASCADE
);

CREATE TABLE Transition(
    TransitionID integer PRIMARY KEY,
    Name varchar2(30),
    Visibility varchar2(15),
    Source integer NOT NULL,
    Target integer NOT NULL,
    CONSTRAINT tr_vis CHECK
        (Visibility IN ('Protected', 'Private', 'Public', 'Package')),
    CONSTRAINT tr_fk1 FOREIGN KEY (Source)
        REFERENCES StateVertex (VID) ON DELETE CASCADE,
    CONSTRAINT tr_fk2 FOREIGN KEY (Target)
        REFERENCES StateVertex (VID) ON DELETE CASCADE,
    CONSTRAINT tr_ck1 UNIQUE (Name, Source, Target)
);

CREATE TABLE GuardCondition(
    TransitionID integer,
    BooleanExpression varchar2(250),
    CONSTRAINT gc_fk1 FOREIGN KEY (TransitionID)
        REFERENCES Transition (TransitionID) ON DELETE CASCADE
);

CREATE TABLE Action(
    ActionID integer PRIMARY KEY,
    StateID integer,
    TransitionID integer,
    Name varchar2(30),
    Visibility varchar2(15),
    CONSTRAINT ac_vis CHECK
        (Visibility IN ('Protected', 'Private', 'Public', 'Package')),
    CONSTRAINT ac_fk1 FOREIGN KEY (StateID)
        REFERENCES State (StateID) ON DELETE CASCADE,
    CONSTRAINT ac_fk2 FOREIGN KEY (TransitionID)
        REFERENCES Transition (TransitionID) ON DELETE CASCADE
);

CREATE TABLE Event(
    EventID integer PRIMARY KEY,
    StateID integer,
    TransitionID integer,
    Name varchar2(30),
    Visibility varchar2(15),
    CONSTRAINT ev_vis CHECK
        (Visibility IN ('Protected', 'Private', 'Public', 'Package')),
    CONSTRAINT ev_fk1 FOREIGN KEY (StateID)
        REFERENCES State (StateID) ON DELETE CASCADE,
    CONSTRAINT ev_fk2 FOREIGN KEY (TransitionID)
        REFERENCES Transition (TransitionID) ON DELETE CASCADE
);

CREATE TABLE CallEvent(
    EventID integer NOT NULL,
    CallType varchar2(50),
    Return varchar2(50),
    CONSTRAINT cev_fk1 FOREIGN KEY (EventID)
        REFERENCES Event (EventID) ON DELETE CASCADE
);

CREATE TABLE TimeEvent(
    EventID integer NOT NULL,
    When varchar2(50),
    CONSTRAINT tev_fk1 FOREIGN KEY (EventID)
        REFERENCES Event (EventID) ON DELETE CASCADE
);

CREATE TABLE SignalEvent(
    EventID integer NOT NULL,
    Return varchar2(50),
    CONSTRAINT sev_fk1 FOREIGN KEY (EventID)
        REFERENCES Event (EventID) ON DELETE CASCADE
);

CREATE TABLE ChangeEvent(
    EventID integer NOT NULL,
    BooleanExpression varchar2(50),
    CONSTRAINT chev_fk1 FOREIGN KEY (EventID)
        REFERENCES Event (EventID) ON DELETE CASCADE
);

CREATE TABLE TPseudo(
    Name varchar2(50)
);

INSERT ALL
    INTO TPseudo VALUES('Initial')
    INTO TPseudo VALUES('Terminate')
    INTO TPseudo VALUES('Junction')
    INTO TPseudo VALUES('Choice')
    INTO TPseudo VALUES('Join')
    INTO TPseudo VALUES('Fork')
    INTO TPseudo VALUES('History')
    INTO TPseudo VALUES('Entry')
    INTO TPseudo VALUES('Exit')
SELECT * FROM DUAL;